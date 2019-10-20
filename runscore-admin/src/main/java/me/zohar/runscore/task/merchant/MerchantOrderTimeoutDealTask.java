package me.zohar.runscore.task.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.zohar.runscore.merchant.service.MerchantOrderService;

@Component
@Slf4j
public class MerchantOrderTimeoutDealTask {

	@Autowired
	private MerchantOrderService merchantOrderService;

	//@Scheduled(fixedRate = 3000)
	public void execute() {
		try {
			log.info("商户订单超时处理定时任务start");
			merchantOrderService.orderTimeoutDeal();
			log.info("商户订单超时处理定时任务end");
		} catch (Exception e) {
			log.error("商户订单超时处理定时任务", e);
		}
	}

}
