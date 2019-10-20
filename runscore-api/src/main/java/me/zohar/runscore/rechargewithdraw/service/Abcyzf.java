package me.zohar.runscore.rechargewithdraw.service;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpUtil;
import me.zohar.runscore.common.exception.BizError;
import me.zohar.runscore.common.exception.BizException;
import me.zohar.runscore.dictconfig.ConfigHolder;

@Service("abcyzf")
public class Abcyzf implements PayPlatformService {

	public static final String 支付成功状态 = "TRADE_SUCCESS";

	@Override
	public String startPay(String orderNo, Double amount, String channelCode) {
		String pid = ConfigHolder.getConfigValue("abcyzf.pid");
		String notifyUrl = ConfigHolder.getConfigValue("abcyzf.notifyUrl");
		String returnUrl = ConfigHolder.getConfigValue("abcyzf.returnUrl");
		String name = ConfigHolder.getConfigValue("abcyzf.name");
		String signType = "MD5";
		Map<String, Object> params = new HashMap<>();
		params.put("pid", pid);
		params.put("type", channelCode);
		params.put("out_trade_no", orderNo);
		params.put("notify_url", notifyUrl);
		params.put("return_url", returnUrl);
		params.put("name", name);
		params.put("money", String.valueOf(amount));
		params.put("sign_type", signType);
		params.put("sign", generateRequestSign(orderNo, String.valueOf(amount), channelCode));

		String result = HttpUtil.get(ConfigHolder.getConfigValue("abcyzf.payUrl"), params);
		System.err.println(result);
		if (StrUtil.isBlank(result)) {
			throw new BizException(BizError.发起支付异常);
		}

		String payUrl = result;
		try {
			Document document = Jsoup.parse(result);
			Element element = document.selectFirst("script");
			if (element != null) {
				payUrl = element.data().replace("window.location.href='", "");
				payUrl = payUrl.substring(0, payUrl.length() - 2);
			}
		} catch (Exception e) {
			throw new BizException(BizError.发起支付异常);
		}
		return payUrl;
	}

	public static String generateCallbackSign(String orderNo, String amount, String payWay, String tradeNo,
			String tradeStatus) {
		String pid = ConfigHolder.getConfigValue("abcyzf.pid");
		String name = ConfigHolder.getConfigValue("abcyzf.name");
		String secretKey = ConfigHolder.getConfigValue("abcyzf.secretKey");
		String string = "money=" + amount + "&name=" + name + "&out_trade_no=" + orderNo + "&pid=" + pid + "&trade_no="
				+ tradeNo + "&trade_status=" + tradeStatus + "&type=" + payWay + secretKey;
		System.out.println(string);
		String sign = new Digester(DigestAlgorithm.MD5).digestHex(string);
		return sign;
	}

	public static String generateRequestSign(String orderNo, String amount, String payWay) {
		String pid = ConfigHolder.getConfigValue("abcyzf.pid");
		String notifyUrl = ConfigHolder.getConfigValue("abcyzf.notifyUrl");
		String returnUrl = ConfigHolder.getConfigValue("abcyzf.returnUrl");
		String name = ConfigHolder.getConfigValue("abcyzf.name");
		String secretKey = ConfigHolder.getConfigValue("abcyzf.secretKey");
		String string = "money=" + amount + "&name=" + name + "&notify_url=" + notifyUrl + "&out_trade_no=" + orderNo
				+ "&pid=" + pid + "&return_url=" + returnUrl + "&type=" + payWay + secretKey;
		String sign = new Digester(DigestAlgorithm.MD5).digestHex(string);
		return sign;
	}

}
