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
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.useraccount.domain.UserAccount;

/**
 * 订单返点
 * 
 * @author zohar
 * @date 2019年6月24日
 *
 */
@Getter
@Setter
@Entity
@Table(name = "order_rebate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrderRebate {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 返点
	 */
	private Double rebate;

	/**
	 * 返点金额
	 */
	private Double rebateAmount;

	/**
	 * 结算时间,即更新到账号保证金的时间
	 */
	private Date settlementTime;

	private Date createTime;

	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;

	/**
	 * 商户订单id
	 */
	@Column(name = "merchant_order_id", length = 32)
	private String merchantOrderId;

	/**
	 * 商户订单
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_order_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MerchantOrder merchantOrder;

	/**
	 * 返点账号id
	 */
	@Column(name = "rebate_account_id", length = 32)
	private String rebateAccountId;

	/**
	 * 返点账号
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rebate_account_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private UserAccount rebateAccount;

	public void settlement() {
		this.setSettlementTime(new Date());
	}

	public static OrderRebate build(Double rebate, Double rebateAmount, String merchantOrderId,
			String rebateAccountId) {
		OrderRebate orderRebate = new OrderRebate();
		orderRebate.setId(IdUtils.getId());
		orderRebate.setCreateTime(new Date());
		orderRebate.setRebate(rebate);
		orderRebate.setRebateAmount(rebateAmount);
		orderRebate.setMerchantOrderId(merchantOrderId);
		orderRebate.setRebateAccountId(rebateAccountId);
		return orderRebate;
	}

}
