package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.omg.CORBA.COMM_FAILURE;

import com.bjpowernode.drp.basedata.domain.Client;
import com.bjpowernode.drp.util.Dbutil;
import com.bjpowernode.drp.util.datadict.domain.ClientLevel;

/**
 * ���õ���ģʽ(����ʽ����ģʽ)
 * @author Administrator
 *
 */
public class ClientManager {
	//�����ṩһ����̬˽�е�ʵ����ʼ�����˴�Ϊ����ʽ����
	private static ClientManager instance = new ClientManager();
	//���췽��˽�л�
	private ClientManager() {
		// TODO Auto-generated constructor stub
	}
	//�����ṩһ�����еľ�̬�Ļ�õ�ǰ���Ͷ���ķ���
	public static ClientManager getInstance(){
		return instance;
	}
	
	//���ط��������ַ���
	public String getClientTreeHTMLString(){
		return new ClientTreeReader().getClientTreeHTMLString();
	}
	
	/**
	 * ����id��ѯ�����̻�����
	 * @param id
	 * @return ������ڷ��ط����̶��� ���򷵻�null
	 */
	public Client findClientOrRegionById(int id){
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select a.ID,a.PID,a.CLIENT_LEVEL_ID,b.NAME as CLIENT_LEVEL_NAME,a.NAME,a.CLIENT_ID,")
			 .append("a.BANK_ACCT_NO,a.CONTACT_TEL,a.ADDRESS,a.ZIP_CODE,a.IS_LEAF,a.IS_CLIENT ")
			 .append("from t_client a left join t_data_dict b on a.CLIENT_LEVEL_ID = b.ID where a.ID = ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Client client = null;
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setPid(rs.getInt("pid"));
				client.setName(rs.getString("name"));
				client.setClientId(rs.getString("client_id"));
				
				ClientLevel clientLevel = new ClientLevel();
				clientLevel.setId(rs.getString("client_level_id"));
				clientLevel.setName(rs.getString("client_level_name"));
				client.setClientLevel(clientLevel);
				
				client.setBankAcctNo(rs.getString("bank_acct_no"));
				client.setContaceTel(rs.getString("contact_tel"));
				client.setAddress(rs.getString("address"));
				client.setZipCode(rs.getString("zip_code"));
				client.setIsClient(rs.getString("is_client"));
				client.setIsLeaf(rs.getString("is_leaf"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		return client;
	}
	
	/**
	 * �޸ķ����̻�����
	 * @param clientOrRegion
	 */
	public void modifyClientOrRegion(Client clientOrRegion){
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("update t_client set client_level_id=?,name=?,bank_acct_no=?,")
		     .append("contact_tel=?,address=?,zip_code=? ")
		     .append("where id=?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setString(1, clientOrRegion.getClientLevel().getId());
			pstmt.setString(2, clientOrRegion.getName());
			pstmt.setString(3, clientOrRegion.getBankAcctNo());
			pstmt.setString(4, clientOrRegion.getContaceTel());
			pstmt.setString(5, clientOrRegion.getAddress());
			pstmt.setString(6, clientOrRegion.getZipCode());
			pstmt.setInt(7, clientOrRegion.getId());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
	}
	
	/**
	 * ���ݷ����̴����ѯ
	 * @param clientId
	 * @return
	 */
	public boolean findClientByClientId(String clientId){
		String strSql = "select count(*) from t_client where client_id=?";
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, clientId);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1) == 0 ? false : true;//���ڷ���true�������ڷ���false
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		return flag;
	}
	
	/**
	 * ɾ�������̻�����
	 */
	//public synchronized void delClientOrRegion(int id){
	public  void delClientOrRegion(int id){
		/*String strSql = "delete from t_client where id = ?";*/
		Connection conn = null;
	/*	PreparedStatement pstmt = null;
		ResultSet rs = null;*/
		try{
			conn = Dbutil.getConnection();
			Dbutil.beginTransaction(conn);//����Ϊ�ֶ��ύ
			/*boolean flag1 = isleafById(conn,id);
			
			//���Ϊid��Ӧ�ķ����̻�����ΪҶ�ӽڵ㣬�����idֱ��ɾ���÷����̻�����
			int pid = findPidById(conn, id);//����id��ѯ����Ӧ��pid
			boolean flag2 = isleafByPid(conn, pid);//����pid�ж�ɾ���ýڵ�󸸽ڵ��Ƿ�ΪҶ�ӽڵ�
			pstmt = conn.prepareStatement(strSql);//ɾ���ýڵ�
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			if(flag2 == true){
				updateIsLeafById(conn, pid);//���ΪҶ�ӽڵ㣬�����pid�ı丸�ڵ��״̬
			}
			//����ýڵ㲻ΪҶ�ӽڵ㣬�ݹ�ɾ�������е��ӽڵ�
			if(flag1 != true){
				List<Integer> list = findIdByPid(conn, id);
				Iterator<Integer> it = list.iterator();
				while(it.hasNext()){
					int Id = it.next();
					delClientOrRegion(Id);
				}
			}*/
			//���浱ǰ�������
			Client currentNode = findClientOrRegionById(id);
			
			recursionDelNode(conn,id);//�ݹ�ɾ��
			//������ڵ���û���ӽڵ����޸�ΪҶ��
			if(getChildren(conn, currentNode.getPid()) == 0){
				updateIsLeafById(conn, currentNode.getPid());//���ΪҶ�ӽڵ㣬�����pid�ı丸�ڵ��״̬
				//modifyIsLeafField(conn, currentNode.getPid());
			}
			Dbutil.commitTransaction(conn);//�ύ����
		}catch(SQLException e){
			e.printStackTrace();
			Dbutil.rollbackTransaction(conn);//��������ع�����
		}finally{
			/*Dbutil.close(rs);
			Dbutil.close(pstmt);*/
			Dbutil.resetConnection(conn);
			Dbutil.close(conn);
		}
	}
	
	/**
	 * ����id�ݹ�ɾ���ڵ�
	 * @param conn
	 * @param id
	 */
	private void  recursionDelNode(Connection conn,int id) throws SQLException{
		String strSql = "select * from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if("N".equals(rs.getString("is_leaf"))){
					recursionDelNode(conn,rs.getInt("id"));
				}
				delNode(conn, rs.getInt("id"));//�ݹ�ɾ���ӽڵ�
			}
			delNode(conn, id);//ɾ������
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		
	}
	
	/**
	 * ɾ���ڵ�
	 * @param conn
	 * @param id
	 */
	private void delNode(Connection conn,int id) throws SQLException{
		String strSql = "delete from t_client where id=?";
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}finally{
			Dbutil.close(pstmt);
		}
		
	}
	/**
	 * ����id�ж϶�Ӧ�ķ����̻������Ƿ�ΪҶ�ӽڵ�
	 * @param id
	 * @return
	 */
	public boolean isleafById(Connection conn,int id) throws SQLException{
		String strSql = "select is_leaf from t_client where id =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = ("Y".equals(rs.getString(1))? true : false);
			}else{
				throw new RuntimeException();
			}
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		return flag;
	}
	
	
	/**
	 * ����idɾ���ýڵ��ʹ�����Ӧ��pid��Ϊ���ڵ��Id���жϸ��ڵ��Ƿ�ΪҶ�ӽڵ�
	 * @param conn
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public boolean isleafByPid(Connection conn,int pid) throws SQLException{
		String strSql = "select count(*) from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = rs.getInt(1)==1?true:false;//���Ϊ1˵������idɾ���ڵ�֮���丸�ڵ��ΪҶ�ӽڵ�
			}else{
				throw new RuntimeException();
			}
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		return flag;
	}
	
	public int getChildren(Connection conn,int pid) throws SQLException{
		String strSql = "select count(*) from t_client where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//boolean flag = false;
		int count = 0;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			/*if(rs.next()){
				flag = rs.getInt(1)==1?true:false;//���Ϊ1˵������idɾ���ڵ�֮���丸�ڵ��ΪҶ�ӽڵ�
			}else{
				throw new RuntimeException();
			}*/
			
			rs.next();
			count = rs.getInt(1);
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		return count;
	}
	/**
	 * ������Ҫɾ����id��ѯ����Ӧ��pid
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int findPidById(Connection conn,int id) throws SQLException{
		String strSql = "select pid from t_client where id =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pid = 1;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pid = rs.getInt(1);
			}else{
				throw new RuntimeException();
			}
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		return pid;
	}
	
	
	/**
	 * ����id�ı�ýڵ��is_leaf״̬
	 * @param conn
	 * @param id
	 * @throws SQLException
	 */
	public void updateIsLeafById(Connection conn,int id) throws SQLException{
		String strSql = "update t_client set is_leaf=? where id=?";
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		}finally{
			Dbutil.close(pstmt);
		}
	}
	
	
	/**
	 * ���Ҫɾ���ķ����̻�������ΪҶ�ӽڵ㣬����Ҫ�����ӽڵ�ȫ��ɾ��
	 * @param conn
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> findIdByPid(Connection conn,int pid) throws SQLException{
		String strSql = "select id from t_client where pid =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<Integer>();
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getInt("id"));//������pid��ѯ����Id���浽list�з��أ�����list�д洢��id����ɾ��
			}
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		
		return list;
	}
	
	/**
	 * ��ӷ����̻�����
	 * @param client
	 */
	//public synchronized void addRegionOrClient(Client clientOrRegion){
	public void addRegionOrClient(Client clientOrRegion){	
		String strSql = "insert into t_client (id,pid,client_level_id,name,client_id,bank_acct_no,contact_tel,address,zip_code,is_leaf,is_client)  values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			//�ֶ����������ύ
			Dbutil.beginTransaction(conn);
			pstmt.setInt(1, clientOrRegion.getId());
			pstmt.setInt(2, clientOrRegion.getPid());
			pstmt.setString(3, clientOrRegion.getClientLevel().getId());
			pstmt.setString(4, clientOrRegion.getName());
			pstmt.setString(5, clientOrRegion.getClientId());
			pstmt.setString(6, clientOrRegion.getBankAcctNo());
			pstmt.setString(7, clientOrRegion.getContaceTel());
			pstmt.setString(8, clientOrRegion.getAddress());
			pstmt.setString(9, clientOrRegion.getZipCode());
			pstmt.setString(10, clientOrRegion.getIsLeaf());
			pstmt.setString(11, clientOrRegion.getIsClient());
			pstmt.executeUpdate();
			
			Client parentClientOrRegion = findClientOrRegionById(clientOrRegion.getPid());
			if("Y".equals(parentClientOrRegion.getIsLeaf())){
				modifyIsLeafField(conn,clientOrRegion.getPid());//���ΪҶ�ӽڵ㣬�����޸�Ϊ��Ҷ�ӽ��
			}
			//�ύ����
			Dbutil.commitTransaction(conn);
		}catch(SQLException e){
			e.printStackTrace();
			//�������⣬�ع�����
			Dbutil.rollbackTransaction(conn);
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		
	}
	
	/**
	 * �����ɷ����̻������ı��丸�ڵ�Ϊ��Ҷ�ӽ������
	 * @param conn
	 * @param pid
	 * @throws SQLException
	 */
	public void modifyIsLeafField(Connection conn,int pid) throws SQLException{
		String strSql = "update t_client set is_leaf=? where id=?";
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, "N");
			pstmt.setInt(2, pid);
			pstmt.executeUpdate();
		}finally{
			Dbutil.close(pstmt);
		}
	}
}
