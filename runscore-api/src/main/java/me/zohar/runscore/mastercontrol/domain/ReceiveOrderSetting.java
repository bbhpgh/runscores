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
@Table(name = "receive_order_setting")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ReceiveOrderSetting {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 接单有效时长
	 */
	private Integer receiveOrderEffectiveDuration;

	/**
	 * 支付有效时长
	 */
	private Integer orderPayEffectiveDuration;

	/**
	 * 接单数量上限
	 */
	private Integer receiveOrderUpperLimit;

	/**
	 * 保证金最低要求
	 */
	private Double cashDepositMinimumRequire;

	private Boolean unfixedGatheringCodeReceiveOrder;

	/**
	 * 最近修改时间
	 */
	private Date latelyUpdateTime;

	public static ReceiveOrderSetting build() {
		ReceiveOrderSetting setting = new ReceiveOrderSetting();
		setting.setId(IdUtils.getId());
		return setting;
	}

}
