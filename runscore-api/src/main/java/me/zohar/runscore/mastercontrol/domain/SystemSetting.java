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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "system_setting")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SystemSetting {

	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 网站标题
	 */
	private String websiteTitle;

	/**
	 * 最近修改时间
	 */
	private Date latelyUpdateTime;

	public static SystemSetting build() {
		SystemSetting setting = new SystemSetting();
		setting.setId(IdUtils.getId());
		return setting;
	}

}
