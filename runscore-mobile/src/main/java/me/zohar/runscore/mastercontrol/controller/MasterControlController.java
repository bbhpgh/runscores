package me.zohar.runscore.mastercontrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.mastercontrol.service.MasterControlService;

/**
 * 总控
 * 
 * @author zohar
 * @date 2019年3月9日
 *
 */
@Controller
@RequestMapping("/masterControl")
public class MasterControlController {

	@Autowired
	private MasterControlService service;

	@GetMapping("/getRechargeSetting")
	@ResponseBody
	public Result getRechargeSetting() {
		return Result.success().setData(service.getRechargeSetting());
	}

	@GetMapping("/getSystemSetting")
	@ResponseBody
	public Result getSystemSetting() {
		return Result.success().setData(service.getSystemSetting());
	}

	@GetMapping("/getRegisterSetting")
	@ResponseBody
	public Result getRegisterSetting() {
		return Result.success().setData(service.getRegisterSetting());
	}

	@GetMapping("/getCustomerQrcodeSetting")
	@ResponseBody
	public Result getCustomerQrcodeSetting() {
		return Result.success().setData(service.getCustomerQrcodeSetting());
	}

}
