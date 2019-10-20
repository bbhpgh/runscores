package me.zohar.runscore.mastercontrol.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zohar.runscore.common.utils.IdUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_qrcode_setting")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CustomerQrcodeSetting {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	private String qrcodeStorageIds;

	/**
	 * 最近修改时间
	 */
	private Date latelyUpdateTime;

	public void update(String qrcodeStorageIds) {
		this.setQrcodeStorageIds(qrcodeStorageIds);
		this.setLatelyUpdateTime(new Date());
	}

	public static CustomerQrcodeSetting build() {
		CustomerQrcodeSetting setting = new CustomerQrcodeSetting();
		setting.setId(IdUtils.getId());
		return setting;
	}

}
