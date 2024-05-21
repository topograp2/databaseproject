import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home {

	JFrame frmHome;
	public Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHome = new JFrame();
		frmHome.setResizable(false);
		frmHome.setTitle("Airline Booking System");
		frmHome.setBounds(100, 100, 375, 667);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(157, 220, 94));
		panel.setBounds(0, 10, 361, 630);
		frmHome.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(-11, 23, 387, 11);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Airline");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(38, 0, 193, 183);
		panel.add(lblNewLabel);
		
		JLabel lblBooking = new JLabel("Booking");
		lblBooking.setForeground(new Color(219, 237, 90));
		lblBooking.setFont(new Font("Arial", Font.BOLD, 40));
		lblBooking.setBounds(88, 44, 193, 183);
		panel.add(lblBooking);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setForeground(Color.WHITE);
		lblSystem.setFont(new Font("Arial", Font.BOLD, 40));
		lblSystem.setBounds(38, 94, 193, 183);
		panel.add(lblSystem);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(-11, 248, 426, 11);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("#YW #JH #SY");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(225, 223, 219, 15);
		panel.add(lblNewLabel_1);
		
		JButton insertBtn = new JButton("Insert Menu");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertMenuView insertView = new InsertMenuView();
				insertView.frame.setVisible(true);
			}
		});
		insertBtn.setForeground(new Color(181, 182, 100));
		insertBtn.setBackground(new Color(254, 255, 194));
		insertBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		insertBtn.setBounds(53, 298, 228, 47);
		panel.add(insertBtn);
		
		JButton selectBtn = new JButton("Select Menu");
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		selectBtn.setForeground(new Color(181, 182, 100));
		selectBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		selectBtn.setBackground(new Color(254, 255, 194));
		selectBtn.setBounds(53, 379, 228, 47);
		panel.add(selectBtn);
		
		JButton DeleteBtn = new JButton("Delete Menu");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteMenu dm = new DeleteMenu();
				dm.frame.setVisible(true);
			}
		});
		DeleteBtn.setForeground(new Color(181, 182, 100));
		DeleteBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		DeleteBtn.setBackground(new Color(254, 255, 194));
		DeleteBtn.setBounds(53, 461, 228, 47);
		panel.add(DeleteBtn);
		
		JLabel lblNewLabel_2 = new JLabel("@ Ewha Womans Uni");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(75, 552, 217, 15);
		panel.add(lblNewLabel_2);
	}

}
