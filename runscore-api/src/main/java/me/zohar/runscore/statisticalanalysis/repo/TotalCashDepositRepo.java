package me.zohar.runscore.statisticalanalysis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.TotalCashDeposit;

public interface TotalCashDepositRepo
		extends JpaRepository<TotalCashDeposit, String>, JpaSpecificationExecutor<TotalCashDeposit> {
	
	TotalCashDeposit findTopBy();

}
