package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

public class ItemDao4OracleImpl implements ItemDao {

	@Override
	public void addItem(Connection conn, Item item) {
		// TODO Auto-generated method stub
		System.out.println("ItemDao4OracleImpl.addItem()");
	}

	@Override
	public boolean delItem(Connection conn, String[] itemNos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void modifyItem(Connection conn, Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item findItemById(Connection conn, String itemNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageModel findItemList(Connection conn, int pagNo, int pagSize,
			String condation) {
		// TODO Auto-generated method stub
		return null;
	}

}
