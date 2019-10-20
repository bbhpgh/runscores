package me.zohar.runscore.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.MerchantAccountDetails;
import me.zohar.runscore.merchant.param.AppealQueryCondParam;
import me.zohar.runscore.merchant.param.MerchantStartAppealParam;
import me.zohar.runscore.merchant.service.AppealService;

@Controller
@RequestMapping("/appeal")
public class AppealController {

	@Autowired
	private AppealService appealService;

	@GetMapping("/merchantCancelAppeal")
	@ResponseBody
	public Result merchantCancelAppeal(String appealId) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		appealService.merchantCancelAppeal(user.getMerchantId(), appealId);
		return Result.success();
	}

	@PostMapping("/merchantStartAppeal")
	@ResponseBody
	public Result merchantStartAppeal(MerchantStartAppealParam param) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		appealService.merchantStartAppeal(user.getMerchantId(), param);
		return Result.success();
	}

	@GetMapping("/findAppealByPage")
	@ResponseBody
	public Result findAppealByPage(AppealQueryCondParam param) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setMerchantName(user.getMerchantName());
		return Result.success().setData(appealService.findAppealByPage(param));
	}

	@GetMapping("/findAppealById")
	@ResponseBody
	public Result findAppealById(String appealId) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return Result.success().setData(appealService.findMerchantAppealById(user.getMerchantName(), appealId));
	}

	@GetMapping("/merchantUploadSreenshot")
	@ResponseBody
	public Result merchantUploadSreenshot(String appealId, String merchantSreenshotIds) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		appealService.merchantUploadSreenshot(user.getMerchantId(), appealId, merchantSreenshotIds);
		return Result.success();
	}

}
