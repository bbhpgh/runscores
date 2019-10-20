package me.zohar.runscore.rechargewithdraw.vo;


import lombok.Data;

@Data
public class BankCardPayOrderDetailsVO {
	
	private String id;
	
	/**
	 * 充值金额
	 */
	private Double rechargeAmount;

}
