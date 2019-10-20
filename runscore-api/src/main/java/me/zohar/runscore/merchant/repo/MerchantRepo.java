package me.zohar.runscore.merchant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.merchant.domain.Merchant;

public interface MerchantRepo extends JpaRepository<Merchant, String>, JpaSpecificationExecutor<Merchant> {

	Merchant findByRelevanceAccountId(String relevanceAccountId);

	Merchant findByName(String name);
	
	Merchant findByMerchantNum(String merchantNum);

}
