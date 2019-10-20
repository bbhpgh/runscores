package me.zohar.runscore.agent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.agent.domain.Rebate;

public interface RebateRepo
		extends JpaRepository<Rebate, String>, JpaSpecificationExecutor<Rebate> {

	Rebate findTopByRebate(Double rebate);

}
