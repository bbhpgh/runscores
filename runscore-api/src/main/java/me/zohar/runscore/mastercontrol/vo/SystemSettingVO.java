package me.zohar.runscore.mastercontrol.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.mastercontrol.domain.SystemSetting;

@Data
public class SystemSettingVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 网站标题
	 */
	private String websiteTitle;

	/**
	 * 最近修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date latelyUpdateTime;

	public static SystemSettingVO convertFor(SystemSetting setting) {
		if (setting == null) {
			return null;
		}
		SystemSettingVO vo = new SystemSettingVO();
		BeanUtils.copyProperties(setting, vo);
		return vo;
	}

}
