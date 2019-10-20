package me.zohar.runscore.mastercontrol.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.mastercontrol.domain.CustomerQrcodeSetting;

public interface CustomerQrcodeSettingRepo
		extends JpaRepository<CustomerQrcodeSetting, String>, JpaSpecificationExecutor<CustomerQrcodeSetting> {

	CustomerQrcodeSetting findTopByOrderByLatelyUpdateTime();

}
