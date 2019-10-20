package me.zohar.runscore.gatheringcode.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zohar.runscore.common.param.PageParam;

@Data
@EqualsAndHashCode(callSuper=false)
public class GatheringCodeQueryCondParam extends PageParam {
	
	private String state;
	
	/**
	 * 收款渠道
	 */
	private String gatheringChannelCode;
	
	private String payee;
	
	private String userName;
	
	/**
	 * 用户账号id
	 */
	private String userAccountId;
	

}
