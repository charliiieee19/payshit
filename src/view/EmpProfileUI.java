package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class EmpProfileUI extends JFrame {

	private JPanel contentPane;
	private JTextField empNameTF;
	private JTextField idTF;
	private JLabel background;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private JTextField bpTF;
	private JTextField allowTF;
	private JTextField otTF;
	private JTextField sssTF;
	private JTextField phTF;
	private JTextField hdmfTF;
	private JTextField npTF;
	private JTextField taxTF;

	public EmpProfileUI() throws ClassNotFoundException, SQLException {
		setTitle("Edit Employee");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\one.jpg"));
		background = new JLabel();
		background
				.setIcon(new ImageIcon("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\empprofile.jpg"));
		background.setBounds(0, 0, 804, 643);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		centerFrame();

		JLabel lblEmpName = new JLabel("Emp Name");
		lblEmpName.setBounds(46, 46, 65, 19);

		contentPane.add(lblEmpName);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(46, 76, 46, 14);

		contentPane.add(lblId);

		empNameTF = new JTextField();
		empNameTF.setEditable(false);
		empNameTF.setBounds(115, 45, 193, 20);
		contentPane.add(empNameTF);
		empNameTF.setColumns(10);

		idTF = new JTextField();
		idTF.setEditable(false);
		idTF.setBounds(115, 73, 193, 20);
		contentPane.add(idTF);
		idTF.setColumns(10);

		bpTF = new JTextField();
		bpTF.setBounds(226, 189, 136, 26);
		bpTF.setEditable(false);
		contentPane.add(bpTF);
		bpTF.setColumns(10);

		JLabel lblBasicPay = new JLabel("Basic Pay:");
		lblBasicPay.setBounds(157, 194, 65, 16);
		contentPane.add(lblBasicPay);

		JLabel lblAllowance = new JLabel("Allowance:");
		lblAllowance.setBounds(157, 224, 65, 19);
		contentPane.add(lblAllowance);

		allowTF = new JTextField();
		allowTF.setBounds(226, 220, 136, 26);
		allowTF.setEditable(false);
		contentPane.add(allowTF);
		allowTF.setColumns(10);

		JLabel lblOvertime = new JLabel("Overtime: ");
		lblOvertime.setBounds(157, 257, 65, 19);
		contentPane.add(lblOvertime);

		otTF = new JTextField();
		otTF.setColumns(10);
		otTF.setEditable(false);
		otTF.setBounds(226, 253, 136, 26);
		contentPane.add(otTF);

		JLabel lblDeductions = new JLabel("Deductions");
		lblDeductions.setBounds(500, 148, 112, 26);
		contentPane.add(lblDeductions);

		JLabel lblSss = new JLabel("SSS:");
		lblSss.setBounds(417, 195, 52, 20);
		contentPane.add(lblSss);

		sssTF = new JTextField();
		sssTF.setEditable(false);
		sssTF.setColumns(10);
		sssTF.setBounds(479, 189, 136, 26);
		contentPane.add(sssTF);

		JLabel lblPhilhealth = new JLabel("PhilHealth");
		lblPhilhealth.setBounds(417, 225, 65, 17);
		contentPane.add(lblPhilhealth);

		phTF = new JTextField();
		phTF.setColumns(10);
		phTF.setEditable(false);
		phTF.setBounds(479, 220, 136, 26);
		contentPane.add(phTF);

		JLabel lblHdmf = new JLabel("HDMF:");
		lblHdmf.setBounds(417, 258, 55, 17);
		contentPane.add(lblHdmf);

		hdmfTF = new JTextField();
		hdmfTF.setColumns(10);
		hdmfTF.setEditable(false);
		hdmfTF.setBounds(479, 253, 136, 26);
		contentPane.add(hdmfTF);

		JLabel lblNetpay = new JLabel("NetPay");
		lblNetpay.setBounds(272, 366, 65, 19);
		contentPane.add(lblNetpay);

		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double bp = Double.parseDouble(bpTF.getText());
				double allow = Double.parseDouble(allowTF.getText());
				double ot = Double.parseDouble(otTF.getText());
				double sss = Double.parseDouble(sssTF.getText());
				double ph = Double.parseDouble(phTF.getText());
				double hdmf = Double.parseDouble(hdmfTF.getText());
				double partialtax = Double.parseDouble(taxTF.getText());
				double tax = partialtax / 100;

				double redeem = bp + allow + ot;
				double xtax = redeem * tax;
				double deduc1 = sss + ph + hdmf + xtax;
				double netpay = redeem - deduc1;

				npTF.setText("" + netpay);

			}
		});
		btnCompute.setBounds(346, 403, 112, 26);
		contentPane.add(btnCompute);

		npTF = new JTextField();
		npTF.setColumns(10);
		npTF.setEditable(false);
		npTF.setBounds(336, 362, 136, 26);
		contentPane.add(npTF);

		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(417, 290, 54, 19);
		contentPane.add(lblTax);

		taxTF = new JTextField();
		taxTF.setEditable(false);
		taxTF.setColumns(10);
		taxTF.setBounds(479, 286, 46, 26);
		contentPane.add(taxTF);
		
		JLabel label = new JLabel("%");
		label.setBounds(535, 290, 20, 22);
		contentPane.add(label);

		display();
		// contentPane.add(background);
		setVisible(true);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new EmpProfileUI();
	}

	
	private void centerFrame() {

		Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;
		setLocation(dx, dy);
	}

	public void display() throws ClassNotFoundException, SQLException {

		String dbURL = "jdbc:db2://localhost:50000/payroll";
		String username = "Charlie";
		String password = "1231234";
		
		try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
			
			String sql = "SELECT * FROM employees where id=?";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			statement.setString(1, "123456789");
		
			
			while (result.next()){
				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}		
	}
}
