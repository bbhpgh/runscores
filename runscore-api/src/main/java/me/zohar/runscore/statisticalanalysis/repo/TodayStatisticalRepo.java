package me.zohar.runscore.statisticalanalysis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.TodayStatistical;

public interface TodayStatisticalRepo
		extends JpaRepository<TodayStatistical, String>, JpaSpecificationExecutor<TodayStatistical> {

	TodayStatistical findTopBy();
}
