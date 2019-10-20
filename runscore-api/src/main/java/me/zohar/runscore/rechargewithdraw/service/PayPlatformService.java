package me.zohar.runscore.rechargewithdraw.service;

public interface PayPlatformService {

	/**
	 * 发起支付
	 * 
	 * @param orderNo
	 * @param amount
	 * @param channelCode
	 * @return 返回实际支付地址
	 */
	String startPay(String orderNo, Double amount, String channelCode);

}
