package me.zohar.runscore.merchant.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;
import me.zohar.runscore.useraccount.domain.UserAccount;

/**
 * 商户
 * @author zohar
 * @date 2019年4月19日
 *
 */
@Getter
@Setter
@Entity
@Table(name = "merchant")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Merchant {
	
	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;
	
	/**
	 * 商户号
	 */
	private String merchantNum;

	/**
	 * 接入商户名称
	 */
	private String name;

	/**
	 * 密钥
	 */
	private String secretKey;

	private Date createTime;
	
	/**
	 * 关联账号id
	 */
	@Column(name = "relevance_account_id", length = 32)
	private String relevanceAccountId;
	
	/**
	 * 乐观锁版本号
	 */
	@Version
	private Long version;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relevance_account_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private UserAccount relevanceAccount;

}
