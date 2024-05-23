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
		//실행
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
		//화면 초기화
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//프레임 만들기
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//패널 표시
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 663, 388);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//타이틀 설정
		JLabel lblNewLabel = new JLabel("Show FlightCount");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(27, 10, 286, 58);
		panel.add(lblNewLabel);
		//출발하는 공항 코드 입력 안내하는 메시지 표시
		JLabel lblNewLabel_1 = new JLabel("Enter Origin Airport Code");
		lblNewLabel_1.setBounds(37, 66, 268, 27);
		panel.add(lblNewLabel_1);
		
		//코드 입력할 textField 표시
		textField = new JTextField();
		textField.setBounds(47, 103, 153, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		//입력 받은 origin airport code를 가지고 sql을 실행하는 버튼 표시
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//origin airport code를 textField에서 가져와 변수 저장
				String origincode = textField.getText();
				//입력 받은 코드를 바탕으로 특정 공항에서 출발하는 항공편 개수 세기
				String res = ManageMenu.selectFlightAggregation(origincode);
				//textField 초기화
				textField.setText("");
				//결과 표시하기
				textArea.append(res);
				
			}
		});
		submitBtn.setBounds(218, 103, 95, 23);
		panel.add(submitBtn);
		//결과를 표시할 textArea 생성
		textArea = new JTextArea();
		textArea.setBounds(47, 188, 537, 144);
		panel.add(textArea);
		//결과 타이틀 설정
		JLabel lblNewLabel_2 = new JLabel("* result");
		lblNewLabel_2.setBounds(51, 157, 52, 15);
		panel.add(lblNewLabel_2);
	}
}
