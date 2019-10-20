package me.zohar.runscore.admin.rechargewithdraw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.rechargewithdraw.param.AddOrUpdatePayChannelParam;
import me.zohar.runscore.rechargewithdraw.param.AddOrUpdatePayTypeParam;
import me.zohar.runscore.rechargewithdraw.param.RechargeOrderQueryCondParam;
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

	@GetMapping("/findRechargeOrderByPage")
	@ResponseBody
	public Result findRechargeOrderByPage(RechargeOrderQueryCondParam param) {
		return Result.success().setData(rechargeService.findRechargeOrderByPage(param));
	}

	@GetMapping("/cancelOrder")
	@ResponseBody
	public Result cancelOrder(String id) {
		rechargeService.cancelOrder(id);
		return Result.success();
	}

	@GetMapping("/manualSettlement")
	@ResponseBody
	public Result manualSettlement(String orderNo) {
		rechargeService.manualSettlement(orderNo);
		return Result.success();
	}

	@GetMapping("/findRechargeOrderById")
	@ResponseBody
	public Result findRechargeOrderById(String id) {
		return Result.success().setData(rechargeService.findRechargeOrderById(id));
	}

	@PostMapping("/approval")
	@ResponseBody
	public Result approval(String id, Double actualPayAmount, String approvalResult) {
		rechargeService.approval(id, actualPayAmount, approvalResult);
		return Result.success();
	}

	@GetMapping("/findAllPayType")
	@ResponseBody
	public Result findAllPayType() {
		return Result.success().setData(payChannelService.findAllPayType());
	}

	@GetMapping("/findAllPayChannel")
	@ResponseBody
	public Result findAllPayChannel() {
		return Result.success().setData(payChannelService.findAllPayChannel());
	}
	
	@PostMapping("/addOrUpdatePayType")
	@ResponseBody
	public Result addOrUpdatePayType(@RequestBody AddOrUpdatePayTypeParam param) {
		payChannelService.addOrUpdatePayType(param);
		return Result.success();
	}

	@GetMapping("/findPayTypeById")
	@ResponseBody
	public Result findPayTypeById(String id) {
		return Result.success().setData(payChannelService.findPayTypeById(id));
	}

	@GetMapping("/delPayTypeById")
	@ResponseBody
	public Result delPayTypeById(String id) {
		payChannelService.delPayTypeById(id);
		return Result.success();
	}
	
	@PostMapping("/addOrUpdatePayChannel")
	@ResponseBody
	public Result addOrUpdatePayChannel(@RequestBody AddOrUpdatePayChannelParam param) {
		payChannelService.addOrUpdatePayChannel(param);
		return Result.success();
	}

	@GetMapping("/findPayChannelById")
	@ResponseBody
	public Result findPayChannelById(String id) {
		return Result.success().setData(payChannelService.findPayChannelById(id));
	}

	@GetMapping("/delPayChannelById")
	@ResponseBody
	public Result delPayChannelById(String id) {
		payChannelService.delPayChannelById(id);
		return Result.success();
	}
}
