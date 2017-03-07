package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

/*
 * ��װ���ݿⳣ�ò�����������
 */
public class Dbutil {
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://127.0.0.1:3306/drpmysql";
//			String userName = "root";
//			String passWord = "123123";
			Class.forName(jdbcConfig.getDriverName().toString());
//			String url = jdbcConfig.getUrl();
//			String userName = jdbcConfig.getUserName();
//			String passWord = jdbcConfig.getPassword();
			conn = DriverManager.getConnection(jdbcConfig.getUrl().toString(),
					jdbcConfig.getUserName().toString(),jdbcConfig.getPassword().toString());
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();//���԰ѹ�����Ϣ��¼���ļ���
			throw new ApplicationException("ϵͳ��������ϵϵͳ����Ա��");
		}catch (SQLException e){
			e.printStackTrace();
			throw new ApplicationException("ϵͳ��������ϵϵͳ����Ա��");
		}
		return conn;
	}
	
	public static void close(Connection conn)
	{
		if(conn != null)
		{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}
	
	public static void close(PreparedStatement pstmt)
	{
		if(pstmt != null)
		{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}
	
	public static void close(Statement stmt)
	{
		if(stmt != null)
		{
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}
	
	public static void close(ResultSet rs)
	{
		if(rs != null)
		{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}
//	public static void main(String[] args)
//	{
//		System.out.println(Dbutil.getConnection());
//	}
	
	/**
	 * ��ʼ����
	 * @param conn
	 */
	public static void beginTransaction(Connection conn){
		try {
			if(conn != null){
				if(conn.getAutoCommit()){
					conn.setAutoCommit(false);//�ֶ��ύ
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * �ύ����
	 * @param conn
	 */
	public static void commitTransaction(Connection conn){
		try {
			if(conn != null){
				if(!conn.getAutoCommit()){
					conn.commit();
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * �ع�����
	 * @param conn
	 */
	public static void rollbackTransaction(Connection conn){
		try {
			if(conn != null){
				if(!conn.getAutoCommit()){
					conn.rollback();
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * ����conn��״̬
	 * @param conn
	 */
	public static void resetConnection(Connection conn){
		try {
			if(conn != null){
				if(conn.getAutoCommit()){
					conn.setAutoCommit(false);;
				}else{
					conn.setAutoCommit(true);;
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
