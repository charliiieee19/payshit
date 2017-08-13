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

public class AdminProfileUI1 extends JFrame {

	private JPanel contentPane;
	private JTextField empNameTF;
	private JTextField idTF;
	private JLabel background;
	private String[] salaryList = { "Daily", "Hours", "Month" };
	private JButton btnSave, btnCancel, btnEdit;
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

	public AdminProfileUI1() throws ClassNotFoundException, SQLException {
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

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editButton();
			}
		});
		btnEdit.setBounds(10, 11, 95, 26);
		contentPane.add(btnEdit);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeTF();
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(115, 11, 95, 26);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeTF();
				try {
					display();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCancel.setEnabled(false);
		btnCancel.setBounds(220, 11, 106, 26);
		contentPane.add(btnCancel);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminLoginUI();
				dispose();
			}
		});
		btnLogout.setBounds(336, 11, 112, 26);
		contentPane.add(btnLogout);

		JLabel lblEmpName = new JLabel("Emp Name");
		lblEmpName.setBounds(23, 119, 65, 19);

		contentPane.add(lblEmpName);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(23, 149, 46, 14);

		contentPane.add(lblId);

		empNameTF = new JTextField();
		empNameTF.setEditable(false);
		empNameTF.setBounds(92, 118, 193, 20);
		contentPane.add(empNameTF);
		empNameTF.setColumns(10);

		idTF = new JTextField();
		idTF.setEditable(false);
		idTF.setBounds(92, 146, 193, 20);
		contentPane.add(idTF);
		idTF.setColumns(10);

		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (rs.first()) {

						empNameTF.setText(rs.getString("empname"));
						idTF.setText(rs.getString("id"));
						bpTF.setText(rs.getString("basicpay"));
						allowTF.setText(rs.getString("allowance"));
						otTF.setText(rs.getString("overtime"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						hdmfTF.setText(rs.getString("hdmf"));
						taxTF.setText(rs.getString("tax"));
					}
				} catch (Exception ex) {

				}
			}
		});
		btnFirst.setBounds(10, 48, 95, 26);
		contentPane.add(btnFirst);

		JButton btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (rs.previous()) {

						empNameTF.setText(rs.getString("empname"));
						idTF.setText(rs.getString("id"));
						bpTF.setText(rs.getString("basicpay"));
						allowTF.setText(rs.getString("allowance"));
						otTF.setText(rs.getString("overtime"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						hdmfTF.setText(rs.getString("hdmf"));
						taxTF.setText(rs.getString("tax"));

					} else {
						rs.next();

					}
				} catch (Exception ex) {

				}
			}
		});
		btnPrev.setBounds(115, 50, 95, 24);
		contentPane.add(btnPrev);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (rs.next()) {

						empNameTF.setText(rs.getString("empname"));
						idTF.setText(rs.getString("id"));
						bpTF.setText(rs.getString("basicpay"));
						allowTF.setText(rs.getString("allowance"));
						otTF.setText(rs.getString("overtime"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						hdmfTF.setText(rs.getString("hdmf"));
						taxTF.setText(rs.getString("tax"));

					} else {
						rs.previous();

					}
				} catch (Exception ex) {

				}

			}
		});
		btnNext.setBounds(220, 48, 106, 26);
		contentPane.add(btnNext);

		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (rs.last()) {

						empNameTF.setText(rs.getString("empname"));
						idTF.setText(rs.getString("id"));
						bpTF.setText(rs.getString("basicpay"));
						allowTF.setText(rs.getString("allowance"));
						otTF.setText(rs.getString("overtime"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						hdmfTF.setText(rs.getString("hdmf"));
						taxTF.setText(rs.getString("tax"));

					}
				} catch (Exception ex) {

				}
			}
		});
		btnLast.setBounds(336, 48, 112, 26);
		contentPane.add(btnLast);

		JButton btnGetEmp = new JButton("Get All Employee List");
		btnGetEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmpListUI();
				dispose();
			}
		});
		btnGetEmp.setBounds(481, 15, 281, 59);
		contentPane.add(btnGetEmp);
		
		bpTF = new JTextField();
		bpTF.setBounds(203, 262, 136, 26);
		bpTF.setEditable(false);
		contentPane.add(bpTF);
		bpTF.setColumns(10);
		
		JLabel lblBasicPay = new JLabel("Basic Pay:");
		lblBasicPay.setBounds(134, 267, 65, 16);
		contentPane.add(lblBasicPay);
		
		JLabel lblAllowance = new JLabel("Allowance:");
		lblAllowance.setBounds(134, 297, 65, 19);
		contentPane.add(lblAllowance);
		
		allowTF = new JTextField();
		allowTF.setBounds(203, 293, 136, 26);
		allowTF.setEditable(false);
		contentPane.add(allowTF);
		allowTF.setColumns(10);
		
		JLabel lblOvertime = new JLabel("Overtime: ");
		lblOvertime.setBounds(134, 330, 65, 19);
		contentPane.add(lblOvertime);
		
		otTF = new JTextField();
		otTF.setColumns(10);
		otTF.setEditable(false);
		otTF.setBounds(203, 326, 136, 26);
		contentPane.add(otTF);
		
		JLabel lblDeductions = new JLabel("Deductions");
		lblDeductions.setBounds(477, 221, 112, 26);
		contentPane.add(lblDeductions);
		
		JLabel lblSss = new JLabel("SSS:");
		lblSss.setBounds(394, 268, 52, 20);
		contentPane.add(lblSss);
		
		sssTF = new JTextField();
		sssTF.setEditable(false);
		sssTF.setColumns(10);
		sssTF.setBounds(453, 262, 136, 26);
		contentPane.add(sssTF);
		
		JLabel lblPhilhealth = new JLabel("PhilHealth");
		lblPhilhealth.setBounds(394, 298, 55, 17);
		contentPane.add(lblPhilhealth);
		
		phTF = new JTextField();
		phTF.setColumns(10);
		phTF.setEditable(false);
		phTF.setBounds(453, 293, 136, 26);
		contentPane.add(phTF);
		
		JLabel lblHdmf = new JLabel("HDMF:");
		lblHdmf.setBounds(394, 331, 55, 17);
		contentPane.add(lblHdmf);
		
		hdmfTF = new JTextField();
		hdmfTF.setColumns(10);
		hdmfTF.setEditable(false);
		hdmfTF.setBounds(453, 326, 136, 26);
		contentPane.add(hdmfTF);
		
		JLabel lblNetpay = new JLabel("NetPay");
		lblNetpay.setBounds(249, 439, 65, 19);
		contentPane.add(lblNetpay);
		
		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCompute.setBounds(323, 476, 112, 26);
		contentPane.add(btnCompute);
		
		npTF = new JTextField();
		npTF.setColumns(10);
		npTF.setEditable(false);
		npTF.setBounds(313, 435, 136, 26);
		contentPane.add(npTF);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(394, 363, 54, 19);
		contentPane.add(lblTax);
		
		taxTF = new JTextField();
		taxTF.setEditable(false);
		taxTF.setColumns(10);
		taxTF.setBounds(453, 359, 136, 26);
		contentPane.add(taxTF);

		display();
		// contentPane.add(background);
		setVisible(true);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new AdminProfileUI1();
	}

	public void editButton() {
		btnEdit.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
		empNameTF.setEditable(true);
		idTF.setEditable(true);
		bpTF.setEditable(true);
		allowTF.setEditable(true);
		otTF.setEditable(true);
		sssTF.setEditable(true);
		phTF.setEditable(true);
		hdmfTF.setEditable(true);
		taxTF.setEditable(true);
		

	}

	public void closeTF() {
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		empNameTF.setEditable(false);
		idTF.setEditable(false);
		bpTF.setEditable(false);
		allowTF.setEditable(false);
		otTF.setEditable(false);
		sssTF.setEditable(false);
		phTF.setEditable(false);
		hdmfTF.setEditable(false);
		taxTF.setEditable(false);
		

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

		String driver = "com.ibm.db2.jcc.DB2Driver";
		Class.forName(driver);

		con = DriverManager.getConnection("jdbc:db2://localhost:50000/payroll", "Charlie", "1231234");
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		String sql = "select * from employees";
		rs = st.executeQuery(sql);

		try {
			rs.next();
			{

				empNameTF.setText(rs.getString("empname"));
				idTF.setText(rs.getString("id"));
				bpTF.setText(rs.getString("basicpay"));
				allowTF.setText(rs.getString("allowance"));
				otTF.setText(rs.getString("overtime"));
				sssTF.setText(rs.getString("sss"));
				phTF.setText(rs.getString("phealth"));
				hdmfTF.setText(rs.getString("hdmf"));
				taxTF.setText(rs.getString("tax"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
