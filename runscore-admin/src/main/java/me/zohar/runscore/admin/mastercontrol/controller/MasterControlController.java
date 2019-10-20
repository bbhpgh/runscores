package me.zohar.runscore.admin.mastercontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.mastercontrol.param.UpdateReceiveOrderSettingParam;
import me.zohar.runscore.mastercontrol.param.UpdateRechargeSettingParam;
import me.zohar.runscore.mastercontrol.param.UpdateRegisterSettingParam;
import me.zohar.runscore.mastercontrol.param.UpdateSystemSettingParam;
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

	@GetMapping("/getSystemSetting")
	@ResponseBody
	public Result getSystemSetting() {
		return Result.success().setData(service.getSystemSetting());
	}

	@PostMapping("/updateSystemSetting")
	@ResponseBody
	public Result updateSystemSetting(UpdateSystemSettingParam param) {
		service.updateSystemSetting(param);
		return Result.success();
	}

	@GetMapping("/getRegisterSetting")
	@ResponseBody
	public Result getRegisterSetting() {
		return Result.success().setData(service.getRegisterSetting());
	}

	@PostMapping("/updateRegisterSetting")
	@ResponseBody
	public Result updateRegisterSetting(UpdateRegisterSettingParam param) {
		service.updateRegisterSetting(param);
		return Result.success();
	}

	@GetMapping("/getReceiveOrderSetting")
	@ResponseBody
	public Result getReceiveOrderSetting() {
		return Result.success().setData(service.getReceiveOrderSetting());
	}

	@PostMapping("/updateReceiveOrderSetting")
	@ResponseBody
	public Result updateReceiveOrderSetting(UpdateReceiveOrderSettingParam param) {
		service.updateReceiveOrderSetting(param);
		return Result.success();
	}

	@GetMapping("/getRechargeSetting")
	@ResponseBody
	public Result getRechargeSetting() {
		return Result.success().setData(service.getRechargeSetting());
	}

	@PostMapping("/updateRechargeSetting")
	@ResponseBody
	public Result updateRechargeSetting(UpdateRechargeSettingParam param) {
		service.updateRechargeSetting(param);
		return Result.success();
	}

	@GetMapping("/getCustomerQrcodeSetting")
	@ResponseBody
	public Result getCustomerQrcodeSetting() {
		return Result.success().setData(service.getCustomerQrcodeSetting());
	}

	@PostMapping("/updateCustomerQrcodeSetting")
	@ResponseBody
	public Result updateCustomerQrcodeSetting(String qrcodeStorageIds) {
		service.updateCustomerQrcodeSetting(qrcodeStorageIds);
		return Result.success();
	}

	@PostMapping("/refreshCache")
	@ResponseBody
	public Result refreshCache(@RequestBody List<String> cacheItems) {
		service.refreshCache(cacheItems);
		return Result.success();
	}

}
