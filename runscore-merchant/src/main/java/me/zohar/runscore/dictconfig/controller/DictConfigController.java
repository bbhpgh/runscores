package me.zohar.runscore.dictconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.runscore.common.vo.Result;
import me.zohar.runscore.dictconfig.DictHolder;

@Controller
@RequestMapping("/dictconfig")
public class DictConfigController {

	@GetMapping("/findDictItemInCache")
	@ResponseBody
	public Result findDictItemInCache(String dictTypeCode) {
		return Result.success().setData(DictHolder.findDictItem(dictTypeCode));
	}

}
