package me.zohar.runscore.statisticalanalysis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "v_total_account_receive_order_situation")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TotalAccountReceiveOrderSituation {

	@Id
	@Column(name = "received_account_id", length = 32)
	private String receivedAccountId;

	private String userName;

	private Double gatheringAmount;

	private Long orderNum;

	private Double paidAmount;

	private Double bounty;

	private Long paidOrderNum;
	
	private Double rebateAmount;

}
