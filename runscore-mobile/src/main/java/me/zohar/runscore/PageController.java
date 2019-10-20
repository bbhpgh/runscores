package me.zohar.runscore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

	/**
	 * 首页
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	/**
	 * 我的主页
	 * 
	 * @return
	 */
	@GetMapping("/my-home-page")
	public String myHomePage() {
		return "my-home-page";
	}

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	@GetMapping("/personal-info")
	public String personalInfo() {
		return "personal-info";
	}

	/**
	 * 个人帐变
	 * 
	 * @return
	 */
	@GetMapping("/personal-account-change")
	public String personalAccountChange() {
		return "personal-account-change";
	}

	/**
	 * 充值
	 * 
	 * @return
	 */
	@GetMapping("/recharge")
	public String recharge() {
		return "recharge";
	}

	/**
	 * 提现
	 * 
	 * @return
	 */
	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}

	/**
	 * 个人充提
	 * @return
	 */
	@GetMapping("/recharge-withdraw-log")
	public String rechargeWithdrawLog() {
		return "recharge-withdraw-log";
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
	 * 接单
	 * 
	 * @return
	 */
	@GetMapping("/receive-order")
	public String receiveOrder() {
		return "receive-order";
	}

	/**
	 * 审核订单
	 * 
	 * @return
	 */
	@GetMapping("/audit-order")
	public String auditOrder() {
		return "audit-order";
	}

	/**
	 * 接单记录
	 * 
	 * @return
	 */
	@GetMapping("/receive-order-record")
	public String receiveOrderRecord() {
		return "receive-order-record";
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

	/**
	 * 申诉详情
	 * 
	 * @return
	 */
	@GetMapping("/appeal-details")
	public String appealDetails() {
		return "appeal-details";
	}

	/**
	 * 在线客服
	 * 
	 * @return
	 */
	@GetMapping("/online-customer")
	public String onlineCustomer() {
		return "online-customer";
	}

	/**
	 * 代理中心
	 * 
	 * @return
	 */
	@GetMapping("/agent-center")
	public String agentCenter() {
		return "agent-center";
	}

	/**
	 * 代理开户
	 * 
	 * @return
	 */
	@GetMapping("/agent-open-an-account")
	public String agentOpenAnAccount() {
		return "agent-open-an-account";
	}

	/**
	 * 下级开户
	 * 
	 * @return
	 */
	@GetMapping("/lower-level-open-an-account")
	public String lowerLevelOpenAnAccount() {
		return "lower-level-open-an-account";
	}

	/**
	 * 下级账号管理
	 * @return
	 */
	@GetMapping("/lower-level-account-manage")
	public String LowerLevelAccountManage() {
		return "lower-level-account-manage";
	}

	@GetMapping("/lower-level-account-change")
	public String LowerLevelAccountChange() {
		return "lower-level-account-change";
	}

	/**
	 * 团队接单明细
	 * @return
	 */
	@GetMapping("/lower-level-account-receive-order-record")
	public String LowerLevelAccountReceiveOrderRecord() {
		return "lower-level-account-receive-order-record";
	}
	
	/**
	 * 团队充值明细
	 * @return
	 */
	@GetMapping("/lower-level-recharge-details")
	public String lowerLevelRechargeDetails() {
		return "lower-level-recharge-details";
	}
	
	/**
	 * 团队提现明细
	 * @return
	 */
	@GetMapping("/lower-level-withdraw-details")
	public String lowerLevelWithdrawDetails() {
		return "lower-level-withdraw-details";
	}
	
	@GetMapping("/pay")
	public String pay() {
		return "pay";
	}

	@ResponseBody
	@GetMapping("/paySuccessNotice")
	public String paySuccessNotice() {
		return "success";
	}

}
