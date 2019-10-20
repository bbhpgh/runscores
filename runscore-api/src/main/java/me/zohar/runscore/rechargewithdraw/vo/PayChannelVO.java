package me.zohar.runscore.rechargewithdraw.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.rechargewithdraw.domain.PayChannel;

@Data
public class PayChannelVO {

	private String id;

	private String channelCode;

	private String channelName;

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
	 * 支付平台code
	 */
	private String payPlatformCode;

	/**
	 * 支付平台名称
	 */
	private String payPlatformName;

	/**
	 * 支付平台对应通道code
	 */
	private String payPlatformChannelCode;

	private Double orderNo;

	/**
	 * 是否启用
	 */
	private Boolean enabled;

	private String payTypeId;

	public static List<PayChannelVO> convertFor(List<PayChannel> payChannels) {
		if (CollectionUtil.isEmpty(payChannels)) {
			return new ArrayList<>();
		}
		List<PayChannelVO> vos = new ArrayList<>();
		for (PayChannel payChannel : payChannels) {
			vos.add(convertFor(payChannel));
		}
		return vos;
	}

	public static PayChannelVO convertFor(PayChannel payChannel) {
		if (payChannel == null) {
			return null;
		}
		PayChannelVO vo = new PayChannelVO();
		BeanUtils.copyProperties(payChannel, vo);
		return vo;
	}

}
