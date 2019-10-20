package me.zohar.runscore.merchant.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "merchant_order_pay_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MerchantOrderPayInfo {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 商户号
	 */
	private String merchantNum;

	/**
	 * 商户自定义的订单号
	 */
	private String orderNo;

	/**
	 * 支付金额
	 */
	private Double amount;

	/**
	 * 支付类型
	 */
	private String payType;

	/**
	 * 异步通知地址
	 */
	private String notifyUrl;

	/**
	 * 同步通知地址
	 */
	private String returnUrl;

	/**
	 * 商户自定义的附加信息
	 */
	private String attch;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 通知状态
	 */
	private String noticeState;

	/**
	 * 通知时间
	 */
	private Date noticeTime;

	@Column(name = "merchant_order_id", length = 32)
	private String merchantOrderId;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_order_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MerchantOrder merchantOrder;

	public void updateNoticeState(String noticeState) {
		this.setNoticeState(noticeState);
		this.setNoticeTime(new Date());
	}

}
