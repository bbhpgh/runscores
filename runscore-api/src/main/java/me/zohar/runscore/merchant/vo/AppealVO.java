package me.zohar.runscore.merchant.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.merchant.domain.Appeal;

@Data
public class AppealVO {

	private String id;
	
	/**
	 * 发起方
	 */
	private String initiatorObj;
	
	private String initiatorObjName;

	/**
	 * 申诉类型
	 */
	private String appealType;

	private String appealTypeName;
	
	/**
	 * 处理方式
	 */
	private String processWay;
	
	private String processWayName;

	/**
	 * 实际支付金额
	 */
	private Double actualPayAmount;

	private List<String> userSreenshotIds = new ArrayList<>();

	private List<String> merchantSreenshotIds = new ArrayList<>();

	private String state;

	private String stateName;

	/**
	 * 发起时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date initiationTime;

	private String orderNo;

	private String merchantName;

	private String gatheringChannelName;

	/**
	 * 收款金额
	 */
	private Double gatheringAmount;

	/**
	 * 接单人用户名
	 */
	private String receiverUserName;

	/**
	 * 接单时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date receivedTime;
	
	public static List<AppealVO> convertFor(List<Appeal> appeals) {
		if (CollectionUtil.isEmpty(appeals)) {
			return new ArrayList<>();
		}
		List<AppealVO> vos = new ArrayList<>();
		for (Appeal appeal : appeals) {
			vos.add(convertFor(appeal));
		}
		return vos;
	}

	public static AppealVO convertFor(Appeal appeal) {
		if (appeal == null) {
			return null;
		}
		AppealVO vo = new AppealVO();
		BeanUtils.copyProperties(appeal, vo);
		vo.setStateName(DictHolder.getDictItemName("appealState", vo.getState()));
		vo.setAppealTypeName(DictHolder.getDictItemName("appealType", vo.getAppealType()));
		vo.setInitiatorObjName(DictHolder.getDictItemName("appealInitiatorObj", vo.getInitiatorObj()));
		vo.setProcessWayName(DictHolder.getDictItemName("appealProcessWay", vo.getProcessWay()));
		if (StrUtil.isNotBlank(appeal.getUserSreenshotIds())) {
			vo.setUserSreenshotIds(Arrays.asList(appeal.getUserSreenshotIds().split(",")));
		}
		if (StrUtil.isNotBlank(appeal.getMerchantSreenshotIds())) {
			vo.setMerchantSreenshotIds(Arrays.asList(appeal.getMerchantSreenshotIds().split(",")));
		}

		if (appeal.getMerchantOrder() != null) {
			vo.setOrderNo(appeal.getMerchantOrder().getOrderNo());
			vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel",
					appeal.getMerchantOrder().getGatheringChannelCode()));
			vo.setGatheringAmount(appeal.getMerchantOrder().getGatheringAmount());
			if (appeal.getMerchantOrder().getMerchant() != null) {
				vo.setMerchantName(appeal.getMerchantOrder().getMerchant().getName());
			}

			if (StrUtil.isNotBlank(appeal.getMerchantOrder().getReceivedAccountId())
					&& appeal.getMerchantOrder().getReceivedAccount() != null) {
				vo.setReceiverUserName(appeal.getMerchantOrder().getReceivedAccount().getUserName());
			}
			vo.setReceivedTime(appeal.getMerchantOrder().getReceivedTime());
		}
		return vo;
	}

}
