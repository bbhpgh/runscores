package me.zohar.runscore.admin.statisticalanalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
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
	
	@GetMapping("/findTotalCashDeposit")
	@ResponseBody
	public Result findTotalCashDeposit() {
		return Result.success().setData(statisticalAnalysisService.findTotalCashDeposit());
	}
	
	@GetMapping("/findTotalStatistical")
	@ResponseBody
	public Result findTotalStatistical() {
		return Result.success().setData(statisticalAnalysisService.findTotalStatistical());
	}
	
	@GetMapping("/findMonthStatistical")
	@ResponseBody
	public Result findMonthStatistical() {
		return Result.success().setData(statisticalAnalysisService.findMonthStatistical());
	}
	
	@GetMapping("/findTodayStatistical")
	@ResponseBody
	public Result findTodayStatistical() {
		return Result.success().setData(statisticalAnalysisService.findTodayStatistical());
	}

}
