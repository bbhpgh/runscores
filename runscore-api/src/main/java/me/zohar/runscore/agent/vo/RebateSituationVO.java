package me.zohar.runscore.agent.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.agent.domain.RebateSituation;

@Data
public class RebateSituationVO {

	private String id;

	/**
	 * 返点
	 */
	private Double rebate;

	/**
	 * 关联账号数量
	 */
	private Integer associatedAccountNum;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	public static List<RebateSituationVO> convertFor(List<RebateSituation> rebateAndOddsSituations) {
		if (CollectionUtil.isEmpty(rebateAndOddsSituations)) {
			return new ArrayList<>();
		}
		List<RebateSituationVO> vos = new ArrayList<>();
		for (RebateSituation rebateAndOddsSituation : rebateAndOddsSituations) {
			vos.add(convertFor(rebateAndOddsSituation));
		}
		return vos;
	}

	public static RebateSituationVO convertFor(RebateSituation rebateAndOddsSituation) {
		if (rebateAndOddsSituation == null) {
			return null;
		}
		RebateSituationVO vo = new RebateSituationVO();
		BeanUtils.copyProperties(rebateAndOddsSituation, vo);
		return vo;
	}

}
