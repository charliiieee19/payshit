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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;

import view.AdminProfileUI.StyledButtonUI;

public class EmpListUI extends JFrame {

	private JPanel contentPane;
	private JLabel background;
	DefaultTableModel model = new DefaultTableModel();

	private JTable jtbl;

	public EmpListUI() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\dollar.png"));
		background = new JLabel();
		background.setIcon(new ImageIcon("C:\\Users\\Charlie\\eclipse-workspace\\PayIdiot\\src\\Images\\admin.jpg"));
		background.setBounds(0, 0, 580, 349);
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
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM employees order by id");
			ResultSet Rs = pstm.executeQuery();
			while (Rs.next()) {
				model.addRow(new Object[] { Rs.getString("empname"), Rs.getString("id") });
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		jtbl = new JTable(model);
		jtbl.setBounds(100, 100, 450, 300);
		jtbl.setEnabled(false);
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
		btnBack.setFont(new Font("Calibri", Font.BOLD, 14));
		btnBack.setBackground(Color.DARK_GRAY);
		btnBack.setForeground(Color.white);
		btnBack.setUI(new StyledButtonUI());
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
}
