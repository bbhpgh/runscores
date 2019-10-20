package me.zohar.runscore.admin.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.merchant.param.AddOrUpdateMerchantParam;
import me.zohar.runscore.merchant.param.MerchantQueryCondParam;
import me.zohar.runscore.merchant.service.MerchantService;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;
	
	@PostMapping("/addOrUpdateMerchant")
	@ResponseBody
	public Result addOrUpdatePlatform(AddOrUpdateMerchantParam param) {
		merchantService.addOrUpdateMerchant(param);
		return Result.success();
	}

	@GetMapping("/findMerchantById")
	@ResponseBody
	public Result findPlatformById(String id) {
		return Result.success().setData(merchantService.findPlatformById(id));
	}

	@GetMapping("/delMerchantById")
	@ResponseBody
	public Result delPlatformById(String id) {
		merchantService.delMerchantById(id);
		return Result.success();
	}

	@GetMapping("/findMerchantByPage")
	@ResponseBody
	public Result findPlatformOrderByPage(MerchantQueryCondParam param) {
		return Result.success().setData(merchantService.findMerchantByPage(param));
	}

}
