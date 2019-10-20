package me.zohar.runscore.admin.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.merchant.param.AppealQueryCondParam;
import me.zohar.runscore.merchant.service.AppealService;

@Controller
@RequestMapping("/appeal")
public class AppealController {

	@Autowired
	private AppealService appealService;

	@GetMapping("/findTop5TodoAppealByPage")
	@ResponseBody
	public Result findTop5TodoAppealByPage() {
		return Result.success().setData(appealService.findTop5TodoAppealByPage());
	}

	@GetMapping("/dontProcess")
	@ResponseBody
	public Result dontProcess(String appealId) {
		appealService.dontProcess(appealId);
		return Result.success();
	}

	@GetMapping("/cancelOrder")
	@ResponseBody
	public Result cancelOrder(String appealId) {
		appealService.cancelOrder(appealId);
		return Result.success();
	}

	@GetMapping("/alterToActualPayAmount")
	@ResponseBody
	public Result alterToActualPayAmount(String appealId) {
		appealService.alterToActualPayAmount(appealId);
		return Result.success();
	}

	@GetMapping("/confirmToPaid")
	@ResponseBody
	public Result confirmToPaid(String appealId) {
		appealService.confirmToPaid(appealId);
		return Result.success();
	}

	@GetMapping("/findAppealByPage")
	@ResponseBody
	public Result findAppealByPage(AppealQueryCondParam param) {
		return Result.success().setData(appealService.findAppealByPage(param));
	}
	
	@GetMapping("/findAppealDetailsById")
	@ResponseBody
	public Result findAppealDetailsById(String id) {
		return Result.success().setData(appealService.findAppealDetailsById(id));
	}

}
