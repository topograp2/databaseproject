import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingView {

	JFrame frame;
	private JButton submitBtn;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingView window = new BookingView();
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
	public BookingView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 605, 510);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Booking");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 10, 211, 59);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the passengerID, flight ID, seat number and pay status to reserve a seat on the airline  ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(24, 69, 581, 22);
		panel.add(lblNewLabel_1);
		
		JTextField passengerIDField = new JTextField();
		passengerIDField.setBounds(24, 137, 106, 21);
		panel.add(passengerIDField);
		passengerIDField.setColumns(10);
		
		JTextField filghtIDField = new JTextField();
		filghtIDField.setBounds(24, 181, 106, 21);
		panel.add(filghtIDField);
		filghtIDField.setColumns(10);
		
		JTextField seatNumberField = new JTextField();
		seatNumberField.setBounds(178, 181, 106, 21);
		panel.add(seatNumberField);
		seatNumberField.setColumns(10);
		
		JTextField payField = new JTextField();
		payField.setBounds(322, 181, 106, 21);
		panel.add(payField);
		payField.setColumns(10);
		
		submitBtn = new JButton("submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int passengerID = Integer.parseInt(passengerIDField.getText());
				String filghtID = filghtIDField.getText();
				String seatNumber = seatNumberField.getText();
				String payStatus = payField.getText();
				filghtIDField.setText("");
				passengerIDField.setText("");
				seatNumberField.setText("");
				payField.setText("");
				
				String res = ManageMenu.bookingSeats(passengerID, filghtID, seatNumber, payStatus);
				textArea.append(res);
			}
		});
		submitBtn.setBounds(463, 180, 95, 23);
		panel.add(submitBtn);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(28, 235, 530, 100);
		panel.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("passengerID");
		lblNewLabel_2.setBounds(40, 112, 78, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("flightID");
		lblNewLabel_3.setBounds(40, 161, 52, 22);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("seat number");
		lblNewLabel_4.setBounds(178, 156, 106, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("pay status(paid or unpaid)");
		lblNewLabel_5.setBounds(322, 156, 169, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("result");
		lblNewLabel_6.setBounds(40, 210, 52, 15);
		panel.add(lblNewLabel_6);
	}
}
