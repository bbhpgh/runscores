package me.zohar.runscore.rechargewithdraw.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.rechargewithdraw.domain.RechargeWithdrawLog;


public interface RechargeWithdrawLogRepo
		extends JpaRepository<RechargeWithdrawLog, String>, JpaSpecificationExecutor<RechargeWithdrawLog> {

}
