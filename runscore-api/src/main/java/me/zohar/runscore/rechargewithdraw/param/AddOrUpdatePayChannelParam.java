package me.zohar.runscore.rechargewithdraw.param;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.rechargewithdraw.domain.PayChannel;

@Data
public class AddOrUpdatePayChannelParam {

	private String id;

	@NotBlank
	private String channelCode;

	@NotBlank
	private String channelName;

	private String bankName;

	/**
	 * 开户人姓名
	 */
	private String accountHolder;

	/**
	 * 银行卡账号
	 */
	private String bankCardAccount;

	/**
	 * 支付平台code
	 */
	private String payPlatformCode;

	/**
	 * 支付平台名称
	 */
	private String payPlatformName;

	/**
	 * 支付平台对应通道code
	 */
	private String payPlatformChannelCode;

	@NotBlank
	private String payTypeId;

	/**
	 * 排序号
	 */
	@NotNull
	@DecimalMin(value = "0", inclusive = true)
	private Double orderNo;

	/**
	 * 是否启用
	 */
	@NotNull
	private Boolean enabled;

	public PayChannel convertToPo() {
		PayChannel po = new PayChannel();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setCreateTime(new Date());
		return po;
	}

}
