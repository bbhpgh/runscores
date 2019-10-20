package me.zohar.runscore.mastercontrol.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.mastercontrol.domain.RechargeSetting;

@Data
public class RechargeSettingVO {

	private String id;

	/**
	 * 订单有效时长
	 */
	private Integer orderEffectiveDuration;

	/**
	 * 充值返水率
	 */
	private Double returnWaterRate;

	/**
	 * 充值返水率启用标识
	 */
	private Boolean returnWaterRateEnabled;

	/**
	 * 快捷设置金额
	 */
	private String quickInputAmount;

	/**
	 * 充值说明
	 */
	private String rechargeExplain;

	/**
	 * 最近修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date latelyUpdateTime;

	public static RechargeSettingVO convertFor(RechargeSetting rechargeSetting) {
		if (rechargeSetting == null) {
			return null;
		}
		RechargeSettingVO vo = new RechargeSettingVO();
		BeanUtils.copyProperties(rechargeSetting, vo);
		return vo;
	}

}
