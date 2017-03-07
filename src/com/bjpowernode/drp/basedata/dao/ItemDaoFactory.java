package com.bjpowernode.drp.basedata.dao;

/**
 * Dao工厂的接口
 * @author Administrator
 *
 */
public interface ItemDaoFactory {
	
	/**
	 * 创建物料Dao
	 * @return
	 */
	public ItemDao createItemDao();
}
