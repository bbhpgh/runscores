package me.zohar.runscore.dictconfig.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zohar.runscore.common.param.PageParam;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigItemQueryCondParam extends PageParam {

	/**
	 * 配置项code
	 */
	private String configCode;

	/**
	 * 配置项名称
	 */
	private String configName;

}
