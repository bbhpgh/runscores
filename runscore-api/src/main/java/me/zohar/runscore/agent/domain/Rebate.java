package me.zohar.runscore.agent.domain;

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

/**
 * 返点
 * 
 * @author zohar
 * @date 2019年6月2日
 *
 */
@Getter
@Setter
@Entity
@Table(name = "rebate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Rebate {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 返点
	 */
	private Double rebate;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;

}
