package com.klee.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.klee.bean.User;
import com.klee.dao.HandleUser;

public class UserAdd implements ActionListener {
	private JPanel p;
	private JLabel lableUser, lablePwd, lableTitle,lableType;
	private JTextField textUser, textPwd;
	private JButton btn;
	private JComboBox<String> jcbType=new JComboBox<String>();

	public UserAdd(JPanel p) {
		this.p = p;
	}

	public void init() {
		lableTitle = new JLabel();
		lableTitle.setBounds(280, 50, 300, 30);
		lableTitle.setFont(new java.awt.Font("������", Font.BOLD, 27));
		lableTitle.setText("����û�");
		p.add(lableTitle);

		lableUser = new JLabel();
		lableUser.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableUser.setBounds(215, 180, 100, 30);
		lableUser.setText("�û�����");
		p.add(lableUser);

		lablePwd = new JLabel();
		lablePwd.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lablePwd.setBounds(215, 230, 100, 30);
		lablePwd.setText("��    �룺");
		p.add(lablePwd);
		
		lableType = new JLabel();
		lableType.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lableType.setBounds(215, 280, 100, 30);
		lableType.setText("Ȩ    �ޣ�");
		p.add(lableType);

		textUser = new JTextField();
		textUser.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		textUser.setBounds(290, 180, 150, 30);
		p.add(textUser);

		textPwd = new JTextField();
		textPwd.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		textPwd.setBounds(290, 230, 150, 30);
		p.add(textPwd);

		jcbType.setBounds(290, 280, 150, 30);
		p.add(jcbType);
		this.jcbType.addItem("��ѡ���û����");
		this.jcbType.addItem("��ͨ�û�");
		this.jcbType.addItem("����Ա");
		
		btn = new JButton("���");
		btn.setBounds(290, 330, 150, 30);
		p.add(btn);
		btn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			insertUser();
		}
	}

	private void insertUser() {

		User u = new User();
		u.setUserName(this.textUser.getText().toString().trim());
		u.setPassWord(this.textPwd.getText().toString().trim());
		if(String.valueOf(jcbType.getSelectedItem())=="����Ա"){
				u.setType(1);
		}else{
			u.setType(2);
		}
		HandleUser hu = new HandleUser();
		if (hu.insertUser(u)) {
			JOptionPane.showMessageDialog(this.p, "��ӳɹ���", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			JOptionPane.showMessageDialog(this.p, "���ʧ�ܣ�", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE, null);
		}

	}

}
