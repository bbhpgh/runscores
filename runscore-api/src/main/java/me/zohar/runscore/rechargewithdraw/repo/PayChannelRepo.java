package me.zohar.runscore.rechargewithdraw.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.rechargewithdraw.domain.PayChannel;

public interface PayChannelRepo extends JpaRepository<PayChannel, String>, JpaSpecificationExecutor<PayChannel> {

	List<PayChannel> findByEnabledIsTrueOrderByOrderNoAsc();

}
