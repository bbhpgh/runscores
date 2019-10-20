package me.zohar.runscore.gatheringcode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.gatheringcode.domain.GatheringCode;

public interface GatheringCodeRepo
		extends JpaRepository<GatheringCode, String>, JpaSpecificationExecutor<GatheringCode> {

	GatheringCode findTopByUserAccountIdAndGatheringChannelCodeAndGatheringAmount(String userAccountId,
			String gatheringChannelCode, Double gatheringAmount);

	List<GatheringCode> findByUserAccountId(String userAccountId);

	GatheringCode findTopByUserAccountIdAndGatheringChannelCodeAndFixedGatheringAmountIsFalse(String userAccountId,
			String gatheringChannelCode);
}
