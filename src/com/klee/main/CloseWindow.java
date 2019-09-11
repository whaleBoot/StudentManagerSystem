package com.klee.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class CloseWindow extends WindowAdapter // 关闭窗口closeWindow类////////////
{
	MainJFrame m;

	CloseWindow(MainJFrame m) {
		this.m = m;
	}

	public void windowClosing(WindowEvent e) {
		
		   int i = JOptionPane.showConfirmDialog(null,"是否退出","提示",JOptionPane.YES_NO_OPTION);
           if(i==JOptionPane.YES_OPTION){
              m.dispose();
           }
	}
}
