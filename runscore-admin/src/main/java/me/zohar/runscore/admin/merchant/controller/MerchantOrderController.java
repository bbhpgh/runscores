package me.zohar.runscore.admin.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.merchant.param.MerchantOrderQueryCondParam;
import me.zohar.runscore.merchant.service.MerchantOrderService;

@Controller
@RequestMapping("/platformOrder")
public class MerchantOrderController {

	@Autowired
	private MerchantOrderService platformOrderService;

	@GetMapping("/findPlatformOrderByPage")
	@ResponseBody
	public Result findPlatformOrderByPage(MerchantOrderQueryCondParam param) {
		return Result.success().setData(platformOrderService.findMerchantOrderByPage(param));
	}

	@GetMapping("/cancelOrder")
	@ResponseBody
	public Result cancelOrder(String id) {
		platformOrderService.cancelOrder(id);
		return Result.success();
	}

	@GetMapping("/resendNotice")
	@ResponseBody
	public Result resendNotice(String id) {
		platformOrderService.paySuccessAsynNotice(id);
		return Result.success();
	}
}
