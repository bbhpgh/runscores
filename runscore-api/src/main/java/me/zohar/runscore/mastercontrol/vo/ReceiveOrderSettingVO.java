package me.zohar.runscore.mastercontrol.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.mastercontrol.domain.ReceiveOrderSetting;

@Data
public class ReceiveOrderSettingVO {

	private String id;

	/**
	 * 接单有效时长
	 */
	private Integer receiveOrderEffectiveDuration;

	/**
	 * 支付有效时长
	 */
	private Integer orderPayEffectiveDuration;

	/**
	 * 接单数量上限
	 */
	private Integer receiveOrderUpperLimit;
	
	/**
	 * 保证金最低要求
	 */
	private Double cashDepositMinimumRequire;

	private Boolean unfixedGatheringCodeReceiveOrder;

	/**
	 * 最近修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date latelyUpdateTime;

	public static ReceiveOrderSettingVO convertFor(ReceiveOrderSetting platformOrderSetting) {
		if (platformOrderSetting == null) {
			return null;
		}
		ReceiveOrderSettingVO vo = new ReceiveOrderSettingVO();
		BeanUtils.copyProperties(platformOrderSetting, vo);
		return vo;
	}

}
