package me.zohar.runscore.statisticalanalysis.service;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.alicp.jetcache.anno.Cached;

import cn.hutool.core.util.NumberUtil;
import me.zohar.runscore.statisticalanalysis.domain.MonthStatistical;
import me.zohar.runscore.statisticalanalysis.domain.TodayAccountReceiveOrderSituation;
import me.zohar.runscore.statisticalanalysis.domain.TodayStatistical;
import me.zohar.runscore.statisticalanalysis.domain.TotalAccountReceiveOrderSituation;
import me.zohar.runscore.statisticalanalysis.domain.TotalCashDeposit;
import me.zohar.runscore.statisticalanalysis.domain.TotalStatistical;
import me.zohar.runscore.statisticalanalysis.repo.MonthStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.repo.TodayAccountReceiveOrderSituationRepo;
import me.zohar.runscore.statisticalanalysis.repo.TodayStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.repo.TotalAccountReceiveOrderSituationRepo;
import me.zohar.runscore.statisticalanalysis.repo.TotalCashDepositRepo;
import me.zohar.runscore.statisticalanalysis.repo.TotalStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.vo.AccountReceiveOrderSituationVO;
import me.zohar.runscore.statisticalanalysis.vo.BountyRankVO;
import me.zohar.runscore.statisticalanalysis.vo.IndexStatisticalVO;

@Validated
@Service
public class StatisticalAnalysisService {

	@Autowired
	private TotalAccountReceiveOrderSituationRepo totalAccountReceiveOrderSituationRepo;

	@Autowired
	private TodayAccountReceiveOrderSituationRepo todayAccountReceiveOrderSituationRepo;

	@Autowired
	private TotalCashDepositRepo totalCashDepositRepo;

	@Autowired
	private TotalStatisticalRepo totalStatisticalRepo;

	@Autowired
	private MonthStatisticalRepo monthStatisticalRepo;

	@Autowired
	private TodayStatisticalRepo todayStatisticalRepo;

	@Transactional(readOnly = true)
	public AccountReceiveOrderSituationVO findMyTodayReceiveOrderSituation(@NotBlank String userAccountId) {
		return AccountReceiveOrderSituationVO
				.convertForToday(todayAccountReceiveOrderSituationRepo.findByReceivedAccountId(userAccountId));
	}

	@Transactional(readOnly = true)
	public AccountReceiveOrderSituationVO findMyTotalReceiveOrderSituation(@NotBlank String userAccountId) {
		return AccountReceiveOrderSituationVO
				.convertForTotal(totalAccountReceiveOrderSituationRepo.findByReceivedAccountId(userAccountId));
	}

	@Cached(name = "totalTop10BountyRank", expire = 300)
	@Transactional(readOnly = true)
	public List<BountyRankVO> findTotalTop10BountyRank() {
		List<TotalAccountReceiveOrderSituation> receiveOrderSituations = totalAccountReceiveOrderSituationRepo
				.findTop10ByOrderByBountyDesc();
		return BountyRankVO.convertFor(receiveOrderSituations);
	}

	@Cached(name = "todayTop10BountyRank", expire = 300)
	@Transactional(readOnly = true)
	public List<BountyRankVO> findTodayTop10BountyRank() {
		List<TodayAccountReceiveOrderSituation> todayReceiveOrderSituations = todayAccountReceiveOrderSituationRepo
				.findTop10ByOrderByBountyDesc();
		return BountyRankVO.convertForToday(todayReceiveOrderSituations);
	}

	@Transactional(readOnly = true)
	public Double findTotalCashDeposit() {
		TotalCashDeposit totalCashDeposit = totalCashDepositRepo.findTopBy();
		if(totalCashDeposit == null){
			return 0.0;
		}
		return NumberUtil.round(totalCashDeposit.getTotalCashDeposit(), 4).doubleValue();
	}

	@Transactional(readOnly = true)
	public IndexStatisticalVO findTotalStatistical() {
		TotalStatistical statistical = totalStatisticalRepo.findTopBy();
		return IndexStatisticalVO.convertForTotal(statistical);
	}

	@Transactional(readOnly = true)
	public IndexStatisticalVO findMonthStatistical() {
		MonthStatistical statistical = monthStatisticalRepo.findTopBy();
		return IndexStatisticalVO.convertForMonth(statistical);
	}

	@Transactional(readOnly = true)
	public IndexStatisticalVO findTodayStatistical() {
		TodayStatistical statistical = todayStatisticalRepo.findTopBy();
		return IndexStatisticalVO.convertForToday(statistical);
	}

}
