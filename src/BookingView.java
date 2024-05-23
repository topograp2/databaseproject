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
		// 화면 실행
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
		//화면 초기화
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//프레임 만들기
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//배경색 표시를 위한 패널 표시
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 605, 510);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//타이틀 설정
		JLabel lblNewLabel = new JLabel("Booking");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 10, 211, 59);
		panel.add(lblNewLabel);
		
		//안내 표시
		JLabel lblNewLabel_1 = new JLabel("Enter the passengerID, flight ID, seat number and pay status to reserve a seat on the airline  ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(24, 69, 581, 22);
		panel.add(lblNewLabel_1);
		
		//passengerID를 입력할 textField 표시
		JTextField passengerIDField = new JTextField();
		passengerIDField.setBounds(24, 137, 106, 21);
		panel.add(passengerIDField);
		passengerIDField.setColumns(10);
		
		//filghtID를 입력할 textField 표시
		JTextField filghtIDField = new JTextField();
		filghtIDField.setBounds(24, 181, 106, 21);
		panel.add(filghtIDField);
		filghtIDField.setColumns(10);
		
		//seat number를 입력할 textField 표시
		JTextField seatNumberField = new JTextField();
		seatNumberField.setBounds(178, 181, 106, 21);
		panel.add(seatNumberField);
		seatNumberField.setColumns(10);
		
		
		//pay status를 입력할 textField 표시
		JTextField payField = new JTextField();
		payField.setBounds(322, 181, 106, 21);
		panel.add(payField);
		payField.setColumns(10);
		
		//textField 입력 후 누르면 db에 부킹 정보가 삽입되는 버튼 보이기
		submitBtn = new JButton("submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼 클릭 시 db에 부킹 정보가 새로 등록됨
				// db에서 passengerID는 int이므로 형변환해서 textField 안의 데이터 가져옴
				int passengerID = Integer.parseInt(passengerIDField.getText());
				// 사용자가 입력한 textField에서 flightID, seatNumber, pay status 문자열 가져옴
				String filghtID = filghtIDField.getText();
				String seatNumber = seatNumberField.getText();
				String payStatus = payField.getText();
				// textField 초기화
				filghtIDField.setText("");
				passengerIDField.setText("");
				seatNumberField.setText("");
				payField.setText("");
				
				//db에 사용자가 입력한 데이터를 기반으로 부킹정보 삽입하기
				String res = ManageMenu.bookingSeats(passengerID, filghtID, seatNumber, payStatus);
				// 잘 등록되었는지 확인 할 수 있도록 textArea에 결과 표시
				textArea.append(res);
			}
		});
		submitBtn.setBounds(463, 180, 95, 23);
		panel.add(submitBtn);
		
		//db에 잘 삽입되었는지 알려주는 메시지를 표시할 textArea 생성
		textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(28, 235, 530, 100);
		panel.add(textArea);
		
		//textField에 passengerID를 입력하라는 거 안내할 text 표시
		JLabel lblNewLabel_2 = new JLabel("passengerID");
		lblNewLabel_2.setBounds(40, 112, 78, 15);
		panel.add(lblNewLabel_2);
		//textField에 filghtID를 입력하라는 거 안내할 text 표시
		JLabel lblNewLabel_3 = new JLabel("flightID");
		lblNewLabel_3.setBounds(40, 161, 52, 22);
		panel.add(lblNewLabel_3);
		//textField에 seatNumber를 입력하라는 거 안내할 text 표시
		JLabel lblNewLabel_4 = new JLabel("seat number");
		lblNewLabel_4.setBounds(178, 156, 106, 15);
		panel.add(lblNewLabel_4);
		//textField에 pay status를 입력하라는 거 안내할 text 표시
		JLabel lblNewLabel_5 = new JLabel("pay status(paid or unpaid)");
		lblNewLabel_5.setBounds(322, 156, 169, 15);
		panel.add(lblNewLabel_5);
		//textArea title 설정
		JLabel lblNewLabel_6 = new JLabel("result");
		lblNewLabel_6.setBounds(40, 210, 52, 15);
		panel.add(lblNewLabel_6);
	}
}
