package me.zohar.runscore.rechargewithdraw.param;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.rechargewithdraw.domain.PayType;

@Data
public class AddOrUpdatePayTypeParam {

	private String id;

	@NotBlank
	private String type;

	@NotBlank
	private String name;

	@NotNull
	private Boolean bankCardFlag;

	/**
	 * 排序号
	 */
	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Double orderNo;

	/**
	 * 是否启用
	 */
	@NotNull
	private Boolean enabled;

	public PayType convertToPo() {
		PayType po = new PayType();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setCreateTime(new Date());
		return po;
	}

}
