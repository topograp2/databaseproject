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

public class InsertMenuView {

	JFrame frame;
	private JTextField passengerID;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JTextField passportNum;
	private JTextArea resultField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//화면 실행
			public void run() {
				try {
					InsertMenuView window = new InsertMenuView();
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
	public InsertMenuView() {
		//화면 초기화
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//프레임 설정
		frame = new JFrame();
		frame.setBounds(100, 100, 755, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//panel 설정
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 249, 255));
		panel.setBounds(0, 0, 741, 366);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		//타이틀 설정
		JLabel title = new JLabel("Insert Passenger");
		title.setBounds(44, 26, 241, 36);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(title);
		//구분선 표시
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 72, 741, 10);
		panel.add(panel_1);
		// 승객 정보 입력하라는 메시지 표시
		JLabel explain = new JLabel("Enter the information of passenger to want to add.");
		explain.setFont(new Font("Arial", Font.PLAIN, 20));
		explain.setBounds(33, 103, 463, 15);
		panel.add(explain);
		//passengerID 입력할 textField 표시
		passengerID = new JTextField();
		passengerID.setBounds(81, 147, 106, 21);
		panel.add(passengerID);
		passengerID.setColumns(10);
		//firstName 입력할 textField 표시
		firstName = new JTextField();
		firstName.setBounds(81, 197, 106, 21);
		panel.add(firstName);
		firstName.setColumns(10);
		//lastName 입력할 textField 표시
		lastName = new JTextField();
		lastName.setBounds(216, 197, 106, 21);
		panel.add(lastName);
		lastName.setColumns(10);
		//email 입력할 textField 표시
		email = new JTextField();
		email.setBounds(81, 250, 241, 21);
		panel.add(email);
		email.setColumns(10);
		//passportNum 입력할 textField 표시
		passportNum = new JTextField();
		passportNum.setBounds(380, 250, 139, 21);
		panel.add(passportNum);
		passportNum.setColumns(10);
		
		//passengerID 입력하라고 안내
		JLabel lblNewLabel = new JLabel("passengerID");
		lblNewLabel.setBounds(97, 128, 90, 15);
		panel.add(lblNewLabel);
		//First Name 입력하라고 안내
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(91, 178, 79, 15);
		panel.add(lblNewLabel_1);
		//Last Name 입력하라고 안내
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(231, 178, 91, 15);
		panel.add(lblNewLabel_2);
		//email 입력하라고 안내
		JLabel lblNewLabel_3 = new JLabel("email");
		lblNewLabel_3.setBounds(91, 228, 52, 15);
		panel.add(lblNewLabel_3);
		//passport number 입력하라고 안내
		JLabel lblNewLabel_4 = new JLabel("passport number");
		lblNewLabel_4.setBounds(392, 228, 104, 15);
		panel.add(lblNewLabel_4);
		
		//사용자가 입력한 data를 db로 전송할 버튼 표시
		JButton btnNewButton = new JButton("Submit");
		ManageMenu insertMenu = new ManageMenu();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//db에서 passengerID는 int이므로 형변환 시킴
				int pasID = Integer.parseInt(passengerID.getText());
				//db에 사용자가 입력한 승객 정보를 바탕으로 승객 tuple 만듦
				
				boolean isItSuccess = insertMenu.insertPassenger(pasID, firstName.getText(), lastName.getText(), email.getText(), passportNum.getText());
				if(isItSuccess) { //isItSuccess가 true면, sql을 잘 실행되었다는 메시지를 표시
					resultField.append("insert "+ firstName.getText() + lastName.getText()+"\n");
					
				}else {//isItSuccess가 false면, 실행 실패했다는 메시지를 표시
					resultField.append("Failure!!!\n");
					
				}
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 128));
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.setBounds(571, 249, 95, 23);
		panel.add(btnNewButton);
		
		//sql 결과 표시할 textArea 만들기
		resultField = new JTextArea();
		resultField.setBounds(80, 299, 586, 57);
		panel.add(resultField);
		//textArea 타이틀 설정
		JLabel lblNewLabel_5 = new JLabel("result");
		lblNewLabel_5.setBounds(91, 274, 129, 15);
		panel.add(lblNewLabel_5);
	}
}
