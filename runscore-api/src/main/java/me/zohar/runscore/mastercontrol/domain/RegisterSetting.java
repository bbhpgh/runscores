package me.zohar.runscore.mastercontrol.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zohar.runscore.common.utils.IdUtils;

/**
 * 注册设置
 * 
 * @author zohar
 * @date 2019年3月12日
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register_setting")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RegisterSetting {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
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
	private Date latelyUpdateTime;

	public static RegisterSetting build() {
		RegisterSetting setting = new RegisterSetting();
		setting.setId(IdUtils.getId());
		return setting;
	}

}
