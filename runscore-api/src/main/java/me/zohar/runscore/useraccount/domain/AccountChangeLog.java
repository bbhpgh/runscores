package me.zohar.runscore.useraccount.domain;

import java.text.MessageFormat;
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

import cn.hutool.core.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.constants.Constant;
import me.zohar.runscore.merchant.domain.MerchantOrder;
import me.zohar.runscore.merchant.domain.OrderRebate;
import me.zohar.runscore.rechargewithdraw.domain.RechargeOrder;
import me.zohar.runscore.rechargewithdraw.domain.WithdrawRecord;

/**
 * 账变日志
 * 
 * @author zohar
 * @date 2019年1月17日
 *
 */
@Getter
@Setter
@Entity
@Table(name = "account_change_log", schema = "lottery")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AccountChangeLog {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 账变时间
	 */
	private Date accountChangeTime;

	/**
	 * 账变类型代码
	 */
	private String accountChangeTypeCode;

	/**
	 * 账变金额
	 */
	private Double accountChangeAmount;

	/**
	 * 保证金
	 */
	private Double cashDeposit;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;

	/**
	 * 用户账号id
	 */
	@Column(name = "user_account_id", length = 32)
	private String userAccountId;

	/**
	 * 用户账号
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private UserAccount userAccount;

	public static AccountChangeLog buildWithCustomerCancelOrderRefund(UserAccount userAccount,
			MerchantOrder platformOrder) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(platformOrder.getOrderNo());
		log.setAccountChangeTime(new Date());
		log.setAccountChangeTypeCode(Constant.账变日志类型_客服取消订单并退款);
		log.setAccountChangeAmount(NumberUtil.round(platformOrder.getGatheringAmount(), 4).doubleValue());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	public static AccountChangeLog buildWithAlterToActualPayAmountRefund(UserAccount userAccount, String orderNo,
			Double refundAmount) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(orderNo);
		log.setAccountChangeTime(new Date());
		log.setAccountChangeTypeCode(Constant.账变日志类型_改单为实际支付金额并退款);
		log.setAccountChangeAmount(NumberUtil.round(refundAmount, 4).doubleValue());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建充值账变日志
	 * 
	 * @param userAccount
	 * @param bettingOrder
	 * @return
	 */
	public static AccountChangeLog buildWithRecharge(UserAccount userAccount, RechargeOrder rechargeOrder) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(rechargeOrder.getOrderNo());
		log.setAccountChangeTime(rechargeOrder.getSettlementTime());
		log.setAccountChangeTypeCode(Constant.账变日志类型_账号充值);
		log.setAccountChangeAmount(NumberUtil.round(rechargeOrder.getActualPayAmount(), 4).doubleValue());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建接单奖励金账变日志
	 * 
	 * @param userAccount
	 * @param bounty
	 * @param rebate
	 * @return
	 */
	public static AccountChangeLog buildWithReceiveOrderBounty(UserAccount userAccount, Double bounty, Double rebate) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(log.getId());
		log.setAccountChangeTime(new Date());
		log.setAccountChangeTypeCode(Constant.账变日志类型_接单奖励金);
		log.setAccountChangeAmount(NumberUtil.round(bounty, 4).doubleValue());
		log.setNote(MessageFormat.format("接单返点:{0}%", rebate));
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建充值优惠账变日志
	 * 
	 * @param userAccount
	 * @param returnWater
	 * @param returnWaterRate
	 * @return
	 */
	public static AccountChangeLog buildWithRechargePreferential(UserAccount userAccount, Double returnWater,
			Double returnWaterRate) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(log.getId());
		log.setAccountChangeTime(new Date());
		log.setAccountChangeTypeCode(Constant.账变日志类型_充值优惠);
		log.setAccountChangeAmount(NumberUtil.round(returnWater, 4).doubleValue());
		log.setNote(MessageFormat.format("充值返水率:{0}%", returnWaterRate));
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建接单扣款日志
	 * 
	 * @param userAccount
	 * @param platformOrder
	 * @return
	 */
	public static AccountChangeLog buildWithReceiveOrderDeduction(UserAccount userAccount,
			MerchantOrder platformOrder) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(platformOrder.getOrderNo());
		log.setAccountChangeTime(platformOrder.getReceivedTime());
		log.setAccountChangeTypeCode(Constant.账变日志类型_接单扣款);
		log.setAccountChangeAmount(-platformOrder.getGatheringAmount());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建发起提现账变日志
	 * 
	 * @param userAccount
	 * @param bettingOrder
	 * @return
	 */
	public static AccountChangeLog buildWithStartWithdraw(UserAccount userAccount, WithdrawRecord withdrawRecord) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(withdrawRecord.getOrderNo());
		log.setAccountChangeTime(withdrawRecord.getSubmitTime());
		log.setAccountChangeTypeCode(Constant.账变日志类型_账号提现);
		log.setAccountChangeAmount(-withdrawRecord.getWithdrawAmount());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建提现不符退款账变日志
	 * 
	 * @param userAccount
	 * @param bettingOrder
	 * @return
	 */
	public static AccountChangeLog buildWithWithdrawNotApprovedRefund(UserAccount userAccount,
			WithdrawRecord withdrawRecord) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(withdrawRecord.getOrderNo());
		log.setAccountChangeTime(withdrawRecord.getApprovalTime());
		log.setAccountChangeTypeCode(Constant.账变日志类型_提现不符退款);
		log.setAccountChangeAmount(withdrawRecord.getWithdrawAmount());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建退还保证金账变日志
	 * 
	 * @param userAccount
	 * @param withdrawRecord
	 * @return
	 */
	public static AccountChangeLog buildWithRefundCashDeposit(UserAccount userAccount, MerchantOrder merchantOrder) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(merchantOrder.getOrderNo());
		log.setAccountChangeTime(merchantOrder.getDealTime());
		log.setAccountChangeTypeCode(Constant.账变日志类型_退还保证金);
		log.setAccountChangeAmount(NumberUtil.round(merchantOrder.getGatheringAmount(), 4).doubleValue());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建手工调整保证金日志
	 * 
	 * @param userAccount
	 * @param platformOrder
	 * @return
	 */
	public static AccountChangeLog buildWithHandworkAdjustCashDeposit(UserAccount userAccount,
			Double accountChangeAmount) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setAccountChangeTime(new Date());
		log.setAccountChangeTypeCode(accountChangeAmount > 0 ? Constant.账变日志类型_手工增保证金 : Constant.账变日志类型_手工减保证金);
		log.setAccountChangeAmount(accountChangeAmount);
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

	/**
	 * 构建订单返点账变日志
	 * 
	 * @param userAccount
	 * @param orderRebate
	 * @return
	 */
	public static AccountChangeLog buildWithOrderRebate(UserAccount userAccount, OrderRebate orderRebate) {
		AccountChangeLog log = new AccountChangeLog();
		log.setId(IdUtils.getId());
		log.setOrderNo(orderRebate.getId());
		log.setAccountChangeTime(new Date());
		log.setAccountChangeTypeCode(Constant.账变日志类型_奖励金返点);
		log.setAccountChangeAmount(NumberUtil.round(orderRebate.getRebateAmount(), 4).doubleValue());
		log.setCashDeposit(userAccount.getCashDeposit());
		log.setUserAccountId(userAccount.getId());
		return log;
	}

}
