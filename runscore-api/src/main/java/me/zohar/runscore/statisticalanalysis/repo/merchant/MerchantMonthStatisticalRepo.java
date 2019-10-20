package me.zohar.runscore.statisticalanalysis.repo.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.merchant.MerchantMonthStatistical;

public interface MerchantMonthStatisticalRepo extends JpaRepository<MerchantMonthStatistical, String>, JpaSpecificationExecutor<MerchantMonthStatistical>  {

	
}
