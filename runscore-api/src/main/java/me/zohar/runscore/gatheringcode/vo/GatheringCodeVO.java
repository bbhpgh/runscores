package me.zohar.runscore.gatheringcode.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.dictconfig.DictHolder;
import me.zohar.runscore.gatheringcode.domain.GatheringCode;

@Data
public class GatheringCodeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 收款渠道
	 */
	private String gatheringChannelCode;

	private String gatheringChannelName;

	/**
	 * 状态,启用:1;禁用:0
	 */
	private String state;

	private String stateName;
	
	private Boolean fixedGatheringAmount;

	/**
	 * 收款金额
	 */
	private Double gatheringAmount;

	/**
	 * 收款人
	 */
	private String payee;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private String storageId;

	/**
	 * 投注人用户账号id
	 */
	private String userAccountId;

	private String userName;

	public static List<GatheringCodeVO> convertFor(Collection<GatheringCode> gatheringCodes) {
		if (CollectionUtil.isEmpty(gatheringCodes)) {
			return new ArrayList<>();
		}
		List<GatheringCodeVO> vos = new ArrayList<>();
		for (GatheringCode gatheringCode : gatheringCodes) {
			vos.add(convertFor(gatheringCode));
		}
		return vos;
	}

	public static GatheringCodeVO convertFor(GatheringCode gatheringCode) {
		if (gatheringCode == null) {
			return null;
		}
		GatheringCodeVO vo = new GatheringCodeVO();
		BeanUtils.copyProperties(gatheringCode, vo);
		vo.setGatheringChannelName(DictHolder.getDictItemName("gatheringChannel", vo.getGatheringChannelCode()));
		vo.setStateName(DictHolder.getDictItemName("gatheringCodeState", vo.getState()));
		if (gatheringCode.getUserAccount() != null) {
			vo.setUserName(gatheringCode.getUserAccount().getUserName());
		}
		return vo;
	}

}
