package com.klee.about;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class mHelp {
	JPanel p;
	private JLabel lable1,lable2,lable3,lable4;
	
	public mHelp(JPanel p) {
		super();
		this.p = p;
	}
	
	
	public void init(){
		lable1=new JLabel();
		lable1.setBounds(200, 120, 400, 30);
		lable1.setText("学生信息管理系统");
		lable1.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		p.add(lable1);
		
		lable2=new JLabel();
		lable2.setBounds(280, 200, 400, 30);
		lable2.setText("请联系我们");
		lable2.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		p.add(lable2);
		
		lable3=new JLabel();
		lable3.setBounds(230, 250, 400, 30);
		lable3.setText("邮箱：xxxxxxx.com");
		lable3.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		p.add(lable3);
		
		lable4=new JLabel();
		lable4.setBounds(230, 300, 400, 30);
		lable4.setText("博客：xxxxxxx.com");
		lable4.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		p.add(lable4);
		
		
	}

}

