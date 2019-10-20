package me.zohar.runscore.useraccount.param;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zohar.runscore.common.param.PageParam;

@Data
@EqualsAndHashCode(callSuper = false)
public class LowerLevelAccountChangeLogQueryCondParam extends PageParam {
	
	/**
	 * 当前账号id
	 */
	@NotBlank
	private String currentAccountId;
	
	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

	/**
	 * 账变类型代码
	 */
	private String accountChangeTypeCode;

}
