package me.zohar.runscore.agent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.agent.domain.RebateSituation;

public interface RebateSituationRepo
		extends JpaRepository<RebateSituation, String>, JpaSpecificationExecutor<RebateSituation> {

}
