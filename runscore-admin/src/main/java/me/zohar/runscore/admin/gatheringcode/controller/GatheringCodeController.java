package me.zohar.runscore.admin.gatheringcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.gatheringcode.param.GatheringCodeParam;
import me.zohar.runscore.gatheringcode.param.GatheringCodeQueryCondParam;
import me.zohar.runscore.gatheringcode.service.GatheringCodeService;

@Controller
@RequestMapping("/gatheringCode")
public class GatheringCodeController {

	@Autowired
	private GatheringCodeService gatheringCodeService;

	@GetMapping("/delGatheringCodeById")
	@ResponseBody
	public Result delGatheringCodeById(String id) {
		gatheringCodeService.delGatheringCodeById(id);
		return Result.success();
	}

	@GetMapping("/findGatheringCodeById")
	@ResponseBody
	public Result findMyGatheringCodeById(String id) {
		return Result.success().setData(gatheringCodeService.findGatheringCodeById(id));
	}

	@PostMapping("/addOrUpdateGatheringCode")
	@ResponseBody
	public Result addOrUpdateGatheringCode(@RequestBody GatheringCodeParam param) {
		gatheringCodeService.addOrUpdateGatheringCode(param);
		return Result.success();
	}

	@GetMapping("/findGatheringCodeByPage")
	@ResponseBody
	public Result findGatheringCodeByPage(GatheringCodeQueryCondParam param) {
		return Result.success().setData(gatheringCodeService.findGatheringCodeByPage(param));
	}

}
