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
@Table(name = "v_today_statistical")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TodayStatistical {
	
	@Id
	private Double tradeAmount;

	private Long paidOrderNum;

	private Long orderNum;

}
