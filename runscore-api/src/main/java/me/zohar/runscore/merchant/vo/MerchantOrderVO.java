package me.zohar.runscore.merchant.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import me.zohar.runscore.dictconfig.ConfigHolder;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.merchant.domain.MerchantOrder;

@Data
public class MerchantOrderVO {

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
	 * 提交时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date submitTime;

	/**
	 * 有效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date usefulTime;

	/**
	 * 订单状态
	 */
	private String orderState;

	private String orderStateName;

	/**
	 * 备注
	 */
	private String note;

	private String platformId;

	private String platformName;

	/**
	 * 接单人账号id
	 */
	private String receivedAccountId;

	/**
	 * 接单人用户名
	 */
	private String receiverUserName;

	/**
	 * 接单时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receivedTime;

	/**
	 * 平台确认时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date platformConfirmTime;

	/**
	 * 处理时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dealTime;

	/**
	 * 确认时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date confirmTime;

	/**
	 * 奖励金
	 */
	private Double bounty;

	private String payUrl;

	private MerchantOrderPayInfoVO payInfo;

	public static List<MerchantOrderVO> convertFor(List<MerchantOrder> platformOrders) {
		if (CollectionUtil.isEmpty(platformOrders)) {
			return new ArrayList<>();
		}
		List<MerchantOrderVO> vos = new ArrayList<>();
		for (MerchantOrder platformOrder : platformOrders) {
			vos.add(convertFor(platformOrder));
		}
		return vos;
	}

	public static MerchantOrderVO convertFor(MerchantOrder merchantOrder) {
		if (merchantOrder == null) {
			return null;
		}
		MerchantOrderVO vo = new MerchantOrderVO();
		BeanUtils.copyProperties(merchantOrder, vo);
		vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel", vo.getGatheringChannelCode()));
		vo.setOrderStateName(DictHolder.getDictItemName("platformOrderState", vo.getOrderState()));
		if (merchantOrder.getMerchant() != null) {
			vo.setPlatformName(merchantOrder.getMerchant().getName());
		}
		if (StrUtil.isNotBlank(merchantOrder.getReceivedAccountId()) && merchantOrder.getReceivedAccount() != null) {
			vo.setReceiverUserName(merchantOrder.getReceivedAccount().getUserName());
		}
		vo.setPayUrl(ConfigHolder.getConfigValue("merchantOrderPayUrl") + vo.getOrderNo());
		vo.setPayInfo(MerchantOrderPayInfoVO.convertFor(merchantOrder.getPayInfo()));
		return vo;
	}

}
