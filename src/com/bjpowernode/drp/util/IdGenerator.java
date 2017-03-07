package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGenerator {
	
	/**
	 * Id生成器,根据表名生成该表的序列，添加分销商或者区域时调用该方法
	 * @param tableName
	 * @return
	 */
	//public static synchronized int generatorValue(String tableName){
	public static int generatorValue(String tableName){
		//使用数据悲观锁update
		String strSql = "select value from t_table_id where table_name=? for update";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int value = 0;
		try{
			conn = Dbutil.getConnection();
			Dbutil.beginTransaction(conn);//开始事务
			//conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				value = rs.getInt("value");
				value++;
				modifyValueField(conn,value,tableName);
				Dbutil.commitTransaction(conn);//提交事务
			}else{
				throw new RuntimeException();
			}
		}catch(SQLException e){
			e.printStackTrace();
			Dbutil.rollbackTransaction(conn);//出错可以回滚事务
			throw new RuntimeException();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.resetConnection(conn);//重置conn的状态
			Dbutil.close(conn);
			
		}
		
		return value;
	}
	
	/**
	 * 根据表明更新序列字段的值
	 * @param conn
	 * @param value
	 * @param tableName
	 */
	public static void modifyValueField(Connection conn,int value,String tableName)
	throws SQLException{
		String strSql = "update t_table_id set value=? where table_name=?";
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, value);
			pstmt.setString(2, tableName);
			pstmt.executeUpdate();
		}finally{
			Dbutil.close(pstmt);
			//Dbutil.close(conn);conn在generatorValue方法中声明，所以在generatorValue方法中关闭
		}
	}
	
	
	public static void main(String[] args){
		System.out.println(IdGenerator.generatorValue("t_client"));
	}
}
