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
 * 采用单例模式(饿汉式单例模式)
 * @author Administrator
 *
 */
public class ClientManager {
	//对内提供一个静态私有的实例初始化，此处为饿汉式单例
	private static ClientManager instance = new ClientManager();
	//构造方法私有化
	private ClientManager() {
		// TODO Auto-generated constructor stub
	}
	//对外提供一个公有的静态的获得当前类型对象的方法
	public static ClientManager getInstance(){
		return instance;
	}
	
	//返回分销商树字符串
	public String getClientTreeHTMLString(){
		return new ClientTreeReader().getClientTreeHTMLString();
	}
	
	/**
	 * 根据id查询分销商或区域
	 * @param id
	 * @return 如果存在返回分销商对象 否则返回null
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
	 * 修改分销商或区域
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
	 * 根据分销商代码查询
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
			return rs.getInt(1) == 0 ? false : true;//存在返回true，不存在返回false
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
	 * 删除分销商或区域
	 */
	//public synchronized void delClientOrRegion(int id){
	public  void delClientOrRegion(int id){
		/*String strSql = "delete from t_client where id = ?";*/
		Connection conn = null;
	/*	PreparedStatement pstmt = null;
		ResultSet rs = null;*/
		try{
			conn = Dbutil.getConnection();
			Dbutil.beginTransaction(conn);//设置为手动提交
			/*boolean flag1 = isleafById(conn,id);
			
			//如果为id对应的分销商或区域为叶子节点，则根据id直接删除该分销商或区域
			int pid = findPidById(conn, id);//根据id查询出对应的pid
			boolean flag2 = isleafByPid(conn, pid);//根据pid判断删除该节点后父节点是否为叶子节点
			pstmt = conn.prepareStatement(strSql);//删除该节点
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			if(flag2 == true){
				updateIsLeafById(conn, pid);//如果为叶子节点，则根据pid改变父节点的状态
			}
			//如果该节点不为叶子节点，递归删除其所有的子节点
			if(flag1 != true){
				List<Integer> list = findIdByPid(conn, id);
				Iterator<Integer> it = list.iterator();
				while(it.hasNext()){
					int Id = it.next();
					delClientOrRegion(Id);
				}
			}*/
			//保存当前对象对象
			Client currentNode = findClientOrRegionById(id);
			
			recursionDelNode(conn,id);//递归删除
			//如果父节点下没有子节点则修改为叶子
			if(getChildren(conn, currentNode.getPid()) == 0){
				updateIsLeafById(conn, currentNode.getPid());//如果为叶子节点，则根据pid改变父节点的状态
				//modifyIsLeafField(conn, currentNode.getPid());
			}
			Dbutil.commitTransaction(conn);//提交事务
		}catch(SQLException e){
			e.printStackTrace();
			Dbutil.rollbackTransaction(conn);//如果出错，回滚事务
		}finally{
			/*Dbutil.close(rs);
			Dbutil.close(pstmt);*/
			Dbutil.resetConnection(conn);
			Dbutil.close(conn);
		}
	}
	
	/**
	 * 根据id递归删除节点
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
				delNode(conn, rs.getInt("id"));//递归删除子节点
			}
			delNode(conn, id);//删除自身
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		
	}
	
	/**
	 * 删除节点
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
	 * 根据id判断对应的分销商或区域是否为叶子节点
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
	 * 根据id删除该节点后，使用其对应的pid作为父节点的Id，判断父节点是否为叶子节点
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
				flag = rs.getInt(1)==1?true:false;//结果为1说明根据id删除节点之后，其父节点变为叶子节点
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
				flag = rs.getInt(1)==1?true:false;//结果为1说明根据id删除节点之后，其父节点变为叶子节点
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
	 * 根据需要删除的id查询出对应的pid
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
	 * 根据id改变该节点的is_leaf状态
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
	 * 如果要删除的分销商或者区域不为叶子节点，则需要将其子节点全部删除
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
				list.add(rs.getInt("id"));//将根据pid查询到的Id保存到list中返回，根据list中存储的id进行删除
			}
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		
		return list;
	}
	
	/**
	 * 添加分销商或区域
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
			//手动控制事务提交
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
				modifyIsLeafField(conn,clientOrRegion.getPid());//如果为叶子节点，则将其修改为非叶子结点
			}
			//提交事务
			Dbutil.commitTransaction(conn);
		}catch(SQLException e){
			e.printStackTrace();
			//出现问题，回滚事务
			Dbutil.rollbackTransaction(conn);
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		
	}
	
	/**
	 * 添加完成分销商或区域后改变其父节点为非叶子结点属性
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
