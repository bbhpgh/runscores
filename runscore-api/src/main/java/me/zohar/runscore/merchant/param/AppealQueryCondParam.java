package me.zohar.runscore.merchant.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zohar.runscore.common.param.PageParam;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppealQueryCondParam extends PageParam {

	private String orderNo;
	
	private String merchantName;

	private String gatheringChannelCode;

	private String receiverUserName;

	private String appealType;
	
	private String appealState;
	
	private String appealProcessWay;

	/**
	 * 发起开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date initiationStartTime;

	/**
	 * 发起结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date initiationEndTime;
	
	private String initiatorObj;

}
