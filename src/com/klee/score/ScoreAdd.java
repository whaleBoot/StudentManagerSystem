package com.klee.score;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.klee.bean.Student;
import com.klee.dao.HandleScore;

public class ScoreAdd implements ActionListener {

	private JPanel p;
	private JLabel lableNum, lableCource, lableScore, lableTitle;
	private JTextField textNum, textScore;
	private JComboBox<String> jcbCource;
	private JButton btn;

	public ScoreAdd() {
		super();
	}

	public ScoreAdd(JPanel p) {
		super();
		this.p = p;
	}

	public void init() {
		lableTitle = new JLabel();
		lableTitle.setBounds(280, 50, 300, 30);
		lableTitle.setFont(new java.awt.Font("������", Font.BOLD, 27));
		lableTitle.setText("�ɼ�¼��");
		p.add(lableTitle);

		lableNum = new JLabel();
		lableNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableNum.setBounds(215, 180, 100, 30);
		lableNum.setText("ѧ�ţ�");
		p.add(lableNum);

		lableCource = new JLabel();
		lableCource.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableCource.setBounds(215, 230, 100, 30);
		lableCource.setText("�γ̣�");
		p.add(lableCource);

		lableScore = new JLabel();
		lableScore.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableScore.setBounds(215, 280, 100, 30);
		lableScore.setText("�ɼ���");
		p.add(lableScore);

		textNum = new JTextField();
		textNum.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		textNum.setBounds(265, 180, 200, 30);
		p.add(textNum);

		jcbCource = new JComboBox<String>();

		jcbCource.setBounds(265, 230, 200, 30);
		p.add(jcbCource);
		this.jcbCource.addItem("��ѡ��γ�");

		textScore = new JTextField();
		textScore.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
		textScore.setBounds(265, 280, 200, 30);
		p.add(textScore);

		btn = new JButton("¼��");
		btn.setBounds(265, 330, 200, 30);
		p.add(btn);
		btn.addActionListener(this);
		worker1.execute();

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

	public void insertScore() {

		Student s = new Student();
		s.setStuNumber(this.textNum.getText().toString().trim());
		Student.setStuScore(Float.parseFloat(this.textScore.getText()));
		Student.setStuCource(String.valueOf(jcbCource.getSelectedItem()));

		HandleScore hs = new HandleScore();
		if (hs.insertScore(s)) {
			JOptionPane.showMessageDialog(this.p, "¼��ɹ���", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		}else{
			JOptionPane.showMessageDialog(this.p, "¼��ʧ�ܣ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btn){
			insertScore();
		}
		
	}
	
	

}
