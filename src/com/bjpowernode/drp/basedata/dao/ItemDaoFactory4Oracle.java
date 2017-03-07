package com.bjpowernode.drp.basedata.dao;

/**
 * oracle¹¤³§
 * @author Administrator
 *
 */
public class ItemDaoFactory4Oracle implements ItemDaoFactory {

	@Override
	public ItemDao createItemDao() {
		// TODO Auto-generated method stub
		return new ItemDao4OracleImpl();
	}

}
