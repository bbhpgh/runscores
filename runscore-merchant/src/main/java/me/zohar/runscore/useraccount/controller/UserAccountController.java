package me.zohar.runscore.useraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.MerchantAccountDetails;
import me.zohar.runscore.useraccount.service.UserAccountService;
import me.zohar.runscore.useraccount.vo.UserAccountInfoVO;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	@GetMapping("/getUserAccountInfo")
	@ResponseBody
	public Result getUserAccountInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ("anonymousUser".equals(principal)) {
			return Result.success();
		}
		MerchantAccountDetails user = (MerchantAccountDetails) principal;
		UserAccountInfoVO userAccountInfo = userAccountService.getUserAccountInfo(user.getUserAccountId());
		return Result.success().setData(userAccountInfo);
	}

}
