package me.zohar.runscore.gatheringcode.param;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.gatheringcode.domain.GatheringCode;

@Data
public class GatheringCodeParam {

	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 所属账号
	 */
	private String userName;

	/**
	 * 收款渠道
	 */
	@NotBlank
	private String gatheringChannelCode;

	/**
	 * 状态,启用:1;禁用:0
	 */
	@NotBlank
	private String state;
	
	@NotNull
	private Boolean fixedGatheringAmount;

	/**
	 * 收款金额
	 */
	private Double gatheringAmount;
	
	

	/**
	 * 收款人
	 */
	@NotBlank
	private String payee;

	private String storageId;

	public GatheringCode convertToPo(String userAccountId) {
		GatheringCode po = new GatheringCode();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setCreateTime(new Date());
		po.setUserAccountId(userAccountId);
		return po;
	}

}
