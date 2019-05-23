package com.lemon.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lemon.entity.OrderInfoEntity;
import com.lemon.properties.LemonResource;
import com.lemon.repository.OrderInfoRepository;
import com.lemon.utils.EncryUtils;
import com.lemon.web.constant.base.ConstantEncryKey;
import com.lemon.web.request.PayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

/**
 * @author sjp 2019/1/10
 */
@Controller
public class PayController {

	private Logger				logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private LemonResource		resource;
	@Resource
	private AlipayClient		alipayClient;
	@Resource
	private OrderInfoRepository	orderInfoRepository;

	@GetMapping("/pay")
	public void toPay(HttpServletResponse httpResponse, @Valid PayRequest request) throws IOException {
		String orderIdEn = request.getOrderIdEn();
		String orderIdStr = EncryUtils.decode3Des(ConstantEncryKey.ORDER_ENCRY_KEY, orderIdEn);
		if (StringUtils.isEmpty(orderIdStr)) {
			return;
		}
		long orderId = Long.parseLong(orderIdStr);
		Optional<OrderInfoEntity> orderInfoEntityOptional = orderInfoRepository.findById(orderId);
		if (!orderInfoEntityOptional.isPresent()) {
			return;
		}
		// 创建API对应的request
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(resource.getReturnUrl());
		// 在公共参数中设置回跳和通知地址
		alipayRequest.setNotifyUrl(resource.getNotifyUrl());
		// 填充业务参数
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderId + "\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\","
				+ "\"total_amount\":12.00,\"subject\":\"柠檬会员\",\"body\":\"充值会员享受会员服务\"}");
		String form = "";
		try {
			// 调用SDK生成表单
			form = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			logger.error("pay error->e:{}", e);
		}
		httpResponse.setContentType("text/html;charset=" + resource.getAliPayCharset());
		// 直接将完整的表单html输出到页面
		httpResponse.getWriter().write(form);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	@ResponseBody
	@GetMapping("/callback")
	public String callback() {
		return "success";
	}

	@ResponseBody
	@GetMapping("/toSuccess")
	public String toSuccess() {
		return "success";
	}

}
