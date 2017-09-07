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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class AdminProfileUI extends JFrame {

	private JPanel contentPane;
	private JTextField empNameTF;
	private JTextField idTF;
	private JLabel background;
	private JButton btnSave, btnCancel, btnEdit, btnAdd, btnFirst, btnNext, btnPrev, btnLast, btnInsert, btnLogout,
			btnGetEmp, btnCompute;
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
	private JTextField grossTF;

	public AdminProfileUI() throws ClassNotFoundException, SQLException {
		setTitle("Edit Employee");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\dollar.png"));
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
		btnEdit.setFont(new Font("Calibri", Font.BOLD, 14));
		btnEdit.setBackground(Color.DARK_GRAY);
		btnEdit.setForeground(Color.white);
		btnEdit.setUI(new StyledButtonUI());
		btnEdit.setBounds(10, 11, 95, 26);
		contentPane.add(btnEdit);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeTF();
				String dbURL = "jdbc:db2://localhost:50000/payroll";
				String username = "Charlie";
				String password = "1231234";
				String id = idTF.getText();
				String bp = bpTF.getText();
				String alw = allowTF.getText();
				String ot = otTF.getText();
				String sss = sssTF.getText();
				String ph = phTF.getText();
				String hdmf = hdmfTF.getText();
				String tax = taxTF.getText();

				try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

					String sql = "UPDATE employees SET basicpay=?, allowance=?, overtime=?, sss=?, phealth=?, hdmf=?, tax=? where id =?";

					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, bp);
					statement.setString(2, alw);
					statement.setString(3, ot);
					statement.setString(4, sss);
					statement.setString(5, ph);
					statement.setString(6, hdmf);
					statement.setString(7, tax);
					statement.setString(8, id);

					int rowsUpdated = statement.executeUpdate();
					if (rowsUpdated > 0) {
						JOptionPane.showMessageDialog(null, "Record Updated", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
					display();

				} catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
				}

			}
		});
		btnSave.setFont(new Font("Calibri", Font.BOLD, 14));
		btnSave.setBackground(Color.DARK_GRAY);
		btnSave.setForeground(Color.white);
		btnSave.setUI(new StyledButtonUI());
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
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 14));
		btnCancel.setBackground(Color.DARK_GRAY);
		btnCancel.setForeground(Color.white);
		btnCancel.setUI(new StyledButtonUI());
		btnCancel.setEnabled(false);
		btnCancel.setBounds(220, 11, 106, 26);
		contentPane.add(btnCancel);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure to Log out?", "Warning",
						JOptionPane.YES_NO_OPTION);

				if (dialogButton == JOptionPane.YES_OPTION) {
					new AdminLoginUI();
					dispose();
				} else {
					disableEvents(dialogButton);
				}

			}
		});
		btnLogout.setFont(new Font("Calibri", Font.BOLD, 14));
		btnLogout.setBackground(Color.DARK_GRAY);
		btnLogout.setForeground(Color.white);
		btnLogout.setUI(new StyledButtonUI());
		btnLogout.setBounds(336, 11, 112, 26);
		contentPane.add(btnLogout);

		JLabel lblEmpName = new JLabel("Emp Name");
		lblEmpName.setBounds(22, 132, 65, 19);

		contentPane.add(lblEmpName);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 162, 46, 14);

		contentPane.add(lblId);

		empNameTF = new JTextField();
		empNameTF.setEditable(false);
		empNameTF.setBounds(91, 131, 193, 20);
		contentPane.add(empNameTF);
		empNameTF.setColumns(10);

		idTF = new JTextField();
		idTF.setEditable(false);
		idTF.setBounds(91, 159, 193, 20);
		contentPane.add(idTF);
		idTF.setColumns(10);

		btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				npTF.setText("");
				grossTF.setText("");
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
		btnFirst.setFont(new Font("Calibri", Font.BOLD, 14));
		btnFirst.setBackground(Color.DARK_GRAY);
		btnFirst.setForeground(Color.white);
		btnFirst.setUI(new StyledButtonUI());
		btnFirst.setBounds(10, 48, 95, 26);
		contentPane.add(btnFirst);

		btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				npTF.setText("");
				grossTF.setText("");
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
		btnPrev.setFont(new Font("Calibri", Font.BOLD, 14));
		btnPrev.setBackground(Color.DARK_GRAY);
		btnPrev.setForeground(Color.white);
		btnPrev.setUI(new StyledButtonUI());
		btnPrev.setBounds(115, 50, 95, 24);
		contentPane.add(btnPrev);

		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				npTF.setText("");
				grossTF.setText("");
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
		btnNext.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNext.setBackground(Color.DARK_GRAY);
		btnNext.setForeground(Color.white);
		btnNext.setUI(new StyledButtonUI());
		btnNext.setBounds(220, 48, 106, 26);
		contentPane.add(btnNext);

		btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				npTF.setText("");
				grossTF.setText("");
				Last();
				// try {
				//
				// if (rs.last()) {
				//
				// empNameTF.setText(rs.getString("empname"));
				// idTF.setText(rs.getString("id"));
				// bpTF.setText(rs.getString("basicpay"));
				// allowTF.setText(rs.getString("allowance"));
				// otTF.setText(rs.getString("overtime"));
				// sssTF.setText(rs.getString("sss"));
				// phTF.setText(rs.getString("phealth"));
				// hdmfTF.setText(rs.getString("hdmf"));
				// taxTF.setText(rs.getString("tax"));
				//
				// }
				// } catch (Exception ex) {
				//
				// }
			}
		});
		btnLast.setFont(new Font("Calibri", Font.BOLD, 14));
		btnLast.setBackground(Color.DARK_GRAY);
		btnLast.setForeground(Color.white);
		btnLast.setUI(new StyledButtonUI());
		btnLast.setBounds(336, 48, 112, 26);
		contentPane.add(btnLast);

		btnGetEmp = new JButton("Get All Employee List");
		btnGetEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmpListUI();
				dispose();
			}
		});
		btnGetEmp.setFont(new Font("Calibri", Font.BOLD, 14));
		btnGetEmp.setBackground(Color.DARK_GRAY);
		btnGetEmp.setForeground(Color.white);
		btnGetEmp.setUI(new StyledButtonUI());
		btnGetEmp.setBounds(481, 11, 281, 63);
		contentPane.add(btnGetEmp);

		bpTF = new JTextField();
		bpTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();

			}
		});

		bpTF.setBounds(203, 262, 136, 26);
		bpTF.setEditable(false);
		bpTF.setColumns(10);
		contentPane.add(bpTF);

		JLabel lblBasicPay = new JLabel("Basic Pay:");
		lblBasicPay.setBounds(134, 267, 65, 16);
		contentPane.add(lblBasicPay);

		JLabel lblAllowance = new JLabel("Allowance:");
		lblAllowance.setBounds(134, 297, 65, 19);
		contentPane.add(lblAllowance);

		allowTF = new JTextField();
		allowTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});
		allowTF.setBounds(203, 293, 136, 26);
		allowTF.setEditable(false);
		contentPane.add(allowTF);
		allowTF.setColumns(10);

		JLabel lblOvertime = new JLabel("Overtime: ");
		lblOvertime.setBounds(134, 330, 65, 19);
		contentPane.add(lblOvertime);

		otTF = new JTextField();
		otTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});
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
		sssTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});
		sssTF.setEditable(false);
		sssTF.setColumns(10);
		sssTF.setBounds(456, 262, 136, 26);
		contentPane.add(sssTF);

		JLabel lblPhilhealth = new JLabel("PhilHealth");
		lblPhilhealth.setBounds(394, 298, 65, 17);
		contentPane.add(lblPhilhealth);

		phTF = new JTextField();
		phTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});
		phTF.setColumns(10);
		phTF.setEditable(false);
		phTF.setBounds(456, 293, 136, 26);
		contentPane.add(phTF);

		JLabel lblHdmf = new JLabel("HDMF:");
		lblHdmf.setBounds(394, 331, 55, 17);
		contentPane.add(lblHdmf);

		hdmfTF = new JTextField();
		hdmfTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});
		hdmfTF.setColumns(10);
		hdmfTF.setEditable(false);
		hdmfTF.setBounds(456, 326, 136, 26);
		contentPane.add(hdmfTF);

		JLabel lblNetpay = new JLabel("NetPay");
		lblNetpay.setBounds(153, 431, 65, 19);
		contentPane.add(lblNetpay);

		btnCompute = new JButton("Compute");
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

				double gross = bp + allow + ot;
				double xtax = bp * tax;
				double netpay = sss + ph + hdmf + xtax;

				double grosstotal = gross - netpay;

				npTF.setText("" + netpay);
				grossTF.setText("" + grosstotal);

			}
		});
		btnCompute.setFont(new Font("Calibri", Font.BOLD, 14));
		btnCompute.setBackground(Color.DARK_GRAY);
		btnCompute.setForeground(Color.white);
		btnCompute.setUI(new StyledButtonUI());
		btnCompute.setBounds(323, 481, 112, 26);
		contentPane.add(btnCompute);

		npTF = new JTextField();
		npTF.setColumns(10);
		npTF.setEditable(false);
		npTF.setBounds(217, 427, 136, 26);
		contentPane.add(npTF);

		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(394, 363, 54, 19);
		contentPane.add(lblTax);

		taxTF = new JTextField();
		taxTF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});
		taxTF.setEditable(false);
		taxTF.setColumns(10);
		taxTF.setBounds(456, 359, 46, 26);
		contentPane.add(taxTF);

		JLabel label = new JLabel("%");
		label.setBounds(512, 363, 20, 22);
		contentPane.add(label);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAdd.setEnabled(false);
				addTF();
			}
		});
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 14));
		btnAdd.setBackground(Color.DARK_GRAY);
		btnAdd.setForeground(Color.white);
		btnAdd.setUI(new StyledButtonUI());
		btnAdd.setBounds(10, 85, 95, 26);
		contentPane.add(btnAdd);

		grossTF = new JTextField();
		grossTF.setEditable(false);
		grossTF.setColumns(10);
		grossTF.setBounds(437, 427, 136, 26);
		contentPane.add(grossTF);

		JLabel lblGross = new JLabel("Gross");
		lblGross.setBounds(383, 433, 46, 14);
		contentPane.add(lblGross);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((empNameTF.getText().length() == 0 || (bpTF.getText().length() == 0)
						|| (allowTF.getText().length() == 0) || (otTF.getText().length() == 0)
						|| (sssTF.getText().length() == 0) || (phTF.getText().length() == 0)
						|| (hdmfTF.getText().length() == 0) || (taxTF.getText().length() == 0))) {

					JOptionPane.showMessageDialog(null, "Please fill up all the requirements", "Warning",
							JOptionPane.ERROR_MESSAGE);
				} else {
					addEmpAcc();
					addPayroll();
					closeTF();
					try {
						display();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Last();

				}

			}
		});
		btnInsert.setFont(new Font("Calibri", Font.BOLD, 14));
		btnInsert.setBackground(Color.DARK_GRAY);
		btnInsert.setForeground(Color.white);
		btnInsert.setUI(new StyledButtonUI());
		btnInsert.setVisible(false);
		btnInsert.setBounds(115, 85, 95, 26);
		contentPane.add(btnInsert);

		display();
		// contentPane.add(background);
		setVisible(true);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new AdminProfileUI();
	}

	public void editButton() {
		btnEdit.setEnabled(false);
		btnAdd.setEnabled(false);
		btnSave.setEnabled(true);
		btnGetEmp.setEnabled(false);
		btnLogout.setEnabled(false);
		btnCompute.setEnabled(false);
		btnCancel.setEnabled(true);
		btnFirst.setEnabled(false);
		btnNext.setEnabled(false);
		btnPrev.setEnabled(false);
		btnLast.setEnabled(false);
		// empNameTF.setEditable(true);
		// idTF.setEditable(true);
		bpTF.setEditable(true);
		allowTF.setEditable(true);
		otTF.setEditable(true);
		sssTF.setEditable(true);
		phTF.setEditable(true);
		hdmfTF.setEditable(true);
		taxTF.setEditable(true);
		npTF.setText("");
		grossTF.setText("");

	}

	public void clearTF() {
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		bpTF.setText("");
		allowTF.setText("");
		otTF.setText("");
		sssTF.setText("");
		phTF.setText("");
		hdmfTF.setText("");
		taxTF.setText("");
		npTF.setText("");

	}

	public void addTF() {
		Last();
		int lastid = Integer.parseInt(idTF.getText());
		int idgen = lastid + 1;

		btnEdit.setEnabled(false);
		btnAdd.setEnabled(false);
		btnSave.setEnabled(false);
		btnLogout.setEnabled(false);
		btnGetEmp.setEnabled(false);
		btnCompute.setEnabled(false);
		btnCancel.setEnabled(true);
		btnFirst.setEnabled(false);
		btnNext.setEnabled(false);
		btnPrev.setEnabled(false);
		btnLast.setEnabled(false);
		empNameTF.setEditable(true);
		bpTF.setEditable(true);
		allowTF.setEditable(true);
		otTF.setEditable(true);
		sssTF.setEditable(true);
		phTF.setEditable(true);
		hdmfTF.setEditable(true);
		taxTF.setEditable(true);
		btnInsert.setVisible(true);
		npTF.setText("");
		empNameTF.setText("");
		idTF.setText(String.valueOf(idgen));
		bpTF.setText("");
		allowTF.setText("");
		otTF.setText("");
		sssTF.setText("");
		phTF.setText("");
		hdmfTF.setText("");
		taxTF.setText("");
		npTF.setText("");

	}

	public void closeTF() {
		btnAdd.setEnabled(true);
		btnInsert.setVisible(false);
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
		btnGetEmp.setEnabled(true);
		btnLogout.setEnabled(true);
		btnCompute.setEnabled(true);
		btnLogout.setEnabled(true);
		btnFirst.setEnabled(true);
		btnNext.setEnabled(true);
		btnPrev.setEnabled(true);
		btnLast.setEnabled(true);
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
		npTF.setText("");

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

		String sql = "select * from employees order by id";
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

	public void addPayroll() {
		String dbURL = "jdbc:db2://localhost:50000/payroll";
		String username = "Charlie";
		String password = "1231234";

		String emp = empNameTF.getText();
		String id = idTF.getText();
		int bp = Integer.parseInt(bpTF.getText());

		int allow = Integer.parseInt(allowTF.getText());
		int ot = Integer.parseInt(otTF.getText());
		int sss = Integer.parseInt(sssTF.getText());
		int ph = Integer.parseInt(phTF.getText());
		int hdmf = Integer.parseInt(hdmfTF.getText());
		int tax = Integer.parseInt(taxTF.getText());
		try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

			String sql = "INSERT INTO employees (empname, id, basicpay, allowance, overtime, sss, phealth, hdmf, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, emp);
			statement.setString(2, id);
			statement.setInt(3, bp);
			statement.setInt(4, allow);
			statement.setInt(5, ot);
			statement.setInt(6, sss);
			statement.setInt(7, ph);
			statement.setInt(8, hdmf);
			statement.setInt(9, tax);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Employee Payroll added.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addEmpAcc() {
		String dbURL = "jdbc:db2://localhost:50000/payroll";
		String username = "Charlie";
		String password = "1231234";
		String user = idTF.getText();
		String pass = idTF.getText();
		String empname = empNameTF.getText();
		try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

			String sql = "INSERT INTO empaccount (username, password, empname) VALUES (?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user);
			statement.setString(2, pass);
			statement.setString(3, empname);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null,
						"Employee account added. The Default username and password is her/his id.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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

	public void Last() {
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
}
