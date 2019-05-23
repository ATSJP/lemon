package com.lemon.web.clock;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lemon.repository.OrderInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * AliPayClock
 *
 * @author sjp
 * @date 2019/5/23
 */
@Component
public class AliPayClock {

	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private AlipayClient		alipayClient;
	@Resource
	private OrderInfoRepository	orderInfoRepository;

	@Scheduled(cron = "*/20 * * * * ?")
	public void getPayResult() {
		List<BigInteger> orderIds = orderInfoRepository.getAllIdByNoPayCallBack();
		if (CollectionUtils.isEmpty(orderIds)) {
			return;
		}
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" + "\"out_trade_no\":\"20150320010101001\","
				+ "\"trade_no\":\"2014112611001004680 073956707\"," + "\"org_pid\":\"2088101117952222\"" + "  }");
		AlipayTradeQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}
	}
}
