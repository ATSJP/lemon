package com.lemon.web.order.rest;

import com.lemon.entity.OrderInfoEntity;
import com.lemon.repository.OrderInfoRepository;
import com.lemon.utils.DateUtils;
import com.lemon.utils.EncryptUtils;
import com.lemon.utils.SnowFlake;
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
	private OrderInfoRepository	orderInfoRepository;
	@Resource
	private SnowFlake			snowFlake;

	@RequestMapping("/toPay")
	public void toPay(HttpServletResponse httpResponse, OrderRequest request) throws IOException {
		String msg = "error";
		Short prodId = request.getProdId();
		if (ConstantOrder.PROD_INFO.isCorrectProdId(prodId)) {
			long orderId = snowFlake.nextId();
			ConstantOrder.PROD_INFO prodInfo = ConstantOrder.PROD_INFO.getProdInfo(prodId);
			if (prodInfo != null) {
				OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
				orderInfoEntity.setOrderId(orderId);
				orderInfoEntity.setLoginId(request.getUid());
				orderInfoEntity.setProdName(prodInfo.desc);
				orderInfoEntity.setPayAmt(prodInfo.amt);
				orderInfoEntity.setRealAmt(prodInfo.amt);
				orderInfoEntity.setDiscount(prodInfo.amt);
                orderInfoEntity.setUpdateId(request.getUid());
                orderInfoEntity.setCreateId(request.getUid());
                orderInfoEntity.setCreateTime(DateUtils.getCurrentTime());
                orderInfoEntity.setUpdateTime(DateUtils.getCurrentTime());
                orderInfoRepository.save(orderInfoEntity);
				String orderIdEn = EncryptUtils.encode3Des(ConstantEncryKey.ORDER_ENCRY_KEY, orderId + "");
				msg = "<script>window.location.replace(\"http://lemon.shijianpeng.top/p/pay?orderIdEn=" + orderIdEn
						+ "\")</script> ";
			}
		}
		// 直接将完整的表单html输出到页面
		httpResponse.getWriter().write(msg);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

}
