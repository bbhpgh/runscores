package me.zohar.runscore.rechargewithdraw.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.rechargewithdraw.domain.PayType;

public interface PayTypeRepo extends JpaRepository<PayType, String>, JpaSpecificationExecutor<PayType> {

	List<PayType> findByEnabledIsTrueOrderByOrderNoAsc();

}
