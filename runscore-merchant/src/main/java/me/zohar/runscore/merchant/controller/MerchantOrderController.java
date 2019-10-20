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
import me.zohar.runscore.merchant.param.ManualStartOrderParam;
import me.zohar.runscore.merchant.param.MerchantOrderQueryCondParam;
import me.zohar.runscore.merchant.service.MerchantOrderService;

@Controller
@RequestMapping("/merchantOrder")
public class MerchantOrderController {

	@Autowired
	private MerchantOrderService platformOrderService;

	@GetMapping("/findMerchantOrderByPage")
	@ResponseBody
	public Result findMerchantOrderByPage(MerchantOrderQueryCondParam param) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setPlatformName(user.getMerchantName());
		return Result.success().setData(platformOrderService.findMerchantOrderByPage(param));
	}

	@GetMapping("/merchantCancelOrder")
	@ResponseBody
	public Result merchatCancelOrder(String id) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		platformOrderService.merchatCancelOrder(user.getMerchantId(), id);
		return Result.success();
	}

	@GetMapping("/findMerchantOrderDetailsById")
	@ResponseBody
	public Result findMerchantOrderDetailsById(String orderId) {
		return Result.success().setData(platformOrderService.findMerchantOrderDetailsById(orderId));
	}

	@PostMapping("/startOrder")
	@ResponseBody
	public Result startOrder(ManualStartOrderParam param) {
		MerchantAccountDetails user = (MerchantAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setMerchantNum(user.getMerchantNum());
		platformOrderService.manualStartOrder(param);
		return Result.success();
	}

	@GetMapping("/resendNotice")
	@ResponseBody
	public Result resendNotice(String id) {
		return Result.success().setData(platformOrderService.paySuccessAsynNotice(id));
	}
}
