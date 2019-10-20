package me.zohar.runscore.agent.param;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.agent.domain.Rebate;
import me.zohar.runscore.common.utils.IdUtils;

@Data
public class AddOrUpdateRebateParam {

	private String id;

	/**
	 * 返点
	 */
	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Double rebate;

	public Rebate convertToPo(Date createTime) {
		Rebate po = new Rebate();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setCreateTime(createTime);
		return po;
	}

}
