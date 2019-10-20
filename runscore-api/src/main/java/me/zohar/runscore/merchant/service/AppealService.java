package me.zohar.runscore.merchant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import me.zohar.runscore.common.exception.BizError;
import me.zohar.runscore.common.exception.BizException;
import me.zohar.runscore.common.valid.ParamValid;
import me.zohar.runscore.common.vo.PageResult;
import me.zohar.runscore.constants.Constant;
import me.zohar.runscore.merchant.domain.Appeal;
import me.zohar.runscore.merchant.domain.MerchantOrder;
import me.zohar.runscore.merchant.param.AppealQueryCondParam;
import me.zohar.runscore.merchant.param.MerchantStartAppealParam;
import me.zohar.runscore.merchant.param.UserStartAppealParam;
import me.zohar.runscore.merchant.repo.AppealRepo;
import me.zohar.runscore.merchant.repo.MerchantOrderRepo;
import me.zohar.runscore.merchant.vo.AppealVO;
import me.zohar.runscore.storage.domain.Storage;
import me.zohar.runscore.storage.repo.StorageRepo;
import me.zohar.runscore.useraccount.domain.AccountChangeLog;
import me.zohar.runscore.useraccount.domain.UserAccount;
import me.zohar.runscore.useraccount.repo.AccountChangeLogRepo;
import me.zohar.runscore.useraccount.repo.UserAccountRepo;

@Validated
@Service
public class AppealService {

	@Autowired
	private MerchantOrderService merchantOrderService;

	@Autowired
	private AppealRepo appealRepo;

	@Autowired
	private StorageRepo storageRepo;

	@Autowired
	private UserAccountRepo userAccountRepo;

	@Autowired
	private MerchantOrderRepo merchantOrderRepo;

	@Autowired
	private AccountChangeLogRepo accountChangeLogRepo;

	@Transactional(readOnly = true)
	public PageResult<AppealVO> findTop5TodoAppealByPage() {
		Specification<Appeal> spec = new Specification<Appeal>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Appeal> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(builder.equal(root.get("state"), Constant.申诉状态_待处理));
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<Appeal> result = appealRepo.findAll(spec,
				PageRequest.of(0, 5, Sort.by(Sort.Order.desc("initiationTime"))));
		PageResult<AppealVO> pageResult = new PageResult<>(AppealVO.convertFor(result.getContent()), 1, 5,
				result.getTotalElements());
		return pageResult;
	}

	@Transactional
	public void userUploadSreenshot(@NotBlank String receivedAccountId, @NotBlank String appealId,
			@NotBlank String userSreenshotIds) {
		Appeal appeal = appealRepo.getOne(appealId);
		MerchantOrder merchantOrder = appeal.getMerchantOrder();
		if (!receivedAccountId.equals(merchantOrder.getReceivedAccountId())) {
			throw new BizException(BizError.无权上传截图);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.只有待处理的申诉记录才能上传截图);
		}
		if (StrUtil.isNotBlank(appeal.getUserSreenshotIds())) {
			throw new BizException(BizError.已有截图不能重复上传);
		}

		appeal.setUserSreenshotIds(userSreenshotIds);
		appealRepo.save(appeal);
		for (String sreenshotId : userSreenshotIds.split(",")) {
			Storage storage = storageRepo.getOne(sreenshotId);
			storage.setAssociateId(appeal.getId());
			storage.setAssociateBiz("appealSreenshot");
			storageRepo.save(storage);
		}
	}

	@Transactional
	public void merchantUploadSreenshot(@NotBlank String merchantId, @NotBlank String appealId,
			@NotBlank String merchantSreenshotIds) {
		Appeal appeal = appealRepo.getOne(appealId);
		MerchantOrder merchantOrder = appeal.getMerchantOrder();
		if (!merchantId.equals(merchantOrder.getMerchantId())) {
			throw new BizException(BizError.无权上传截图);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.只有待处理的申诉记录才能上传截图);
		}
		if (StrUtil.isNotBlank(appeal.getMerchantSreenshotIds())) {
			throw new BizException(BizError.已有截图不能重复上传);
		}

		appeal.setMerchantSreenshotIds(merchantSreenshotIds);
		appealRepo.save(appeal);
		for (String sreenshotId : merchantSreenshotIds.split(",")) {
			Storage storage = storageRepo.getOne(sreenshotId);
			storage.setAssociateId(appeal.getId());
			storage.setAssociateBiz("appealSreenshot");
			storageRepo.save(storage);
		}
	}

	@Transactional(readOnly = true)
	public AppealVO findUserAppealById(@NotBlank String userName, @NotBlank String appealId) {
		AppealVO vo = findAppealById(appealId);
		if (!userName.equals(vo.getReceiverUserName())) {
			throw new BizException(BizError.无权查看数据);
		}
		return vo;
	}

	@Transactional(readOnly = true)
	public AppealVO findMerchantAppealById(@NotBlank String merchantName, @NotBlank String appealId) {
		AppealVO vo = findAppealById(appealId);
		if (!merchantName.equals(vo.getMerchantName())) {
			throw new BizException(BizError.无权查看数据);
		}
		return vo;
	}

	@Transactional(readOnly = true)
	public AppealVO findAppealById(@NotBlank String appealId) {
		return AppealVO.convertFor(appealRepo.getOne(appealId));
	}

	@Transactional
	public void dontProcess(@NotBlank String appealId) {
		Appeal appeal = appealRepo.findById(appealId).orElse(null);
		if (appeal == null) {
			throw new BizException(BizError.参数异常);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.当前申诉已完结);
		}
		appeal.dontProcess();
		appealRepo.save(appeal);
		MerchantOrder merchantOrder = merchantOrderRepo.getOne(appeal.getMerchantOrderId());
		merchantOrder.setOrderState(Constant.商户订单状态_已接单);
		merchantOrderRepo.save(merchantOrder);
	}

	@Transactional
	public void cancelOrder(@NotBlank String appealId) {
		Appeal appeal = appealRepo.findById(appealId).orElse(null);
		if (appeal == null) {
			throw new BizException(BizError.参数异常);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.当前申诉已完结无法更改处理方式);
		}
		appeal.cancelOrder();
		appealRepo.save(appeal);
		merchantOrderService.customerServiceCancelOrderRefund(appeal.getMerchantOrderId());
	}

	@Transactional
	public void alterToActualPayAmount(@NotBlank String appealId) {
		Appeal appeal = appealRepo.findById(appealId).orElse(null);
		if (appeal == null) {
			throw new BizException(BizError.参数异常);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.当前申诉已完结无法更改处理方式);
		}
		if (!Constant.申诉类型_实际支付金额小于收款金额.equals(appeal.getAppealType())) {
			throw new BizException(BizError.该申诉类型的处理方式不能是改为实际支付金额);
		}

		appeal.alterToActualPayAmount();
		appealRepo.save(appeal);
		MerchantOrder merchantOrder = appeal.getMerchantOrder();
		UserAccount userAccount = merchantOrder.getReceivedAccount();
		Double refundAmount = merchantOrder.getGatheringAmount() - appeal.getActualPayAmount();
		Double cashDeposit = NumberUtil.round(userAccount.getCashDeposit() + refundAmount, 4).doubleValue();
		userAccount.setCashDeposit(cashDeposit);
		userAccountRepo.save(userAccount);
		merchantOrder.setGatheringAmount(appeal.getActualPayAmount());
		merchantOrderRepo.save(merchantOrder);
		accountChangeLogRepo.save(AccountChangeLog.buildWithAlterToActualPayAmountRefund(userAccount,
				merchantOrder.getOrderNo(), refundAmount));
		merchantOrderService.customerServiceConfirmToPaid(merchantOrder.getId(), "客服改单为实际支付金额并确认已支付");
	}

	@Transactional
	public void confirmToPaid(@NotBlank String appealId) {
		Appeal appeal = appealRepo.findById(appealId).orElse(null);
		if (appeal == null) {
			throw new BizException(BizError.参数异常);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.当前申诉已完结无法更改处理方式);
		}

		appeal.confirmToPaid();
		appealRepo.save(appeal);
		merchantOrderService.customerServiceConfirmToPaid(appeal.getMerchantOrderId(), "客服确认已支付");
	}

	@Transactional(readOnly = true)
	public AppealVO findAppealDetailsById(@NotBlank String id) {
		return AppealVO.convertFor(appealRepo.getOne(id));
	}

	@Transactional(readOnly = true)
	public PageResult<AppealVO> findAppealByPage(AppealQueryCondParam param) {
		Specification<Appeal> spec = new Specification<Appeal>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Appeal> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (StrUtil.isNotBlank(param.getOrderNo())) {
					predicates.add(builder.equal(root.join("merchantOrder", JoinType.INNER).get("orderNo"),
							param.getOrderNo()));
				}
				if (StrUtil.isNotBlank(param.getMerchantName())) {
					predicates.add(builder.equal(
							root.join("merchantOrder", JoinType.INNER).join("merchant", JoinType.INNER).get("name"),
							param.getMerchantName()));
				}
				if (StrUtil.isNotBlank(param.getGatheringChannelCode())) {
					predicates.add(builder.equal(root.join("merchantOrder", JoinType.INNER).get("gatheringChannelCode"),
							param.getGatheringChannelCode()));
				}
				if (StrUtil.isNotBlank(param.getReceiverUserName())) {
					predicates.add(builder.equal(root.join("merchantOrder", JoinType.INNER)
							.join("receivedAccount", JoinType.INNER).get("userName"), param.getReceiverUserName()));
				}
				if (StrUtil.isNotBlank(param.getAppealType())) {
					predicates.add(builder.equal(root.get("appealType"), param.getAppealType()));
				}
				if (StrUtil.isNotBlank(param.getAppealState())) {
					predicates.add(builder.equal(root.get("state"), param.getAppealState()));
				}
				if (StrUtil.isNotBlank(param.getAppealProcessWay())) {
					predicates.add(builder.equal(root.get("processWay"), param.getAppealProcessWay()));
				}
				if (param.getInitiationStartTime() != null) {
					predicates.add(builder.greaterThanOrEqualTo(root.get("initiationTime").as(Date.class),
							DateUtil.beginOfDay(param.getInitiationStartTime())));
				}
				if (param.getInitiationEndTime() != null) {
					predicates.add(builder.lessThanOrEqualTo(root.get("initiationTime").as(Date.class),
							DateUtil.endOfDay(param.getInitiationEndTime())));
				}
				if (StrUtil.isNotBlank(param.getInitiatorObj())) {
					predicates.add(builder.equal(root.get("initiatorObj"), param.getInitiatorObj()));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<Appeal> result = appealRepo.findAll(spec, PageRequest.of(param.getPageNum() - 1, param.getPageSize(),
				Sort.by(Sort.Order.desc("initiationTime"))));
		PageResult<AppealVO> pageResult = new PageResult<>(AppealVO.convertFor(result.getContent()), param.getPageNum(),
				param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

	@Transactional
	public void userCancelAppeal(@NotBlank String userAccountId, @NotBlank String appealId) {
		Appeal appeal = appealRepo.findById(appealId).orElse(null);
		if (appeal == null) {
			throw new BizException(BizError.参数异常);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.无权撤销申诉);
		}
		MerchantOrder merchantOrder = appeal.getMerchantOrder();
		if (merchantOrder == null) {
			throw new BizException(BizError.商户订单不存在);
		}
		if (!merchantOrder.getReceivedAccountId().equals(userAccountId)) {
			throw new BizException(BizError.无权撤销申诉);
		}
		if (!Constant.申诉发起方_用户.equals(appeal.getInitiatorObj())) {
			throw new BizException(BizError.不是申诉发起方无权撤销申诉);
		}
		if (StrUtil.isNotBlank(appeal.getMerchantSreenshotIds())) {
			throw new BizException(BizError.商户已提供截图无法撤销申诉);
		}
		merchantOrder.setOrderState(Constant.商户订单状态_已接单);
		merchantOrderRepo.save(merchantOrder);
		appeal.userCancelAppeal();
		appealRepo.save(appeal);
	}

	@Transactional
	public void merchantCancelAppeal(@NotBlank String merchantId, @NotBlank String appealId) {
		Appeal appeal = appealRepo.findById(appealId).orElse(null);
		if (appeal == null) {
			throw new BizException(BizError.参数异常);
		}
		if (!Constant.申诉状态_待处理.equals(appeal.getState())) {
			throw new BizException(BizError.无权撤销申诉);
		}
		MerchantOrder merchantOrder = appeal.getMerchantOrder();
		if (merchantOrder == null) {
			throw new BizException(BizError.商户订单不存在);
		}
		if (!merchantOrder.getMerchantId().equals(merchantId)) {
			throw new BizException(BizError.无权撤销申诉);
		}
		if (!Constant.申诉发起方_商户.equals(appeal.getInitiatorObj())) {
			throw new BizException(BizError.不是申诉发起方无权撤销申诉);
		}
		if (StrUtil.isNotBlank(appeal.getUserSreenshotIds())) {
			throw new BizException(BizError.用户已提供截图无法撤销申诉);
		}
		merchantOrder.setOrderState(Constant.商户订单状态_已接单);
		merchantOrderRepo.save(merchantOrder);
		appeal.merchantCancelAppeal();
		appealRepo.save(appeal);
	}

	@ParamValid
	@Transactional
	public void userStartAppeal(String receivedAccountId, UserStartAppealParam param) {
		MerchantOrder merchantOrder = merchantOrderRepo.findByIdAndReceivedAccountId(param.getMerchantOrderId(),
				receivedAccountId);
		if (merchantOrder == null) {
			throw new BizException(BizError.商户订单不存在);
		}
		if (Constant.商户订单状态_申诉中.equals(merchantOrder.getOrderState())) {
			throw new BizException(BizError.该订单存在未处理的申诉记录不能重复发起);
		}
		if (!Constant.申诉类型_未支付申请取消订单.equals(param.getAppealType())
				&& !Constant.申诉类型_实际支付金额小于收款金额.equals(param.getAppealType())) {
			throw new BizException(BizError.参数异常);
		}
		if (Constant.申诉类型_实际支付金额小于收款金额.equals(param.getAppealType())) {
			if (param.getActualPayAmount() == null || param.getActualPayAmount() <= 0) {
				throw new BizException(BizError.参数异常);
			}
			if (merchantOrder.getGatheringAmount() - param.getActualPayAmount() < 0) {
				throw new BizException(BizError.实际支付金额须小于收款金额);
			}
			if (StrUtil.isBlank(param.getUserSreenshotIds())) {
				throw new BizException(BizError.参数异常);
			}
		}

		merchantOrder.setOrderState(Constant.商户订单状态_申诉中);
		merchantOrderRepo.save(merchantOrder);
		Appeal appeal = param.convertToPo();
		appealRepo.save(appeal);
		if (StrUtil.isNotBlank(param.getUserSreenshotIds())) {
			for (String sreenshotId : param.getUserSreenshotIds().split(",")) {
				Storage storage = storageRepo.getOne(sreenshotId);
				storage.setAssociateId(appeal.getId());
				storage.setAssociateBiz("appealSreenshot");
				storageRepo.save(storage);
			}
		}
	}

	@ParamValid
	@Transactional
	public void merchantStartAppeal(String merchantId, MerchantStartAppealParam param) {
		MerchantOrder merchantOrder = merchantOrderRepo.findByIdAndMerchantId(param.getMerchantOrderId(), merchantId);
		if (merchantOrder == null) {
			throw new BizException(BizError.商户订单不存在);
		}
		if (Constant.商户订单状态_申诉中.equals(merchantOrder.getOrderState())) {
			throw new BizException(BizError.该订单存在未处理的申诉记录不能重复发起);
		}
		if (!Constant.申诉类型_已支付用户未进行确认.equals(param.getAppealType())
				&& !Constant.申诉类型_实际支付金额小于收款金额.equals(param.getAppealType())) {
			throw new BizException(BizError.参数异常);
		}
		if (Constant.申诉类型_实际支付金额小于收款金额.equals(param.getAppealType())) {
			if (param.getActualPayAmount() == null || param.getActualPayAmount() <= 0) {
				throw new BizException(BizError.参数异常);
			}
			if (merchantOrder.getGatheringAmount() - param.getActualPayAmount() < 0) {
				throw new BizException(BizError.实际支付金额须小于收款金额);
			}
			if (StrUtil.isBlank(param.getMerchantSreenshotIds())) {
				throw new BizException(BizError.参数异常);
			}
		}

		merchantOrder.setOrderState(Constant.商户订单状态_申诉中);
		merchantOrderRepo.save(merchantOrder);
		Appeal appeal = param.convertToPo();
		appealRepo.save(appeal);
		if (StrUtil.isNotBlank(param.getMerchantSreenshotIds())) {
			for (String sreenshotId : param.getMerchantSreenshotIds().split(",")) {
				Storage storage = storageRepo.getOne(sreenshotId);
				storage.setAssociateId(appeal.getId());
				storage.setAssociateBiz("appealSreenshot");
				storageRepo.save(storage);
			}
		}
	}

}
