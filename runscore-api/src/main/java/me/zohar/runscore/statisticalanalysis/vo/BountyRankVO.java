package me.zohar.runscore.statisticalanalysis.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import me.zohar.runscore.statisticalanalysis.domain.TodayAccountReceiveOrderSituation;
import me.zohar.runscore.statisticalanalysis.domain.TotalAccountReceiveOrderSituation;

@Data
public class BountyRankVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String receivedAccountId;

	private String userName;

	private Double bounty;

	public static List<BountyRankVO> convertForToday(List<TodayAccountReceiveOrderSituation> situations) {
		if (CollectionUtil.isEmpty(situations)) {
			return new ArrayList<>();
		}
		List<BountyRankVO> vos = new ArrayList<>();
		for (TodayAccountReceiveOrderSituation situation : situations) {
			vos.add(convertForToday(situation));
		}
		return vos;
	}

	public static BountyRankVO convertForToday(TodayAccountReceiveOrderSituation situation) {
		if (situation == null) {
			return null;
		}
		BountyRankVO vo = new BountyRankVO();
		BeanUtils.copyProperties(situation, vo);
		vo.userNameDesensitization();
		return vo;
	}

	public static List<BountyRankVO> convertFor(List<TotalAccountReceiveOrderSituation> situations) {
		if (CollectionUtil.isEmpty(situations)) {
			return new ArrayList<>();
		}
		List<BountyRankVO> vos = new ArrayList<>();
		for (TotalAccountReceiveOrderSituation situation : situations) {
			vos.add(convertFor(situation));
		}
		return vos;
	}

	public static BountyRankVO convertFor(TotalAccountReceiveOrderSituation situation) {
		if (situation == null) {
			return null;
		}
		BountyRankVO vo = new BountyRankVO();
		BeanUtils.copyProperties(situation, vo);
		vo.userNameDesensitization();
		return vo;
	}

	public void userNameDesensitization() {
		if (StrUtil.isNotBlank(this.userName)) {
			this.setUserName(this.userName.substring(0, 1) + "***"
					+ this.userName.substring(this.userName.length() - 3, this.userName.length()));
		}

	}

}
