package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;


import com.bjpowernode.drp.basedata.dao.ItemDao;
//import com.bjpowernode.drp.basedata.dao.ItemDaoFactory4MySql;
//import com.bjpowernode.drp.basedata.dao.ItemDaoFactory4Oracle;
import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.ApplicationException;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.Dbutil;
import com.bjpowernode.drp.util.PageModel;
import com.bjpowernode.drp.util.XmlConfigReader;

/**
 * 物料管理接口实现
 * @author Administrator
 *
 */
public class ItemManagerImpl implements ItemManager {

	private ItemDao itemDao = null;
	/*public ItemManagerImpl() {
		// TODO Auto-generated constructor stub
		String className = XmlConfigReader.getInstance().getDaoFactory("item-dao-sqlFactory");
		ItemDaoFactory factory = null;
		try {
			factory = (ItemDaoFactory)Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		itemDao = factory.createItemDao();//创建sql	
		}*/
	
	public ItemManagerImpl() {
		itemDao = (ItemDao)BeanFactory.getInstance().getDaoObject(ItemDao.class);
	}
	
	@Override
	public void addItem(Item item) {
		//ItemDaoFactory factory = null;
		/*ItemDaoFactory factory = new ItemDaoFactory4Oracle();
		ItemDao itemDao = factory.createItemDao();//创建oracle的Dao，需要换工厂*/		
		/*ItemDaoFactory factory = new ItemDaoFactory4MySql();
		ItemDao itemDao = factory.createItemDao();//创建MySql的Dao*/	
		Connection conn = null;
		try{
			conn = Dbutil.getConnection();//获取Connnection对象
			if(itemDao.findItemById(conn, item.getItemNo()) != null){
				throw new ApplicationException("物料代码已经存在，代码=【" + item.getItemNo() + "】");
			}
			itemDao.addItem(conn, item);
		}finally{
			Dbutil.close(conn);
		}
	}

	@Override
	public boolean delItem(String[] itemNos) {
		Connection conn = null;
		try{
			conn = Dbutil.getConnection();
			return itemDao.delItem(conn, itemNos);
		}finally{
			Dbutil.close(conn);
		}
	}

	@Override
	public void modifyItem(Item item) {
		Connection conn = null;
		try{
			conn = Dbutil.getConnection();
			itemDao.modifyItem(conn, item);
		}finally{
			Dbutil.close(conn);
		}
	}

	public Item findItemById(String itemNo) {
		Connection conn = null;
		try{
			conn = Dbutil.getConnection();
			return itemDao.findItemById(conn,itemNo);
		}finally{
			Dbutil.close(conn);
		}
	}

	@Override
	public PageModel<Item> findItemList(int pageNo, int pageSize, String condation) {
		Connection conn = null;
		try{
			conn = Dbutil.getConnection();//获取Connnection对象
			return itemDao.findItemList(conn, pageNo, pageSize, condation);
		}finally{
			Dbutil.close(conn);
		}
	}
	
/*	public void setItemDao(ItemDao itemDao){
		this.itemDao = itemDao;
	}*/
}
