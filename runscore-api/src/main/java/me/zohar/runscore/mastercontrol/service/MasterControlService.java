package me.zohar.runscore.mastercontrol.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;
import me.zohar.runscore.common.valid.ParamValid;
import me.zohar.runscore.mastercontrol.domain.CustomerQrcodeSetting;
import me.zohar.runscore.mastercontrol.domain.ReceiveOrderSetting;
import me.zohar.runscore.mastercontrol.domain.RechargeSetting;
import me.zohar.runscore.mastercontrol.domain.RegisterSetting;
import me.zohar.runscore.mastercontrol.domain.SystemSetting;
import me.zohar.runscore.mastercontrol.param.UpdateReceiveOrderSettingParam;
import me.zohar.runscore.mastercontrol.param.UpdateRechargeSettingParam;
import me.zohar.runscore.mastercontrol.param.UpdateRegisterSettingParam;
import me.zohar.runscore.mastercontrol.param.UpdateSystemSettingParam;
import me.zohar.runscore.mastercontrol.repo.CustomerQrcodeSettingRepo;
import me.zohar.runscore.mastercontrol.repo.ReceiveOrderSettingRepo;
import me.zohar.runscore.mastercontrol.repo.RechargeSettingRepo;
import me.zohar.runscore.mastercontrol.repo.RegisterSettingRepo;
import me.zohar.runscore.mastercontrol.repo.SystemSettingRepo;
import me.zohar.runscore.mastercontrol.vo.CustomerQrcodeSettingVO;
import me.zohar.runscore.mastercontrol.vo.ReceiveOrderSettingVO;
import me.zohar.runscore.mastercontrol.vo.RechargeSettingVO;
import me.zohar.runscore.mastercontrol.vo.RegisterSettingVO;
import me.zohar.runscore.mastercontrol.vo.SystemSettingVO;

@Validated
@Service
@Slf4j
public class MasterControlService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private ReceiveOrderSettingRepo receiveOrderSettingRepo;

	@Autowired
	private RegisterSettingRepo registerSettingRepo;

	@Autowired
	private RechargeSettingRepo rechargeSettingRepo;

	@Autowired
	private CustomerQrcodeSettingRepo customerQrcodeSettingRepo;

	@Autowired
	private SystemSettingRepo systemSettingRepo;

	@Transactional(readOnly = true)
	public SystemSettingVO getSystemSetting() {
		SystemSetting setting = systemSettingRepo.findTopByOrderByLatelyUpdateTime();
		return SystemSettingVO.convertFor(setting);
	}

	@ParamValid
	@Transactional
	public void updateSystemSetting(UpdateSystemSettingParam param) {
		SystemSetting setting = systemSettingRepo.findTopByOrderByLatelyUpdateTime();
		if (setting == null) {
			setting = SystemSetting.build();
		}
		BeanUtils.copyProperties(param, setting);
		setting.setLatelyUpdateTime(new Date());
		systemSettingRepo.save(setting);
	}

	@Transactional(readOnly = true)
	public RegisterSettingVO getRegisterSetting() {
		RegisterSetting setting = registerSettingRepo.findTopByOrderByLatelyUpdateTime();
		return RegisterSettingVO.convertFor(setting);
	}

	@ParamValid
	@Transactional
	public void updateRegisterSetting(UpdateRegisterSettingParam param) {
		RegisterSetting setting = registerSettingRepo.findTopByOrderByLatelyUpdateTime();
		if (setting == null) {
			setting = RegisterSetting.build();
		}
		BeanUtils.copyProperties(param, setting);
		setting.setLatelyUpdateTime(new Date());
		registerSettingRepo.save(setting);
	}

	@Transactional(readOnly = true)
	public ReceiveOrderSettingVO getReceiveOrderSetting() {
		ReceiveOrderSetting setting = receiveOrderSettingRepo.findTopByOrderByLatelyUpdateTime();
		return ReceiveOrderSettingVO.convertFor(setting);
	}

	@ParamValid
	@Transactional
	public void updateReceiveOrderSetting(UpdateReceiveOrderSettingParam param) {
		ReceiveOrderSetting setting = receiveOrderSettingRepo.findTopByOrderByLatelyUpdateTime();
		if (setting == null) {
			setting = ReceiveOrderSetting.build();
		}
		BeanUtils.copyProperties(param, setting);
		setting.setLatelyUpdateTime(new Date());
		receiveOrderSettingRepo.save(setting);
	}

	@Transactional(readOnly = true)
	public RechargeSettingVO getRechargeSetting() {
		RechargeSetting setting = rechargeSettingRepo.findTopByOrderByLatelyUpdateTime();
		return RechargeSettingVO.convertFor(setting);
	}

	@ParamValid
	@Transactional
	public void updateRechargeSetting(UpdateRechargeSettingParam param) {
		RechargeSetting setting = rechargeSettingRepo.findTopByOrderByLatelyUpdateTime();
		if (setting == null) {
			setting = RechargeSetting.build();
		}
		BeanUtils.copyProperties(param, setting);
		setting.setLatelyUpdateTime(new Date());
		rechargeSettingRepo.save(setting);
	}

	@Transactional(readOnly = true)
	public CustomerQrcodeSettingVO getCustomerQrcodeSetting() {
		CustomerQrcodeSetting setting = customerQrcodeSettingRepo.findTopByOrderByLatelyUpdateTime();
		return CustomerQrcodeSettingVO.convertFor(setting);
	}

	@Transactional
	public void updateCustomerQrcodeSetting(@NotBlank String qrcodeStorageIds) {
		CustomerQrcodeSetting setting = customerQrcodeSettingRepo.findTopByOrderByLatelyUpdateTime();
		if (setting == null) {
			setting = CustomerQrcodeSetting.build();
		}
		setting.update(qrcodeStorageIds);
		customerQrcodeSettingRepo.save(setting);
	}

	/**
	 * 刷新缓存
	 * 
	 * @param cacheItems
	 */
	public void refreshCache(@NotEmpty List<String> cacheItems) {
		List<String> deleteSuccessKeys = new ArrayList<>();
		List<String> deleteFailKeys = new ArrayList<>();
		for (String cacheItem : cacheItems) {
			Set<String> keys = redisTemplate.keys(cacheItem);
			for (String key : keys) {
				Boolean flag = redisTemplate.delete(key);
				if (flag) {
					deleteSuccessKeys.add(key);
				} else {
					deleteFailKeys.add(key);
				}
			}
		}
		if (!deleteFailKeys.isEmpty()) {
			log.warn("以下的缓存删除失败:", deleteFailKeys);
		}
	}

}
