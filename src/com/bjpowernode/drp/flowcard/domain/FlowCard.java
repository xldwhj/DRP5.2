package com.bjpowernode.drp.flowcard.domain;

import java.util.Date;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.Client;
import com.bjpowernode.drp.basedata.domain.FiscalYearPeriod;
import com.bjpowernode.drp.sysmgr.domain.User;

public class FlowCard {
	/**
	 * ���򵥺�
	 */
	private String flowCardNo;
	
	/**
	 * ��������:A���� B�̵㵥��
	 */
	private String optType;
	
	/**
	 * ��������
	 */
	private Date optDate;
	
	/**
	 * ����״̬��S:���� N��¼��
	 */
	private String vouSts;
	
	/**
	 * ��������
	 */
	private Date adjustDate;
	
	/**
	 * �������
	 */
	private Date spotDate;
	
	/**
	 * �������
	 */
	private String spotDesc;
	
	/**
	 * ��������
	 */
	private Date confDate;
	
	/**
	 * ¼����
	 */
	private User recorder;
	
	/**
	 * ������
	 */
	private User adjuster;
	
	/**
	 * ������
	 */
	private User confirmer;
	
	/**
	 * �����
	 */
	private User spotter;
	
	/**
	 * ����������
	 */
	private Client cilent;
	
	/**
	 * ��ƺ�����
	 */
	private FiscalYearPeriod fiscalYearPeriod;
	
	/**
	 * ������ϸ
	 */
	private List<FlowCardDetail> flowCardDetail;

	public String getFlowCardNo() {
		return flowCardNo;
	}

	public void setFlowCardNo(String flowCardNo) {
		this.flowCardNo = flowCardNo;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getVouSts() {
		return vouSts;
	}

	public void setVouSts(String vosSts) {
		this.vouSts = vosSts;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public Date getSpotDate() {
		return spotDate;
	}

	public void setSpotDate(Date spotDate) {
		this.spotDate = spotDate;
	}

	public String getSpotDesc() {
		return spotDesc;
	}

	public void setSpotDesc(String spotDesc) {
		this.spotDesc = spotDesc;
	}

	public Date getConfDate() {
		return confDate;
	}

	public void setConfDate(Date confDate) {
		this.confDate = confDate;
	}

	public User getRecorder() {
		return recorder;
	}

	public void setRecorder(User recorder) {
		this.recorder = recorder;
	}

	public User getAdjuster() {
		return adjuster;
	}

	public void setAdjuster(User adjuster) {
		this.adjuster = adjuster;
	}

	public User getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(User confirmer) {
		this.confirmer = confirmer;
	}

	public User getSpotter() {
		return spotter;
	}

	public void setSpotter(User spotter) {
		this.spotter = spotter;
	}

	public Client getCilent() {
		return cilent;
	}

	public void setCilent(Client cilent) {
		this.cilent = cilent;
	}

	public FiscalYearPeriod getFiscalYearPeriod() {
		return fiscalYearPeriod;
	}

	public void setFiscalYearPeriod(FiscalYearPeriod fiscalYearPeriod) {
		this.fiscalYearPeriod = fiscalYearPeriod;
	}

	public List<FlowCardDetail> getFlowCardDetail() {
		return flowCardDetail;
	}

	public void setFlowCardDetail(List<FlowCardDetail> flowCardDetail) {
		this.flowCardDetail = flowCardDetail;
	}
}
