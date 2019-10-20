package me.zohar.runscore.mastercontrol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.mastercontrol.domain.SystemSetting;

public interface SystemSettingRepo
		extends JpaRepository<SystemSetting, String>, JpaSpecificationExecutor<SystemSetting> {

	SystemSetting findTopByOrderByLatelyUpdateTime();

}
