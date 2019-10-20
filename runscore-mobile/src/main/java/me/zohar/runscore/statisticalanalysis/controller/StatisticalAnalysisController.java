package me.zohar.runscore.statisticalanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.UserAccountDetails;
import me.zohar.runscore.statisticalanalysis.service.StatisticalAnalysisService;

@Controller
@RequestMapping("/statisticalAnalysis")
public class StatisticalAnalysisController {

	@Autowired
	private StatisticalAnalysisService statisticalAnalysisService;

	@GetMapping("/findTodayTop10BountyRank")
	@ResponseBody
	public Result findTodayTop10BountyRank() {
		return Result.success().setData(statisticalAnalysisService.findTodayTop10BountyRank());
	}

	@GetMapping("/findTotalTop10BountyRank")
	@ResponseBody
	public Result findTotalTop10BountyRank() {
		return Result.success().setData(statisticalAnalysisService.findTotalTop10BountyRank());
	}

	@GetMapping("/findMyTodayReceiveOrderSituation")
	@ResponseBody
	public Result findMyTodayReceiveOrderSituation() {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return Result.success().setData(statisticalAnalysisService.findMyTodayReceiveOrderSituation(user.getUserAccountId()));
	}

	@GetMapping("/findMyTotalReceiveOrderSituation")
	@ResponseBody
	public Result findMyTotalReceiveOrderSituation() {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return Result.success().setData(statisticalAnalysisService.findMyTotalReceiveOrderSituation(user.getUserAccountId()));
	}
}
