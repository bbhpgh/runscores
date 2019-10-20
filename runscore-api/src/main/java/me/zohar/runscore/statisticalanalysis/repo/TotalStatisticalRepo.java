package me.zohar.runscore.statisticalanalysis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.TotalStatistical;

public interface TotalStatisticalRepo
		extends JpaRepository<TotalStatistical, String>, JpaSpecificationExecutor<TotalStatistical> {

	TotalStatistical findTopBy();
	
}
