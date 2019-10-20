package me.zohar.runscore.rechargewithdraw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pay_channel")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PayChannel {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	private String channelCode;

	private String channelName;

	private String bankName;

	/**
	 * 开户人姓名
	 */
	private String accountHolder;

	/**
	 * 银行卡账号
	 */
	private String bankCardAccount;

	/**
	 * 支付平台code
	 */
	private String payPlatformCode;

	/**
	 * 支付平台名称
	 */
	private String payPlatformName;

	/**
	 * 支付平台对应通道code
	 */
	private String payPlatformChannelCode;

	private Date createTime;

	private Double orderNo;

	/**
	 * 是否启用
	 */
	private Boolean enabled;

	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;

	@Column(name = "pay_type_id", length = 32)
	private String payTypeId;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_type_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private PayType payType;

}
