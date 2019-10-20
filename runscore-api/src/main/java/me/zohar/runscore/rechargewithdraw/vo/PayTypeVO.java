package me.zohar.runscore.rechargewithdraw.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.rechargewithdraw.domain.PayType;

@Data
public class PayTypeVO {

	private String id;

	private String type;

	private String name;

	private Boolean bankCardFlag;

	/**
	 * 排序号
	 */
	private Double orderNo;
	
	private Boolean enabled;

	public static List<PayTypeVO> convertFor(List<PayType> payTypes) {
		if (CollectionUtil.isEmpty(payTypes)) {
			return new ArrayList<>();
		}
		List<PayTypeVO> vos = new ArrayList<>();
		for (PayType payType : payTypes) {
			vos.add(convertFor(payType));
		}
		return vos;
	}

	public static PayTypeVO convertFor(PayType payType) {
		if (payType == null) {
			return null;
		}
		PayTypeVO vo = new PayTypeVO();
		BeanUtils.copyProperties(payType, vo);
		return vo;
	}

}
