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
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// delete pasenger 할 수 있는 화면 실행
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
		//화면 초기화
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//프레임 설정
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 353);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//바탕이 될 패널 설정
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 331);
		panel.setBackground(new Color(219, 237, 90));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		//타이틀 표시
		JLabel lblNewLabel = new JLabel("-Delete Menu");
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setBounds(12, 10, 299, 61);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(lblNewLabel);
		//꾸미기용 구분선 표시
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 61, 436, 10);
		panel.add(panel_1);
		// 어떤 정보를 입력할지 안내하는 문자열 표시
		JLabel lblNewLabel_1 = new JLabel("Please enter the ID of the passenger to be deleted.");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(22, 81, 379, 15);
		panel.add(lblNewLabel_1);
		// 사용자가 성을 입력할 textField 표시
		JTextField pasIDText = new JTextField();
		pasIDText.setBounds(33, 136, 106, 21);
		panel.add(pasIDText);
		pasIDText.setColumns(10);
		
		// 사용자가 입력한 first name과 last name을 db에 전달하는 버튼 표시
		submitBtn = new JButton("Click!");
		ManageMenu deleteMenu = new ManageMenu();
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//승객 ID를 db에 보내고 해당되는 승객을 db에서 삭제함
				//true이면 sql이 잘 실행되었다는 뜻, false면 sql이 실행되지 않았음
				Boolean isItSuccess = deleteMenu.deletePassenger(Integer.parseInt(pasIDText.getText()));
				if(isItSuccess) {
					//잘 실행되었음을 알리는 메시지를 reaultArea에 표시
					resultArea.append("delete "+ pasIDText.getText() +"\n");
					
				}else {
					//실행 실패 알리는 메시지를 reaultArea에 표시
					resultArea.append("Failure!!!\n");
					
				}
				// testField 초기화
				pasIDText.setText("");
				
			}
		});
		submitBtn.setBackground(new Color(255, 128, 64));
		submitBtn.setBounds(263, 135, 95, 23);
		panel.add(submitBtn);
		
		//결과를 표시하는 textArea 표시
		resultArea = new JTextArea();
		resultArea.setBounds(31, 209, 299, 76);
		panel.add(resultArea);
		
		//result title 설정
		JLabel lblNewLabel_2 = new JLabel("* result");
		lblNewLabel_2.setBounds(41, 184, 52, 15);
		panel.add(lblNewLabel_2);
		
		//구분선 표시
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(168, 109, 58, 0);
		panel.add(lblNewLabel_3);
		
		//승객 id 입력하라는 메시지 안내
		JLabel lblNewLabel_4 = new JLabel("PassengerID");
		lblNewLabel_4.setBounds(41, 111, 80, 15);
		panel.add(lblNewLabel_4);
		
	}
}
