package me.zohar.runscore.task.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.zohar.runscore.merchant.service.MerchantOrderService;

@Component
@Slf4j
public class OrderRebateAutoSettlementTask {

	@Autowired
	private MerchantOrderService merchantOrderService;

	//@Scheduled(fixedRate = 50000)
	public void execute() {
		try {
			log.info("订单返点自动结算定时任务start");
			merchantOrderService.orderRebateAutoSettlement();
			log.info("订单返点自动结算定时任务end");
		} catch (Exception e) {
			log.error("订单返点自动结算定时任务", e);
		}
	}

}
