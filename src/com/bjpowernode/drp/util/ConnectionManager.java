package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 采用ThreadLocal封装Connection，可以采用同步机制解决线程安全问题
 * 
 * @author Administrator
 *
 */
public class ConnectionManager {
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	/**
	 * 得到Connection
	 * @return
	 */
	public static Connection getConnection(){
		//根据调用该方法的当前线程取得connectionHolder存储对应于该线程的value
		Connection conn = connectionHolder.get();
		//如果在当前线程中没有绑定相应的connection
		if(conn == null){
			try {
				JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
				Class.forName(jdbcConfig.getDriverName().toString());
				/*conn = DriverManager.getConnection(jdbcConfig.getUrl().toString(),
						jdbcConfig.getUserName().toString(),jdbcConfig.getPassword().toString());*/
				conn = DriverManager.getConnection(jdbcConfig.getUrl().toString(),jdbcConfig.getUserName().toString(),jdbcConfig.getPassword().toString());
				//将Connection设置到ThreadLocal中
				connectionHolder.set(conn);
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();//可以把故障信息记录到文件中
				throw new ApplicationException("系统错误，请联系系统管理员！");
			}catch (SQLException e){
				e.printStackTrace();
				throw new ApplicationException("系统错误，请联系系统管理员！");
			}
		}
		return conn;
	}
	
	/**
	 * 关闭connection
	 */
	public static void closeConnection(){
		Connection conn = connectionHolder.get();
		if(conn != null){
			try {
				conn.close();
				//从ThreadLocal中清除Connection
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
	 * 开始事务
	 * @param conn
	 */
	public static void beginTransaction(Connection conn){
		try {
			if(conn != null){
				if(conn.getAutoCommit()){
					conn.setAutoCommit(false);//手动提交
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 提交事务
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
	 * 回滚事务
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
