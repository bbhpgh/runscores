package me.zohar.runscore.useraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.UserAccountDetails;
import me.zohar.runscore.useraccount.param.AccountChangeLogQueryCondParam;
import me.zohar.runscore.useraccount.param.BindBankInfoParam;
import me.zohar.runscore.useraccount.param.LowerLevelAccountChangeLogQueryCondParam;
import me.zohar.runscore.useraccount.param.ModifyLoginPwdParam;
import me.zohar.runscore.useraccount.param.ModifyMoneyPwdParam;
import me.zohar.runscore.useraccount.param.UserAccountRegisterParam;
import me.zohar.runscore.useraccount.service.UserAccountService;
import me.zohar.runscore.useraccount.vo.UserAccountInfoVO;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	@PostMapping("/updateReceiveOrderState")
	@ResponseBody
	public Result updateReceiveOrderState(String receiveOrderState) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		userAccountService.updateReceiveOrderState(user.getUserAccountId(), receiveOrderState);
		return Result.success();
	}

	@PostMapping("/bindBankInfo")
	@ResponseBody
	public Result bindBankInfo(BindBankInfoParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setUserAccountId(user.getUserAccountId());
		userAccountService.bindBankInfo(param);
		return Result.success();
	}

	@GetMapping("/getBankInfo")
	@ResponseBody
	public Result getBankInfo() {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return Result.success().setData(userAccountService.getBankInfo(user.getUserAccountId()));
	}

	@PostMapping("/modifyLoginPwd")
	@ResponseBody
	public Result modifyLoginPwd(ModifyLoginPwdParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setUserAccountId(user.getUserAccountId());
		userAccountService.modifyLoginPwd(param);
		return Result.success();
	}

	@PostMapping("/modifyMoneyPwd")
	@ResponseBody
	public Result modifyMoneyPwd(ModifyMoneyPwdParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setUserAccountId(user.getUserAccountId());
		userAccountService.modifyMoneyPwd(param);
		return Result.success();
	}

	@PostMapping("/register")
	@ResponseBody
	public Result register(UserAccountRegisterParam param) {
		userAccountService.register(param);
		return Result.success();
	}

	@GetMapping("/getUserAccountInfo")
	@ResponseBody
	public Result getUserAccountInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ("anonymousUser".equals(principal)) {
			return Result.success();
		}
		UserAccountDetails user = (UserAccountDetails) principal;
		UserAccountInfoVO userAccountInfo = userAccountService.getUserAccountInfo(user.getUserAccountId());
		return Result.success().setData(userAccountInfo);
	}

	@GetMapping("/findMyAccountChangeLogByPage")
	@ResponseBody
	public Result findMyAccountChangeLogByPage(AccountChangeLogQueryCondParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setUserAccountId(user.getUserAccountId());
		return Result.success().setData(userAccountService.findAccountChangeLogByPage(param));
	}

	@GetMapping("/findLowerLevelAccountChangeLogByPage")
	@ResponseBody
	public Result findLowerLevelAccountChangeLogByPage(LowerLevelAccountChangeLogQueryCondParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setCurrentAccountId(user.getUserAccountId());
		return Result.success().setData(userAccountService.findLowerLevelAccountChangeLogByPage(param));
	}

}
