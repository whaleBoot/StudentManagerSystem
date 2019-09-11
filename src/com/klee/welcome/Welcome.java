package com.klee.welcome;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome {
	JPanel p;
	private JLabel lable1,lable2;
	
	public Welcome(JPanel p) {
		super();
		this.p = p;
	}
	
	
	public void init(){
		lable1=new JLabel();
		lable1.setBounds(250, 180, 200, 30);
		lable1.setText("欢迎使用");
		lable1.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		p.add(lable1);
		
		lable2=new JLabel();
		lable2.setBounds(190, 250, 400, 30);
		lable2.setText("学生信息管理系统");
		lable2.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		p.add(lable2);
		
		
		
		
	}

}
