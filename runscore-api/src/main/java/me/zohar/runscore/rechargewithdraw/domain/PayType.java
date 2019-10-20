package me.zohar.runscore.rechargewithdraw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pay_type")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PayType {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	private String type;

	private String name;

	private Boolean bankCardFlag;

	/**
	 * 排序号
	 */
	private Double orderNo;
	
	/**
	 * 是否启用
	 */
	private Boolean enabled;
	
	private Date createTime;

	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;

}
