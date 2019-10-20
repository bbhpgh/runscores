package me.zohar.runscore.merchant.domain;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;
import me.zohar.runscore.constants.Constant;

@Getter
@Setter
@Entity
@Table(name = "appeal")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Appeal {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 发起方
	 */
	private String initiatorObj;
	
	/**
	 * 申诉类型
	 */
	private String appealType;
	
	/**
	 * 处理方式
	 */
	private String processWay;

	/**
	 * 实际支付金额
	 */
	private Double actualPayAmount;

	private String userSreenshotIds;
	
	private String merchantSreenshotIds;

	/**
	 * 客户订单id
	 */
	@Column(name = "merchant_order_id", length = 32)
	private String merchantOrderId;

	private String state;

	/**
	 * 发起时间
	 */
	private Date initiationTime;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_order_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MerchantOrder merchantOrder;
	
	public void dontProcess() {
		this.setState(Constant.申诉状态_已完结);
		this.setProcessWay(Constant.申诉处理方式_不做处理);
	}

	public void userCancelAppeal() {
		this.setState(Constant.申诉状态_已完结);
		this.setProcessWay(Constant.申诉处理方式_用户撤销申诉);
	}
	
	public void merchantCancelAppeal() {
		this.setState(Constant.申诉状态_已完结);
		this.setProcessWay(Constant.申诉处理方式_商户撤销申诉);
	}
	
	public void alterToActualPayAmount() {
		this.setState(Constant.申诉状态_已完结);
		this.setProcessWay(Constant.申诉处理方式_改为实际支付金额);
	}
	
	public void cancelOrder() {
		this.setState(Constant.申诉状态_已完结);
		this.setProcessWay(Constant.申诉处理方式_取消订单);
	}
	
	public void confirmToPaid() {
		this.setState(Constant.申诉状态_已完结);
		this.setProcessWay(Constant.申诉处理方式_确认已支付);
	}

}
