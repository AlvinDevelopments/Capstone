import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JTextField sPortValue;
	private JTextField hPortValue;
	private JTextField IP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 398, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 145, -1, 0, 0};
		gbl_contentPane.rowHeights = new int[]{8, -44, -4, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIpAddress = new JLabel("IP Address");
		GridBagConstraints gbc_lblIpAddress = new GridBagConstraints();
		gbc_lblIpAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpAddress.gridx = 1;
		gbc_lblIpAddress.gridy = 0;
		contentPane.add(lblIpAddress, gbc_lblIpAddress);
		
		IP = new JTextField();
		GridBagConstraints gbc_IP = new GridBagConstraints();
		gbc_IP.fill = GridBagConstraints.BOTH;
		gbc_IP.insets = new Insets(0, 0, 5, 5);
		gbc_IP.gridx = 2;
		gbc_IP.gridy = 0;
		contentPane.add(IP, gbc_IP);
		IP.setText(Launcher.address);
		IP.setColumns(10);
		
		JLabel lblServoPort = new JLabel("Servo Port");
		GridBagConstraints gbc_lblServoPort = new GridBagConstraints();
		gbc_lblServoPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblServoPort.gridx = 1;
		gbc_lblServoPort.gridy = 1;
		contentPane.add(lblServoPort, gbc_lblServoPort);
		
		sPortValue = new JTextField();
		GridBagConstraints gbc_sPortValue = new GridBagConstraints();
		gbc_sPortValue.fill = GridBagConstraints.BOTH;
		gbc_sPortValue.insets = new Insets(0, 0, 5, 5);
		gbc_sPortValue.gridx = 2;
		gbc_sPortValue.gridy = 1;
		contentPane.add(sPortValue, gbc_sPortValue);
		sPortValue.setText(Launcher.sPort);
		sPortValue.setColumns(10);
		
		JLabel lblHbridgePort = new JLabel("HBridge Port");
		GridBagConstraints gbc_lblHbridgePort = new GridBagConstraints();
		gbc_lblHbridgePort.insets = new Insets(0, 0, 5, 5);
		gbc_lblHbridgePort.gridx = 1;
		gbc_lblHbridgePort.gridy = 2;
		contentPane.add(lblHbridgePort, gbc_lblHbridgePort);
		
		hPortValue = new JTextField();
		GridBagConstraints gbc_hPortValue = new GridBagConstraints();
		gbc_hPortValue.fill = GridBagConstraints.BOTH;
		gbc_hPortValue.insets = new Insets(0, 0, 5, 5);
		gbc_hPortValue.gridx = 2;
		gbc_hPortValue.gridy = 2;
		contentPane.add(hPortValue, gbc_hPortValue);
		hPortValue.setText(Launcher.hPort);
		hPortValue.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 3;
		contentPane.add(btnSave, gbc_btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.setPorts(IP.getText(), hPortValue.getText(), sPortValue.getText());
				GUI.txtIP.setText(IP.getText());
				GUI.txtHBridgePort.setText(hPortValue.getText());
				GUI.txtServoPort.setText(sPortValue.getText());
				close();
			}
		});
	}
	
	public void close() {
		this.dispose();
	}

}


