package me.zohar.runscore.common.utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadPoolUtils {

	/**
	 * 充值结算线程池
	 */
	private static ScheduledThreadPoolExecutor rechargeSettlementPool = new ScheduledThreadPoolExecutor(5);
	
	/**
	 * 已支付的商户订单线程池
	 */
	private static ScheduledThreadPoolExecutor paidMerchantOrderPool = new ScheduledThreadPoolExecutor(9);


	/**
	 * 登录日志线程池
	 */
	private static ScheduledThreadPoolExecutor loginLogPool = new ScheduledThreadPoolExecutor(3);

	public static ScheduledThreadPoolExecutor getRechargeSettlementPool() {
		return rechargeSettlementPool;
	}
	
	public static ScheduledThreadPoolExecutor getPaidMerchantOrderPool() {
		return paidMerchantOrderPool;
	}

	public static ScheduledThreadPoolExecutor getLoginLogPool() {
		return loginLogPool;
	}
}
