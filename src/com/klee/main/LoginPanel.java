package com.klee.main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {

	
	ImageIcon icon;  
    Image img;  
    public LoginPanel() {  
        //  /img/HomeImg.jpg �Ǵ���������ڱ�д����Ŀ��bin�ļ����µ�img�ļ����µ�һ��ͼƬ  
        icon=new ImageIcon(getClass().getResource("/images/login_bg.jpg"));  
        img=icon.getImage();  
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        //����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С  
        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
}
