package me.zohar.runscore.mastercontrol.param;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateReceiveOrderSettingParam {

	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Integer receiveOrderEffectiveDuration;

	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Integer orderPayEffectiveDuration;
	
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private Integer receiveOrderUpperLimit;
	
	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Double cashDepositMinimumRequire;

	@NotNull
	private Boolean unfixedGatheringCodeReceiveOrder;

}
