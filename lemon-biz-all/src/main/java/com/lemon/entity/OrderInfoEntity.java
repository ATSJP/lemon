package com.lemon.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * OrderInfoEntity
 *
 * @author sjp
 * @date 2019/5/23
 */
@Entity
@Table(name = "order_info", schema = "lemon", catalog = "")
public class OrderInfoEntity {
	private long		orderId;
	private String		prodName;
	private BigDecimal	payAmt;
	private BigDecimal	realAmt;
	private BigDecimal	discount;
	private short		orderStatus;
	private Long		createId;
	private Timestamp	createTime;
	private Long		updateId;
	private Timestamp	updateTime;

	@Id
	@Column(name = "order_id")
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Basic
	@Column(name = "prod_name")
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Basic
	@Column(name = "pay_amt")
	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	@Basic
	@Column(name = "real_amt")
	public BigDecimal getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(BigDecimal realAmt) {
		this.realAmt = realAmt;
	}

	@Basic
	@Column(name = "discount")
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Basic
	@Column(name = "order_status")
	public short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(short orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Basic
	@Column(name = "create_id")
	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	@Basic
	@Column(name = "create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Basic
	@Column(name = "update_id")
	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	@Basic
	@Column(name = "update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderInfoEntity that = (OrderInfoEntity) o;
		return orderId == that.orderId && payAmt == that.payAmt && realAmt == that.realAmt && discount == that.discount
				&& orderStatus == that.orderStatus && Objects.equals(prodName, that.prodName)
				&& Objects.equals(createId, that.createId) && Objects.equals(createTime, that.createTime)
				&& Objects.equals(updateId, that.updateId) && Objects.equals(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, prodName, payAmt, realAmt, discount, orderStatus, createId, createTime, updateId,
				updateTime);
	}
}
