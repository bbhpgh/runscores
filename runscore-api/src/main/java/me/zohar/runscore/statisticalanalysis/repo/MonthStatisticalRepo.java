package me.zohar.runscore.statisticalanalysis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.MonthStatistical;

public interface MonthStatisticalRepo extends JpaRepository<MonthStatistical, String>, JpaSpecificationExecutor<MonthStatistical>  {

	MonthStatistical findTopBy();
	
}
