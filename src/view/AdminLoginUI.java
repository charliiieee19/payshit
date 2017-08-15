package view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import control.AdminLoginCtrl;
import model.Admin;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminLoginUI extends JFrame {

	private JPanel contentPane;
	private JLabel background;
	private JTextField textField;
	private JPasswordField passwordField;
	private Connection connection;
	private Admin user;
	private AdminLoginCtrl adminDA;

	public AdminLoginUI() {
		setTitle("Payroll System");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\one.jpg"));
		background = new JLabel();
		background.setIcon(new ImageIcon("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\admin.jpg"));
		background.setBounds(0, 0, 580, 349);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		centerFrame();

		textField = new JTextField();
		textField.setBounds(117, 83, 169, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblEmployeeLogin = new JLabel("Admin Login:");
		lblEmployeeLogin.setBounds(28, 23, 101, 31);
		lblEmployeeLogin.setForeground(Color.WHITE);
		contentPane.add(lblEmployeeLogin);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 87, 64, 23);
		lblUsername.setForeground(Color.WHITE);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 129, 64, 23);
		lblPassword.setForeground(Color.WHITE);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(117, 125, 169, 31);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validateUser();
				dispose();
			}
		});
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 14));
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setForeground(Color.white);
		btnLogin.setUI(new StyledButtonUI());
		btnLogin.setBounds(190, 193, 96, 31);
		contentPane.add(btnLogin);

		JButton btnEmp = new JButton("Employee Login");
		btnEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmpLoginUI();
				dispose();
			}
		});
		btnEmp.setFont(new Font("Calibri", Font.BOLD, 14));
		btnEmp.setBackground(Color.DARK_GRAY);
		btnEmp.setForeground(Color.white);
		btnEmp.setUI(new StyledButtonUI());
		btnEmp.setBounds(384, 109, 139, 31);
		contentPane.add(btnEmp);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD, 14));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setForeground(Color.white);
		btnExit.setUI(new StyledButtonUI());
		btnExit.setBounds(190, 235, 96, 31);

		contentPane.add(btnExit);
		contentPane.add(background);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AdminLoginUI();

	}

	private void centerFrame() {

		Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;
		setLocation(dx, dy);
	}

	class StyledButtonUI extends BasicButtonUI {

		@Override
		public void installUI(JComponent c) {
			super.installUI(c);
			AbstractButton button = (AbstractButton) c;
			button.setOpaque(false);
			button.setBorder(new EmptyBorder(5, 15, 5, 15));
		}

		@Override
		public void paint(Graphics g, JComponent c) {
			AbstractButton b = (AbstractButton) c;
			paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
			super.paint(g, c);
		}

		private void paintBackground(Graphics g, JComponent c, int yOffset) {
			Dimension size = c.getSize();
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(c.getBackground().darker());
			g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
			g.setColor(c.getBackground());
			g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
		}
	}

	public Connection getConnection() {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection = DriverManager.getConnection("jdbc:db2://localhost:50000/payroll", "Charlie", "1231234");

		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void validateUser() {
		user = new Admin();
		user.setUsername(textField.getText());
		user.setPassword(passwordField.getText());

		// instantiate persistent objects
		adminDA = new AdminLoginCtrl(getConnection(), user.getUsername());

		Admin validUser = new Admin();
		validUser = adminDA.getUser(user);

		try {

			if (validUser.getUsername().equals(user.getUsername())
					&& validUser.getPassword().equals(user.getPassword()))
				displayMain(adminDA.getUser(user));
			
			
			else {
				JOptionPane.showMessageDialog(null, "Invalid username or password", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
		
		}
		 catch (Exception e) {
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "Invalid username or password", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			
		}
	}
	

	

	public void displayMain(Admin user) throws ClassNotFoundException, SQLException {
		new AdminProfileUI();

	}
}
