package me.zohar.runscore.merchant.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.merchant.domain.MerchantOrder;

@Data
public class OrderGatheringCodeVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 收款渠道
	 */
	private String gatheringChannelCode;

	private String gatheringChannelName;

	/**
	 * 收款金额
	 */
	private Double gatheringAmount;

	/**
	 * 订单状态
	 */
	private String orderState;

	private String orderStateName;

	/**
	 * 提交时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date submitTime;

	/**
	 * 有效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date usefulTime;

	private String gatheringCodeStorageId;

	private String gatheringCodeUrl;

	/**
	 * 同步通知地址
	 */
	private String returnUrl;

	public static OrderGatheringCodeVO convertFor(MerchantOrder platformOrder) {
		if (platformOrder == null) {
			return null;
		}
		OrderGatheringCodeVO vo = new OrderGatheringCodeVO();
		BeanUtils.copyProperties(platformOrder, vo);
		vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel", vo.getGatheringChannelCode()));
		vo.setOrderStateName(DictHolder.getDictItemName("platformOrderState", vo.getOrderState()));
		vo.setReturnUrl(platformOrder.getPayInfo().getReturnUrl());
		return vo;
	}

}
