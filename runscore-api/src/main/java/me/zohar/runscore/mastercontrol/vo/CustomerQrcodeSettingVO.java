package me.zohar.runscore.mastercontrol.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import me.zohar.runscore.mastercontrol.domain.CustomerQrcodeSetting;

@Data
public class CustomerQrcodeSettingVO {

	private String id;

	private String qrcodeStorageIds;

	/**
	 * 最近修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date latelyUpdateTime;

	public static CustomerQrcodeSettingVO convertFor(CustomerQrcodeSetting setting) {
		if (setting == null) {
			return null;
		}
		CustomerQrcodeSettingVO vo = new CustomerQrcodeSettingVO();
		BeanUtils.copyProperties(setting, vo);
		return vo;
	}

}
