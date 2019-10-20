package me.zohar.runscore.gatheringcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.gatheringcode.service.GatheringCodeService;

@Controller
@RequestMapping("/gatheringCode")
public class GatheringCodeController {

	@Autowired
	private GatheringCodeService gatheringCodeService;

	@GetMapping("/findGatheringCodeById")
	@ResponseBody
	public Result findMyGatheringCodeById(String id) {
		return Result.success().setData(gatheringCodeService.findGatheringCodeById(id));
	}

}
