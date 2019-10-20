package me.zohar.runscore.merchant.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.util.StrUtil;
import me.zohar.runscore.common.exception.BizError;
import me.zohar.runscore.common.exception.BizException;
import me.zohar.runscore.common.valid.ParamValid;
import me.zohar.runscore.common.vo.PageResult;
import me.zohar.runscore.constants.Constant;
import me.zohar.runscore.merchant.domain.Merchant;
import me.zohar.runscore.merchant.param.AddOrUpdateMerchantParam;
import me.zohar.runscore.merchant.param.MerchantQueryCondParam;
import me.zohar.runscore.merchant.repo.MerchantRepo;
import me.zohar.runscore.merchant.vo.MerchantVO;
import me.zohar.runscore.useraccount.domain.UserAccount;
import me.zohar.runscore.useraccount.repo.UserAccountRepo;

@Validated
@Service
public class MerchantService {

	@Autowired
	private MerchantRepo merchantRepo;

	@Autowired
	private UserAccountRepo userAccountRepo;

	@Transactional(readOnly = true)
	public MerchantVO findMerchantByRelevanceAccountId(@NotBlank String relevanceAccountId) {
		return MerchantVO.convertFor(merchantRepo.findByRelevanceAccountId(relevanceAccountId));
	}
	
	@Transactional(readOnly = true)
	public MerchantVO findPlatformById(@NotBlank String id) {
		return MerchantVO.convertFor(merchantRepo.getOne(id));
	}

	@Transactional
	public void delMerchantById(@NotBlank String id) {
		merchantRepo.deleteById(id);
	}

	@ParamValid
	@Transactional
	public void addOrUpdateMerchant(AddOrUpdateMerchantParam param) {
		Merchant merchantWithMerchantNum = merchantRepo.findByMerchantNum(param.getMerchantNum());
		if (merchantWithMerchantNum != null && !merchantWithMerchantNum.getId().equals(param.getId())) {
			throw new BizException(BizError.商户号已使用);
		}
		Merchant merchantWithName = merchantRepo.findByName(param.getName());
		if (merchantWithName != null && !merchantWithName.getId().equals(param.getId())) {
			throw new BizException(BizError.商户名称已使用);
		}
		UserAccount relevanceAccount = userAccountRepo.findByUserName(param.getRelevanceAccountUserName());
		if (relevanceAccount == null) {
			throw new BizException(BizError.关联账号不存在);
		}
		if (!Constant.账号类型_商户.equals(relevanceAccount.getAccountType())) {
			throw new BizException(BizError.只能关联商户类型的账号);
		}
		Merchant relevanceMerchant = merchantRepo.findByRelevanceAccountId(relevanceAccount.getId());
		if (relevanceMerchant != null && !relevanceMerchant.getId().equals(param.getId())) {
			throw new BizException(BizError.账号已关联其他商户);
		}
		// 新增
		if (StrUtil.isBlank(param.getId())) {
			Merchant merchant = param.convertToPo();
			merchant.setRelevanceAccountId(relevanceAccount.getId());
			merchantRepo.save(merchant);
		}
		// 修改
		else {
			Merchant merchant = merchantRepo.getOne(param.getId());
			BeanUtils.copyProperties(param, merchant);
			merchant.setRelevanceAccountId(relevanceAccount.getId());
			merchantRepo.save(merchant);
		}
	}

	@Transactional(readOnly = true)
	public PageResult<MerchantVO> findMerchantByPage(MerchantQueryCondParam param) {
		Specification<Merchant> spec = new Specification<Merchant>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (StrUtil.isNotBlank(param.getName())) {
					predicates.add(builder.equal(root.get("name"), param.getName()));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<Merchant> result = merchantRepo.findAll(spec,
				PageRequest.of(param.getPageNum() - 1, param.getPageSize(), Sort.by(Sort.Order.desc("createTime"))));
		PageResult<MerchantVO> pageResult = new PageResult<>(MerchantVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

}
