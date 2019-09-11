package com.klee.students;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.klee.bean.Student;
import com.klee.dao.DBConnection;
import com.klee.dao.HandleScore;
import com.klee.dao.HandleStudent;

public class StuAdd implements ActionListener {
	public JPanel p;
	JLabel lableTitle = new JLabel("ѧ����Ϣ¼��");
	JLabel lableName = new JLabel("����:");
	JLabel lableSex = new JLabel("�Ա�:");
	JLabel lableDepart = new JLabel("ѧԺ:");
	JLabel lableSpect = new JLabel("רҵ:");
	JLabel lableClass = new JLabel("�༶:");
	JTextField textName = new JTextField();
	JTextField textNum = new JTextField();
	JRadioButton rdbtnMale = new JRadioButton("��");
	JRadioButton rdbtnFemale = new JRadioButton("Ů");
	JComboBox<String> jcbDepart = new JComboBox<String>();
	JComboBox<String> jcbSpect = new JComboBox<String>();
	JComboBox<String> jcbClass = new JComboBox<String>();
	ButtonGroup group = new ButtonGroup();
	JButton btn_add = new JButton("���");
	JOptionPane jOptionPane1 = new JOptionPane();
	JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
	String number, name, sdepart, sspec, sclass, sex;

	public StuAdd() {

	}

	public StuAdd(JPanel p) {
		this.p = p;

		btn_add.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_add) {
		
			jButton1_actionPerformed();

		}

	}

	public void jcb_ItemListener() {
		// ����ѧԺ����
		jcbDepart.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// ���ѡ����һ�� String text=(String) comboBox_4.getSelectedItem();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					jcbSpect.setEnabled(true);
					jcbSpec_Change();

				}
			}
		});

		// ����רҵѡ��
		jcbSpect.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					class_Change();
					jcbClass.setEnabled(true);
				}
			}
		});

	}

	public void init() {

		lableTitle.setBounds(280, 50, 300, 30);
		lableTitle.setFont(new java.awt.Font("������", Font.BOLD, 27));
		p.add(lableTitle);

		JLabel lableNum = new JLabel("ѧ��:");
		lableNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableNum.setBounds(215, 130, 100, 30);
		p.add(lableNum);

		lableName.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableName.setBounds(215, 180, 100, 30);
		p.add(lableName);

		lableSex.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableSex.setBounds(215, 230, 100, 30);
		p.add(lableSex);

		lableDepart.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableDepart.setBounds(215, 280, 100, 30);
		p.add(lableDepart);

		lableSpect.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableSpect.setBounds(215, 330, 100, 30);
		p.add(lableSpect);

		lableClass.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableClass.setBounds(215, 380, 100, 30);
		p.add(lableClass);

		textNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		textNum.setBounds(265, 130, 200, 30);
		p.add(textNum);

		textName.setBounds(265, 180, 200, 30);
		textName.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		p.add(textName);

		rdbtnMale.setBounds(300, 230, 50, 30);
		p.add(rdbtnMale);

		rdbtnFemale.setBounds(400, 230, 50, 30);
		p.add(rdbtnFemale);

		group.add(rdbtnMale);
		group.add(rdbtnFemale);

		jcbDepart.setBounds(265, 280, 200, 30);
		p.add(jcbDepart);
		this.jcbDepart.addItem("��ѡ��ѧԺ");

		worker1.execute();

		jcbSpect.setBounds(265, 330, 200, 30);
		p.add(jcbSpect);

		jcbClass.setBounds(265, 380, 200, 30);
		p.add(jcbClass);
		jcbSpect.setEnabled(false);
		jcbClass.setEnabled(false);

		btn_add.setBounds(265, 430, 200, 30);
		p.add(btn_add);

	}

	// ѡ��רҵ
	public void jcbSpec_Change() {
		jcbSpect.removeAllItems();
		jcbSpect.addItem("��ѡ��רҵ");
		try {

			HandleScore hd = new HandleScore();

			ResultSet rs = hd
					.queryDepart("select * from tb_spec where departName='"
							+ String.valueOf(jcbDepart.getSelectedItem()) + "'");

			while (rs.next()) {
				String spec = rs.getString("specName");
				jcbSpect.addItem(spec);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ѡ��༶
	public void class_Change() {
		jcbClass.removeAllItems();
		jcbClass.addItem("��ѡ��༶");
		try {

			HandleScore hd = new HandleScore();
			ResultSet rs = hd
					.queryDepart("select * from tb_class where specName='"
							+ String.valueOf(jcbSpect.getSelectedItem()) + "' ");
			while (rs.next()) {
				String myClass = rs.getString("className");
				jcbClass.addItem(myClass);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void myInsert() {

		Student s = new Student();
		HandleStudent hs = new HandleStudent();
		String num = null;
		ArrayList<Student> result = hs.queryStu(this.textNum.getText().trim());
		for (Student s1 : result) {

			num = s1.getStuNumber();
			System.out.println(num);
		}
		if (num != "") {
			JOptionPane.showMessageDialog(this.p, "ѧ��ѧ���Ѵ��ڣ����������룡", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {

			s.setStuNumber(this.textNum.getText().toString().trim());
			s.setStuName(this.textName.getText().toString().trim());
			s.setStuDepart(this.jcbDepart.getSelectedItem().toString().trim());
			s.setStuSpec(this.jcbSpect.getSelectedItem().toString().trim());
			s.setStuClass(this.jcbClass.getSelectedItem().toString().trim());
			if (this.rdbtnMale.isSelected()) {
				s.setStuSex("��");
			} else {
				s.setStuSex("Ů");
			}

			if (hs.insertStu(s)) {
				JOptionPane.showMessageDialog(null, "�ɹ����һ����¼��");
			} else {
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
			}
		}

	}

	// ѧ����Ϣ¼��
	public void jButton1_actionPerformed() {

		// �쳣�ж�
		if (textNum.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this.p, "������ѧ��ѧ�ţ�", "�� ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (textName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this.p, "������ѧ��������", "�� ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (jcbDepart.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this.p, "��ѡ��ѧ������ѧԺ��", "�� ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (jcbSpect.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this.p, "��ѡ��ѧ������רҵ��", "�� ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (jcbClass.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this.p, "��ѡ��ѧ�����ڰ༶��", "�� ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {

				myInsert();
			} catch (Exception ce) {
				System.out.println(ce.getMessage());
			}

		}

	}

	SwingWorker<?, ?> worker1 = new SwingWorker<Object, Object>() {
		protected Object doInBackground() throws Exception {

			jcb_ItemListener();

			HandleScore hd = new HandleScore();
			ResultSet rs = hd.queryDepart("select departName from tb_depart");

			while (rs.next()) {
				jcbDepart.addItem(rs.getString(1));
			}

			return null;
		}
	};

}
