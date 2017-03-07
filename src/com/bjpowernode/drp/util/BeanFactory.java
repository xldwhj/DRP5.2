package com.bjpowernode.drp.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bjpowernode.drp.basedata.dao.ItemDao;
import com.bjpowernode.drp.basedata.manager.ItemManager;


/**
 * 抽象工厂,主要创建两个系列的产品
 * 1、Manager系列
 * 2、Dao产品系列
 * @author Administrator
 *
 */
public class BeanFactory {
	private static BeanFactory instacne = new BeanFactory();
	
	private Document doc;
	private final String beansConfigFile = "beans-config.xml";
	
	//保存Service相关对象
	private Map serviceMap = new HashMap();
	
	//保存Dao相关对象
	private Map daoMap = new HashMap();
	
	private BeanFactory(){
		try {
			doc = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(beansConfigFile));
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static BeanFactory getInstance(){
		return instacne;
	}
	
	/**
	 * 根据产品编号取得Service系列的产品
	 * @param beanId
	 * @return
	 */
	public synchronized Object getServiceObject(Class Service){
		if(serviceMap.containsKey(Service.getName())){
			return serviceMap.get(Service.getName());
		}
		//Xpath语法根据beanId使用selectSingleNode每次拿到一个值
		Element beanEle = (Element)doc.selectSingleNode("//service[@id=\"" + Service.getName() + "\"]");
		/*String className = beanEle.getTextTrim();*/
		String className = beanEle.attributeValue("class");
		//System.out.println(className);
		//通过Class可以装载类
		Object service=null;
		try {
			service = Class.forName(className).newInstance();
			//将创建好的对象放到beansMap中
			serviceMap.put(Service.getName(), service);
		} catch (Exception e) {
			throw new RuntimeException("");
		}
		return service;
	}
	
	/**
	 * 根据产品编号取得Dao系列的产品
	 * @param beanId
	 * @return
	 */
	public synchronized Object getDaoObject(Class Dao){
		if(serviceMap.containsKey(Dao.getName())){
			return serviceMap.get(Dao.getName());
		}
		//Xpath语法根据beanId使用selectSingleNode每次拿到一个值
		Element beanEle = (Element)doc.selectSingleNode("//dao[@id=\"" + Dao.getName() + "\"]");
		/*String className = beanEle.getTextTrim();*/
		String className = beanEle.attributeValue("class");
		//System.out.println(className);
		//通过Class可以装载类
		Object dao = null;
		try {
			dao = Class.forName(className).newInstance();
			//将创建好的对象放到beansMap中
			serviceMap.put(Dao.getName(), dao);
		} catch (Exception e) {
			throw new RuntimeException("");
		}
		return dao;
	}
	
	public static void main(String[] args){
		ItemManager itemManager = (ItemManager)BeanFactory.getInstance().getServiceObject(ItemManager.class);
		ItemDao itemDao = (ItemDao)BeanFactory.getInstance().getDaoObject(ItemDao.class);
		System.out.println(itemManager);
		System.out.println(itemDao);
	}
}
