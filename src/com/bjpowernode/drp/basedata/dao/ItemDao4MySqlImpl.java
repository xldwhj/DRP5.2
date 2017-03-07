package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.ApplicationException;
import com.bjpowernode.drp.util.Dbutil;
import com.bjpowernode.drp.util.PageModel;
import com.bjpowernode.drp.util.datadict.domain.ItemCategory;
import com.bjpowernode.drp.util.datadict.domain.ItemUnit;

/**
 * 实现mysql的JDBC
 * @author Administrator
 *
 */
public class ItemDao4MySqlImpl implements ItemDao {

	@Override
	public void addItem(Connection conn, Item item) {
		String strSql = "insert into t_items (item_no,item_name,spec,pattern,item_category_id,item_unit_id) "
				+ "value(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try{
			//Dao的设计比较单纯，不应该放入过多的业务规则
			/*if(findItemById(conn, item.getItemNo()) != null){
				throw new ApplicationException("物料代码已经存在，代码=【" + item.getItemNo() + "】");
			}*/
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, item.getItemNo());
			pstmt.setString(2, item.getItemName());
			pstmt.setString(3, item.getSpec());
			pstmt.setString(4, item.getPattern());
			pstmt.setString(5, item.getItemCategory().getId());
			pstmt.setString(6, item.getItemUnit().getId());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			/*int i = e.getErrorCode();//在MySql中，错误代码为1062表示违反唯一主键约束
			if(i == 1062){
				throw new ApplicationException("物料代码已经存在，代码【" + item.getItemNo() + "】");
			}*/
			throw new ApplicationException("添加物料信息失败！");
		}finally{
			Dbutil.close(pstmt);
		}
	}

	@Override
	public boolean delItem(Connection conn, String[] itemNos) {
		boolean flag = false;
		String strSql = "delete from t_items where item_no=?";
		PreparedStatement pstmt = null;
		try{
			//设置为手动提交
			Dbutil.beginTransaction(conn);
			pstmt = conn.prepareStatement(strSql);
			for(int i=0;i<itemNos.length;i++){
				pstmt.setString(1, itemNos[i].trim());
				pstmt.addBatch();
			}
			pstmt.executeBatch();//批量执行
			//conn.commit();//提交事务
			Dbutil.commitTransaction(conn);
			flag = true;
		}catch(SQLException e){
			//回滚事务
			Dbutil.rollbackTransaction(conn);
			throw new ApplicationException("删除物料信息失败！");
		}finally{
			Dbutil.close(pstmt);
		}
		return flag;
	}

	@Override
	public void modifyItem(Connection conn, Item item) {
		String strSql = "update t_items set item_name=?,spec=?,pattern=?,item_category_id=?,item_unit_id=? where item_no=?";
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, item.getItemName());
			pstmt.setString(2, item.getSpec());
			pstmt.setString(3, item.getPattern());
			pstmt.setString(4, item.getItemCategory().getId());
			pstmt.setString(5, item.getItemUnit().getId());
			pstmt.setString(6, item.getItemNo());
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			throw new ApplicationException("修改物料信息失败！");
		}finally{
			Dbutil.close(pstmt);
		}
	}

	@Override
	public Item findItemById(Connection conn, String itemNo) {
		// TODO Auto-generated method stub
		String strSql = "select a.item_no,a.item_name,a.spec,a.pattern,a.item_category_id,a.item_unit_id,"+
						"b.name as item_category_name,c.name as item_unit_name "+
						"from t_items a,t_data_dict b,t_data_dict c "+
						"where a.item_category_id=b.id and a.item_unit_id=c.id and a.item_no = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Item item = null;
		try{
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, itemNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				item = new Item();
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				
				ItemCategory ic = new ItemCategory();
				ic.setId(rs.getString("item_category_id"));
				ic.setName(rs.getString("item_category_name"));
				item.setItemCategory(ic);
				
				ItemUnit it = new ItemUnit();
				it.setId(rs.getString("item_unit_id"));
				it.setName(rs.getString("item_unit_name"));
				item.setItemUnit(it);
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new ApplicationException("根据物料代码查询出错。物料代码【"+itemNo+"】");
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		return item;
	}

	@Override
	public PageModel<Item> findItemList(Connection conn,int pageNo, int pageSize, String condation) {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from (select a.item_no,a.item_name,a.spec,a.pattern,a.item_category_id,a.item_unit_id,b.name as item_category_name,c.name as item_unit_name from t_items a,t_data_dict b,t_data_dict c where a.item_category_id=b.id and a.item_unit_id=c.id");
		if(condation != null && !"".equals(condation)){
			sbSql.append(" and (a.item_no like '%" + condation + "%' or a.item_name like '%" + condation + "%')");
		}
		sbSql.append(") as items limit ?,?");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageModel<Item> pageModel = null;
		try{
			pstmt = conn.prepareStatement(sbSql.toString());
			//int pageStart = (pageNo-1) * pageSize;
			pstmt.setInt(1, (pageNo-1) * pageSize);
			//int pageEnd = pageNo * pageSize;
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			List<Item> itemList = new ArrayList<Item>();
			while(rs.next()){
				Item item = new Item();
				item.setItemNo(rs.getString("item_no"));
				item.setItemName(rs.getString("item_name"));
				ItemCategory itemCategory = new ItemCategory();
				itemCategory.setId(rs.getString("item_category_id"));
				itemCategory.setName(rs.getString("item_category_name"));
				item.setItemCategory(itemCategory);
				
				ItemUnit itemUnit = new ItemUnit();
				itemUnit.setId(rs.getString("item_unit_id"));
				itemUnit.setName(rs.getString("item_unit_name"));
				item.setItemUnit(itemUnit);
				
				item.setSpec(rs.getString("spec"));
				item.setPattern(rs.getString("pattern"));
				itemList.add(item);
			}
			pageModel = new PageModel<Item>();
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setList(itemList);
			int totalRecords = getTotalRecords(conn, condation);
			
			pageModel.setTotalRecords(totalRecords);
		}catch(SQLException e){
			e.printStackTrace();
			throw new ApplicationException("分页查询出错！");
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		return pageModel;
	}
	
	private int getTotalRecords(Connection conn,String condation)
	throws SQLException{
		String strSql = "select count(*) from t_items";
		if(condation != null && !"".equals(condation)){
			strSql += " where item_no like '%" + condation + "%' or item_name like '%" + condation + "%'";
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int temp = 0;
		try{
			pstmt = conn.prepareStatement(strSql);
			rs = pstmt.executeQuery();
			rs.next();
			temp = rs.getInt(1);
		}finally{
			Dbutil.close(rs);
			Dbutil.close(pstmt);
		}
		
		return temp;
	}

}
