package me.zohar.runscore.merchant.param;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import me.zohar.runscore.common.utils.IdUtils;
import me.zohar.runscore.constants.Constant;
import me.zohar.runscore.merchant.domain.Appeal;

@Data
public class UserStartAppealParam {
	
	@NotBlank
	private String appealType;
	
	private Double actualPayAmount;
	
	private String userSreenshotIds;
	
	@NotBlank
	private String merchantOrderId;
	
	public Appeal convertToPo() {
		Appeal po = new Appeal();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setInitiatorObj(Constant.申诉发起方_用户);
		po.setState(Constant.申诉状态_待处理);
		po.setInitiationTime(new Date());
		return po;
	}

}
