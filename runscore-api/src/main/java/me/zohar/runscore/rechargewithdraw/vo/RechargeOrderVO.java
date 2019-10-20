package me.zohar.runscore.rechargewithdraw.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.rechargewithdraw.domain.RechargeOrder;

@Data
public class RechargeOrderVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 支付通道id
	 */
	private String payChannelId;

	/**
	 * 支付通道名称
	 */
	private String payChannelName;

	/**
	 * 充值金额
	 */
	private Double rechargeAmount;

	/**
	 * 实际支付金额
	 */
	private Double actualPayAmount;

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

	/**
	 * 支付地址
	 */
	private String payUrl;

	/**
	 * 支付时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;

	/**
	 * 处理时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dealTime;

	/**
	 * 结算时间,即更新到账号余额的时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date settlementTime;

	/**
	 * 存款时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date depositTime;

	/**
	 * 存款人姓名
	 */
	private String depositor;

	private String bankName;

	/**
	 * 开户人姓名
	 */
	private String accountHolder;

	/**
	 * 银行卡账号
	 */
	private String bankCardAccount;

	/**
	 * 用户账号id
	 */
	private String userAccountId;

	/**
	 * 用户名
	 */
	private String userName;

	public static List<RechargeOrderVO> convertFor(List<RechargeOrder> rechargeOrders) {
		if (CollectionUtil.isEmpty(rechargeOrders)) {
			return new ArrayList<>();
		}
		List<RechargeOrderVO> vos = new ArrayList<>();
		for (RechargeOrder rechargeOrder : rechargeOrders) {
			vos.add(convertFor(rechargeOrder));
		}
		return vos;
	}

	public static RechargeOrderVO convertFor(RechargeOrder rechargeOrder) {
		if (rechargeOrder == null) {
			return null;
		}
		RechargeOrderVO vo = new RechargeOrderVO();
		BeanUtils.copyProperties(rechargeOrder, vo);
		vo.setOrderStateName(DictHolder.getDictItemName("rechargeOrderState", vo.getOrderState()));
		if (rechargeOrder.getPayChannel() != null) {
			vo.setPayChannelName(rechargeOrder.getPayChannel().getChannelName());
			vo.setBankName(rechargeOrder.getPayChannel().getBankName());
			vo.setAccountHolder(rechargeOrder.getPayChannel().getAccountHolder());
			vo.setBankCardAccount(rechargeOrder.getPayChannel().getBankCardAccount());
		}
		if (rechargeOrder.getUserAccount() != null) {
			vo.setUserName(rechargeOrder.getUserAccount().getUserName());
		}
		return vo;
	}

}
