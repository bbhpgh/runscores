package me.zohar.runscore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 帐变日志
	 * 
	 * @return
	 */
	@GetMapping("/account-change-log")
	public String accountChangeLog() {
		return "account-change-log";
	}

	/**
	 * 充值订单
	 * 
	 * @return
	 */
	@GetMapping("/recharge-order")
	public String rechargeOrder() {
		return "recharge-order";
	}

	/**
	 * 提现记录
	 * 
	 * @return
	 */
	@GetMapping("/withdraw-record")
	public String withdrawRecord() {
		return "withdraw-record";
	}

	/**
	 * 平台订单
	 * 
	 * @return
	 */
	@GetMapping("/platform-order")
	public String platformOrder() {
		return "platform-order";
	}

	/**
	 * 统计分析
	 * 
	 * @return
	 */
	@GetMapping("/statistical-analysis")
	public String statisticalAnalysis() {
		return "statistical-analysis";
	}

	/**
	 * 申诉记录
	 * 
	 * @return
	 */
	@GetMapping("/appeal-record")
	public String appealRecord() {
		return "appeal-record";
	}

	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}

}
