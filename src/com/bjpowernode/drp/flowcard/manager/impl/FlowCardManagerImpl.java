package com.bjpowernode.drp.flowcard.manager.impl;

import java.sql.Connection;
import java.util.Date;

import com.bjpowernode.drp.flowcard.dao.FlowCardDao;
import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.manager.FlowCardManager;
import com.bjpowernode.drp.util.ApplicationException;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.ConnectionManager;
import com.bjpowernode.drp.util.DaoException;
import com.bjpowernode.drp.util.PageModel;

public class FlowCardManagerImpl implements FlowCardManager {
	
	private  FlowCardDao flowCardDao;
	
	public FlowCardManagerImpl() {
		//ȡ��flowCardDao
		this.flowCardDao = (FlowCardDao)BeanFactory.getInstance().getDaoObject(FlowCard.class);
	}
	@Override
	public void addFlowCard(FlowCard flowCard) throws ApplicationException {
		Connection conn = null;
		try {
			//ȡ��connection
			conn = ConnectionManager.getConnection();
			
			//��ʼ����
			ConnectionManager.beginTransaction(conn);
			
			//�����������������ڴ���ͬһ���߳��У�����ʹ�õ���ͬһ��Connection
			//�������򵥵���
			String flowCardVouNo = flowCardDao.generateVouNo();
			
			//�����������Ϣ
			flowCardDao.addFlowCardMaster(flowCardVouNo, flowCard);
			
			//���������ϸ��Ϣ
			flowCardDao.addFlowCardDetail(flowCardVouNo, flowCard.getFlowCardDetail());
			
			//�ύ����
			ConnectionManager.commitTransaction(conn);
		} catch (DaoException e) {
			//�����쳣���ع�����
			ConnectionManager.rollbackTransaction(conn);
			throw new ApplicationException("�������ʧ��");	
		}finally{
			//��ThreadLocal�йرղ����Connection
			ConnectionManager.closeConnection();
		}
		
	}

	@Override
	public void delFlowCard(String[] flowCardVouNos)
			throws ApplicationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyFlowCard(FlowCard flowCard) throws ApplicationException {
		// TODO Auto-generated method stub

	}

	@Override
	public PageModel findFlowCardList(int page, int pageSize, String clientId,
			Date beginDate, Date endDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void auditFlowCard(String[] flowCardVouNos)
			throws ApplicationException {
		// TODO Auto-generated method stub

	}

	@Override
	public FlowCard findFlowCardDetail(String flowCardVouNo)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
