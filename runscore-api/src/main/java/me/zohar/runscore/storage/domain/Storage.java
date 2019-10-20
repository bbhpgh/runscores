package me.zohar.runscore.storage.domain;

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
 * 对象存储
 * 
 * @author zohar
 * @date 2019年4月10日
 *
 */
@Getter
@Setter
@Entity
@Table(name = "storage")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Storage {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 文件大小
	 */
	private Long fileSize;

	private String url;

	private Date uploadTime;

	/**
	 * 关联的业务
	 */
	private String associateBiz;

	/**
	 * 关联id
	 */
	private String associateId;
	
	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;

}
