package com.bjpowernode.drp.basedata.dao;

/**
 * mysql����
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
