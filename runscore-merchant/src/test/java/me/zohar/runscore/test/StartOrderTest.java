package me.zohar.runscore.test;

import java.text.DecimalFormat;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class StartOrderTest {

	/**
	 * 发起订单接口生成签名例子
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String merchantNum = "201906001";
		String merchantOrderNo = "20190629023134U936283877";
		Double amount = 100d;
		String notifyUrl = "http://xhbc10.com/index.php/Pay/Paynotify";
		String secretKey = "l54x9426o68962464";
		String sign = merchantNum + merchantOrderNo
				+ new DecimalFormat("###################.###########").format(amount) + notifyUrl + secretKey;
		System.out.println(sign);
		sign = new Digester(DigestAlgorithm.MD5).digestHex(sign);
		System.out.println(sign);
		/**
		 * 明文:1001dfjkrjekre3434300.0http://localhost:8082/platform-order456
		 * 试试用上面的明文MD5加密后能不能得到下面的签名 签名:32216dbbd49293c4ff3085cfec9960ec
		 */
	}

}
