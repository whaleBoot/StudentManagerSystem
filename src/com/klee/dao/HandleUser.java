package com.klee.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.klee.bean.User;

public class HandleUser {
	
	private DBConnection db;
	private PreparedStatement prestmt;

	public HandleUser() {
		super();

		db = new DBConnection();

	}

	public boolean insertUser(User u) {
		boolean flag = false;

		String userName = u.getUserName();
		String passWord = u.getPassWord();
		int type=u.getType();
		db.openConn();
		String sql = "insert into tb_user(userName,userPwd,userType) values(?,?,?)";

		prestmt = db.getPrepstmt(sql);

		try {

			prestmt.setString(1, userName);
			prestmt.setString(2, passWord);
			prestmt.setInt(3, type);

			if (prestmt.executeUpdate() == 1) {

				flag = true;

			} else {

				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			db.closePrepstmt();
		}

		return flag;
	}
	
	
}
