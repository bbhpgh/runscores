package me.zohar.runscore.useraccount.param;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 调整保证金入参
 * 
 * @author zohar
 * @date 2019年4月23日
 *
 */
@Data
public class AdjustCashDepositParam {

	/**
	 * 用户账号id
	 */
	@NotBlank
	private String userAccountId;

	/**
	 * 账变类型代码
	 */
	@NotBlank
	private String accountChangeTypeCode;

	/**
	 * 账变金额
	 */
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	private Double accountChangeAmount;

}
