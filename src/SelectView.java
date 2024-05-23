import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectView {

	JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectView window = new SelectView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, -14, 546, 571);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Booking information of passenger");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 32, 436, 34);
		panel.add(lblNewLabel);
		
		JLabel lblFlightInformation = new JLabel("Flight information");
		lblFlightInformation.setFont(new Font("Arial", Font.BOLD, 20));
		lblFlightInformation.setBounds(12, 303, 436, 34);
		panel.add(lblFlightInformation);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(12, 141, 481, 152);
		panel.add(textArea);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setBackground(Color.LIGHT_GRAY);
		textArea2.setBounds(12, 402, 481, 101);
		
		JScrollPane scrollpane= new JScrollPane(textArea2);
		scrollpane.setBounds(12, 402, 481, 101);
		panel.add(scrollpane);
		
		textField = new JTextField();
		textField.setBounds(12, 110, 106, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton bookingInfoBtn = new JButton("Submit");
		bookingInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int passengerID = Integer.parseInt(textField.getText());
				System.out.println(passengerID);
				String res = ManageMenu.getPassengerBookingInfo(passengerID);
				textArea.append(res);
			}
		});
		bookingInfoBtn.setBounds(139, 109, 95, 23);
		panel.add(bookingInfoBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the ID of the passenger to get the booking information.");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 65, 436, 15);
		panel.add(lblNewLabel_1);
		
		JButton flightInfoBtn = new JButton("Show");
		flightInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res = ManageMenu.getFlightInfoFromView();
				textArea2.append(res);
			}
		});
		flightInfoBtn.setBounds(23, 369, 95, 23);
		panel.add(flightInfoBtn);
		
		JLabel lblNewLabel_1_1 = new JLabel("Click the button below to get flight information");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(22, 344, 436, 15);
		panel.add(lblNewLabel_1_1);
	}
}
