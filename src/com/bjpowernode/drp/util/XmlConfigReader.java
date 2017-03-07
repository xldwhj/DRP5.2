package com.bjpowernode.drp.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 解析sys-config.xml文件
 */
public class XmlConfigReader {
	
	//懒汉式（延迟加载lazy）
	private static XmlConfigReader instance = null;
	//key=名称，value=具体类完整路径
	/*private Map<String,String> daoFactoryMap = new HashMap<String,String>();*/
	//保存jdbc相关配置信息
	private JdbcConfig jdbcConfig = new JdbcConfig();
	private XmlConfigReader() {
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml");
		try {
			Document doc = reader.read(in);
			//取得jdbc相关配置信息
			Element driverNameElt = (Element)doc.selectObject("/config/db-info/driver-name");
			Element urlElt = (Element)doc.selectObject("/config/db-info/url");
			Element userNameElt = (Element)doc.selectObject("/config/db-info/user-name");
			Element passwordElt = (Element)doc.selectObject("/config/db-info/password");
			
			//设置jdbc相关的配置
			//System.out.println(driverNameElt.getStringValue());
			jdbcConfig.setDriverName(driverNameElt.getStringValue());
			jdbcConfig.setUrl(urlElt.getStringValue());
			jdbcConfig.setUserName(userNameElt.getStringValue());
			jdbcConfig.setPassword(passwordElt.getStringValue());
			//取得DaoFactory信息
			/*List daoFactoryList = doc.selectNodes("/config/dao-factory/*");//会把/config/dao-factory/下的所有内容全部返回
			for(int i=0;i<daoFactoryList.size();i++){
				Element daoFactoryElt = (Element)daoFactoryList.get(i);
				//getName拿到标签名item-dao-sqlFactory
				String tagName = daoFactoryElt.getName();
				//getText拿到对应标签item-dao-sqlFactory的内容
				String tagText = daoFactoryElt.getText();
				//放入到Map中
				daoFactoryMap.put(tagName, tagText);
			}*/
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static synchronized XmlConfigReader getInstance() {
		if (instance == null) {
			instance = new XmlConfigReader();
		}
		return instance;
	}
	
	/**
	 * 返回jdbc相关配置
	 * @return
	 */
	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}
	
	/**
	 * 根据标签名称取得DaoFactory的名字
	 * @param name
	 * @return DaoFactory的完整类路径
	 */
	/*public String getDaoFactory(String name){
		//return (String)daoFactoryMap.get(name);
		return daoFactoryMap.get(name);
	}*/
	/*public String getDaoFactory(String itemFactoryName){
		
	}*/
//	public static void main(String[] args) {
//		JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
//		System.out.println(jdbcConfig.getDriverName());
//		System.out.println(jdbcConfig.getUrl());
//		System.out.println(jdbcConfig.getUserName());
//		//System.out.println(jdbcConfig);
//	}
}
