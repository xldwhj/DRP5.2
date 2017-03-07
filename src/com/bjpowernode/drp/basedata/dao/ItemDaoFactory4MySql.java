package com.bjpowernode.drp.basedata.dao;

/**
 * mysql¹¤³§
 * @author Administrator
 *
 */
public class ItemDaoFactory4MySql implements ItemDaoFactory {

	@Override
	public ItemDao createItemDao() {
		// TODO Auto-generated method stub
		return new ItemDao4MySqlImpl();
	}

}
