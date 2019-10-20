package me.zohar.runscore.merchant.vo;

import lombok.Data;
import me.zohar.runscore.dictconfig.ConfigHolder;

@Data
public class StartOrderSuccessVO {

	/**
	 * 支付地址
	 */
	private String payUrl;

	public static StartOrderSuccessVO convertFor(String orderNo) {
		StartOrderSuccessVO vo = new StartOrderSuccessVO();
		vo.setPayUrl(ConfigHolder.getConfigValue("merchantOrderPayUrl") + orderNo);
		return vo;
	}

}
