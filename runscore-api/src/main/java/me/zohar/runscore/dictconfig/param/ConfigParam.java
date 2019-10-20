package me.zohar.runscore.dictconfig.param;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.dictconfig.domain.ConfigItem;

@Data
public class ConfigParam {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 配置项code
	 */
	@NotBlank
	private String configCode;

	/**
	 * 配置项名称
	 */
	@NotBlank
	private String configName;

	/**
	 * 配置项值
	 */
	@NotBlank
	private String configValue;
	
	public ConfigItem convertToPo() {
		ConfigItem po = new ConfigItem();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		return po;
	}

}
