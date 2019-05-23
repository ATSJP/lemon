package com.lemon.web.order.rest;

import com.lemon.entity.OrderInfoEntity;
import com.lemon.repository.OrderInfoRepository;
import com.lemon.utils.EncryUtils;
import com.lemon.web.constant.ConstantOrder;
import com.lemon.web.constant.base.ConstantEncryKey;
import com.lemon.web.order.request.OrderRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OrderController
 *
 * @author sjp
 * @date 2019/5/23
 */
@Controller
public class OrderController {

	@Resource
	private OrderInfoRepository orderInfoRepository;

	@RequestMapping("/toPay")
	public void toPay(HttpServletResponse httpResponse, OrderRequest request) throws IOException {
		String msg = "error";
		Short prodId = request.getProdId();
		if (ConstantOrder.PROD_INFO.isCorrectProdId(prodId)) {
			long orderId = System.currentTimeMillis();
			ConstantOrder.PROD_INFO prodInfo = ConstantOrder.PROD_INFO.getProdInfo(prodId);
			if (prodInfo != null) {
				OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
				orderInfoEntity.setOrderId(orderId);
				orderInfoEntity.setProdName(prodInfo.desc);
				orderInfoEntity.setPayAmt(prodInfo.amt);
				orderInfoEntity.setRealAmt(prodInfo.amt);
				orderInfoEntity.setDiscount(prodInfo.amt);
				orderInfoRepository.save(orderInfoEntity);
				String orderIdEn = EncryUtils.encode3Des(ConstantEncryKey.ORDER_ENCRY_KEY, orderId + "");
				msg = "<script>window.location.replace(\"http://www.lemon.com/p/pay?orderIdEn=" + orderIdEn
						+ "\")</script> ";
			}
		}
		// 直接将完整的表单html输出到页面
		httpResponse.getWriter().write(msg);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

}
