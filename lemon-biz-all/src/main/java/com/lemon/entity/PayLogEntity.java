package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * PayLogEntity
 *
 * @author sjp
 * @date 2019/5/17
 */
@Entity
@Table(name = "pay_log", schema = "lemon", catalog = "")
public class PayLogEntity {
	private long		logId;
	private short		payTypeKey;
	private Timestamp	payTime;
	private Short		isDel;
	private Long		createId;
	private Timestamp	createTime;
	private Long		updateId;
	private Timestamp	updateTime;

	@Id
	@Column(name = "log_id")
	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	@Basic
	@Column(name = "pay_type_key")
	public short getPayTypeKey() {
		return payTypeKey;
	}

	public void setPayTypeKey(short payTypeKey) {
		this.payTypeKey = payTypeKey;
	}

	@Basic
	@Column(name = "pay_time")
	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	@Basic
	@Column(name = "is_del")
	public Short getIsDel() {
		return isDel;
	}

	public void setIsDel(Short isDel) {
		this.isDel = isDel;
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
		PayLogEntity that = (PayLogEntity) o;
		return logId == that.logId && payTypeKey == that.payTypeKey && Objects.equals(payTime, that.payTime)
				&& Objects.equals(isDel, that.isDel) && Objects.equals(createId, that.createId)
				&& Objects.equals(createTime, that.createTime) && Objects.equals(updateId, that.updateId)
				&& Objects.equals(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(logId, payTypeKey, payTime, isDel, createId, createTime, updateId, updateTime);
	}
}
