package me.zohar.runscore.agent.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import me.zohar.runscore.agent.domain.Rebate;

@Data
public class RebateVO {

	private String id;

	/**
	 * 返点
	 */
	private Double rebate;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	public static List<RebateVO> convertFor(List<Rebate> rebateAndOddses) {
		if (CollectionUtil.isEmpty(rebateAndOddses)) {
			return new ArrayList<>();
		}
		List<RebateVO> vos = new ArrayList<>();
		for (Rebate rebateAndOdds : rebateAndOddses) {
			vos.add(convertFor(rebateAndOdds));
		}
		return vos;
	}

	public static RebateVO convertFor(Rebate rebateAndOdds) {
		if (rebateAndOdds == null) {
			return null;
		}
		RebateVO vo = new RebateVO();
		BeanUtils.copyProperties(rebateAndOdds, vo);
		return vo;
	}

}
