package me.zohar.runscore.merchant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.merchant.domain.MerchantOrderPayInfo;

public interface MerchantOrderPayInfoRepo
		extends JpaRepository<MerchantOrderPayInfo, String>, JpaSpecificationExecutor<MerchantOrderPayInfo> {

	MerchantOrderPayInfo findByMerchantOrderId(String merchantOrderId);

}
