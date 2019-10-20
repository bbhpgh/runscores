package me.zohar.runscore.statisticalanalysis.service;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.date.DateUtil;
import me.zohar.runscore.common.valid.ParamValid;
import me.zohar.runscore.statisticalanalysis.domain.merchant.MerchantEverydayStatistical;
import me.zohar.runscore.statisticalanalysis.domain.merchant.MerchantMonthStatistical;
import me.zohar.runscore.statisticalanalysis.domain.merchant.MerchantTodayStatistical;
import me.zohar.runscore.statisticalanalysis.domain.merchant.MerchantTotalStatistical;
import me.zohar.runscore.statisticalanalysis.param.MerchantIndexQueryParam;
import me.zohar.runscore.statisticalanalysis.repo.merchant.MerchantEverydayStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.repo.merchant.MerchantMonthStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.repo.merchant.MerchantTodayStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.repo.merchant.MerchantTotalStatisticalRepo;
import me.zohar.runscore.statisticalanalysis.vo.IndexStatisticalVO;

@Validated
@Service
public class MerchantStatisticalAnalysisService {

	@Autowired
	private MerchantTotalStatisticalRepo totalStatisticalRepo;

	@Autowired
	private MerchantMonthStatisticalRepo monthStatisticalRepo;

	@Autowired
	private MerchantTodayStatisticalRepo todayStatisticalRepo;

	@Autowired
	private MerchantEverydayStatisticalRepo everydayStatisticalRepo;

	@Transactional(readOnly = true)
	public IndexStatisticalVO findTotalStatistical(@NotBlank String merchantId) {
		MerchantTotalStatistical statistical = totalStatisticalRepo.findById(merchantId).orElse(null);
		return IndexStatisticalVO.convertForTotal(statistical);
	}

	@Transactional(readOnly = true)
	public IndexStatisticalVO findMonthStatistical(@NotBlank String merchantId) {
		MerchantMonthStatistical statistical = monthStatisticalRepo.findById(merchantId).orElse(null);
		return IndexStatisticalVO.convertForMonth(statistical);
	}

	@Transactional(readOnly = true)
	public IndexStatisticalVO findTodayStatistical(@NotBlank String merchantId) {
		MerchantTodayStatistical statistical = todayStatisticalRepo.findById(merchantId).orElse(null);
		return IndexStatisticalVO.convertForToday(statistical);
	}

	@ParamValid
	@Transactional(readOnly = true)
	public List<IndexStatisticalVO> findEverydayStatistical(MerchantIndexQueryParam param) {
		List<MerchantEverydayStatistical> statisticals = everydayStatisticalRepo
				.findByMerchantIdAndEverydayGreaterThanEqualAndEverydayLessThanEqualOrderByEveryday(
						param.getMerchantId(), DateUtil.beginOfDay(param.getStartTime()),
						DateUtil.beginOfDay(param.getEndTime()));
		return IndexStatisticalVO.convertForEvery(statisticals);
	}

}
