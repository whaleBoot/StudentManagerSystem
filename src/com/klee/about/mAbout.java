package com.klee.about;


import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class mAbout {
	JPanel p;
	private JLabel lable1,lable2,lable3,lable4;
	
	public mAbout(JPanel p) {
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
		lable2.setBounds(230, 200, 400, 30);
		lable2.setText("作者：李可");
		lable2.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		p.add(lable2);
		
		lable3=new JLabel();
		lable3.setBounds(230, 250, 400, 30);
		lable3.setText("版本号：1.0");
		lable3.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		p.add(lable3);
		
		lable4=new JLabel();
		lable4.setBounds(230, 300, 400, 30);
		lable4.setText("本程序仅供Java课程设计所用");
		lable4.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		p.add(lable4);
		
		
	}

}

