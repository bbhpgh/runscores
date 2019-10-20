package me.zohar.runscore.merchant.param;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zohar.runscore.common.param.PageParam;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyReceiveOrderRecordQueryCondParam extends PageParam {

	@NotBlank
	private String receiverUserName;

	private String gatheringChannelCode;

	/**
	 * 接单时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date receiveOrderTime;

}
