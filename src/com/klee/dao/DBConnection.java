package com.klee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://47.95.221.42:3306/stuManagerDB?useSSL=true";
	private static final String USERNAME = "root";
	private static final String PWD = "demo";
	private Connection conn = null;
	private Statement stmt;
	private PreparedStatement preStmt;

	public DBConnection() {

	}

	public void openConn() {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	
	public ResultSet getResultSet(String sql) {

		try {

			preStmt = conn.prepareStatement(sql);
			ResultSet rs = preStmt.executeQuery();

			return rs;
		} catch (SQLException ex) {
			return null;
		}
	}

	public PreparedStatement getPrepstmt(String sql) {
		try {
			preStmt = conn.prepareStatement(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return preStmt;
	}

	public void closePrepstmt() {
		try {
			preStmt.close();
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

}
