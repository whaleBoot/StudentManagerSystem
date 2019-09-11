package com.klee.score;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.klee.bean.Student;
import com.klee.dao.HandleScore;
import com.klee.dao.HandleStudent;

/**
 * 
 * @author 李可
 *
 */
public class ScoreModify extends JFrame implements ActionListener {

	private JPanel p;
	private JLabel lableNum, lableCource, lableScore, lableTitle;
	private JTextField textNum, textScore;
	private JComboBox<String> jcbCource;
	private JButton btn;
	String number, name, sdepart, sspec, sclass, sex;

	public ScoreModify(String title) {
		super(title);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
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

		lableTitle = new JLabel();
		lableTitle.setBounds(120, 50, 300, 30);
		lableTitle.setFont(new java.awt.Font("新宋体", Font.BOLD, 27));
		lableTitle.setText("学生成绩修改");
		p.add(lableTitle);

		lableNum = new JLabel();
		lableNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableNum.setBounds(80, 180, 100, 30);
		lableNum.setText("学号：");
		p.add(lableNum);

		lableCource = new JLabel();
		lableCource.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableCource.setBounds(80, 230, 100, 30);
		lableCource.setText("课程：");
		p.add(lableCource);

		lableScore = new JLabel();
		lableScore.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableScore.setBounds(80, 280, 100, 30);
		lableScore.setText("成绩：");
		p.add(lableScore);

		textNum = new JTextField();
		textNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		textNum.setBounds(130, 180, 200, 30);
		p.add(textNum);
		textNum.setEditable(false);

		jcbCource = new JComboBox<String>();

		jcbCource.setBounds(130, 230, 200, 30);
		p.add(jcbCource);
		this.jcbCource.addItem("请选择课程");

		textScore = new JTextField();
		textScore.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		textScore.setBounds(130, 280, 200, 30);
		p.add(textScore);

		btn = new JButton("修改");
		btn.setBounds(130, 330, 200, 30);
		p.add(btn);
		btn.addActionListener(this);
		worker1.execute();
		showInfo();

	}

	SwingWorker<?, ?> worker1 = new SwingWorker<Object, Object>() {
		protected Object doInBackground() throws Exception {

			HandleScore hd = new HandleScore();
			ResultSet rs = hd.queryDepart("select courceName from tb_cource");

			while (rs.next()) {
				jcbCource.addItem(rs.getString(1));
			}

			return null;
		}
	};

	private void modify() {
		Student s = new Student();
		Student.setStuScore(Float.parseFloat(this.textScore.getText().toString().trim()));
		
		Student.setStuCource(String.valueOf(jcbCource.getSelectedItem()));
		HandleScore hs=new HandleScore();
		if (hs.modify(s)) {
			JOptionPane.showMessageDialog(this.p, "修改成功！", "提示",
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			JOptionPane.showMessageDialog(this.p, "修改失败！", "提示",
					JOptionPane.INFORMATION_MESSAGE, null);
		}

	}

	private void showInfo() {
		Student s = new Student();
		this.textNum.setText(s.getStuNumber());
		this.textScore.setText(Student.getStuScore() + "");

	}

}
