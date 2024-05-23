import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Home {

	JFrame frmHome;
	public Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//화면 실행
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		//초기화면 설정
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//프레임 생성
		frmHome = new JFrame();
		frmHome.setResizable(false);
		frmHome.setTitle("Airline Booking System");
		frmHome.setBounds(100, 100, 375, 667);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		//배경색 설정을 위해 panel 추가
		JPanel panel = new JPanel();
		panel.setBackground(new Color(157, 220, 94));
		panel.setBounds(0, 10, 361, 630);
		frmHome.getContentPane().add(panel);
		panel.setLayout(null);
		
		//꾸미기 용도 선 화면
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(-11, 23, 387, 11);
		panel.add(panel_1);
		
		//타이틀 설정
		JLabel lblNewLabel = new JLabel("Airline");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(38, 0, 193, 183);
		panel.add(lblNewLabel);
		
		//타이틀 설정
		JLabel lblBooking = new JLabel("Booking");
		lblBooking.setForeground(new Color(219, 237, 90));
		lblBooking.setFont(new Font("Arial", Font.BOLD, 40));
		lblBooking.setBounds(88, 44, 193, 183);
		panel.add(lblBooking);
		
		//타이틀 설정
		JLabel lblSystem = new JLabel("System");
		lblSystem.setForeground(Color.WHITE);
		lblSystem.setFont(new Font("Arial", Font.BOLD, 40));
		lblSystem.setBounds(38, 94, 193, 183);
		panel.add(lblSystem);
		
		//꾸미기 용도 구분선 보이기
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(-11, 248, 426, 11);
		panel.add(panel_1_1);
		
		//참여자 이름 표시
		JLabel lblNewLabel_1 = new JLabel("#YW #JH #SY");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(225, 223, 219, 15);
		panel.add(lblNewLabel_1);
		
		//승객 tuple 집어넣는 화면 보여주는 버튼 보이기
		JButton insertBtn = new JButton("InsertPassenger");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼 클릭 시 승객 tuple 집어넣는 화면 보이기
				InsertMenuView insertView = new InsertMenuView();
				insertView.frame.setVisible(true);
			}
		});
		insertBtn.setForeground(new Color(181, 182, 100));
		insertBtn.setBackground(new Color(254, 255, 194));
		insertBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		insertBtn.setBounds(53, 266, 228, 47);
		panel.add(insertBtn);
		
		// 특정 공항에서 출발하는 항공편 세어주는 화면을 보여주는 버튼 보이기
		JButton selectBtn = new JButton("ShowFlightCount");
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼 클릭 시 특정 공항에서 출발하는 항공편 세어주는 화면 보이기
				ShowFlightCount flightCount = new ShowFlightCount();
				flightCount.frame.setVisible(true);
			}
		});
		selectBtn.setForeground(new Color(181, 182, 100));
		selectBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		selectBtn.setBackground(new Color(254, 255, 194));
		selectBtn.setBounds(53, 429, 228, 47);
		panel.add(selectBtn);
		
		//승객 없애는 화면 보여주는 버튼 표시
		JButton DeleteBtn = new JButton("Delete Passenger");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼 클릭 시 승객 없애는 화면 보이기
				DeleteMenu dm = new DeleteMenu();
				dm.frame.setVisible(true);
			}
		});
		DeleteBtn.setForeground(new Color(181, 182, 100));
		DeleteBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		DeleteBtn.setBackground(new Color(254, 255, 194));
		DeleteBtn.setBounds(53, 537, 228, 47);
		panel.add(DeleteBtn);
		
		//이화여대 글자 표시 
		JLabel lblNewLabel_2 = new JLabel("@ Ewha Womans Uni");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(64, 594, 217, 15);
		panel.add(lblNewLabel_2);
		
		//부킹할 수 있는 화면을 보이는 버튼 표시
		JButton btnNewButton = new JButton("Booking");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼 클릭 부킹할 수 있는 화면 보임
				BookingView view = new BookingView();
				view.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(53, 323, 228, 46);
		panel.add(btnNewButton);
		
		//지불여부를 업데이트하는 화면을 보이는 버튼 표시
		JButton updateBtn = new JButton("Update payStatus");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//버튼 클릭 시 지불여부 업데이트 화면 표시
				UpdateView view= new UpdateView();
				view.frame.setVisible(true);
			}
		});
		updateBtn.setBackground(new Color(254, 255, 194));
		updateBtn.setForeground(new Color(181, 182, 100));
		updateBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		updateBtn.setBounds(53, 486, 228, 41);
		panel.add(updateBtn);
		
		JButton showInfoBtn = new JButton("ShowBooking&flignt Info");
		showInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectView view= new SelectView();
				view.frame.setVisible(true);
			}
		});
		showInfoBtn.setHorizontalAlignment(SwingConstants.LEADING);
		showInfoBtn.setForeground(new Color(181, 182, 100));
		showInfoBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		showInfoBtn.setBackground(new Color(254, 255, 194));
		showInfoBtn.setBounds(53, 372, 228, 47);
		panel.add(showInfoBtn);
	}
}
