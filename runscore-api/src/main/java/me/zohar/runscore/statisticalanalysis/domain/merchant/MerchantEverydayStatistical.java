package me.zohar.runscore.statisticalanalysis.domain.merchant;

import java.util.Date;

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
@Table(name = "v_merchant_everyday_statistical")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MerchantEverydayStatistical {

	@Id
	private String id;

	private String merchantId;

	private Date everyday;

	private Double tradeAmount;

	private Long paidOrderNum;

	private Long orderNum;

}
