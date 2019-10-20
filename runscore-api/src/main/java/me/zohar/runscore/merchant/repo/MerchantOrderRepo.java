package me.zohar.runscore.merchant.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.merchant.domain.MerchantOrder;

public interface MerchantOrderRepo
		extends JpaRepository<MerchantOrder, String>, JpaSpecificationExecutor<MerchantOrder> {

	List<MerchantOrder> findTop10ByOrderStateAndGatheringAmountIsLessThanEqualAndGatheringChannelCodeInOrderBySubmitTimeDesc(String orderState,
			Double gatheringAmount, List<String> gatheringChannelCodes);

	List<MerchantOrder> findByOrderStateInAndReceivedAccountIdOrderBySubmitTimeDesc(List<String> orderStates,
			String receivedAccountId);
	
	MerchantOrder findByIdAndReceivedAccountId(String id, String receivedAccountId);
	
	List<MerchantOrder> findTop10ByOrderStateAndGatheringAmountInAndGatheringChannelCodeOrderBySubmitTimeDesc(String orderState,
			List<Double> gatheringAmounts, String gatheringChannelCode);
	
	MerchantOrder findByIdAndMerchantId(String id, String merchantId);
	
	MerchantOrder findByOrderNo(String orderNo);
	
	List<MerchantOrder> findByOrderStateAndUsefulTimeLessThan(String orderState, Date usefulTime);

}
