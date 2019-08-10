package com.lemon.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lemon.config.ConfigProperties;
import com.lemon.entity.OrderInfoEntity;
import com.lemon.entity.UserInfoEntity;
import com.lemon.repository.OrderInfoRepository;
import com.lemon.repository.UserInfoRepository;
import com.lemon.utils.EncryUtils;
import com.lemon.web.constant.ConstantUser;
import com.lemon.web.constant.base.ConstantEncryKey;
import com.lemon.web.request.PayCallbackRequest;
import com.lemon.web.request.PayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

/**
 * @author sjp 2019/1/10
 */
@Controller
public class PayController {

	private Logger              logger	= LoggerFactory.getLogger(this.getClass());
	@Resource
	private ConfigProperties    resource;
	@Resource
	private AlipayClient        alipayClient;
	@Resource
	private OrderInfoRepository orderInfoRepository;
	@Resource
	private UserInfoRepository  userInfoRepository;

	@GetMapping("/pay")
	public void toPay(HttpServletResponse httpResponse, @Valid PayRequest request) throws IOException {
		String msg = "";
		String orderIdEn = request.getOrderIdEn();
		String orderIdStr = EncryUtils.decode3Des(ConstantEncryKey.ORDER_ENCRY_KEY, orderIdEn);
		if (StringUtils.isEmpty(orderIdStr)) {
			msg = "error";
			// 直接将完整的表单html输出到页面
			httpResponse.getWriter().write(msg);
			httpResponse.getWriter().flush();
			httpResponse.getWriter().close();
			return;
		}
		long orderId = Long.parseLong(orderIdStr);
		Optional<OrderInfoEntity> orderInfoEntityOptional = orderInfoRepository.findById(orderId);
		if (!orderInfoEntityOptional.isPresent()) {
			msg = "error";
			// 直接将完整的表单html输出到页面
			httpResponse.getWriter().write(msg);
			httpResponse.getWriter().flush();
			httpResponse.getWriter().close();
			return;
		}
		OrderInfoEntity orderInfoEntity = orderInfoEntityOptional.get();
		// 创建API对应的request
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(resource.getReturnUrl());
		// 在公共参数中设置回跳和通知地址
		alipayRequest.setNotifyUrl(resource.getNotifyUrl());
		// 填充业务参数
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderId + "\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\","
				+ "\"total_amount\":" + orderInfoEntity.getRealAmt() + ",\"subject\":\"" + orderInfoEntity.getProdName()
				+ "\",\"body\":\"充值会员享受会员服务\"}");
		try {
			// 调用SDK生成表单
			msg = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			logger.error("pay error->e:{}", e);
		}
		httpResponse.setContentType("text/html;charset=" + resource.getAliPayCharset());
		// 直接将完整的表单html输出到页面
		httpResponse.getWriter().write(msg);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	@GetMapping("/pay/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("异步回调结果:{}", request.getQueryString());
		// 调用SDK验证签名
		boolean signVerified = false;
		try {
			/// signVerified = AlipaySignature.rsaCheckV1(Map2ObjectUtils.objectToMap(request),
			// resource.getAliPayPublicKey(), resource.getAliPayCharset());
		} catch (Exception e) {
			logger.error("valid pay sign error->e:{}", e);
		}
		if (signVerified) {

		}
	}

	@GetMapping("/pay/toSuccess")
	public void toSuccess(PayCallbackRequest request, HttpServletResponse httpResponse) throws IOException {
		logger.info("同步回调结果:{}", request.toString());
		String msg = "";
		// 调用SDK验证签名
		boolean signVerified = true;
		try {
			/// signVerified = AlipaySignature.rsaCheckV1((Map<String, String>) Map2ObjectUtils.objectToMap(request),
			// resource.getAliPayPublicKey(), request.getCharset());
		} catch (Exception e) {
			logger.error("valid pay sign error->e:{}", e);
			msg = "error";
		}
		if (signVerified) {
			// 充值成功
			msg = "<script>window.location.replace(\"http://lemon.shijianpeng.top/userInfo.html?state=paysuccess\")</script> ";
			Long orderId = Long.parseLong(request.getOut_trade_no());
			Optional<OrderInfoEntity> orderInfoEntityOptional = orderInfoRepository.findById(orderId);
			if (orderInfoEntityOptional.isPresent()) {
				long loginId = orderInfoEntityOptional.get().getLoginId();
				Optional<UserInfoEntity> userInfoEntityOptional = userInfoRepository.findById(loginId);
				if (userInfoEntityOptional.isPresent()) {
					UserInfoEntity userInfoEntity = userInfoEntityOptional.get();
					userInfoEntity.setLoginId(loginId);
					userInfoEntity.setUserType(ConstantUser.USER_TYPE.VIP.code);
					userInfoRepository.save(userInfoEntity);
				}
			}
		}
		// 直接将完整的表单html输出到页面
		httpResponse.getWriter().write(msg);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

}
