package me.zohar.runscore.statisticalanalysis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.TotalAccountReceiveOrderSituation;

public interface TotalAccountReceiveOrderSituationRepo
		extends JpaRepository<TotalAccountReceiveOrderSituation, String>, JpaSpecificationExecutor<TotalAccountReceiveOrderSituation> {

	List<TotalAccountReceiveOrderSituation> findTop10ByOrderByBountyDesc();
	
	TotalAccountReceiveOrderSituation findByReceivedAccountId(String receivedAccountId);
}
