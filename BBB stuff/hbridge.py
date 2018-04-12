import socket
import select
import sys
import Adafruit_BBIO.PWM as PWM

myPWM3="P8_13"
myPWM2="P9_42"
myPWM1="P9_16"

PWM.start(myPWM1, 48, 10000, 0)
PWM.start(myPWM2, 41, 10000, 0)
PWM.start(myPWM3, 50, 10000, 0)

#PWM.start(myPWM1, 0, 1000)
#PWM.start(myPWM2, 0, 1000)
#PWM.start(myPWM3, 0, 1000)

serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#socket = raw_input('Enter socket number')
serversocket.bind(('192.168.7.2', int(sys.argv[1])))
serversocket.listen(5) # become a server socket, maximum 5 connections

print 'tcp server started'
connection, address = serversocket.accept()


# while True:
#     try:
#         ready_to_read, ready_to_write, in_error = \
#             select.select([connection,], [connection,], [], 5)
#     except select.error:
#         connection.shutdown(2)    # 0 = done receiving, 1 = done sending, 2 = both
#         connection.close()
#         # connection error event here, maybe reconnect
#         print 'connection error'
#         break
#     if len(ready_to_read) > 0:
#         buf = connection.recv(2048)
#         # do stuff with received data
#         print buf

    # else:
    #     print 'connection error'
    #     connection.close()
    #     break
    # if len(ready_to_write) > 0:
    #     # connection established, send some stuff
    #     connection.send('some stuff')



while True:
    try:
        buf = connection.recv(1000)
    #    if len(buf) > 0:
   #         if buf == "quit":
  #              socket.close()
 #               break
#            else:
        buf = buf.split(' ')
	print buf[0]
	print buf[1]
	print buf[2]
        PWM.set_duty_cycle(myPWM1, int(buf[0]))
        PWM.set_duty_cycle(myPWM2, int(buf[1]))
        PWM.set_duty_cycle(myPWM3, int(buf[2]))


    except:
        print "disconnected"
	PWM.set_duty_cycle(myPWM1, 48)
        PWM.set_duty_cycle(myPWM2, 41)
        PWM.set_duty_cycle(myPWM3, 50)
 #       PWM.stop(myPWM1)
# 	PWM.stop(myPWM2)
#        PWM.stop(myPWM3)
#        PWM.cleanup()
#	socket.close()
	break


