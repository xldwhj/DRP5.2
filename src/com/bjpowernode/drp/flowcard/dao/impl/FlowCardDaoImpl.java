package com.bjpowernode.drp.flowcard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bjpowernode.drp.flowcard.dao.FlowCardDao;
import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.domain.FlowCardDetail;
import com.bjpowernode.drp.util.ConnectionManager;
import com.bjpowernode.drp.util.DaoException;
/**
 * 对流向单维护Dao层接口进行实现
 * @author Administrator
 *
 */
public class FlowCardDaoImpl implements FlowCardDao {

	/**
	 * 同一个线程中的Connection都是一个
	 */
	//同步标志，防止两个人同时操作得到同一个编号
	public synchronized String generateVouNo() throws DaoException {
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String strSql = "select max(flow_card_no) from t_flow_card_master where substr(flow_card_no,1,8) = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String vouNo = currentDate + "0001";
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, currentDate);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getLong(1) != 0){
				vouNo += (rs.getLong(1) + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl-->>generateVouNo,exception=" + e);
			throw new DaoException(e);
		}finally{
			ConnectionManager.close(rs);
			ConnectionManager.close(pstmt);
		}
		
		return vouNo;
	}

	@Override
	public void addFlowCardMaster(String flowCardVouNo, FlowCard flowCard)
			throws DaoException {
		// TODO Auto-generated method stub
		StringBuffer sbSql = new StringBuffer();
		PreparedStatement pstmt = null;
		sbSql.append("insert into t_flow_card_mast (flow_card_no,opt_type,fiscal_year_period_id,"
				+ "client_id,recorder_id,opt_date,vou_sts) values(?,?,?,?,?,?,?)");
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setString(1, flowCardVouNo);
			pstmt.setString(2, flowCard.getOptType());
			pstmt.setInt(3, flowCard.getFiscalYearPeriod().getId());
			pstmt.setInt(4, flowCard.getCilent().getId());
			pstmt.setString(5, flowCard.getRecorder().getUserId());
			//时间戳
			pstmt.setTimestamp(6, new Timestamp(flowCard.getOptDate().getTime()));
			pstmt.setString(7, flowCard.getVouSts());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl-->>addFlowCard,exception" + e);
			throw new DaoException(e);
		}finally{
			ConnectionManager.close(pstmt);
		}
	}

	@Override
	public void addFlowCardDetail(String flowCardVouNo,
			List<FlowCardDetail> flowCardDetailList) throws DaoException {
		//采用批量执行的方法
		String strSql = "insert into t_flow_detail (flow_card_no,aim_client_id,item_no,opt_qty,adjust_flag) values (?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(strSql);
			for(Iterator<FlowCardDetail> iter = flowCardDetailList.iterator();iter.hasNext();){
				FlowCardDetail flowCardDetail = iter.next();
				pstmt.setString(1, flowCardVouNo);
				pstmt.setInt(2, flowCardDetail.getAimClient().getId());
				pstmt.setString(3, flowCardDetail.getItem().getItemNo());
				pstmt.setBigDecimal(4, flowCardDetail.getOptQty());//操作数量
				pstmt.setString(5, flowCardDetail.getAdjustFlag());//调整标记
				pstmt.addBatch();//批量执行
			}
			pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FlowCardDaoImpl-->>addFlowCardDetail,exception" + e);
			throw new DaoException(e);
		}finally{
			ConnectionManager.close(pstmt);
		}
	}

	@Override
	public void delFlowCardMaster(String[] flowCardVouNos) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delFlowCardDetail(String[] flowCardVouNos) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyFlowCardMaster(String flowCardVouNo, FlowCard flowCard)
			throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyFlowCardDetail(String flowCardVouNo,
			List<FlowCardDetail> flowCardDetailList) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FlowCard> findFlowCardList(int page, int pageSize,
			String clientId, Date beginDate, Date endDate) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRecordCount(String clientId, Date beginDate, Date endDate)
			throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void auditFlowCard(String[] flowCardVouNos) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public FlowCard findFlowCardMasterById(String vouNo) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlowCardDetail> findFlowCardDetailList(String vouNo)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
