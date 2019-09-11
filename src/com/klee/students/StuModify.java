package com.klee.students;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.klee.bean.Student;
import com.klee.dao.HandleScore;
import com.klee.dao.HandleStudent;

/**
 * 
 * @author ���
 *
 */
public class StuModify extends JFrame implements ActionListener {

	private JPanel p;
	JLabel lableTitle = new JLabel("ѧ����Ϣ�޸�");
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
	JButton btn_add = new JButton("�޸�");

	public StuModify(String title) {
		super(title);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_add) {
			modify();

		}

	}

	public void init() {
		Container c = this.getContentPane();
		p = new JPanel();
		p.setLayout(null);
		p.setPreferredSize(new Dimension(300, 400));
		c.add(p, BorderLayout.CENTER);
		this.setLocation(500, 150);
		this.setSize(400, 500);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

		lableTitle.setBounds(100, 40, 300, 30);
		lableTitle.setFont(new java.awt.Font("Dialog", Font.TRUETYPE_FONT, 30));
		p.add(lableTitle);

		JLabel lableNum = new JLabel("ѧ��:");
		lableNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableNum.setBounds(70, 120, 100, 30);
		p.add(lableNum);

		lableName.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableName.setBounds(70, 170, 100, 30);
		p.add(lableName);

		lableSex.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableSex.setBounds(70, 220, 100, 30);
		p.add(lableSex);

		lableDepart.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableDepart.setBounds(70, 270, 100, 30);
		p.add(lableDepart);

		lableSpect.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableSpect.setBounds(70, 320, 100, 30);
		p.add(lableSpect);

		lableClass.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableClass.setBounds(70, 370, 100, 30);
		p.add(lableClass);

		textNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		textNum.setBounds(140, 120, 200, 30);
		p.add(textNum);
		textNum.setEditable(false);

		textName.setBounds(140, 170, 200, 30);
		textName.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		p.add(textName);

		rdbtnMale.setBounds(180, 220, 50, 30);
		p.add(rdbtnMale);

		rdbtnFemale.setBounds(260, 220, 50, 30);
		p.add(rdbtnFemale);

		group.add(rdbtnMale);
		group.add(rdbtnFemale);

		jcbDepart.setBounds(140, 270, 200, 30);
		p.add(jcbDepart);
		this.jcbDepart.addItem("��ѡ��ѧԺ");

		worker1.execute();

		jcbSpect.setBounds(140, 320, 200, 30);
		p.add(jcbSpect);

		jcbClass.setBounds(140, 370, 200, 30);
		p.add(jcbClass);
		jcbSpect.setEnabled(false);
		jcbClass.setEnabled(false);

		btn_add.setBounds(140, 420, 200, 30);
		p.add(btn_add);
		btn_add.addActionListener(this);
		showInfo();

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

	private void modify() {
		Student s = new Student();
		s.setStuName(this.textName.getText().toString().trim());
		if (this.rdbtnMale.isSelected()) {
			s.setStuSex("��");
		} else {
			s.setStuSex("Ů");
		}
		s.setStuDepart(String.valueOf(jcbDepart.getSelectedItem()));
		s.setStuSpec(String.valueOf(jcbSpect.getSelectedItem()));
		s.setStuClass(String.valueOf(jcbClass.getSelectedItem()));

		HandleStudent hs = new HandleStudent();
		if (hs.modify(s)) {
			JOptionPane.showMessageDialog(this.p, "�޸ĳɹ���", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			JOptionPane.showMessageDialog(this.p, "�޸�ʧ�ܣ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		}
	}

	private void showInfo() {

		Student s = new Student();
		this.textNum.setText(s.getStuNumber());
		this.textName.setText(s.getStuName());
		if (s.getStuSex().equals("��")) {
			this.rdbtnMale.setSelected(true);
		} else {
			this.rdbtnFemale.setSelected(true);
		}

		this.jcbDepart.setSelectedItem(s.getStuDepart());
		this.jcbSpect.setSelectedItem(s.getStuSpec());
		this.jcbClass.setSelectedItem(s.getStuClass());
	}

}
