package me.zohar.runscore.agent.domain;

import java.io.Serializable;
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
@Table(name = "v_rebate_situation")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RebateSituation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Id
	private Double id;

	/**
	 * 返点
	 */
	private Double rebate;

	/**
	 * 关联账号数量
	 */
	private Integer associatedAccountNum;

	private Date createTime;

}
