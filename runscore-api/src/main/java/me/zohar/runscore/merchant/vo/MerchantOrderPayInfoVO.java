package me.zohar.runscore.merchant.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.merchant.domain.MerchantOrderPayInfo;

@Data
public class MerchantOrderPayInfoVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 商户号
	 */
	private String merchantNum;

	/**
	 * 商户自定义的订单号
	 */
	private String orderNo;

	/**
	 * 支付金额
	 */
	private Double amount;

	/**
	 * 支付类型
	 */
	private String payType;

	/**
	 * 异步通知地址
	 */
	private String notifyUrl;

	/**
	 * 同步通知地址
	 */
	private String returnUrl;

	/**
	 * 商户自定义的附加信息
	 */
	private String attch;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 通知状态
	 */
	private String noticeState;

	private String noticeStateName;

	/**
	 * 通知时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date noticeTime;

	private String merchantOrderId;

	public static MerchantOrderPayInfoVO convertFor(MerchantOrderPayInfo payInfo) {
		if (payInfo == null) {
			return null;
		}
		MerchantOrderPayInfoVO vo = new MerchantOrderPayInfoVO();
		BeanUtils.copyProperties(payInfo, vo);
		vo.setNoticeStateName(DictHolder.getDictItemName("merchantOrderPayNoticeState", vo.getNoticeState()));
		return vo;
	}

}
