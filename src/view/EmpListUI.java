package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpListUI extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model = new DefaultTableModel();

	private JTable jtbl;

	public EmpListUI() {
		setTitle("Employee List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		centerFrame();

		model.addColumn("Employee Name");
		model.addColumn("Employee ID");

		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/payroll", "Charlie", "1231234");
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM employees order by empname");
			ResultSet Rs = pstm.executeQuery();
			while (Rs.next()) {
				model.addRow(new Object[] { Rs.getString("empname"), Rs.getString("id") });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		jtbl = new JTable(model);
		jtbl.setBounds(100, 100, 450, 300);
		JScrollPane pg = new JScrollPane(jtbl);
		pg.setBounds(5, 5, 601, 251);
		contentPane.add(pg);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AdminProfileUI();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnBack.setBounds(452, 319, 128, 34);
		contentPane.add(btnBack);

		setVisible(true);
	}

	public static void main(String[] args) {
		new EmpListUI();

	}

	private void centerFrame() {

		Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;
		setLocation(dx, dy);
	}
}
