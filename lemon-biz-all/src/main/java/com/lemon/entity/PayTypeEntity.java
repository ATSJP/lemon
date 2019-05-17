package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * PayTypeEntity
 *
 * @author sjp
 * @date 2019/5/17
 */
@Entity
@Table(name = "pay_type", schema = "lemon", catalog = "")
public class PayTypeEntity {
	private long		id;
	private short		payTypeKey;
	private String		payTypeName;
	private short		inUse;
	private Long		createId;
	private Timestamp	createTime;
	private Long		updateId;
	private Timestamp	updateTime;

	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	@Column(name = "pay_type_name")
	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	@Basic
	@Column(name = "in_use")
	public short getInUse() {
		return inUse;
	}

	public void setInUse(short inUse) {
		this.inUse = inUse;
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
		PayTypeEntity that = (PayTypeEntity) o;
		return id == that.id && payTypeKey == that.payTypeKey && inUse == that.inUse
				&& Objects.equals(payTypeName, that.payTypeName) && Objects.equals(createId, that.createId)
				&& Objects.equals(createTime, that.createTime) && Objects.equals(updateId, that.updateId)
				&& Objects.equals(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, payTypeKey, payTypeName, inUse, createId, createTime, updateId, updateTime);
	}
}
