package com.bjpowernode.drp.flowcard.domain;

import java.util.Date;
import java.util.List;

import com.bjpowernode.drp.basedata.domain.Client;
import com.bjpowernode.drp.basedata.domain.FiscalYearPeriod;
import com.bjpowernode.drp.sysmgr.domain.User;

public class FlowCard {
	/**
	 * 流向单号
	 */
	private String flowCardNo;
	
	/**
	 * 操作类型:A流向单 B盘点单据
	 */
	private String optType;
	
	/**
	 * 操作日期
	 */
	private Date optDate;
	
	/**
	 * 单据状态：S:送审 N：录入
	 */
	private String vouSts;
	
	/**
	 * 调整日期
	 */
	private Date adjustDate;
	
	/**
	 * 抽查日期
	 */
	private Date spotDate;
	
	/**
	 * 抽查描述
	 */
	private String spotDesc;
	
	/**
	 * 复审日期
	 */
	private Date confDate;
	
	/**
	 * 录入人
	 */
	private User recorder;
	
	/**
	 * 调整人
	 */
	private User adjuster;
	
	/**
	 * 复审人
	 */
	private User confirmer;
	
	/**
	 * 抽查人
	 */
	private User spotter;
	
	/**
	 * 供方分销商
	 */
	private Client cilent;
	
	/**
	 * 会计核算期
	 */
	private FiscalYearPeriod fiscalYearPeriod;
	
	/**
	 * 流向单明细
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
