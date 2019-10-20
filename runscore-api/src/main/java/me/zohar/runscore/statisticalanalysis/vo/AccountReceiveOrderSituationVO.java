package me.zohar.runscore.statisticalanalysis.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.statisticalanalysis.domain.TodayAccountReceiveOrderSituation;
import me.zohar.runscore.statisticalanalysis.domain.TotalAccountReceiveOrderSituation;

@Data
public class AccountReceiveOrderSituationVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String receivedAccountId;

	private String userName;

	private Double gatheringAmount;

	private Long orderNum;

	private Double paidAmount;

	private Double bounty;

	private Long paidOrderNum;
	
	private Double rebateAmount;
	
	public static AccountReceiveOrderSituationVO convertForToday(TodayAccountReceiveOrderSituation situation) {
		if (situation == null) {
			return null;
		}
		AccountReceiveOrderSituationVO vo = new AccountReceiveOrderSituationVO();
		BeanUtils.copyProperties(situation, vo);
		return vo;
	}
	
	public static AccountReceiveOrderSituationVO convertForTotal(TotalAccountReceiveOrderSituation situation) {
		if (situation == null) {
			return null;
		}
		AccountReceiveOrderSituationVO vo = new AccountReceiveOrderSituationVO();
		BeanUtils.copyProperties(situation, vo);
		return vo;
	}

}
