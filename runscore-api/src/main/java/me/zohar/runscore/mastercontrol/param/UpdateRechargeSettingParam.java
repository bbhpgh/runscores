package me.zohar.runscore.mastercontrol.param;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateRechargeSettingParam {

	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Integer orderEffectiveDuration;

	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Double returnWaterRate;

	@NotNull
	private Boolean returnWaterRateEnabled;

	/**
	 * 快捷设置金额
	 */
	@NotBlank
	private String quickInputAmount;

	/**
	 * 充值说明
	 */
	@NotBlank
	private String rechargeExplain;

}
