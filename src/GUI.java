import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import javax.swing.JTextPane;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.beans.PropertyChangeEvent;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179297588688460469L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public static JTextField txtFieldZ, txtFieldSpeed, txtFieldNose, txtFieldRoll, txtEulerRoll, txtEulerPitch,
			txtEulerYaw, txtAccX, txtAccY, txtAccZ;

	static JSlider servo_1;
	static JSlider servo_2;
	static JSlider servo_3;
	static JSlider thruster_1;
	static JSlider thruster_2;
	static JSlider thruster_3;

	public static Controls control;
	public static Settings setting;
	public static JTextField txtIP;
	public static JTextField txtHBridgePort;
	public static JTextField txtServoPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws CannotRealizeException
	 * @throws NoPlayerException
	 */
	public GUI() throws NoPlayerException, CannotRealizeException, IOException {
		
		
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1460, 750);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 10, 0, 0));
		setJMenuBar(menuBar);

		JLabel lblMenu = new JLabel("HMAUV Navigation");
		menuBar.add(lblMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 387, 418, 424, 6 };
		gbl_contentPane.rowHeights = new int[] { 425, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel io = new JPanel();
		io.setBackground(SystemColor.window);
		GridBagConstraints gbc_io = new GridBagConstraints();
		gbc_io.fill = GridBagConstraints.BOTH;
		gbc_io.insets = new Insets(0, 0, 0, 5);
		gbc_io.gridx = 0;
		gbc_io.gridy = 0;
		contentPane.add(io, gbc_io);
		GridBagLayout gbl_io = new GridBagLayout();
		gbl_io.columnWidths = new int[] { 333, 333 };
		gbl_io.rowHeights = new int[] { 263, 290, 0 };
		gbl_io.columnWeights = new double[] { 1.0, 1.0 };
		gbl_io.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		io.setLayout(gbl_io);

		CaptureDeviceInfo cam = null;
		cam = Camera.run();

		MediaLocator ml = null;
		Player player;
		Component cameras = null;

		if (cam != null) {
			ml = new MediaLocator(cam.getLocator().toString());

			try {

				player = Manager.createRealizedPlayer(ml);

				player.start();
				// create video screen to display webcam preview
				cameras = player.getVisualComponent();
				cameras.setSize(io.getWidth() / 4, io.getHeight());
			} finally {
			}

//			 cameras.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			GridBagConstraints gbc_cameras = new GridBagConstraints();
			gbc_cameras.fill = GridBagConstraints.BOTH;
			gbc_cameras.insets = new Insets(0, 0, 5, 0);
			gbc_cameras.gridx = 0;
			gbc_cameras.gridy = 0;
			io.add(cameras, gbc_cameras);
			GridBagLayout gbl_cameras = new GridBagLayout();
			gbl_cameras.columnWidths = new int[] { 413, 0 };
			gbl_cameras.rowHeights = new int[] { 258, 0 };
			gbl_cameras.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_cameras.rowWeights = new double[] { 0.0, Double.MIN_VALUE };

			JPanel cam1Panel = new JPanel();
			GridBagConstraints gbc_cam1Panel = new GridBagConstraints();
			gbc_cam1Panel.gridwidth = 2;
			gbc_cam1Panel.ipady = 10;
			gbc_cam1Panel.ipadx = 10;
			gbc_cam1Panel.insets = new Insets(0, 0, 5, 5);
			gbc_cam1Panel.fill = GridBagConstraints.BOTH;
			gbc_cam1Panel.gridx = 0;
			gbc_cam1Panel.gridy = 0;
			cam1Panel.add(cameras);
			io.add(cam1Panel, gbc_cam1Panel);
//			 cameras.setLayout(gbl_cameras);
		}

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.window);
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.gridwidth = 2;
		gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		io.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] { 200, 30, 200 };
		gbl_panel_7.rowHeights = new int[] { 29, 222, 0 };
		gbl_panel_7.columnWeights = new double[] { 1.0, 0.0, 1.0 };
		gbl_panel_7.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_7.setLayout(gbl_panel_7);

		JLabel lblThrusters = new JLabel("Thruster Speed (Duty Cycle)");
		GridBagConstraints gbc_lblThrusters = new GridBagConstraints();
		gbc_lblThrusters.fill = GridBagConstraints.VERTICAL;
		gbc_lblThrusters.insets = new Insets(0, 0, 5, 5);
		gbc_lblThrusters.gridx = 0;
		gbc_lblThrusters.gridy = 0;
		panel_7.add(lblThrusters, gbc_lblThrusters);

		JLabel lblServos = new JLabel("Servos (Angle)");
		GridBagConstraints gbc_lblServos = new GridBagConstraints();
		gbc_lblServos.insets = new Insets(0, 0, 5, 0);
		gbc_lblServos.gridx = 2;
		gbc_lblServos.gridy = 0;
		panel_7.add(lblServos, gbc_lblServos);

		JPanel thrusters = new JPanel();
		thrusters.setBackground(SystemColor.window);
		GridBagConstraints gbc_thrusters = new GridBagConstraints();
		gbc_thrusters.fill = GridBagConstraints.BOTH;
		gbc_thrusters.insets = new Insets(0, 0, 0, 5);
		gbc_thrusters.gridx = 0;
		gbc_thrusters.gridy = 1;
		panel_7.add(thrusters, gbc_thrusters);
		GridBagLayout gbl_thrusters = new GridBagLayout();
		gbl_thrusters.columnWidths = new int[] { 70, 70, 70 };
		gbl_thrusters.rowHeights = new int[] { 0, 190, 0 };
		gbl_thrusters.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_thrusters.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		thrusters.setLayout(gbl_thrusters);

		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		thrusters.add(textField, gbc_textField);
		textField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				thruster_1.setValue(Integer.valueOf(textField.getText()));
			}
		});

		textField.setText("50");
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 0;
		thrusters.add(textField_2, gbc_textField_2);
		textField_2.setText("41");
		textField_2.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 0;
		thrusters.add(textField_4, gbc_textField_4);
		textField_4.setText("48");
		textField_4.setColumns(10);

		thruster_1 = new JSlider();
		GridBagConstraints gbc_thruster_1 = new GridBagConstraints();
		gbc_thruster_1.fill = GridBagConstraints.BOTH;
		gbc_thruster_1.insets = new Insets(0, 0, 0, 5);
		gbc_thruster_1.gridx = 0;
		gbc_thruster_1.gridy = 1;
		thrusters.add(thruster_1, gbc_thruster_1);
		thruster_1.setValue(50);
		thruster_1.setMinimum(0);
		thruster_1.setMaximum(100);

		thruster_1.setPaintLabels(true);
		// thruster_1.setMinorTickSpacing(5);
		thruster_1.setMajorTickSpacing(25);
		thruster_1.setPaintTicks(true);
		// thruster_1.setSnapToTicks(true);
		thruster_1.setLabelTable(thruster_1.createStandardLabels(50));

		thruster_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField.setText(Integer.toString(thruster_1.getValue()));
				Launcher.thrust.setFlagOn(thruster_1.getValue(), thruster_2.getValue(), thruster_3.getValue());
			}

		});

		thruster_1.setOrientation(SwingConstants.VERTICAL);

		thruster_2 = new JSlider();
		GridBagConstraints gbc_thruster_2 = new GridBagConstraints();
		gbc_thruster_2.fill = GridBagConstraints.BOTH;
		gbc_thruster_2.insets = new Insets(0, 0, 0, 5);
		gbc_thruster_2.gridx = 1;
		gbc_thruster_2.gridy = 1;
		thrusters.add(thruster_2, gbc_thruster_2);
		thruster_2.setValue(41);
		thruster_2.setMinimum(0);
		thruster_2.setMaximum(100);

		thruster_2.setPaintLabels(true);
		thruster_2.setPaintTicks(true);
		thruster_2.setMajorTickSpacing(25);

		thruster_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_2.setText(Integer.toString(thruster_2.getValue()));
				Launcher.thrust.setFlagOn(thruster_1.getValue(), thruster_2.getValue(), thruster_3.getValue());
			}
		});
		thruster_2.setOrientation(SwingConstants.VERTICAL);
		thruster_2.setLabelTable(thruster_2.createStandardLabels(50));

		thruster_3 = new JSlider();
		GridBagConstraints gbc_thruster_3 = new GridBagConstraints();
		gbc_thruster_3.fill = GridBagConstraints.BOTH;
		gbc_thruster_3.gridx = 2;
		gbc_thruster_3.gridy = 1;
		thrusters.add(thruster_3, gbc_thruster_3);
		thruster_3.setValue(48);
		thruster_3.setMinimum(0);
		thruster_3.setMaximum(100);

		thruster_3.setPaintLabels(true);
		thruster_3.setPaintTicks(true);
		thruster_3.setMajorTickSpacing(25);

		thruster_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("thruster 3 changed");
				textField_4.setText(Integer.toString(thruster_3.getValue()));
				Launcher.thrust.setFlagOn(thruster_1.getValue(), thruster_2.getValue(), thruster_3.getValue());
			}
		});
		thruster_3.setOrientation(SwingConstants.VERTICAL);

		JPanel servos = new JPanel();
		servos.setBackground(SystemColor.window);
		GridBagConstraints gbc_servos = new GridBagConstraints();
		gbc_servos.fill = GridBagConstraints.HORIZONTAL;
		gbc_servos.gridx = 2;
		gbc_servos.gridy = 1;
		panel_7.add(servos, gbc_servos);
		GridBagLayout gbl_servos = new GridBagLayout();
		gbl_servos.columnWidths = new int[] { 70, 70, 70 };
		gbl_servos.rowHeights = new int[] { 0, 222, 0 };
		gbl_servos.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_servos.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		servos.setLayout(gbl_servos);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 0;
		servos.add(textField_1, gbc_textField_1);
		textField_1.setText("0");
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 0;
		servos.add(textField_3, gbc_textField_3);
		textField_3.setText("0");
		textField_3.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 0;
		servos.add(textField_5, gbc_textField_5);
		textField_5.setText("0");
		textField_5.setColumns(10);

		servo_1 = new JSlider();
		GridBagConstraints gbc_servo_1 = new GridBagConstraints();
		gbc_servo_1.fill = GridBagConstraints.BOTH;
		gbc_servo_1.insets = new Insets(0, 0, 0, 5);
		gbc_servo_1.gridx = 0;
		gbc_servo_1.gridy = 1;
		servos.add(servo_1, gbc_servo_1);
		servo_1.setValue(0);
		servo_1.setMinimum(0);
		servo_1.setMaximum(1023);

		servo_1.setPaintLabels(true);
		servo_1.setPaintTicks(true);
		servo_1.setMajorTickSpacing(255);

		servo_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_1.setText(Integer.toString(servo_1.getValue()));
				Launcher.servo.setFlagOn(servo_1.getValue(), servo_3.getValue(), servo_2.getValue());
			}
		});
		servo_1.setOrientation(SwingConstants.VERTICAL);

		servo_2 = new JSlider();
		GridBagConstraints gbc_servo_2 = new GridBagConstraints();
		gbc_servo_2.fill = GridBagConstraints.BOTH;
		gbc_servo_2.insets = new Insets(0, 0, 0, 5);
		gbc_servo_2.gridx = 1;
		gbc_servo_2.gridy = 1;
		servos.add(servo_2, gbc_servo_2);
		servo_2.setValue(0);
		servo_2.setMinimum(0);
		servo_2.setMaximum(1023);

		servo_2.setPaintLabels(true);
		servo_2.setPaintTicks(true);
		servo_2.setMajorTickSpacing(255);

		servo_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_3.setText(Integer.toString(servo_2.getValue()));
				Launcher.servo.setFlagOn(servo_1.getValue(), servo_3.getValue(), servo_2.getValue());
			}
		});
		servo_2.setOrientation(SwingConstants.VERTICAL);

		servo_3 = new JSlider();
		GridBagConstraints gbc_servo_3 = new GridBagConstraints();
		gbc_servo_3.fill = GridBagConstraints.BOTH;
		gbc_servo_3.gridx = 2;
		gbc_servo_3.gridy = 1;
		servos.add(servo_3, gbc_servo_3);
		servo_3.setValue(0);
		servo_3.setMinimum(0);
		servo_3.setMaximum(1023);

		servo_3.setPaintLabels(true);
		servo_3.setPaintTicks(true);
		servo_3.setMajorTickSpacing(255);

		servo_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_5.setText(Integer.toString(servo_3.getValue()));
				Launcher.servo.setFlagOn(servo_1.getValue(), servo_3.getValue(), servo_2.getValue());
			}
		});
		servo_3.setOrientation(SwingConstants.VERTICAL);

		JPanel data = new JPanel();
		data.setBackground(SystemColor.window);
		data.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_data = new GridBagConstraints();
		gbc_data.fill = GridBagConstraints.BOTH;
		gbc_data.insets = new Insets(0, 0, 0, 5);
		gbc_data.gridx = 1;
		gbc_data.gridy = 0;
		contentPane.add(data, gbc_data);
		GridBagLayout gbl_data = new GridBagLayout();
		gbl_data.columnWidths = new int[] { 30, 70, 70, 30 };
		gbl_data.rowHeights = new int[] { 361, 0, 28, 29, 28, 0, 0, 81, 0 };
		gbl_data.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gbl_data.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0 };
		data.setLayout(gbl_data);

		JTextArea scriptPane = new JTextArea();
		scriptPane.setBackground(SystemColor.menu);
		scriptPane.setRows(200);
		scriptPane.setColumns(200);
		scriptPane.setWrapStyleWord(true);

		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(UIManager.getColor("Button.background"));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scriptPane.setText("");
			}
		});

		JScrollPane scrollPane = new JScrollPane(scriptPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		data.add(scrollPane, gbc_scrollPane);

		scrollPane.setRowHeaderView(scriptPane);
		scriptPane.setFont(new Font("Courier", Font.PLAIN, 13));
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 1;
		gbc_btnClear.gridy = 2;
		data.add(btnClear, gbc_btnClear);

		JButton btnSettings = new JButton("Connection Settings");
		btnSettings.setBackground(UIManager.getColor("Button.background"));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.setting.setVisible(true);
			}
		});

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(UIManager.getColor("Button.background"));
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.fill = GridBagConstraints.BOTH;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 2;
		data.add(btnSubmit, gbc_btnSubmit);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.controller.sendCommand(scriptPane.getText());
			}
		});

		JButton btnReset = new JButton("Reset Controls");
		btnReset.setBackground(UIManager.getColor("Button.background"));
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.fill = GridBagConstraints.BOTH;
		gbc_btnReset.gridwidth = 2;
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 3;
		data.add(btnReset, gbc_btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thruster_1.setValue(50);
				servo_1.setValue(0);
				thruster_2.setValue(41);
				servo_2.setValue(0);
				thruster_3.setValue(48);
				servo_3.setValue(0);
			}
		});
		GridBagConstraints gbc_btnSettings = new GridBagConstraints();
		gbc_btnSettings.gridwidth = 2;
		gbc_btnSettings.fill = GridBagConstraints.BOTH;
		gbc_btnSettings.insets = new Insets(0, 0, 5, 5);
		gbc_btnSettings.gridx = 1;
		gbc_btnSettings.gridy = 4;
		data.add(btnSettings, gbc_btnSettings);

		JButton btnControls = new JButton("Controls");
		btnControls.setBackground(UIManager.getColor("Button.background"));
		btnControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.control.setVisible(true);
			}
		});
		btnControls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Launcher.control.setVisible(true);
				control.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnControls = new GridBagConstraints();
		gbc_btnControls.gridwidth = 2;
		gbc_btnControls.fill = GridBagConstraints.BOTH;
		gbc_btnControls.insets = new Insets(0, 0, 5, 5);
		gbc_btnControls.gridx = 1;
		gbc_btnControls.gridy = 5;
		data.add(btnControls, gbc_btnControls);

		JButton btnConnection = new JButton("CONNECT");
		btnConnection.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Launcher.launch();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnConnection = new GridBagConstraints();
		gbc_btnConnection.gridwidth = 2;
		gbc_btnConnection.fill = GridBagConstraints.BOTH;
		gbc_btnConnection.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnection.gridx = 1;
		gbc_btnConnection.gridy = 7;
		data.add(btnConnection, gbc_btnConnection);

		// JPanel panel_2 = new JPanel();
		// panel_2.
		// panel_1.add(panel_2);

		JPanel panel_info = new JPanel();
		panel_info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_info = new GridBagConstraints();
		gbc_panel_info.insets = new Insets(0, 0, 0, 5);
		gbc_panel_info.fill = GridBagConstraints.BOTH;
		gbc_panel_info.gridx = 2;
		gbc_panel_info.gridy = 0;
		contentPane.add(panel_info, gbc_panel_info);
		panel_info.setBackground(SystemColor.window);
		GridBagLayout gbl_panel_info = new GridBagLayout();
		gbl_panel_info.columnWidths = new int[] { 30, 135, 40, 40, 40, 0 };
		gbl_panel_info.rowHeights = new int[] { 35, 17, 24, 24, 24, 24, 0, 18, 0, 24, 24, 24, 24, 24, 22, 25, 37, 0 };
		gbl_panel_info.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel_info.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_info.setLayout(gbl_panel_info);

		JLabel lblNewLabel1 = new JLabel("Vehicle Status");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel1.gridx = 1;
		gbc_lblNewLabel1.gridy = 0;
		panel_info.add(lblNewLabel1, gbc_lblNewLabel1);
		JLabel lblAxisZ = new JLabel("Elevation");
		lblAxisZ.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblAxisZ.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblAxisZ = new GridBagConstraints();
		gbc_lblAxisZ.anchor = GridBagConstraints.WEST;
		gbc_lblAxisZ.gridwidth = 2;
		gbc_lblAxisZ.fill = GridBagConstraints.VERTICAL;
		gbc_lblAxisZ.insets = new Insets(0, 0, 5, 5);
		gbc_lblAxisZ.gridx = 1;
		gbc_lblAxisZ.gridy = 1;
		panel_info.add(lblAxisZ, gbc_lblAxisZ);

		txtFieldZ = new JTextField();
		txtFieldZ.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtFieldZ.setText("0.0");
		txtFieldZ.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldZ.setEditable(false);
		txtFieldZ.setColumns(10);
		GridBagConstraints gbc_txtFieldZ = new GridBagConstraints();
		gbc_txtFieldZ.fill = GridBagConstraints.BOTH;
		gbc_txtFieldZ.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldZ.gridx = 4;
		gbc_txtFieldZ.gridy = 1;
		panel_info.add(txtFieldZ, gbc_txtFieldZ);

		JLabel lblNose = new JLabel("Nose");
		lblNose.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNose.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblNose = new GridBagConstraints();
		gbc_lblNose.anchor = GridBagConstraints.WEST;
		gbc_lblNose.gridwidth = 2;
		gbc_lblNose.fill = GridBagConstraints.VERTICAL;
		gbc_lblNose.insets = new Insets(0, 0, 5, 5);
		gbc_lblNose.gridx = 1;
		gbc_lblNose.gridy = 2;
		panel_info.add(lblNose, gbc_lblNose);

		txtFieldNose = new JTextField();
		txtFieldNose.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtFieldNose.setText("0.0");
		txtFieldNose.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldNose.setEditable(false);
		txtFieldNose.setColumns(10);
		GridBagConstraints gbc_txtFieldNose = new GridBagConstraints();
		gbc_txtFieldNose.fill = GridBagConstraints.BOTH;
		gbc_txtFieldNose.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldNose.gridx = 4;
		gbc_txtFieldNose.gridy = 2;
		panel_info.add(txtFieldNose, gbc_txtFieldNose);

		JLabel lblRoll = new JLabel("Roll");
		lblRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblRoll.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblRoll = new GridBagConstraints();
		gbc_lblRoll.anchor = GridBagConstraints.WEST;
		gbc_lblRoll.gridwidth = 2;
		gbc_lblRoll.fill = GridBagConstraints.VERTICAL;
		gbc_lblRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll.gridx = 1;
		gbc_lblRoll.gridy = 3;
		panel_info.add(lblRoll, gbc_lblRoll);

		txtFieldRoll = new JTextField();
		txtFieldRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtFieldRoll.setText("0.0");
		txtFieldRoll.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFieldRoll.setEditable(false);
		txtFieldRoll.setColumns(10);
		GridBagConstraints gbc_txtFieldRoll = new GridBagConstraints();
		gbc_txtFieldRoll.fill = GridBagConstraints.BOTH;
		gbc_txtFieldRoll.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldRoll.gridx = 4;
		gbc_txtFieldRoll.gridy = 3;
		panel_info.add(txtFieldRoll, gbc_txtFieldRoll);

		JLabel labelEuler = new JLabel("Euler Angles");
		labelEuler.setHorizontalAlignment(SwingConstants.LEFT);
		labelEuler.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_labelEuler = new GridBagConstraints();
		gbc_labelEuler.anchor = GridBagConstraints.WEST;
		gbc_labelEuler.fill = GridBagConstraints.VERTICAL;
		gbc_labelEuler.insets = new Insets(0, 0, 5, 5);
		gbc_labelEuler.gridx = 1;
		gbc_labelEuler.gridy = 4;
		panel_info.add(labelEuler, gbc_labelEuler);

		JLabel lblEulerRoll = new JLabel("Roll");
		lblEulerRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblEulerRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblEulerRoll = new GridBagConstraints();
		gbc_lblEulerRoll.fill = GridBagConstraints.BOTH;
		gbc_lblEulerRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblEulerRoll.gridx = 2;
		gbc_lblEulerRoll.gridy = 4;
		panel_info.add(lblEulerRoll, gbc_lblEulerRoll);

		JLabel lblEurlerPitch = new JLabel("Pitch");
		lblEurlerPitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblEurlerPitch.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblEurlerPitch = new GridBagConstraints();
		gbc_lblEurlerPitch.fill = GridBagConstraints.BOTH;
		gbc_lblEurlerPitch.insets = new Insets(0, 0, 5, 5);
		gbc_lblEurlerPitch.gridx = 3;
		gbc_lblEurlerPitch.gridy = 4;
		panel_info.add(lblEurlerPitch, gbc_lblEurlerPitch);

		JLabel lblEulerYaw = new JLabel("Yaw");
		lblEulerYaw.setHorizontalAlignment(SwingConstants.CENTER);
		lblEulerYaw.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblEulerYaw = new GridBagConstraints();
		gbc_lblEulerYaw.fill = GridBagConstraints.BOTH;
		gbc_lblEulerYaw.insets = new Insets(0, 0, 5, 5);
		gbc_lblEulerYaw.gridx = 4;
		gbc_lblEulerYaw.gridy = 4;
		panel_info.add(lblEulerYaw, gbc_lblEulerYaw);

		// Euler's angle
		txtEulerRoll = new JTextField();
		txtEulerRoll.setText("0.0");
		txtEulerRoll.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEulerRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtEulerRoll.setEditable(false);
		txtEulerRoll.setColumns(10);
		GridBagConstraints gbc_txtEulerRoll = new GridBagConstraints();
		gbc_txtEulerRoll.fill = GridBagConstraints.BOTH;
		gbc_txtEulerRoll.insets = new Insets(0, 0, 5, 5);
		gbc_txtEulerRoll.gridx = 2;
		gbc_txtEulerRoll.gridy = 5;
		panel_info.add(txtEulerRoll, gbc_txtEulerRoll);

		txtEulerPitch = new JTextField();
		txtEulerPitch.setText("0.0");
		txtEulerPitch.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEulerPitch.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtEulerPitch.setEditable(false);
		txtEulerPitch.setColumns(10);
		GridBagConstraints gbc_txtEulerPitch = new GridBagConstraints();
		gbc_txtEulerPitch.fill = GridBagConstraints.BOTH;
		gbc_txtEulerPitch.insets = new Insets(0, 0, 5, 5);
		gbc_txtEulerPitch.gridx = 3;
		gbc_txtEulerPitch.gridy = 5;
		panel_info.add(txtEulerPitch, gbc_txtEulerPitch);

		txtEulerYaw = new JTextField();
		txtEulerYaw.setText("0.0");
		txtEulerYaw.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEulerYaw.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtEulerYaw.setEditable(false);
		txtEulerYaw.setColumns(10);
		GridBagConstraints gbc_txtEulerYaw = new GridBagConstraints();
		gbc_txtEulerYaw.fill = GridBagConstraints.BOTH;
		gbc_txtEulerYaw.insets = new Insets(0, 0, 5, 5);
		gbc_txtEulerYaw.gridx = 4;
		gbc_txtEulerYaw.gridy = 5;
		panel_info.add(txtEulerYaw, gbc_txtEulerYaw);

		JLabel lblAcceleration = new JLabel("Acceleration");
		lblAcceleration.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcceleration.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblAcceleration = new GridBagConstraints();
		gbc_lblAcceleration.anchor = GridBagConstraints.WEST;
		gbc_lblAcceleration.fill = GridBagConstraints.VERTICAL;
		gbc_lblAcceleration.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcceleration.gridx = 1;
		gbc_lblAcceleration.gridy = 6;
		panel_info.add(lblAcceleration, gbc_lblAcceleration);

		// Acceleration from IMU
		JLabel lblAccX = new JLabel("X");
		lblAccX.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccX.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblAccX = new GridBagConstraints();
		gbc_lblAccX.fill = GridBagConstraints.BOTH;
		gbc_lblAccX.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccX.gridx = 2;
		gbc_lblAccX.gridy = 6;
		panel_info.add(lblAccX, gbc_lblAccX);

		JLabel lblAccY = new JLabel("Y");
		lblAccY.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccY.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblAccY = new GridBagConstraints();
		gbc_lblAccY.fill = GridBagConstraints.BOTH;
		gbc_lblAccY.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccY.gridx = 3;
		gbc_lblAccY.gridy = 6;
		panel_info.add(lblAccY, gbc_lblAccY);

		JLabel lblAccZ = new JLabel("Z");
		lblAccZ.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccZ.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblAccZ = new GridBagConstraints();
		gbc_lblAccZ.fill = GridBagConstraints.BOTH;
		gbc_lblAccZ.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccZ.gridx = 4;
		gbc_lblAccZ.gridy = 6;
		panel_info.add(lblAccZ, gbc_lblAccZ);

		txtAccX = new JTextField();
		txtAccX.setText("0.0");
		txtAccX.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAccX.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtAccX.setEditable(false);
		txtAccX.setColumns(10);
		GridBagConstraints gbc_txtAccX = new GridBagConstraints();
		gbc_txtAccX.fill = GridBagConstraints.BOTH;
		gbc_txtAccX.insets = new Insets(0, 0, 5, 5);
		gbc_txtAccX.gridx = 2;
		gbc_txtAccX.gridy = 7;
		panel_info.add(txtAccX, gbc_txtAccX);

		txtAccY = new JTextField();
		txtAccY.setText("0.0");
		txtAccY.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAccY.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtAccY.setEditable(false);
		txtAccY.setColumns(10);
		GridBagConstraints gbc_txtAccY = new GridBagConstraints();
		gbc_txtAccY.fill = GridBagConstraints.BOTH;
		gbc_txtAccY.insets = new Insets(0, 0, 5, 5);
		gbc_txtAccY.gridx = 3;
		gbc_txtAccY.gridy = 7;
		panel_info.add(txtAccY, gbc_txtAccY);

		txtAccZ = new JTextField();
		txtAccZ.setText("0.0");
		txtAccZ.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAccZ.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		txtAccZ.setEditable(false);
		txtAccZ.setColumns(10);
		GridBagConstraints gbc_txtAccZ = new GridBagConstraints();
		gbc_txtAccZ.fill = GridBagConstraints.BOTH;
		gbc_txtAccZ.insets = new Insets(0, 0, 5, 5);
		gbc_txtAccZ.gridx = 4;
		gbc_txtAccZ.gridy = 7;
		panel_info.add(txtAccZ, gbc_txtAccZ);

		JLabel lblNetworkStatus = new JLabel("Network Status");
		lblNetworkStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblNetworkStatus.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_lblNetworkStatus = new GridBagConstraints();
		gbc_lblNetworkStatus.anchor = GridBagConstraints.WEST;
		gbc_lblNetworkStatus.fill = GridBagConstraints.VERTICAL;
		gbc_lblNetworkStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblNetworkStatus.gridx = 1;
		gbc_lblNetworkStatus.gridy = 9;
		panel_info.add(lblNetworkStatus, gbc_lblNetworkStatus);

		JLabel lblIp = new JLabel("IP");
		lblIp.setForeground(Color.BLACK);
		lblIp.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.WEST;
		gbc_lblIp.fill = GridBagConstraints.VERTICAL;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 1;
		gbc_lblIp.gridy = 10;
		panel_info.add(lblIp, gbc_lblIp);

		ImageIcon logo_icon = new ImageIcon("logo.png");
		Image logo_resized = logo_icon.getImage().getScaledInstance(200, -1, java.awt.Image.SCALE_SMOOTH);
		logo_icon = new ImageIcon(logo_resized);

		txtIP = new JTextField();
		txtIP.setBackground(SystemColor.menu);
		txtIP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIP.setText(Launcher.address);
		GridBagConstraints gbc_txtIP = new GridBagConstraints();
		gbc_txtIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIP.gridwidth = 3;
		gbc_txtIP.insets = new Insets(0, 0, 5, 5);
		gbc_txtIP.gridx = 2;
		gbc_txtIP.gridy = 10;
		panel_info.add(txtIP, gbc_txtIP);
		txtIP.setColumns(10);

		JLabel lblHbridgePort = new JLabel("HBridge Port");
		lblHbridgePort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblHbridgePort = new GridBagConstraints();
		gbc_lblHbridgePort.anchor = GridBagConstraints.WEST;
		gbc_lblHbridgePort.insets = new Insets(0, 0, 5, 5);
		gbc_lblHbridgePort.gridx = 1;
		gbc_lblHbridgePort.gridy = 11;
		panel_info.add(lblHbridgePort, gbc_lblHbridgePort);

		txtHBridgePort = new JTextField();
		txtHBridgePort.setBackground(SystemColor.menu);
		txtHBridgePort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtHBridgePort.setText(Launcher.hPort);
		GridBagConstraints gbc_txtHBridgePort = new GridBagConstraints();
		gbc_txtHBridgePort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHBridgePort.gridwidth = 2;
		gbc_txtHBridgePort.insets = new Insets(0, 0, 5, 5);
		gbc_txtHBridgePort.gridx = 3;
		gbc_txtHBridgePort.gridy = 11;
		panel_info.add(txtHBridgePort, gbc_txtHBridgePort);
		txtHBridgePort.setColumns(10);

		JLabel lblServoPort = new JLabel("Servo Port");
		lblServoPort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblServoPort = new GridBagConstraints();
		gbc_lblServoPort.anchor = GridBagConstraints.WEST;
		gbc_lblServoPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblServoPort.gridx = 1;
		gbc_lblServoPort.gridy = 12;
		panel_info.add(lblServoPort, gbc_lblServoPort);

		txtServoPort = new JTextField();
		txtServoPort.setBackground(SystemColor.menu);
		txtServoPort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtServoPort.setText(Launcher.sPort);
		GridBagConstraints gbc_txtServoPort = new GridBagConstraints();
		gbc_txtServoPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtServoPort.gridwidth = 2;
		gbc_txtServoPort.insets = new Insets(0, 0, 5, 5);
		gbc_txtServoPort.gridx = 3;
		gbc_txtServoPort.gridy = 12;
		panel_info.add(txtServoPort, gbc_txtServoPort);
		txtServoPort.setColumns(10);
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 3;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 13;
		panel_info.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setIcon(logo_icon);

		JButton btnNewButton = new JButton("Quit");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 16;
		// panel_info.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
//					Launcher.sServer.out.flush();
				} catch (Exception a) {
					System.exit(0);
				} finally {
					System.out.println("exited");
					System.exit(0);
				}
			}
		});
		// pack();

	}

}
