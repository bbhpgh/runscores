package me.zohar.runscore.rechargewithdraw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.UserAccountDetails;
import me.zohar.runscore.rechargewithdraw.param.AbcyzfCallbackParam;
import me.zohar.runscore.rechargewithdraw.param.LowerLevelRechargeOrderQueryCondParam;
import me.zohar.runscore.rechargewithdraw.param.RechargeOrderParam;
import me.zohar.runscore.rechargewithdraw.service.PayChannelService;
import me.zohar.runscore.rechargewithdraw.service.RechargeService;

/**
 * 
 * @author zohar
 * @date 2019年1月21日
 *
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;

	@Autowired
	private PayChannelService payChannelService;

	@RequestMapping("/callback/abcyzfCallback")
	@ResponseBody
	public String abcyzfCallback(AbcyzfCallbackParam param) throws IOException {
		rechargeService.checkOrderWithAbcyzf(param);
		return Result.success().getMsg();
	}

	@PostMapping("/generateRechargeOrder")
	@ResponseBody
	public Result generateRechargeOrder(RechargeOrderParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setUserAccountId(user.getUserAccountId());
		return Result.success().setData(rechargeService.generateRechargeOrder(param));
	}

	@RequestMapping("/findEnabledPayType")
	@ResponseBody
	public Result findEnabledPayType() throws IOException {
		return Result.success().setData(payChannelService.findEnabledPayType());
	}

	@RequestMapping("/findEnabledPayChannel")
	@ResponseBody
	public Result findEnabledPayChannel() throws IOException {
		return Result.success().setData(payChannelService.findEnabledPayChannel());
	}

	@GetMapping("/findLowerLevelRechargeOrderByPage")
	@ResponseBody
	public Result findLowerLevelRechargeOrderByPage(LowerLevelRechargeOrderQueryCondParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setCurrentAccountId(user.getUserAccountId());
		return Result.success().setData(rechargeService.findLowerLevelRechargeOrderByPage(param));
	}

}
