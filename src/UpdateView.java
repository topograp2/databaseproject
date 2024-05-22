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

public class UpdateView {

	JFrame frame;
	private JTextField textField;
	private JTextField textField1;
	private JButton submitBtn;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateView window = new UpdateView();
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
	public UpdateView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 567, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(-20, 0, 628, 372);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update paystatus");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(64, 10, 286, 51);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the name of the passenger whose payment status is to be updated");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(74, 67, 481, 15);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(74, 126, 106, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField1 = new JTextField();
		textField1.setBounds(210, 126, 106, 21);
		panel.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("first name");
		lblNewLabel_2.setBounds(74, 103, 80, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("last name");
		lblNewLabel_3.setBounds(210, 103, 85, 15);
		panel.add(lblNewLabel_3);
		
		submitBtn = new JButton("submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = textField.getText();
				String lastName = textField.getText();
				
				String res = ManageMenu.updatePaymentStatus(firstName, lastName);
				textArea.append(res+"\n");
				
			}
		});
		submitBtn.setBounds(358, 125, 95, 23);
		panel.add(submitBtn);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(74, 203, 379, 110);
		panel.add(textArea);
		
		JLabel lblNewLabel_4 = new JLabel("result");
		lblNewLabel_4.setBounds(74, 178, 52, 15);
		panel.add(lblNewLabel_4);
	}

}
