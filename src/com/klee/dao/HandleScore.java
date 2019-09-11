package com.klee.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.klee.bean.Student;

public class HandleScore {

	private DBConnection db;
	private PreparedStatement prestmt;
	private ResultSet rs;

	public HandleScore() {
		super();
		db = new DBConnection();
	}

	public ResultSet queryDepart(String sql) {

		db.openConn();

		prestmt = db.getPrepstmt(sql);
		try {
			rs = prestmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public boolean insertScore(Student s) {
		boolean flag = false;

		String stuNum = s.getStuNumber();
		String courceName = s.getStuCource();
		float score = s.getStuScore();

		db.openConn();
		String sql = "insert into tb_score(stuNumber,courceName,score) values(?,?,?)";

		prestmt = db.getPrepstmt(sql);

		try {

			prestmt.setString(1, stuNum);
			prestmt.setString(2, courceName);
			prestmt.setFloat(3, score);

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

	public boolean delete(String num) {
		String sql = "delete from tb_score where stuNumber=?";
		boolean flag = false;
		db.openConn();
		try {
			prestmt = db.getPrepstmt(sql);
			prestmt.setString(1, num);
			if (prestmt.executeUpdate() == 1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	public boolean modify(Student s) {
		boolean flag = false;
		db.openConn();
		String stuNum = s.getStuNumber();
		String stuCource = s.getStuCource();
		float score = s.getStuScore();

		String sql = "update tb_score set courceName=?,score=? where stuNumber=?";
		try {
			prestmt = db.getPrepstmt(sql);
			prestmt.setString(3, stuNum);
			prestmt.setString(1, stuCource);
			prestmt.setFloat(2, score);

			if (prestmt.executeUpdate() == 1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			db.closePrepstmt();
		}
		return flag;
	}

}
