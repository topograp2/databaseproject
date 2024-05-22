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

public class ShowFlightCount {

	JFrame frame;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowFlightCount window = new ShowFlightCount();
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
	public ShowFlightCount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 663, 388);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Show FlightCount");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(27, 10, 286, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Origin Airport Code");
		lblNewLabel_1.setBounds(37, 66, 268, 27);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(47, 103, 153, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String origincode = textField.getText();
				String res = ManageMenu.selectFlightAggregation(origincode);
				textField.setText("");
				textArea.append(res);
				
			}
		});
		submitBtn.setBounds(218, 103, 95, 23);
		panel.add(submitBtn);
		
		textArea = new JTextArea();
		textArea.setBounds(47, 188, 537, 144);
		panel.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("* result");
		lblNewLabel_2.setBounds(51, 157, 52, 15);
		panel.add(lblNewLabel_2);
	}
}
