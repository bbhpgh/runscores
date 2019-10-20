package me.zohar.runscore.merchant.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.merchant.domain.MerchantOrder;

/**
 * 接单记录vo
 * 
 * @author zohar
 * @date 2019年6月29日
 *
 */
@Data
public class ReceiveOrderRecordVO {

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
	 * 接单时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receivedTime;

	/**
	 * 奖励金
	 */
	private Double bounty;

	/**
	 * 接单人账号id
	 */
	private String receivedAccountId;

	/**
	 * 接单人用户名
	 */
	private String receiverUserName;

	public static List<ReceiveOrderRecordVO> convertFor(List<MerchantOrder> platformOrders) {
		if (CollectionUtil.isEmpty(platformOrders)) {
			return new ArrayList<>();
		}
		List<ReceiveOrderRecordVO> vos = new ArrayList<>();
		for (MerchantOrder platformOrder : platformOrders) {
			vos.add(convertFor(platformOrder));
		}
		return vos;
	}

	public static ReceiveOrderRecordVO convertFor(MerchantOrder merchantOrder) {
		if (merchantOrder == null) {
			return null;
		}
		ReceiveOrderRecordVO vo = new ReceiveOrderRecordVO();
		BeanUtils.copyProperties(merchantOrder, vo);
		vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel", vo.getGatheringChannelCode()));
		vo.setOrderStateName(DictHolder.getDictItemName("platformOrderState", vo.getOrderState()));
		if (StrUtil.isNotBlank(merchantOrder.getReceivedAccountId()) && merchantOrder.getReceivedAccount() != null) {
			vo.setReceiverUserName(merchantOrder.getReceivedAccount().getUserName());
		}
		return vo;
	}

}
