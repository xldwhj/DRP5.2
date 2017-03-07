package com.bjpowernode.drp.util.datadict.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.util.Dbutil;
import com.bjpowernode.drp.util.datadict.domain.ClientLevel;
import com.bjpowernode.drp.util.datadict.domain.ItemCategory;
import com.bjpowernode.drp.util.datadict.domain.ItemUnit;

public class DataDictManager {
	//�����ṩһ��˽�еľ�̬��ʵ����ʼ��
	private static DataDictManager instance = new DataDictManager();
	//���췽��˽�л�
	private DataDictManager(){
		
	}
	//�����ṩһ�����еľ�̬�Ļ�õ�ǰ���Ͷ���ķ�
	
	public static DataDictManager getInstance(){
		/*if(instance == null){
			instance = new DataDictManager();
		}*/
		return instance;
	}
	
	
	/**
	 * ȡ�÷����̼����б�
	 * @return
	 */
	public List<ClientLevel> findClientLevelList(){
		String strSql = "select * from t_data_dict where category='A'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClientLevel> list = new ArrayList<ClientLevel>();
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ClientLevel cl = new ClientLevel();
				cl.setId(rs.getString("id"));
				cl.setName(rs.getString("name"));
				list.add(cl);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		return list;
	}
	
	/**
	 * ȡ����������б�
	 * @return
	 */
	public List<ItemCategory> findItemCategoryList(){
		String strSql = "select * from t_data_dict where category='C'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemCategory> list = new ArrayList<ItemCategory>();
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ItemCategory ic = new ItemCategory();
				ic.setId(rs.getString("id"));
				ic.setName(rs.getString("name"));
				list.add(ic);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		return list;
	}
	
	/**
	 * ȡ�ü�����λ�б�
	 * @return
	 */
	public List<ItemUnit> findItemUnitList(){
		String strSql = "select * from t_data_dict where category='D'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemUnit> list = new ArrayList<ItemUnit>();
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ItemUnit iu = new ItemUnit();
				iu.setId(rs.getString("id"));
				iu.setName(rs.getString("name"));
				list.add(iu);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		return list;
	}
}
