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

public class AdminProfileUI extends JFrame {

	private JPanel contentPane;
	private JTextField empNameTF;
	private JTextField idTF;
	private JLabel background;
	private String[] salaryList = { "Daily", "Hours", "Month" };
	private JTextField rateTF;
	private JTextField nofdaysTF;
	private JTextField otTF;
	private JTextField holidayTF;
	private JTextField legalHolTF;
	private JTextField sssTF;
	private JTextField phTF;
	private JTextField withHoldTF;
	private JTextField pagibigTF;
	private JTextField pagibigloanTF;
	private JTextField sssloanTF;
	private JTextField deducTF;
	private JTextField deduc2TF;
	private JTextField totaldeducTF;
	private JTextField npayTF;
	private JTextField totalOTTF;
	private JTextField allowTF;
	private JTextField otherTF;
	private JTextField grossTF;
	private JTextField sss2TF;
	private JTextField ph2TF;
	private JTextField pagibig2TF;
	private JButton btnSave, btnCancel, btnEdit;
	private Connection con;
	private Statement st;
	private ResultSet rs;

	public AdminProfileUI() throws ClassNotFoundException, SQLException {
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
		empNameTF.setBounds(92, 118, 136, 20);
		contentPane.add(empNameTF);
		empNameTF.setColumns(10);

		idTF = new JTextField();
		idTF.setEditable(false);
		idTF.setBounds(92, 146, 136, 20);
		contentPane.add(idTF);
		idTF.setColumns(10);

		JLabel lblSalaryMethod = new JLabel("Salary Method");
		lblSalaryMethod.setBounds(276, 119, 95, 19);
		contentPane.add(lblSalaryMethod);

		JComboBox comboBox = new JComboBox(salaryList);
		comboBox.setBounds(381, 118, 95, 20);
		contentPane.add(comboBox);

		JLabel lblRegularOvertime = new JLabel("Regular & Overtime pay:");
		lblRegularOvertime.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRegularOvertime.setBounds(23, 194, 143, 19);
		contentPane.add(lblRegularOvertime);

		JLabel lblRate = new JLabel("Rate:");
		lblRate.setBounds(23, 232, 46, 14);
		contentPane.add(lblRate);

		rateTF = new JTextField();
		rateTF.setEditable(false);
		rateTF.setBounds(142, 224, 86, 20);
		contentPane.add(rateTF);
		rateTF.setColumns(10);

		JLabel lblNoOfDays = new JLabel("No. of Days:");
		lblNoOfDays.setBounds(23, 257, 82, 19);
		contentPane.add(lblNoOfDays);

		nofdaysTF = new JTextField();
		nofdaysTF.setEditable(false);
		nofdaysTF.setBounds(142, 255, 86, 20);
		contentPane.add(nofdaysTF);
		nofdaysTF.setColumns(10);

		JLabel lblRegularOt = new JLabel("Regular OT:");
		lblRegularOt.setBounds(23, 287, 95, 19);
		contentPane.add(lblRegularOt);

		otTF = new JTextField();
		otTF.setEditable(false);
		otTF.setBounds(142, 286, 86, 20);
		contentPane.add(otTF);
		otTF.setColumns(10);

		JLabel lblSpecialHoliday = new JLabel("Special Holiday");
		lblSpecialHoliday.setBounds(23, 317, 82, 19);
		contentPane.add(lblSpecialHoliday);

		holidayTF = new JTextField();
		holidayTF.setEditable(false);
		holidayTF.setBounds(142, 317, 86, 20);
		contentPane.add(holidayTF);
		holidayTF.setColumns(10);

		JLabel lblLegalHoliday = new JLabel("Legal Holiday:");
		lblLegalHoliday.setBounds(23, 347, 82, 19);
		contentPane.add(lblLegalHoliday);

		legalHolTF = new JTextField();
		legalHolTF.setEditable(false);
		legalHolTF.setBounds(142, 348, 86, 20);
		contentPane.add(legalHolTF);
		legalHolTF.setColumns(10);

		JLabel lblEmployeeContribution = new JLabel("Employee Contribution");
		lblEmployeeContribution.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmployeeContribution.setBounds(276, 194, 136, 19);
		contentPane.add(lblEmployeeContribution);

		JLabel lblSss = new JLabel("SSS:");
		lblSss.setBounds(276, 227, 65, 19);
		contentPane.add(lblSss);

		sssTF = new JTextField();
		sssTF.setEditable(false);
		sssTF.setBounds(390, 224, 86, 20);
		contentPane.add(sssTF);
		sssTF.setColumns(10);

		JLabel lblPhilhealth = new JLabel("PhilHealth:");
		lblPhilhealth.setBounds(276, 259, 65, 17);
		contentPane.add(lblPhilhealth);

		phTF = new JTextField();
		phTF.setEditable(false);
		phTF.setBounds(390, 256, 86, 20);
		contentPane.add(phTF);
		phTF.setColumns(10);

		JLabel lblWithholdingTax = new JLabel("Withholding Tax:");
		lblWithholdingTax.setBounds(276, 289, 95, 17);
		contentPane.add(lblWithholdingTax);

		withHoldTF = new JTextField();
		withHoldTF.setEditable(false);
		withHoldTF.setBounds(390, 286, 86, 20);
		contentPane.add(withHoldTF);
		withHoldTF.setColumns(10);

		JLabel lblPagibigFund = new JLabel("Pagibig Fund:");
		lblPagibigFund.setBounds(276, 319, 95, 17);
		contentPane.add(lblPagibigFund);

		pagibigTF = new JTextField();
		pagibigTF.setEditable(false);
		pagibigTF.setBounds(390, 316, 86, 20);
		contentPane.add(pagibigTF);
		pagibigTF.setColumns(10);

		JLabel lblPagibigLoan = new JLabel("Pagibig Loan:");
		lblPagibigLoan.setBounds(276, 349, 95, 17);
		contentPane.add(lblPagibigLoan);

		pagibigloanTF = new JTextField();
		pagibigloanTF.setEditable(false);
		pagibigloanTF.setBounds(390, 346, 86, 20);
		contentPane.add(pagibigloanTF);
		pagibigloanTF.setColumns(10);

		JLabel lblSssLoan = new JLabel("SSS Loan:");
		lblSssLoan.setBounds(276, 379, 95, 17);
		contentPane.add(lblSssLoan);

		sssloanTF = new JTextField();
		sssloanTF.setEditable(false);
		sssloanTF.setBounds(390, 376, 86, 20);
		contentPane.add(sssloanTF);
		sssloanTF.setColumns(10);

		JLabel lblDeduction = new JLabel("Deduction:");
		lblDeduction.setBounds(276, 407, 95, 19);
		contentPane.add(lblDeduction);

		deducTF = new JTextField();
		deducTF.setEditable(false);
		deducTF.setBounds(390, 406, 86, 20);
		contentPane.add(deducTF);
		deducTF.setColumns(10);

		JLabel lblOtherDeduction = new JLabel("Other Deduction:");
		lblOtherDeduction.setBounds(276, 437, 95, 19);
		contentPane.add(lblOtherDeduction);

		deduc2TF = new JTextField();
		deduc2TF.setEditable(false);
		deduc2TF.setBounds(390, 436, 86, 20);
		contentPane.add(deduc2TF);
		deduc2TF.setColumns(10);

		JLabel lblTotalDeduction = new JLabel("Total Deduction:");
		lblTotalDeduction.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTotalDeduction.setBounds(276, 467, 95, 19);
		contentPane.add(lblTotalDeduction);

		totaldeducTF = new JTextField();
		totaldeducTF.setEditable(false);
		totaldeducTF.setBounds(390, 467, 86, 20);
		contentPane.add(totaldeducTF);
		totaldeducTF.setColumns(10);

		JLabel lblNetPay = new JLabel("Net Pay:");
		lblNetPay.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNetPay.setBounds(276, 497, 95, 19);
		contentPane.add(lblNetPay);

		npayTF = new JTextField();
		npayTF.setEditable(false);
		npayTF.setBounds(390, 498, 86, 20);
		contentPane.add(npayTF);
		npayTF.setColumns(10);

		JLabel lblTotalOtPay = new JLabel("Total OT Pay:");
		lblTotalOtPay.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTotalOtPay.setBounds(23, 406, 106, 19);
		contentPane.add(lblTotalOtPay);

		totalOTTF = new JTextField();
		totalOTTF.setEditable(false);
		totalOTTF.setBounds(142, 405, 86, 20);
		contentPane.add(totalOTTF);
		totalOTTF.setColumns(10);

		JLabel lblAllowance = new JLabel("Allowance:");
		lblAllowance.setBounds(23, 438, 106, 17);
		contentPane.add(lblAllowance);

		allowTF = new JTextField();
		allowTF.setEditable(false);
		allowTF.setBounds(142, 435, 86, 20);
		contentPane.add(allowTF);
		allowTF.setColumns(10);

		JLabel lblOtherPay = new JLabel("Other Pay:");
		lblOtherPay.setBounds(23, 468, 95, 17);
		contentPane.add(lblOtherPay);

		otherTF = new JTextField();
		otherTF.setEditable(false);
		otherTF.setBounds(142, 465, 86, 20);
		contentPane.add(otherTF);
		otherTF.setColumns(10);

		JLabel lblGrossPay = new JLabel("Gross Pay:");
		lblGrossPay.setFont(new Font("Dialog", Font.BOLD, 12));
		lblGrossPay.setBounds(23, 496, 106, 19);
		contentPane.add(lblGrossPay);

		grossTF = new JTextField();
		grossTF.setEditable(false);
		grossTF.setBounds(142, 496, 86, 20);
		contentPane.add(grossTF);
		grossTF.setColumns(10);

		JLabel lblEmployerContribution = new JLabel("Employer Contribution");
		lblEmployerContribution.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmployerContribution.setBounds(562, 190, 162, 26);
		contentPane.add(lblEmployerContribution);

		JLabel lblSss_1 = new JLabel("SSS:");
		lblSss_1.setBounds(541, 227, 82, 19);
		contentPane.add(lblSss_1);

		sss2TF = new JTextField();
		sss2TF.setEditable(false);
		sss2TF.setBounds(638, 224, 86, 20);
		contentPane.add(sss2TF);
		sss2TF.setColumns(10);

		JLabel lblPhilhealth_1 = new JLabel("PhilHealth");
		lblPhilhealth_1.setBounds(541, 259, 82, 17);
		contentPane.add(lblPhilhealth_1);

		ph2TF = new JTextField();
		ph2TF.setEditable(false);
		ph2TF.setBounds(638, 256, 86, 20);
		contentPane.add(ph2TF);
		ph2TF.setColumns(10);

		JLabel lblPagibigFund_1 = new JLabel("Pagibig Fund");
		lblPagibigFund_1.setBounds(541, 289, 82, 17);
		contentPane.add(lblPagibigFund_1);

		pagibig2TF = new JTextField();
		pagibig2TF.setEditable(false);
		pagibig2TF.setBounds(638, 286, 86, 20);
		contentPane.add(pagibig2TF);
		pagibig2TF.setColumns(10);

		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (rs.first()) {

						empNameTF.setText(rs.getString("empname"));
						idTF.setText(rs.getString("id"));
						rateTF.setText(rs.getString("rate"));
						nofdaysTF.setText(rs.getString("nofdays"));
						otTF.setText(rs.getString("regularot"));
						holidayTF.setText(rs.getString("sholiday"));
						legalHolTF.setText(rs.getString("lholiday"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						withHoldTF.setText(rs.getString("wholdtax"));
						pagibigTF.setText(rs.getString("pagibigf"));
						pagibigloanTF.setText(rs.getString("pagibigl"));
						sssloanTF.setText(rs.getString("sssl"));
						deducTF.setText(rs.getString("deduction"));
						deduc2TF.setText(rs.getString("otherdeduction"));
						totaldeducTF.setText(rs.getString("tdeduc"));
						npayTF.setText(rs.getString("netpay"));
						totalOTTF.setText(rs.getString("totalotpay"));
						allowTF.setText(rs.getString("allowance"));
						otherTF.setText(rs.getString("otherpay"));
						grossTF.setText(rs.getString("grosspay"));
						sss2TF.setText(rs.getString("sss1"));
						ph2TF.setText(rs.getString("phealth1"));
						pagibig2TF.setText(rs.getString("pagibigf1"));
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
						rateTF.setText(rs.getString("rate"));
						nofdaysTF.setText(rs.getString("nofdays"));
						otTF.setText(rs.getString("regularot"));
						holidayTF.setText(rs.getString("sholiday"));
						legalHolTF.setText(rs.getString("lholiday"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						withHoldTF.setText(rs.getString("wholdtax"));
						pagibigTF.setText(rs.getString("pagibigf"));
						pagibigloanTF.setText(rs.getString("pagibigl"));
						sssloanTF.setText(rs.getString("sssl"));
						deducTF.setText(rs.getString("deduction"));
						deduc2TF.setText(rs.getString("otherdeduction"));
						totaldeducTF.setText(rs.getString("tdeduc"));
						npayTF.setText(rs.getString("netpay"));
						totalOTTF.setText(rs.getString("totalotpay"));
						allowTF.setText(rs.getString("allowance"));
						otherTF.setText(rs.getString("otherpay"));
						grossTF.setText(rs.getString("grosspay"));
						sss2TF.setText(rs.getString("sss1"));
						ph2TF.setText(rs.getString("phealth1"));
						pagibig2TF.setText(rs.getString("pagibigf1"));

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
						rateTF.setText(rs.getString("rate"));
						nofdaysTF.setText(rs.getString("nofdays"));
						otTF.setText(rs.getString("regularot"));
						holidayTF.setText(rs.getString("sholiday"));
						legalHolTF.setText(rs.getString("lholiday"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						withHoldTF.setText(rs.getString("wholdtax"));
						pagibigTF.setText(rs.getString("pagibigf"));
						pagibigloanTF.setText(rs.getString("pagibigl"));
						sssloanTF.setText(rs.getString("sssl"));
						deducTF.setText(rs.getString("deduction"));
						deduc2TF.setText(rs.getString("otherdeduction"));
						totaldeducTF.setText(rs.getString("tdeduc"));
						npayTF.setText(rs.getString("netpay"));
						totalOTTF.setText(rs.getString("totalotpay"));
						allowTF.setText(rs.getString("allowance"));
						otherTF.setText(rs.getString("otherpay"));
						grossTF.setText(rs.getString("grosspay"));
						sss2TF.setText(rs.getString("sss1"));
						ph2TF.setText(rs.getString("phealth1"));
						pagibig2TF.setText(rs.getString("pagibigf1"));

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
						rateTF.setText(rs.getString("rate"));
						nofdaysTF.setText(rs.getString("nofdays"));
						otTF.setText(rs.getString("regularot"));
						holidayTF.setText(rs.getString("sholiday"));
						legalHolTF.setText(rs.getString("lholiday"));
						sssTF.setText(rs.getString("sss"));
						phTF.setText(rs.getString("phealth"));
						withHoldTF.setText(rs.getString("wholdtax"));
						pagibigTF.setText(rs.getString("pagibigf"));
						pagibigloanTF.setText(rs.getString("pagibigl"));
						sssloanTF.setText(rs.getString("sssl"));
						deducTF.setText(rs.getString("deduction"));
						deduc2TF.setText(rs.getString("otherdeduction"));
						totaldeducTF.setText(rs.getString("tdeduc"));
						npayTF.setText(rs.getString("netpay"));
						totalOTTF.setText(rs.getString("totalotpay"));
						allowTF.setText(rs.getString("allowance"));
						otherTF.setText(rs.getString("otherpay"));
						grossTF.setText(rs.getString("grosspay"));
						sss2TF.setText(rs.getString("sss1"));
						ph2TF.setText(rs.getString("phealth1"));
						pagibig2TF.setText(rs.getString("pagibigf1"));
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
			}
		});
		btnGetEmp.setBounds(481, 15, 281, 59);
		contentPane.add(btnGetEmp);

		display();
		// contentPane.add(background);
		setVisible(true);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new AdminProfileUI();
	}

	public void editButton() {
		btnEdit.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
		empNameTF.setEditable(true);
		idTF.setEditable(true);
		rateTF.setEditable(true);
		nofdaysTF.setEditable(true);
		otTF.setEditable(true);
		holidayTF.setEditable(true);
		legalHolTF.setEditable(true);
		totalOTTF.setEditable(true);
		allowTF.setEditable(true);
		otherTF.setEditable(true);
		grossTF.setEditable(true);
		sssTF.setEditable(true);
		phTF.setEditable(true);
		withHoldTF.setEditable(true);
		pagibigTF.setEditable(true);
		pagibigloanTF.setEditable(true);
		sssloanTF.setEditable(true);
		deducTF.setEditable(true);
		deduc2TF.setEditable(true);
		totaldeducTF.setEditable(true);
		npayTF.setEditable(true);
		sss2TF.setEditable(true);
		ph2TF.setEditable(true);
		pagibig2TF.setEditable(true);

	}

	public void closeTF() {
		btnEdit.setEnabled(true);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		empNameTF.setEditable(false);
		idTF.setEditable(false);
		rateTF.setEditable(false);
		nofdaysTF.setEditable(false);
		otTF.setEditable(false);
		holidayTF.setEditable(false);
		legalHolTF.setEditable(false);
		totalOTTF.setEditable(false);
		allowTF.setEditable(false);
		otherTF.setEditable(false);
		grossTF.setEditable(false);
		sssTF.setEditable(false);
		phTF.setEditable(false);
		withHoldTF.setEditable(false);
		pagibigTF.setEditable(false);
		pagibigloanTF.setEditable(false);
		sssloanTF.setEditable(false);
		deducTF.setEditable(false);
		deduc2TF.setEditable(false);
		totaldeducTF.setEditable(false);
		npayTF.setEditable(false);
		sss2TF.setEditable(false);
		ph2TF.setEditable(false);
		pagibig2TF.setEditable(false);

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
				rateTF.setText(rs.getString("rate"));
				nofdaysTF.setText(rs.getString("nofdays"));
				otTF.setText(rs.getString("regularot"));
				holidayTF.setText(rs.getString("sholiday"));
				legalHolTF.setText(rs.getString("lholiday"));
				sssTF.setText(rs.getString("sss"));
				phTF.setText(rs.getString("phealth"));
				withHoldTF.setText(rs.getString("wholdtax"));
				pagibigTF.setText(rs.getString("pagibigf"));
				pagibigloanTF.setText(rs.getString("pagibigl"));
				sssloanTF.setText(rs.getString("sssl"));
				deducTF.setText(rs.getString("deduction"));
				deduc2TF.setText(rs.getString("otherdeduction"));
				totaldeducTF.setText(rs.getString("tdeduc"));
				npayTF.setText(rs.getString("netpay"));
				totalOTTF.setText(rs.getString("totalotpay"));
				allowTF.setText(rs.getString("allowance"));
				otherTF.setText(rs.getString("otherpay"));
				grossTF.setText(rs.getString("grosspay"));
				sss2TF.setText(rs.getString("sss1"));
				ph2TF.setText(rs.getString("phealth1"));
				pagibig2TF.setText(rs.getString("pagibigf1"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
