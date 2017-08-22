package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class EmpLoginUI extends JFrame {

	private JPanel contentPane;
	private JLabel background;
	static JTextField userTF;
	private JPasswordField passTF;

	public EmpLoginUI() {
		setTitle("Payroll System");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\one.jpg"));
		background = new JLabel();
		background.setIcon(new ImageIcon("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\one.jpg"));
		background.setBounds(0, 0, 564, 310);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		centerFrame();

		userTF = new JTextField();
		userTF.setBounds(117, 83, 169, 31);
		contentPane.add(userTF);
		userTF.setColumns(10);

		JLabel lblEmployeeLogin = new JLabel("Employee Login:");
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

		passTF = new JPasswordField();

		passTF.setBounds(117, 125, 169, 31);
		contentPane.add(passTF);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();

			}
		});

		btnLogin.setFont(new Font("Calibri", Font.BOLD, 14));
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setForeground(Color.white);
		btnLogin.setUI(new StyledButtonUI());
		btnLogin.setBounds(190, 193, 96, 31);
		contentPane.add(btnLogin);

		JButton btnEmp = new JButton("Admin Login");
		btnEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminLoginUI();
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
				int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Warning",
						JOptionPane.YES_NO_OPTION);

				if (dialogButton == JOptionPane.YES_OPTION) {

					dispose();
				} else {
					disableEvents(dialogButton);
				}
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD, 14));
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setForeground(Color.white);
		btnExit.setUI(new StyledButtonUI());
		btnExit.setBounds(190, 235, 96, 31);
		contentPane.add(btnExit);

		JLabel lblForgotPassword = new JLabel("Forgot Password?");
		lblForgotPassword.setBounds(30, 197, 111, 23);
		lblForgotPassword.setForeground(Color.WHITE);
		lblForgotPassword.setToolTipText("Contact Admin 09187654321");
		contentPane.add(lblForgotPassword);
		contentPane.add(background);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EmpLoginUI();

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

	public void Login() {
		String strDriver = "com.ibm.db2.jcc.DB2Driver";
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String strUrl = "jdbc:db2://localhost:50000/payroll";
		String user = "Charlie";
		String pass = "1231234";
		String usern = userTF.getText();
		String passw = passTF.getText();

		try {
			Class.forName(strDriver);
			conn = DriverManager.getConnection(strUrl, user, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select username,password from empaccount where username='" + usern
					+ "' and password='" + passw + "'");

			if (rs.next()) {

				dispose();
				new EmpProfileUI();
				JOptionPane.showMessageDialog(null, "Welcome Employee!");
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}
}
