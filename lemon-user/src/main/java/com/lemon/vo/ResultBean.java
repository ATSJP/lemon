package com.lemon.vo;

import java.io.Serializable;

/**
 * result数据封装工具类
 * 
 * @author sjp
 */
public class ResultBean implements Serializable {

	/**
	 * 0 成功 1 失败
	 */
	private String	code	= "";
	/**
	 * 提示信息
	 */
	private String	msg		= "";
	/**
	 * 数据
	 */
	private Object	data	= null;

	public ResultBean() {
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return "ResultBean{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}

}
