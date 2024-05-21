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

public class DeleteMenu {

	JFrame frame;
	private JTextArea resultArea;
	private JButton submitBtn;
	private JTextField lastNameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteMenu window = new DeleteMenu();
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
	public DeleteMenu() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 353);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 331);
		panel.setBackground(new Color(219, 237, 90));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("-Delete Menu");
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setBounds(12, 10, 299, 61);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 61, 436, 10);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Please enter the name of the passenger to be deleted.");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(22, 81, 379, 15);
		panel.add(lblNewLabel_1);
		
		JTextField firstNameText = new JTextField();
		firstNameText.setBounds(33, 136, 106, 21);
		panel.add(firstNameText);
		firstNameText.setColumns(10);
		
		submitBtn = new JButton("Click!");
		ManageMenu deleteMenu = new ManageMenu();
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean isItSuccess = deleteMenu.deletePassenger(firstNameText.getText(), lastNameText.getText());
				if(isItSuccess) {
					resultArea.append("delete "+ firstNameText.getText() + lastNameText.getText()+"\n");
					
				}else {
					resultArea.append("Failure!!!\n");
					
				}
				
				firstNameText.setText("");
				lastNameText.setText("");
			}
		});
		submitBtn.setBackground(new Color(255, 128, 64));
		submitBtn.setBounds(263, 135, 95, 23);
		panel.add(submitBtn);
		
		resultArea = new JTextArea();
		resultArea.setBounds(31, 209, 299, 76);
		panel.add(resultArea);
		
		JLabel lblNewLabel_2 = new JLabel("* result");
		lblNewLabel_2.setBounds(41, 184, 52, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(168, 109, 58, 0);
		panel.add(lblNewLabel_3);
		
		lastNameText = new JTextField();
		lastNameText.setColumns(10);
		lastNameText.setBounds(145, 136, 106, 21);
		panel.add(lastNameText);
		
		JLabel lblNewLabel_4 = new JLabel("First Name");
		lblNewLabel_4.setBounds(41, 111, 80, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Last Name");
		lblNewLabel_4_1.setBounds(157, 111, 80, 15);
		panel.add(lblNewLabel_4_1);
	}
}
