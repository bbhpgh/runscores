package me.zohar.runscore.statisticalanalysis.domain;

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
@Table(name = "v_total_cash_deposit")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TotalCashDeposit {
	
	@Id
	private Double totalCashDeposit;

}
