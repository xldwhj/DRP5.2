package com.bjpowernode.drp.sysmgr.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bjpowernode.drp.sysmgr.domain.User;
import com.bjpowernode.drp.util.Dbutil;
import com.bjpowernode.drp.util.PageModel;
//import com.mysql.jdbc.ResultSet;
//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.jmx.snmp.Timestamp;

/*
 * 采用单例管理用户
 */
public class UserManager {
	private static UserManager instance = new UserManager();
	
	private UserManager(){}
	
	public static UserManager getInstance()
	{
		return instance;
	}
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user){
		String strSql = "insert into t_user (user_id,user_name,password,contact_tel,email,create_date) "
				+ "values (?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getContactTel());
			pstmt.setString(5, user.getEmail());
			pstmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
	}
	/**
	 * 修改用户
	 * @param user
	 */
	public void modifyUser(User user){
		String strSql = "update t_user set user_name =?,password=?,contact_tel=?,email=? where user_id =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getContactTel());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getUserId());
			//pstmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
	}
	
	
	/**
	 * 删除用户
	 * @param user
	 */
	//public void deleteUser(User user){
		
	//}
	/**
	 * 根据用户代码查询用户是否已存在
	 * @param userId
	 * @return
	 */
	public User findUserById(String userId){
		//System.out.println(userId);
		String strSql = "select * from t_user where user_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setContactTel((rs.getString("contact_tel")));
				user.setEmail(rs.getString("email"));
				user.setCreateDate(rs.getTimestamp("create_date"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
			Dbutil.close(rs);
		}
		return user;
	}
	
	/**
	 * 分页查询
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条数据
	 * @return
	 */
	public PageModel<User> findUserList(int pageNo,int pageSize){
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select user_id,user_name,password,contact_tel,email,create_date ")
			.append("from ")
			.append("(")
			.append("select @rownum:=@rownum+1 rownum,user_id,user_name,password,contact_tel,email,create_date ")
			.append("from ")
			.append("(")
			.append("select @rownum:=0,user_id,user_name,password,contact_tel,email,create_date from t_user where user_id <> 'root' order by user_id")
			.append(") ")
			.append("as t_user")
			.append(") ")
			.append("as t_user ")
			.append("where ")
			.append("rownum<=? and rownum>?");
		String strSql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<User> pageModel = null;
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo * pageSize);
			pstmt.setInt(2, (pageNo - 1) * pageSize);
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<User>();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setContactTel((rs.getString("contact_tel")));
				user.setEmail(rs.getString("email"));
				user.setCreateDate(rs.getTimestamp("create_date"));
				userList.add(user);
			}
			pageModel = new PageModel<User>();
			pageModel.setList(userList);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
			Dbutil.close(rs);
		}
		return pageModel;
	}
	
	public int findAllUser(){
		String strSql = "select user_id from t_user where user_id <> ?";
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
		try {
			conn = Dbutil.getConnection();
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(strSql);
			//rs = stmt.executeQuery(strSql);
			pstmt.setString(1, "root");
			rs = pstmt.executeQuery();
			while(rs.next()){
				sum++ ;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//Dbutil.close(stmt);
			Dbutil.close(pstmt);
			Dbutil.close(conn);
			Dbutil.close(rs);
		}
		
		return sum;
	}
	
	/**
	 * 一条一条删除用户
	 *
	public boolean deleteByUserId(String userId){
		boolean flag = false;
		String strSql = "delete from t_user where user_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		
		return flag;
	}*/
	
	
	/**
	 * 一条一条删除用户
	 * @param userId
	 */
	public void deleteByUserId(String userId){
		//boolean flag = false;
		String strSql = "delete from t_user where user_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			//flag = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		
		//return flag;
	}
	
	/**
	 * 采用事务批量删除用户
	 * 采用一条语句完成删除
	 */
	public boolean deleteByUserId(String[] userIds){
		boolean flag = false;
		String strSql = "delete from t_user where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Dbutil.getConnection();
			//conn.setAutoCommit(false);
			//设置为手动提交
			Dbutil.beginTransaction(conn);
			pstmt = conn.prepareStatement(strSql);
			//pstmt.setString(1, userId);
			for(int i=0;i<userIds.length;i++){
				pstmt.setString(1, userIds[i].trim());
				pstmt.addBatch();
			}
			//pstmt.executeUpdate();
			pstmt.executeBatch();//批量执行
			//conn.commit();//提交事务
			Dbutil.commitTransaction(conn);
			flag = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			/*try{
				conn.rollback();//
			}catch(SQLException ex){
				
			}*/
			Dbutil.rollbackTransaction(conn);
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		
		return flag;
	}
	
	public User login(String userId,String password){
		User user = findUserById(userId);
		if(user == null){
			throw new UserNotFountException("用户代码不正确！");
		}
		
		if(!user.getPassword().equals(password)){
			throw new PasswordNotCorrentException("密码错误！");
		}
		return user;
	}
	
	public boolean updateUserById(String password,String userId){
		boolean flag = false;
		String strSql = "update t_user set password = ? where user_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Dbutil.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Dbutil.close(pstmt);
			Dbutil.close(conn);
		}
		
		return flag;
	}
}
