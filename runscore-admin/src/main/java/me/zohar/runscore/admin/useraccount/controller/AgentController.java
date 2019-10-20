package me.zohar.runscore.admin.useraccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.agent.param.AddOrUpdateRebateParam;
import me.zohar.runscore.agent.service.AgentService;
import me.zohar.runscore.common.param.PageParam;
import me.zohar.runscore.common.vo.Result;

@Controller
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentService agentService;

	@GetMapping("/findAllRebate")
	@ResponseBody
	public Result findAllRebate() {
		return Result.success().setData(agentService.findAllRebate());
	}

	@GetMapping("/findRebateSituationByPage")
	@ResponseBody
	public Result findRebateSituationByPage(PageParam param) {
		return Result.success().setData(agentService.findRebateSituationByPage(param));
	}

	@PostMapping("/resetRebate")
	@ResponseBody
	public Result resetRebate(@RequestBody List<AddOrUpdateRebateParam> params) {
		agentService.resetRebate(params);
		return Result.success();
	}

	@GetMapping("/findRebate")
	@ResponseBody
	public Result findRebate(Double rebate) {
		return Result.success().setData(agentService.findRebate(rebate));
	}

	@PostMapping("/addOrUpdateRebate")
	@ResponseBody
	public Result addOrUpdateRebate(@RequestBody AddOrUpdateRebateParam param) {
		agentService.addOrUpdateRebate(param);
		return Result.success();
	}

	@GetMapping("/delRebate")
	@ResponseBody
	public Result delRebate(Double rebate) {
		agentService.delRebate(rebate);
		return Result.success();
	}
}
