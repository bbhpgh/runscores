package me.zohar.runscore.mastercontrol.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.mastercontrol.domain.RegisterSetting;

@Data
public class RegisterSettingVO {

	private String id;

	/**
	 * 是否开放注册功能
	 */
	private Boolean registerEnabled;

	/**
	 * 注册默认返点
	 */
	private Double regitserDefaultRebate;

	/**
	 * 邀请码有效时长
	 */
	private Integer inviteCodeEffectiveDuration;

	/**
	 * 邀请码注册模式启用标识
	 */
	private Boolean inviteRegisterEnabled;

	/**
	 * 最近修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date latelyUpdateTime;

	public static RegisterSettingVO convertFor(RegisterSetting registerSetting) {
		if (registerSetting == null) {
			return null;
		}
		RegisterSettingVO vo = new RegisterSettingVO();
		BeanUtils.copyProperties(registerSetting, vo);
		return vo;
	}

}
