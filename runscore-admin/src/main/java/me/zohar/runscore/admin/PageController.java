package me.zohar.runscore.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String index() {
		return "statistical-analysis";
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
	 * 投注记录
	 * 
	 * @return
	 */
	@GetMapping("/betting-record")
	public String bettingRecord() {
		return "betting-record";
	}

	/**
	 * 账号管理
	 * 
	 * @return
	 */
	@GetMapping("/account-manage")
	public String accountManage() {
		return "account-manage";
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
	 * 配置项管理
	 * 
	 * @return
	 */
	@GetMapping("/config-manage")
	public String configManage() {
		return "config-manage";
	}

	/**
	 * 字典管理
	 * 
	 * @return
	 */
	@GetMapping("/dict-manage")
	public String dictManage() {
		return "dict-manage";
	}

	/**
	 * 总控室
	 * 
	 * @return
	 */
	@GetMapping("/master-control-room")
	public String masterControlRoom() {
		return "master-control-room";
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
	 * 接入商家
	 * 
	 * @return
	 */
	@GetMapping("/merchant")
	public String merchant() {
		return "merchant";
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
	 * 收款码
	 * 
	 * @return
	 */
	@GetMapping("/gathering-code")
	public String gatheringCode() {
		return "gathering-code";
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
	
	@GetMapping("/appeal-details")
	public String appealDetails() {
		return "appeal-details";
	}
	
	@GetMapping("/pay-channel")
	public String payChannel() {
		return "pay-channel";
	}
	
	@GetMapping("/login-log")
	public String loginLog() {
		return "login-log";
	}
	
	/**
	 * 返点表
	 * 
	 * @return
	 */
	@GetMapping("/rebate")
	public String rebate() {
		return "rebate";
	}

}
