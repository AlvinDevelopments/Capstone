import serial, fcntl, struct, time, logging, array, sys, socket, select, sys


####################################################################################
# Serial primitives #
####################################################################################

def makePacket( id, instruction, params ):
        p = [
                0xff,
                0xff,
                id & 0xff,
                len(params)+2,
                instruction & 0xff
        ]
        for param in params:
                p.append(param & 0xff)
        p.append(checksumPacket(p))
        return p

def checksumPacket( p ):
        sum = 0
        for byte in p[2:]:
                sum = 0xff & (sum + byte)
        notSum = 0xff & (~sum)
        return notSum

def checkPacket( id, p ):
        # check bytes for errors or unexpected conditions (http://support.robotis.com/en/product/dynamixel/communication/dxl_packet.htm)
        if p[2] != id:
		print 'Bad packet read (Unexpected id)'
                return -1

	if p[3] + 4 != len(p):
		print 'Bad packet read (Incorrect length)'
		return -1

	if p[4] != 0x00:
                print 'Bad packet read (Error bits set: ', p[4], ' [decimal representation])'
                return -1

        if p[-1] != checksumPacket(p[:-1]):
                print 'Bad packet read (bad checksum)'
                return -1

        return 0

def p2str( p ):
        return array.array('B', p).tostring();

def str2p( s ):
        return [ord(char) for char in list(s)]

def sendPacket( ser, p ):
	if PRINT_PACKETS:
		print 'sent:     ', p 
	i = ser.write(p2str(p))
	if i == 0:
		print 'No bytes written in sendPacket'
	return

def receivePacket( ser, id ):
	return # failing to receive packets after shorted with battery wire
	strHead = ser.read(4) # read packet up to length byte
	pHead = str2p(strHead)
	strTail = ser.read(pHead[3]) # read remaining bytes
	p = str2p(strHead + strTail)
	if checkPacket(id, p) != 0:
		return None
	if PRINT_PACKETS:
		print 'received: ', p 
	return p



#####################################################################################
# Dynamixel Instructions                                                            #
# http://support.robotis.com/en/product/dynamixel/communication/dxl_instruction.htm #
#####################################################################################

def instructionPing( ser, id ):
	p = makePacket(id, 0x01, [])
	sendPacket(ser, p)
	p = receivePacket(ser, id)
	return

def instructionWriteData( ser, id, params ):
	p = makePacket(id, 0x03, params) 
        sendPacket(ser, p)
        p = receivePacket(ser, id)
	return

def instructionRegWrite( ser, id, params ):
	p = makePacket(id, 0x04, params)
        sendPacket(ser, p)
        p = receivePacket(ser, id)
        return

def instructionAction( ser, id ):
	p = makePacket(id, 0x05, [])
        sendPacket(ser, p)
        p = receivePacket(ser, id)
        return

####################################################################################
# Dynamixel Commands                                                               #
#   Addresses can be found here:                                                   # 
#   http://support.robotis.com/en/product/dynamixel/rx_series/rx-24f.htm           #
####################################################################################

# on = 1 turns LED on, on = 0 turns LED off
def commandSetLED( ser, id, on ):
	instructionWriteData(ser, id, [0x19, on])
	return

# set the goal position 
# goal between 0 (maximum clockwise), and 1023 (maximum counterclockwise)
# only applies in joint mode
def commandSetGoal( ser, id, goal ):
	if goal < 0:
		goal = 0
	elif goal > 1023:
		goal = 1023
	
	# split goal into lower and upper byte
	loGoal = goal & 0xff
	hiGoal = (goal >> 8) & 0xff	
	instructionWriteData(ser, id, [0x1e, loGoal, hiGoal])
	return
	
# set moving speed
# speed between 1 (slowest) and 1023 (fastest, except 0)
# 0 means maximum rpm without controlling speed
def commandSetSpeed( ser, id, speed ):
        if speed < 0:
                speed = 0
        elif speed > 1023:
                speed = 1023

        # split speed into lower and upper byte
        loSpeed = speed & 0xff
        hiSpeed = (speed >> 8) & 0xff
        instructionWriteData(ser, id, [0x20, loSpeed, hiSpeed])
        return

####################################################################################
# Main Program                                                                     #
####################################################################################

PRINT_PACKETS = 1 # 1 print packets sent and recieved, 0 disable printing packets
ID1 = 0x01
ID2 = 0x02 # dynamixel id of rx-24f servo (can be found from wizard, or read data instruction)
ID3 = 0x03

ser = serial.Serial( 
    port='/dev/ttyO4',  
    baudrate=57600,  # baud rate can be set in the wizard 
    timeout=1, 
    parity=serial.PARITY_NONE, 
    stopbits=serial.STOPBITS_ONE, 
    bytesize=serial.EIGHTBITS 
) 
  
# Standard Linux RS485 ioctl: 
TIOCSRS485 = 0x542F 
  
# define serial_rs485 struct per Michael Musset's patch that adds gpio RE/DE  
# control: 
# (https://github.com/RobertCNelson/bb-kernel/blob/am33x-v3.8/patches/fixes/0007-omap-RS485-support-by-Michael-Musset.patch#L30) 
SER_RS485_ENABLED         = (1 << 0) 
SER_RS485_RTS_ON_SEND     = (1 << 1) 
SER_RS485_RTS_AFTER_SEND  = (1 << 2) 
SER_RS485_RTS_BEFORE_SEND = (1 << 3) 
SER_RS485_USE_GPIO        = (1 << 5) 
  
# Enable RS485 mode using a GPIO pin to control RE/DE:  
RS485_FLAGS = SER_RS485_ENABLED | SER_RS485_USE_GPIO  
# With this configuration the GPIO pin will be high when transmitting and low 
# when not 
  
# If SER_RS485_RTS_ON_SEND and SER_RS485_RTS_AFTER_SEND flags are included the 
# RE/DE signal will be inverted, i.e. low while transmitting 
  
# The GPIO pin to use, using the Kernel numbering:  
RS485_RTS_GPIO_PIN = 48 # GPIO1_16 -> GPIO(1)_(16) = (1)*32+(16) = 48 
  
# Pack the config into 8 consecutive unsigned 32-bit values: 
# (per  struct serial_rs485 in patched serial.h) 
serial_rs485 = struct.pack('IIIIIIII',  
                           RS485_FLAGS,        # config flags 
                           0,                  # delay in us before send 
                           0,                  # delay in us after send 
                           RS485_RTS_GPIO_PIN, # the pin number used for DE/RE 
                           0, 0, 0, 0          # padding - space for more values  
                           ) 
  
# Apply the ioctl to the open ttyO4 file descriptor: 
fd=ser.fileno() 
fcntl.ioctl(fd, TIOCSRS485, serial_rs485) 


# GPIO1_16 should be low here 
time.sleep(0.2) 
# GPIO1_16 should be high while this is being transmitted: 


# move to full clockwise and counterclockwise and back to the center
#commandSetSpeed(ser, ID, 100)
#time.sleep(0.25)
#commandSetGoal(ser, ID, 0)
#time.sleep(2.0)
#commandSetSpeed(ser, ID, 0)
#commandSetGoal(ser, ID, 1023)
#time.sleep(0.25)
#commandSetGoal(ser, ID, 512)
#time.sleep(0.25)

commandSetSpeed(ser, ID1, 300)
commandSetSpeed(ser, ID2, 300)
commandSetSpeed(ser, ID3, 300)

# enter goal positions 

serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#socket = raw_input('Enter socket number')
serversocket.bind(('192.168.7.2', int(sys.argv[1])))
serversocket.listen(5) # become a server socket, maximum 5 connections

print 'tcp server started'
connection, address = serversocket.accept()

while True:
	
	try:
		buf = connection.recv(2048)
		goal = buf.split(' ')
		print goal[0]
		print goal[1]
		print goal[2]
		time.sleep(0.02)
		commandSetGoal(ser, ID1, int(goal[0]))
		time.sleep(0.02)
                commandSetGoal(ser, ID2, int(goal[1]))
		time.sleep(0.02)
		commandSetGoal(ser, ID3, int(goal[2]))

	except:
		print 'Exiting'
		break

# GPIO1_16 should be low again after transmitting 
time.sleep(0.2) 
ser.close() 

