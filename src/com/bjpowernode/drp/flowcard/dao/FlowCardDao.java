package com.bjpowernode.drp.flowcard.dao;

import java.util.Date;
import java.util.List;

import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.domain.FlowCardDetail;
import com.bjpowernode.drp.util.DaoException;

/**
 * ���򵥵����ݷ��ʽӿڣ���ΪMySql�Լ�Oracle�����ݿ�ʵ�ֵĽӿ�
 * @author Administrator
 *
 */
public interface FlowCardDao {
	/**
	 * �������򵥺�
	 * @return
	 */
	public String generateVouNo()
	throws DaoException;
	
	/**
	 * �����������Ϣ
	 * @param flowCardVouNo
	 * @param flowCard
	 */
	public void addFlowCardMaster(String flowCardVouNo,FlowCard flowCard)
	throws DaoException;
	
	/**
	 * ���������ϸ
	 * @throws DaoException
	 */
	public void addFlowCardDetail(String flowCardVouNo,List<FlowCardDetail> flowCardDetailList)
	throws DaoException;
	
	/**
	 * ɾ����������Ϣ
	 * @param flowCardVouNo
	 * @throws DaoException
	 */
	public void delFlowCardMaster(String[] flowCardVouNos)
	throws DaoException;
	
	/**
	 * ɾ��������ϸ
	 * @param flowCardVouNos
	 * @throws DaoException
	 */
	public void delFlowCardDetail(String[] flowCardVouNos)
	throws DaoException;
	
	/**
	 * �޸���������Ϣ
	 * @param flowCardVouNo
	 * @param flowCard
	 * @throws DaoException
	 */
	public void modifyFlowCardMaster(String flowCardVouNo,FlowCard flowCard)
	throws DaoException;
	
	/**
	 * �޸�������ϸ��Ϣ
	 * @param flowCardVouNo
	 * @param flowCard
	 * @throws DaoException
	 */
	public void modifyFlowCardDetail(String flowCardVouNo,List<FlowCardDetail> flowCardDetailList)
	throws DaoException;
	
	/**
	 * ��ҳ��ѯ
	 * @param page
	 * @param pageSize
	 * @param clientId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<FlowCard> findFlowCardList(int page,int pageSize,String clientId,Date beginDate,Date endDate)
	throws DaoException;
	
	/**
	 * ��������ȡ�ü�¼��
	 * @param clientId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws DaoException
	 */
	public int getRecordCount(String clientId,Date beginDate,Date endDate)
	throws DaoException;
	
	/**
	 * ��������
	 * @param flowCardVouNos
	 * @throws DaoException
	 */
	public void auditFlowCard(String[] flowCardVouNos )
	throws DaoException;
	
	/**
	 * ������������Ϣ
	 * @return
	 * @throws DaoException
	 */
	public FlowCard findFlowCardMasterById(String vouNo)
	throws DaoException;
	
	/**
	 * ��ѯ������ϸ
	 * @return
	 * @throws DaoException
	 */
	public List<FlowCardDetail> findFlowCardDetailList(String vouNo)
			throws DaoException;
}
