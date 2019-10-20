package me.zohar.runscore.merchantorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.UserAccountDetails;
import me.zohar.runscore.merchant.param.AppealQueryCondParam;
import me.zohar.runscore.merchant.param.UserStartAppealParam;
import me.zohar.runscore.merchant.service.AppealService;

@Controller
@RequestMapping("/appeal")
public class AppealController {

	@Autowired
	private AppealService appealService;

	@GetMapping("/userCancelAppeal")
	@ResponseBody
	public Result userCancelAppeal(String appealId) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		appealService.userCancelAppeal(user.getUserAccountId(), appealId);
		return Result.success();
	}

	@GetMapping("/findUserAppealRecordByPage")
	@ResponseBody
	public Result findUserAppealRecordByPage(AppealQueryCondParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setReceiverUserName(user.getUsername());
		return Result.success().setData(appealService.findAppealByPage(param));
	}

	@PostMapping("/userStartAppeal")
	@ResponseBody
	public Result userStartAppeal(UserStartAppealParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		appealService.userStartAppeal(user.getUserAccountId(), param);
		return Result.success();
	}

	@GetMapping("/findAppealById")
	@ResponseBody
	public Result findAppealById(String appealId) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return Result.success().setData(appealService.findUserAppealById(user.getUsername(), appealId));
	}

	@GetMapping("/userUploadSreenshot")
	@ResponseBody
	public Result merchantUploadSreenshot(String appealId, String userSreenshotIds) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		appealService.userUploadSreenshot(user.getUserAccountId(), appealId, userSreenshotIds);
		return Result.success();
	}

}
