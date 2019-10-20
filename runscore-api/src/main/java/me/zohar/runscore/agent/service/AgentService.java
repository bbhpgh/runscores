package me.zohar.runscore.agent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import me.zohar.runscore.agent.domain.InviteCode;
import me.zohar.runscore.agent.domain.Rebate;
import me.zohar.runscore.agent.domain.RebateSituation;
import me.zohar.runscore.agent.param.AddOrUpdateRebateParam;
import me.zohar.runscore.agent.param.AgentOpenAnAccountParam;
import me.zohar.runscore.agent.param.GenerateInviteCodeParam;
import me.zohar.runscore.agent.repo.InviteCodeRepo;
import me.zohar.runscore.agent.repo.RebateRepo;
import me.zohar.runscore.agent.repo.RebateSituationRepo;
import me.zohar.runscore.agent.vo.InviteCodeDetailsInfoVO;
import me.zohar.runscore.agent.vo.RebateSituationVO;
import me.zohar.runscore.agent.vo.RebateVO;
import me.zohar.runscore.common.exception.BizError;
import me.zohar.runscore.common.exception.BizException;
import me.zohar.runscore.common.param.PageParam;
import me.zohar.runscore.common.valid.ParamValid;
import me.zohar.runscore.common.vo.PageResult;
import me.zohar.runscore.constants.Constant;
import me.zohar.runscore.mastercontrol.domain.RegisterSetting;
import me.zohar.runscore.mastercontrol.repo.RegisterSettingRepo;
import me.zohar.runscore.useraccount.domain.UserAccount;
import me.zohar.runscore.useraccount.repo.UserAccountRepo;

@Validated
@Service
public class AgentService {

	@Autowired
	private RebateRepo rebateRepo;

	@Autowired
	private RebateSituationRepo rebateSituationRepo;

	@Autowired
	private UserAccountRepo userAccountRepo;

	@Autowired
	private InviteCodeRepo inviteCodeRepo;

	@Autowired
	private RegisterSettingRepo inviteRegisterSettingRepo;

	@Transactional(readOnly = true)
	public List<RebateVO> findAllRebate() {
		List<Rebate> rebateAndOddses = rebateRepo.findAll(Sort.by(Sort.Order.desc("rebate")));
		return RebateVO.convertFor(rebateAndOddses);
	}

	@Transactional(readOnly = true)
	public PageResult<RebateSituationVO> findRebateSituationByPage(PageParam param) {
		Specification<RebateSituation> spec = new Specification<RebateSituation>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Predicate toPredicate(Root<RebateSituation> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<RebateSituation> result = rebateSituationRepo.findAll(spec,
				PageRequest.of(param.getPageNum() - 1, param.getPageSize(), Sort.by(Sort.Order.asc("rebate"))));
		PageResult<RebateSituationVO> pageResult = new PageResult<>(RebateSituationVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

	@Transactional
	public void resetRebate(@NotEmpty List<AddOrUpdateRebateParam> params) {
		Map<String, String> map = new HashMap<>();
		for (AddOrUpdateRebateParam param : params) {
			String key = param.getRebate() + "";
			if (map.get(key) != null) {
				throw new BizException(BizError.不能设置重复的返点);
			}
			map.put(key, key);
		}

		rebateRepo.deleteAll();
		Date now = new Date();
		for (AddOrUpdateRebateParam param : params) {
			Rebate rebate = param.convertToPo(now);
			rebateRepo.save(rebate);
		}
	}

	@Transactional
	public void delRebate(@NotNull Double rebate) {
		Rebate po = rebateRepo.findTopByRebate(rebate);
		rebateRepo.delete(po);
	}

	@Transactional(readOnly = true)
	public RebateVO findRebate(@NotNull Double rebate) {
		Rebate po = rebateRepo.findTopByRebate(rebate);
		return RebateVO.convertFor(po);
	}

	@ParamValid
	@Transactional
	public void addOrUpdateRebate(AddOrUpdateRebateParam param) {
		// 新增
		if (StrUtil.isBlank(param.getId())) {
			Rebate rebate = rebateRepo.findTopByRebate(param.getRebate());
			if (rebate != null) {
				throw new BizException(BizError.该返点已存在);
			}
			Rebate newRebate = param.convertToPo(new Date());
			rebateRepo.save(newRebate);
		}
		// 修改
		else {
			Rebate rebate = rebateRepo.findTopByRebate(param.getRebate());
			if (rebate != null && !rebate.getId().equals(param.getId())) {
				throw new BizException(BizError.该返点已存在);
			}
			Rebate editRebate = rebateRepo.getOne(param.getId());
			BeanUtils.copyProperties(param, editRebate);
			rebateRepo.save(editRebate);
		}
	}

	/**
	 * 代理开户
	 */
	@ParamValid
	@Transactional
	public void agentOpenAnAccount(AgentOpenAnAccountParam param) {
		UserAccount inviter = userAccountRepo.getOne(param.getInviterId());
		if (!(Constant.账号类型_管理员.equals(inviter.getAccountType())
				|| Constant.账号类型_代理.equals(inviter.getAccountType()))) {
			throw new BizException(BizError.只有管理员或代理才能进行代理开户操作);
		}
		if (param.getRebate() > inviter.getRebate()) {
			throw new BizException(BizError.下级账号的返点不能大于上级账号);
		}
		Rebate rebate = rebateRepo.findTopByRebate(param.getRebate());
		if (rebate == null) {
			throw new BizException(BizError.该返点未设置);
		}
		UserAccount userAccount = userAccountRepo.findByUserName(param.getUserName());
		if (userAccount != null) {
			throw new BizException(BizError.用户名已存在);
		}

		String encodePwd = new BCryptPasswordEncoder().encode(param.getLoginPwd());
		param.setLoginPwd(encodePwd);
		UserAccount lowerLevelAccount = param.convertToPo();
		lowerLevelAccount.setInviterId(inviter.getId());
		lowerLevelAccount.setAccountLevel(inviter.getAccountLevel() + 1);
		lowerLevelAccount.setAccountLevelPath(inviter.getAccountLevelPath() + "." + lowerLevelAccount.getId());
		userAccountRepo.save(lowerLevelAccount);
	}

	/**
	 * 生成邀请码
	 * 
	 * @param param
	 * @return
	 */
	@ParamValid
	@Transactional
	public String generateInviteCode(GenerateInviteCodeParam param) {
		RegisterSetting setting = inviteRegisterSettingRepo.findTopByOrderByLatelyUpdateTime();
		if (setting == null || !setting.getInviteRegisterEnabled()) {
			throw new BizException(BizError.邀请注册功能已关闭);
		}

		String code = IdUtil.fastSimpleUUID().substring(0, 6);
		while (inviteCodeRepo.findTopByCodeAndPeriodOfValidityGreaterThanEqual(code, new Date()) != null) {
			code = IdUtil.fastSimpleUUID().substring(0, 6);
		}
		InviteCode newInviteCode = param.convertToPo(code, setting.getInviteCodeEffectiveDuration());
		inviteCodeRepo.save(newInviteCode);
		return newInviteCode.getId();
	}

	@Transactional(readOnly = true)
	public InviteCodeDetailsInfoVO getInviteCodeDetailsInfoById(@NotBlank String id) {
		InviteCode inviteCode = inviteCodeRepo.getOne(id);
		InviteCodeDetailsInfoVO inviteDetailsInfo = InviteCodeDetailsInfoVO.convertFor(inviteCode);
		return inviteDetailsInfo;
	}

}
