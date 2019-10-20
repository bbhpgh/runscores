package me.zohar.runscore.merchant.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.merchant.domain.Merchant;

@Data
public class MerchantVO {

	private String id;
	
	/**
	 * 商户号
	 */
	private String merchantNum;

	/**
	 * 商户名称
	 */
	private String name;

	/**
	 * 密钥
	 */
	private String secretKey;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private String relevanceAccountUserName;

	public static List<MerchantVO> convertFor(List<Merchant> merchants) {
		if (CollectionUtil.isEmpty(merchants)) {
			return new ArrayList<>();
		}
		List<MerchantVO> vos = new ArrayList<>();
		for (Merchant merchant : merchants) {
			vos.add(convertFor(merchant));
		}
		return vos;
	}

	public static MerchantVO convertFor(Merchant merchant) {
		if (merchant == null) {
			return null;
		}
		MerchantVO vo = new MerchantVO();
		BeanUtils.copyProperties(merchant, vo);
		if (merchant.getRelevanceAccount() != null) {
			vo.setRelevanceAccountUserName(merchant.getRelevanceAccount().getUserName());
		}
		return vo;
	}

}
