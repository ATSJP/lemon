package com.lemon.web.constant;

import java.math.BigDecimal;

/**
 * ConstantOrder
 *
 * @author sjp
 * @date 2019/5/23
 */
public interface ConstantOrder {

	enum PROD_INFO {
		/**
		 * 会员产品：1 年充 2 季充 3 月充
		 */
		YEAY((short) 1, new BigDecimal(124), "年充"),
		QUARTER((short) 2, new BigDecimal(28.8), "季充"),
		MONTH((short) 3, new BigDecimal(11), "月充");

		public Short		code;
		public BigDecimal	amt;
		public String		desc;

		PROD_INFO(Short code, BigDecimal amt, String desc) {
			this.code = code;
			this.amt = amt;
			this.desc = desc;
		}

		public static boolean isCorrectProdId(Short prodId) {
			if (YEAY.code.equals(prodId)) {
				return true;
			}
			if (QUARTER.code.equals(prodId)) {
				return true;
			}
			if (MONTH.code.equals(prodId)) {
				return true;
			}
			return false;
		}

		public static PROD_INFO getProdInfo(Short prodId) {
			for (PROD_INFO prodInfo : PROD_INFO.values()) {
				if (prodInfo.code.equals(prodId)) {
					return prodInfo;
				}
			}
			return null;
		}
	}

}
