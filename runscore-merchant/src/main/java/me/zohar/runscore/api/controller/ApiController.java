package me.zohar.runscore.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.merchant.param.StartOrderParam;
import me.zohar.runscore.merchant.service.MerchantOrderService;
import me.zohar.runscore.merchant.vo.OrderGatheringCodeVO;

@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private MerchantOrderService platformOrderService;

	@PostMapping("/startOrder")
	@ResponseBody
	public Result startOrder(StartOrderParam param) {
		return Result.success().setData(platformOrderService.startOrder(param));
	}

	@GetMapping("/getOrderGatheringCode")
	@ResponseBody
	public Result getOrderGatheringCode(String orderNo) {
		OrderGatheringCodeVO vo = platformOrderService.getOrderGatheringCode(orderNo);
		return Result.success().setData(vo);
	}

}
