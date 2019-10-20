package me.zohar.runscore.mastercontrol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.mastercontrol.domain.RechargeSetting;

public interface RechargeSettingRepo
		extends JpaRepository<RechargeSetting, String>, JpaSpecificationExecutor<RechargeSetting> {
	
	RechargeSetting findTopByOrderByLatelyUpdateTime();

}
