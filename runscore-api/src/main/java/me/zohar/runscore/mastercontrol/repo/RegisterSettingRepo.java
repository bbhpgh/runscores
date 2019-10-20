package me.zohar.runscore.mastercontrol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.mastercontrol.domain.RegisterSetting;

public interface RegisterSettingRepo
		extends JpaRepository<RegisterSetting, String>, JpaSpecificationExecutor<RegisterSetting> {

	RegisterSetting findTopByOrderByLatelyUpdateTime();
	
}
