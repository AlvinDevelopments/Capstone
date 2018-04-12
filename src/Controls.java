import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controls extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793650930257551005L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controls frame = new Controls();
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
	public Controls() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Forward");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Launcher.controller.moveForward(100);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Launcher.controller.stopThrusters();
			}
		});
		btnNewButton.setBounds(164, 21, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnBackward = new JButton("Backward");
		btnBackward.setBounds(164, 64, 117, 29);
		contentPane.add(btnBackward);
		
		JButton btnBackward_1 = new JButton("Left");
		btnBackward_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBackward_1.setBounds(53, 45, 117, 29);
		contentPane.add(btnBackward_1);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(271, 45, 117, 29);
		contentPane.add(btnRight);
		
		JButton btnRollForward = new JButton("Tilt Forward");
		btnRollForward.setBounds(164, 134, 117, 29);
		contentPane.add(btnRollForward);
		
		JButton btnRollBackward = new JButton("Tilt Backward");
		btnRollBackward.setBounds(164, 228, 117, 29);
		contentPane.add(btnRollBackward);
		
		JButton btnRollLeft = new JButton("Roll Left");
		btnRollLeft.setBounds(6, 175, 117, 29);
		contentPane.add(btnRollLeft);
		
		JButton btnRollRight = new JButton("Roll Right");
		btnRollRight.setBounds(333, 175, 117, 29);
		contentPane.add(btnRollRight);
		
		JButton btnYawLeft = new JButton("Yaw Left");
		btnYawLeft.setBounds(115, 175, 117, 29);
		contentPane.add(btnYawLeft);
		
		JButton btnYawRight = new JButton("Yaw Right");
		btnYawRight.setBounds(224, 175, 117, 29);
		contentPane.add(btnYawRight);
	}
}
