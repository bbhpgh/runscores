package me.zohar.runscore.merchant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.merchant.domain.Appeal;

public interface AppealRepo extends JpaRepository<Appeal, String>, JpaSpecificationExecutor<Appeal> {
	
	Appeal findByMerchantOrderIdAndState(String merchantOrderId, String state);

}
