package com.bjpowernode.drp.flowcard.domain;

import java.math.BigDecimal;

import com.bjpowernode.drp.basedata.domain.AimClient;
import com.bjpowernode.drp.basedata.domain.Item;

/**
 * ������ϸ
 * @author Administrator
 *
 */
public class FlowCardDetail {
	/**
	 * ��������
	 */
	private BigDecimal optQty;
	
	/**
	 * ����ԭ��
	 */
	private String adjustReason;
	
	/**
	 * �������
	 * Y:���� N��δ����
	 */
	private String adjustFlag;
	
	/**
	 * �跽�ͻ�
	 */
	private AimClient aimClient;
	
	/**
	 * ����
	 */
	private Item item;
	
	/**
	 * ��������Ϣ
	 */
	private FlowCard flowCard;

	public BigDecimal getOptQty() {
		return optQty;
	}

	public void setOptQty(BigDecimal optQty) {
		this.optQty = optQty;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public String getAdjustFlag() {
		return adjustFlag;
	}

	public void setAdjustFlag(String adjustFlag) {
		this.adjustFlag = adjustFlag;
	}

	public AimClient getAimClient() {
		return aimClient;
	}

	public void setAimClient(AimClient aimClient) {
		this.aimClient = aimClient;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public FlowCard getFlowCard() {
		return flowCard;
	}

	public void setFlowCard(FlowCard flowCard) {
		this.flowCard = flowCard;
	}
	
}
