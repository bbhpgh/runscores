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
 * 充值设置
 * 
 * @author zohar
 * @date 2019年3月13日
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recharge_setting")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RechargeSetting {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 订单有效时长
	 */
	private Integer orderEffectiveDuration;

	/**
	 * 充值返水率
	 */
	private Double returnWaterRate;

	/**
	 * 充值返水率启用标识
	 */
	private Boolean returnWaterRateEnabled;
	
	/**
	 * 快捷设置金额
	 */
	private String quickInputAmount;
	
	/**
	 * 充值说明
	 */
	private String rechargeExplain;

	/**
	 * 最近修改时间
	 */
	private Date latelyUpdateTime;

	public void update(Integer orderEffectiveDuration, Double returnWaterRate, Boolean returnWaterRateEnabled) {
		this.setOrderEffectiveDuration(orderEffectiveDuration);
		this.setReturnWaterRate(returnWaterRate);
		this.setReturnWaterRateEnabled(returnWaterRateEnabled);
		this.setLatelyUpdateTime(new Date());
	}

	public static RechargeSetting build() {
		RechargeSetting setting = new RechargeSetting();
		setting.setId(IdUtils.getId());
		return setting;
	}

}
