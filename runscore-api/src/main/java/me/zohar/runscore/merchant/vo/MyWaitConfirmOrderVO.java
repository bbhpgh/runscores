package me.zohar.runscore.merchant.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.merchant.domain.MerchantOrder;

@Data
public class MyWaitConfirmOrderVO {

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
	 * 接单时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receivedTime;

	public static List<MyWaitConfirmOrderVO> convertFor(List<MerchantOrder> platformOrders) {
		if (CollectionUtil.isEmpty(platformOrders)) {
			return new ArrayList<>();
		}
		List<MyWaitConfirmOrderVO> vos = new ArrayList<>();
		for (MerchantOrder platformOrder : platformOrders) {
			vos.add(convertFor(platformOrder));
		}
		return vos;
	}

	public static MyWaitConfirmOrderVO convertFor(MerchantOrder platformOrder) {
		if (platformOrder == null) {
			return null;
		}
		MyWaitConfirmOrderVO vo = new MyWaitConfirmOrderVO();
		BeanUtils.copyProperties(platformOrder, vo);
		vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel", vo.getGatheringChannelCode()));
		return vo;
	}
	
}
