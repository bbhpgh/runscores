package me.zohar.runscore.agent.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.runscore.agent.domain.InviteCode;

public interface InviteCodeRepo extends JpaRepository<InviteCode, String>, JpaSpecificationExecutor<InviteCode> {
	
	InviteCode findTopByInviterIdOrderByPeriodOfValidityDesc(String inviterId);
	
	InviteCode findTopByCodeAndPeriodOfValidityGreaterThanEqual(String code, Date periodOfValidity);
	

}
