package me.zohar.runscore.gatheringcode.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import me.zohar.runscore.gatheringcode.domain.GatheringCode;
import me.zohar.runscore.gatheringcode.param.GatheringCodeParam;
import me.zohar.runscore.gatheringcode.param.GatheringCodeQueryCondParam;
import me.zohar.runscore.gatheringcode.repo.GatheringCodeRepo;
import me.zohar.runscore.gatheringcode.vo.GatheringCodeVO;
import me.zohar.runscore.storage.domain.Storage;
import me.zohar.runscore.storage.repo.StorageRepo;
import me.zohar.runscore.useraccount.domain.UserAccount;
import me.zohar.runscore.useraccount.repo.UserAccountRepo;

@Validated
@Service
public class GatheringCodeService {

	@Autowired
	private GatheringCodeRepo gatheringCodeRepo;

	@Autowired
	private StorageRepo storageRepo;

	@Autowired
	private UserAccountRepo userAccountRepo;

	@Transactional
	public void delMyGatheringCodeById(String id, String userAccountId) {
		GatheringCode gatheringCode = gatheringCodeRepo.getOne(id);
		if (!userAccountId.equals(gatheringCode.getUserAccountId())) {
			throw new BizException(BizError.无权删除数据);
		}
		delGatheringCodeById(id);
	}

	@Transactional
	public void delGatheringCodeById(String id) {
		GatheringCode gatheringCode = gatheringCodeRepo.getOne(id);
		disassociationGatheringCodeStorage(gatheringCode.getStorageId());
		gatheringCodeRepo.delete(gatheringCode);
	}

	@Transactional(readOnly = true)
	public GatheringCodeVO findMyGatheringCodeById(String id, String userAccountId) {
		GatheringCodeVO vo = findGatheringCodeById(id);
		if (!userAccountId.equals(vo.getUserAccountId())) {
			throw new BizException(BizError.无权查看数据);
		}
		return vo;
	}

	@Transactional(readOnly = true)
	public GatheringCodeVO findGatheringCodeById(String id) {
		GatheringCode gatheringCode = gatheringCodeRepo.getOne(id);
		return GatheringCodeVO.convertFor(gatheringCode);
	}

	@Transactional(readOnly = true)
	public PageResult<GatheringCodeVO> findMyGatheringCodeByPage(GatheringCodeQueryCondParam param) {
		if (StrUtil.isBlank(param.getUserAccountId())) {
			throw new BizException(BizError.无权查看数据);
		}
		return findGatheringCodeByPage(param);
	}

	@Transactional(readOnly = true)
	public PageResult<GatheringCodeVO> findGatheringCodeByPage(GatheringCodeQueryCondParam param) {
		Specification<GatheringCode> spec = new Specification<GatheringCode>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<GatheringCode> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (StrUtil.isNotEmpty(param.getState())) {
					predicates.add(builder.equal(root.get("state"), param.getState()));
				}
				if (StrUtil.isNotEmpty(param.getGatheringChannelCode())) {
					predicates.add(builder.equal(root.get("gatheringChannelCode"), param.getGatheringChannelCode()));
				}
				if (StrUtil.isNotEmpty(param.getPayee())) {
					predicates.add(builder.equal(root.get("payee"), param.getPayee()));
				}
				if (StrUtil.isNotEmpty(param.getUserName())) {
					predicates.add(builder.equal(root.join("userAccount", JoinType.INNER).get("userName"),
							param.getUserName()));
				}
				if (StrUtil.isNotEmpty(param.getUserAccountId())) {
					predicates.add(builder.equal(root.get("userAccountId"), param.getUserAccountId()));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<GatheringCode> result = gatheringCodeRepo.findAll(spec,
				PageRequest.of(param.getPageNum() - 1, param.getPageSize(), Sort.by(Sort.Order.desc("createTime"))));
		PageResult<GatheringCodeVO> pageResult = new PageResult<>(GatheringCodeVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

	@Transactional
	public void associateGatheringCodeStorage(String storageId, String gatheringCodeId) {
		Storage storage = storageRepo.getOne(storageId);
		storage.setAssociateId(gatheringCodeId);
		storage.setAssociateBiz("gatheringCode");
		storageRepo.save(storage);
	}

	@Transactional
	public void disassociationGatheringCodeStorage(String storageId) {
		Storage oldStorage = storageRepo.getOne(storageId);
		oldStorage.setAssociateId(null);
		oldStorage.setAssociateBiz(null);
		storageRepo.save(oldStorage);
	}

	@Transactional
	public void addOrUpdateGatheringCode(GatheringCodeParam param) {
		String userAccountId = null;
		if (StrUtil.isBlank(param.getId())) {
			if (StrUtil.isBlank(param.getUserName())) {
				throw new BizException(BizError.参数异常);
			}
			UserAccount userAccount = userAccountRepo.findByUserName(param.getUserName());
			if (userAccount == null) {
				throw new BizException(BizError.找不到所属账号无法新增收款码);
			}
			userAccountId = userAccount.getId();
		}
		addOrUpdateGatheringCode(param, userAccountId);
	}

	@ParamValid
	@Transactional
	public void addOrUpdateGatheringCode(GatheringCodeParam param, String userAccountId) {
		if (param.getFixedGatheringAmount()) {
			if (param.getGatheringAmount() == null) {
				throw new BizException(BizError.参数异常);
			}
			if (param.getGatheringAmount() <= 0) {
				throw new BizException(BizError.参数异常);
			}
		}
		// 新增
		if (StrUtil.isBlank(param.getId())) {
			GatheringCode gatheringCode = param.convertToPo(userAccountId);
			gatheringCodeRepo.save(gatheringCode);
			associateGatheringCodeStorage(param.getStorageId(), gatheringCode.getId());
		}
		// 修改
		else {
			GatheringCode gatheringCode = gatheringCodeRepo.getOne(param.getId());
			// 取消关联旧的收款码图片
			if (!param.getStorageId().equals(gatheringCode.getStorageId())) {
				disassociationGatheringCodeStorage(gatheringCode.getStorageId());
			}
			BeanUtils.copyProperties(param, gatheringCode);
			gatheringCodeRepo.save(gatheringCode);
			associateGatheringCodeStorage(param.getStorageId(), gatheringCode.getId());
		}
	}

}
