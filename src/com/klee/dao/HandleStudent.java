package com.klee.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.klee.bean.Student;

public class HandleStudent {

	private DBConnection db;
	private PreparedStatement prestmt;
	private ResultSet rs;

	public HandleStudent() {
		super();

		db = new DBConnection();

	}

	public boolean insertStu(Student s) {
		boolean flag = false;

		String stuNum = s.getStuNumber();
		String stuName = s.getStuName();
		String stuSex = s.getStuSex();
		String stuDepart = s.getStuDepart();
		String stuSpec = s.getStuSpec();
		String stuClass = s.getStuClass();
		db.openConn();
		String sql = "insert into tb_student(stuNumber,stuName,stuSex,stuDepart,stuSpec,stuClass) values(?,?,?,?,?,?)";

		prestmt = db.getPrepstmt(sql);

		try {

			prestmt.setString(1, stuNum);
			prestmt.setString(2, stuName);
			prestmt.setString(3, stuSex);
			prestmt.setString(4, stuDepart);
			prestmt.setString(5, stuSpec);
			prestmt.setString(6, stuClass);

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

	public ArrayList<Student> queryStu(String name) {
		ArrayList<Student> result = new ArrayList<Student>();

		db.openConn();
		String sql = "select stuName,stuSex,stuDepart,stuSpec,stuClass from tb_student where stuNumber=?";
		prestmt = db.getPrepstmt(sql);
		try {
			prestmt.setString(1, name);
			rs = prestmt.executeQuery();
			if (rs.next()) {
				Student s = new Student();
				s.setStuName(rs.getString(1));
				s.setStuSex(rs.getString(2));
				s.setStuDepart(rs.getString(3));
				s.setStuSpec(rs.getString(4));
				s.setStuClass(rs.getString(5));
				result.add(s);
			} else {
				// JOptionPane.showMessageDialog(null, "无此学生信息！");
				Student s = new Student();
				s.setStuName("");
				s.setStuNumber("");
				s.setStuSex("");
				s.setStuDepart("");
				s.setStuSpec("");
				s.setStuClass("");
				result.add(s);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public ResultSet query(String sql) {
		db.openConn();

		prestmt = db.getPrepstmt(sql);
		try {
			rs = prestmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public boolean delete(String num) {
		String sql = "delete from tb_student where stuNumber=?";
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
		String stuName = s.getStuName();
		String stuSex = s.getStuSex();
		String stuDepart = s.getStuDepart();
		String stuSpec = s.getStuSpec();
		String stuClass = s.getStuClass();
	
		String sql = "update tb_student set stuName=?,stuSex=?,stuDepart=?,stuSpec=?,stuClass=? where stuNumber=?";
		try {
			prestmt = db.getPrepstmt(sql);
			prestmt.setString(6, stuNum);
			prestmt.setString(1, stuName);
			prestmt.setString(2, stuSex);
			prestmt.setString(3, stuDepart);
			prestmt.setString(4, stuSpec);
			prestmt.setString(5, stuClass);
		
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
