package com.bjpowernode.drp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGenerator {
	
	/**
	 * Id������,���ݱ������ɸñ�����У���ӷ����̻�������ʱ���ø÷���
	 * @param tableName
	 * @return
	 */
	//public static synchronized int generatorValue(String tableName){
	public static int generatorValue(String tableName){
		//ʹ�����ݱ�����update
		String strSql = "select value from t_table_id where table_name=? for update";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int value = 0;
		try{
			conn = Dbutil.getConnection();
			Dbutil.beginTransaction(conn);//��ʼ����
			//conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				value = rs.getInt("value");
				value++;
				modifyValueField(conn,value,tableName);
				Dbutil.commitTransaction(conn);//�ύ����
			}else{
				throw new RuntimeException();
			}
		}catch(SQLException e){
			e.printStackTrace();
			Dbutil.rollbackTransaction(conn);//������Իع�����
			throw new RuntimeException();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.resetConnection(conn);//����conn��״̬
			Dbutil.close(conn);
			
		}
		
		return value;
	}
	
	/**
	 * ���ݱ������������ֶε�ֵ
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
			//Dbutil.close(conn);conn��generatorValue������������������generatorValue�����йر�
		}
	}
	
	
	public static void main(String[] args){
		System.out.println(IdGenerator.generatorValue("t_client"));
	}
}
