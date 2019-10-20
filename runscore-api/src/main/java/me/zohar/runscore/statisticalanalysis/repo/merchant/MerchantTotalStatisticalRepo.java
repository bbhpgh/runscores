package me.zohar.runscore.statisticalanalysis.repo.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.statisticalanalysis.domain.merchant.MerchantTotalStatistical;

public interface MerchantTotalStatisticalRepo
		extends JpaRepository<MerchantTotalStatistical, String>, JpaSpecificationExecutor<MerchantTotalStatistical> {

}
