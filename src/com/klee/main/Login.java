package com.klee.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.klee.dao.DBConnection;
import com.klee.dao.HandleStudent;

/**
 * 
 * Title: 登录类 Description: 登陆模块，程序的入口类
 * 
 * @author 李可
 *
 */
public class Login extends JFrame implements ActionListener {

	private JPanel p = new LoginPanel();
	private JLabel lableTitle = new JLabel();
	private JTextField textUserName = new JTextField();
	private JPasswordField textPassWord = new JPasswordField();
	private JButton btnLogin = new JButton();
	private boolean flag = false;

	public Login(String title) {

		super(title);
	}

	private void init() {
		Container c = this.getContentPane();
		p.setLayout(null);
		p.setPreferredSize(new Dimension(300, 500));
		c.add(p, BorderLayout.CENTER);
		this.setLocation(200, 100);
		this.setSize(900, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

		lableTitle.setBounds(300, 80, 500, 30);
		lableTitle.setText("欢迎使用学生管理系统");
		lableTitle.setFont(new java.awt.Font("Dialog", Font.TRUETYPE_FONT, 30));
		lableTitle.setForeground(Color.white);
		p.add(lableTitle);

		textUserName.setBounds(345, 200, 200, 30);
		p.add(textUserName);

		textPassWord.setBounds(345, 250, 200, 30);
		p.add(textPassWord);

		btnLogin.setText("登陆");
		btnLogin.setBackground(Color.decode("#ff6638"));
		btnLogin.setBorder(new RoundBorder());
		btnLogin.setBounds(345, 300, 200, 30);
		p.add(btnLogin);
		btnLogin.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {

			String userName = this.textUserName.getText().trim();
			String passWord = this.textPassWord.getText().trim();

			HandleStudent hs = new HandleStudent();
			ResultSet rs = hs.query("select * from tb_user where userName='"
					+ userName + "' ");

			// DBConnection db = new DBConnection();
			// db.openConn();
			// ResultSet rs = db.getRs("select * from tb_user where userName='"
			// + userName + "' ");
			try {
				while (rs.next()) {
					if (rs.getString("userPwd").trim().equals(passWord)) {
						flag = true;
//						SwingUtilities.invokeLater(new Runnable() {
//							public void run() {
								new MainJFrame("学生管理系统").init();
//							}
//						});

						this.dispose();
					} 

				}
				if (flag == false) {
					JOptionPane.showMessageDialog(this.p, "用户名或密码错误！", "提示",
							JOptionPane.INFORMATION_MESSAGE, null);

				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login("登陆").init();
			}
		});

	}

}
