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
 * ����sys-config.xml�ļ�
 */
public class XmlConfigReader {
	
	//����ʽ���ӳټ���lazy��
	private static XmlConfigReader instance = null;
	//key=���ƣ�value=����������·��
	/*private Map<String,String> daoFactoryMap = new HashMap<String,String>();*/
	//����jdbc���������Ϣ
	private JdbcConfig jdbcConfig = new JdbcConfig();
	private XmlConfigReader() {
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sys-config.xml");
		try {
			Document doc = reader.read(in);
			//ȡ��jdbc���������Ϣ
			Element driverNameElt = (Element)doc.selectObject("/config/db-info/driver-name");
			Element urlElt = (Element)doc.selectObject("/config/db-info/url");
			Element userNameElt = (Element)doc.selectObject("/config/db-info/user-name");
			Element passwordElt = (Element)doc.selectObject("/config/db-info/password");
			
			//����jdbc��ص�����
			//System.out.println(driverNameElt.getStringValue());
			jdbcConfig.setDriverName(driverNameElt.getStringValue());
			jdbcConfig.setUrl(urlElt.getStringValue());
			jdbcConfig.setUserName(userNameElt.getStringValue());
			jdbcConfig.setPassword(passwordElt.getStringValue());
			//ȡ��DaoFactory��Ϣ
			/*List daoFactoryList = doc.selectNodes("/config/dao-factory/*");//���/config/dao-factory/�µ���������ȫ������
			for(int i=0;i<daoFactoryList.size();i++){
				Element daoFactoryElt = (Element)daoFactoryList.get(i);
				//getName�õ���ǩ��item-dao-sqlFactory
				String tagName = daoFactoryElt.getName();
				//getText�õ���Ӧ��ǩitem-dao-sqlFactory������
				String tagText = daoFactoryElt.getText();
				//���뵽Map��
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
	 * ����jdbc�������
	 * @return
	 */
	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}
	
	/**
	 * ���ݱ�ǩ����ȡ��DaoFactory������
	 * @param name
	 * @return DaoFactory��������·��
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
