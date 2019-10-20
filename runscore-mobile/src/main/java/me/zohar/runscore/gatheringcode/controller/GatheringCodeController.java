package me.zohar.runscore.gatheringcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.config.security.UserAccountDetails;
import me.zohar.runscore.gatheringcode.param.GatheringCodeParam;
import me.zohar.runscore.gatheringcode.param.GatheringCodeQueryCondParam;
import me.zohar.runscore.gatheringcode.service.GatheringCodeService;

@Controller
@RequestMapping("/gatheringCode")
public class GatheringCodeController {

	@Autowired
	private GatheringCodeService gatheringCodeService;

	@GetMapping("/delMyGatheringCodeById")
	@ResponseBody
	public Result delMyGatheringCodeById(String id) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		gatheringCodeService.delMyGatheringCodeById(id, user.getUserAccountId());
		return Result.success();
	}

	@GetMapping("/findMyGatheringCodeById")
	@ResponseBody
	public Result findMyGatheringCodeById(String id) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return Result.success().setData(gatheringCodeService.findMyGatheringCodeById(id, user.getUserAccountId()));
	}

	@PostMapping("/addOrUpdateGatheringCode")
	@ResponseBody
	public Result addOrUpdateGatheringCode(@RequestBody GatheringCodeParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		gatheringCodeService.addOrUpdateGatheringCode(param, user.getUserAccountId());
		return Result.success();
	}

	@GetMapping("/findMyGatheringCodeByPage")
	@ResponseBody
	public Result findMyGatheringCodeByPage(GatheringCodeQueryCondParam param) {
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		param.setUserAccountId(user.getUserAccountId());
		return Result.success().setData(gatheringCodeService.findMyGatheringCodeByPage(param));
	}

}
