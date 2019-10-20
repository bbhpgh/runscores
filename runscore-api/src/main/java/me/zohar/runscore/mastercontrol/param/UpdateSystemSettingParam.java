package me.zohar.runscore.mastercontrol.param;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateSystemSettingParam {

	/**
	 * 网站标题
	 */
	@NotBlank
	private String websiteTitle;

}
