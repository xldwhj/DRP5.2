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
 * ���󹤳�,��Ҫ��������ϵ�еĲ�Ʒ
 * 1��Managerϵ��
 * 2��Dao��Ʒϵ��
 * @author Administrator
 *
 */
public class BeanFactory {
	private static BeanFactory instacne = new BeanFactory();
	
	private Document doc;
	private final String beansConfigFile = "beans-config.xml";
	
	//����Service��ض���
	private Map serviceMap = new HashMap();
	
	//����Dao��ض���
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
	 * ���ݲ�Ʒ���ȡ��Serviceϵ�еĲ�Ʒ
	 * @param beanId
	 * @return
	 */
	public synchronized Object getServiceObject(Class Service){
		if(serviceMap.containsKey(Service.getName())){
			return serviceMap.get(Service.getName());
		}
		//Xpath�﷨����beanIdʹ��selectSingleNodeÿ���õ�һ��ֵ
		Element beanEle = (Element)doc.selectSingleNode("//service[@id=\"" + Service.getName() + "\"]");
		/*String className = beanEle.getTextTrim();*/
		String className = beanEle.attributeValue("class");
		//System.out.println(className);
		//ͨ��Class����װ����
		Object service=null;
		try {
			service = Class.forName(className).newInstance();
			//�������õĶ���ŵ�beansMap��
			serviceMap.put(Service.getName(), service);
		} catch (Exception e) {
			throw new RuntimeException("");
		}
		return service;
	}
	
	/**
	 * ���ݲ�Ʒ���ȡ��Daoϵ�еĲ�Ʒ
	 * @param beanId
	 * @return
	 */
	public synchronized Object getDaoObject(Class Dao){
		if(serviceMap.containsKey(Dao.getName())){
			return serviceMap.get(Dao.getName());
		}
		//Xpath�﷨����beanIdʹ��selectSingleNodeÿ���õ�һ��ֵ
		Element beanEle = (Element)doc.selectSingleNode("//dao[@id=\"" + Dao.getName() + "\"]");
		/*String className = beanEle.getTextTrim();*/
		String className = beanEle.attributeValue("class");
		//System.out.println(className);
		//ͨ��Class����װ����
		Object dao = null;
		try {
			dao = Class.forName(className).newInstance();
			//�������õĶ���ŵ�beansMap��
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
