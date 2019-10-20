package me.zohar.runscore.rechargewithdraw.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.rechargewithdraw.domain.WithdrawRecord;

public interface WithdrawRecordRepo
		extends JpaRepository<WithdrawRecord, String>, JpaSpecificationExecutor<WithdrawRecord> {

}
