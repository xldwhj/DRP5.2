package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * ����ThreadLocal��װConnection�����Բ���ͬ�����ƽ���̰߳�ȫ����
 * 
 * @author Administrator
 *
 */
public class ConnectionManager {
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	/**
	 * �õ�Connection
	 * @return
	 */
	public static Connection getConnection(){
		//���ݵ��ø÷����ĵ�ǰ�߳�ȡ��connectionHolder�洢��Ӧ�ڸ��̵߳�value
		Connection conn = connectionHolder.get();
		//����ڵ�ǰ�߳���û�а���Ӧ��connection
		if(conn == null){
			try {
				JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
				Class.forName(jdbcConfig.getDriverName().toString());
				/*conn = DriverManager.getConnection(jdbcConfig.getUrl().toString(),
						jdbcConfig.getUserName().toString(),jdbcConfig.getPassword().toString());*/
				conn = DriverManager.getConnection(jdbcConfig.getUrl().toString(),jdbcConfig.getUserName().toString(),jdbcConfig.getPassword().toString());
				//��Connection���õ�ThreadLocal��
				connectionHolder.set(conn);
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();//���԰ѹ�����Ϣ��¼���ļ���
				throw new ApplicationException("ϵͳ��������ϵϵͳ����Ա��");
			}catch (SQLException e){
				e.printStackTrace();
				throw new ApplicationException("ϵͳ��������ϵϵͳ����Ա��");
			}
		}
		return conn;
	}
	
	/**
	 * �ر�connection
	 */
	public static void closeConnection(){
		Connection conn = connectionHolder.get();
		if(conn != null){
			try {
				conn.close();
				//��ThreadLocal�����Connection
				connectionHolder.remove();
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
}
