package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * CollectionDetailEntity
 *
 * @author sjp
 * @date 2019/5/6
 */
@Entity
@Table(name = "collection_detail", schema = "lemon", catalog = "")
public class CollectionDetailEntity {
	private long		collectionId;
	private long		videoId;
	private Short		isDel;
	private long		createId;
	private Timestamp	createTime;
	private Long		updateId;
	private Timestamp	updateTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collection_id")
	public long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(long collectionId) {
		this.collectionId = collectionId;
	}

	@Basic
	@Column(name = "video_id")
	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
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
	public long getCreateId() {
		return createId;
	}

	public void setCreateId(long createId) {
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
		CollectionDetailEntity entity = (CollectionDetailEntity) o;
		return collectionId == entity.collectionId && videoId == entity.videoId && createId == entity.createId
				&& Objects.equals(isDel, entity.isDel) && Objects.equals(createTime, entity.createTime)
				&& Objects.equals(updateId, entity.updateId) && Objects.equals(updateTime, entity.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(collectionId, videoId, isDel, createId, createTime, updateId, updateTime);
	}
}
