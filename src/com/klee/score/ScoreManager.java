package com.klee.score;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.klee.bean.SqlBean;
import com.klee.bean.Student;
import com.klee.dao.HandleScore;
import com.klee.dao.HandleStudent;

public class ScoreManager implements ActionListener {

	private JLabel lableTitle = new JLabel();

	private JPanel p;
	private JTable table;
	private JScrollPane jscrollpane = new JScrollPane();
	private String strSelectedRow;
	private DefaultTableModel dtm;
	private JButton btnQuery, btnDelete, btnMod;
	private JComboBox<String> jcb;
	private JTextField textKind;
	SqlBean sqlbean;
	JOptionPane jOptionPane1, jOptionPane2;
	public String strKind;

	private int intRow;

	public ScoreManager(JPanel p) {
		this.p = p;

	}

	public ScoreManager(String string) {
	}

	public void init() {

		lableTitle.setBounds(250, 50, 300, 30);
		lableTitle.setFont(new java.awt.Font("������", Font.BOLD, 27));
		lableTitle.setText("ѧ���ɼ�����");
		p.add(lableTitle);

		jcb = new JComboBox<String>();
		jcb.setBounds(50, 150, 100, 30);
		jcb.addItem("��ѧ��");
		jcb.addItem("������");
		jcb.addItem("��ѧԺ");
		// p.add(jcb);

		textKind = new JTextField();
		textKind.setBounds(160, 150, 150, 30);
		// p.add(textKind);

		btnQuery = new JButton("��ѯ");
		btnQuery.setBounds(320, 150, 100, 30);
		btnQuery.addActionListener(this);
		// p.add(btnQuery);

		btnDelete = new JButton("ɾ��");
		btnDelete.setBounds(440, 150, 100, 30);
		btnDelete.addActionListener(this);
		// p.add(btnDelete);

		btnMod = new JButton("�޸� ");
		btnMod.setBounds(560, 150, 100, 30);
		btnMod.addActionListener(this);
		// p.add(btnMod);

		table = new JTable();

		jscrollpane.setBounds(0, 200, 700, 300);
		jscrollpane.setViewportView(table);// ������Ҫ
		table.setRowHeight(35);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				p.add(jcb);
				p.add(textKind);
				p.add(btnQuery);
				p.add(btnDelete);
				p.add(btnMod);
				p.add(jscrollpane);

			}
		});

		/**
		 * �־�����ʾ����
		 */
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "ѧ��", "����", "�Ա�", "ѧԺ", "רҵ", "�༶","�γ�","�ɼ�" }));

		dtm = (DefaultTableModel) this.table.getModel();
		dtm.setRowCount(0);
		table.setCellSelectionEnabled(true);
		table.setGridColor(Color.blue);
		table.setDragEnabled(true);
		table.setSelectionForeground(Color.red);
		table.setSelectionBackground(Color.green);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setShowVerticalLines(true);
		String sql = "SELECT a.score,a.courceName,b.* FROM tb_student b, tb_score a WHERE a.stuNumber=b.stuNumber";
		sqlbean = new SqlBean(sql);

		worker1.execute();// ���±��
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {

			getSelectedRow();
			if (intRow != -1) {
				delstu();
				dtm.fireTableDataChanged();
			}
		} else if (e.getSource() == btnQuery) {
			
			if (jcb.getSelectedItem().toString().equals("��ѧ��")) {
				myQuery("SELECT a.score,a.courceName,b.* FROM tb_student b, tb_score a WHERE a.stuNumber=b.stuNumber AND a.stuNumber like '%"
						+ this.textKind.getText().toString().trim()+ "%'");
			} else if (jcb.getSelectedItem().toString().equals("������")) {
				myQuery("SELECT a.score,a.courceName,b.* FROM tb_student b, tb_score a WHERE a.stuNumber=b.stuNumber AND b.stuName like '%"
						+ this.textKind.getText().toString().trim()+ "%'");
			} else if (jcb.getSelectedItem().toString().equals("��ѧԺ")) {
				
				myQuery("SELECT a.score,a.courceName,b.* FROM tb_student b, tb_score a WHERE a.stuNumber=b.stuNumber AND b.stuDepart like '%"
						+ this.textKind.getText().toString().trim() + "%'");
			}
		}else if(e.getSource()==btnMod){
			getSelectedRow();
			
			Student s=new Student();
			
			s.setStuNumber(dtm.getValueAt(intRow, 0).toString().trim());
			Student.setStuCource(dtm.getValueAt(intRow, 6).toString().trim());
			Student.setStuScore(Float.parseFloat(dtm.getValueAt(intRow, 7)
					.toString().trim()));
			new ScoreModify("ѧ���ɼ��޸�").init();
			
		}

	}

	// ��ȡѡ������
	public void getSelectedRow() {
		intRow = table.getSelectedRow();
		if (intRow == -1)
			JOptionPane.showMessageDialog(this.p, "����ѡ��һ�����ݣ�", "�� ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		try {
			strSelectedRow = dtm.getValueAt(intRow, 0).toString().trim();
			System.err.println(strSelectedRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ɾ��
	public void delstu() {
		try {
			HandleScore hs=new HandleScore();
			if (hs.delete(strSelectedRow)) {
				JOptionPane.showMessageDialog(this.p, "ѧ���ɼ�ɾ���ɹ���", "�� ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				dtm.removeRow(intRow);
			} else {
				JOptionPane.showMessageDialog(this.p, "ѧ���ɼ�ɾ��ʧ�ܣ�", "�� ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void myQuery(String sql){
		
		HandleStudent hs = new HandleStudent();
		ResultSet rs=hs.query(sql);

		try {
			dtm.setRowCount(0);
			while (rs.next()) {

				Vector<String> rowDate = new Vector<String>();
				rowDate.add(rs.getString("b.stuNumber").trim());
				rowDate.add(rs.getString("b.stuName").trim());
				rowDate.add(rs.getString("b.stuSex").trim());
				rowDate.add(rs.getString("b.stuDepart").trim());
				rowDate.add(rs.getString("b.stuSpec").trim());
				rowDate.add(rs.getString("b.stuClass").trim());
				rowDate.add(rs.getString("a.courceName").trim());
				rowDate.add(rs.getString("a.score").trim());
				dtm.addRow(rowDate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
		

	SwingWorker<?, ?> worker1 = new SwingWorker<Object, Object>() {
		protected Object doInBackground() throws Exception {

			String sql = sqlbean.getSql();

			HandleStudent hs = new HandleStudent();
			ResultSet rs = hs.query(sql);

			try {
				while (rs.next()) {
					Vector<String> rowDate = new Vector<String>();
					rowDate.add(rs.getString("b.stuNumber").trim());
					rowDate.add(rs.getString("b.stuName").trim());
					rowDate.add(rs.getString("b.stuSex").trim());
					rowDate.add(rs.getString("b.stuDepart").trim());
					rowDate.add(rs.getString("b.stuSpec").trim());
					rowDate.add(rs.getString("b.stuClass").trim());
					rowDate.add(rs.getString("a.courceName").trim());
					rowDate.add(rs.getString("a.score").trim());
					
					dtm.addRow(rowDate);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return null;
		}
	};

}
