package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author sjp
 * @date 2019/2/11
 **/
@Entity
@Table(name = "video", schema = "lemon", catalog = "")
public class VideoEntity {
    private long videoId;
    private String videoName;
    private String videoContext;
    private long categoryId;
    private Long userId;
    private Short auditStatus;
    private long createId;
    private Timestamp createTime;
    private long updateId;
    private Timestamp updateTime;

    @Id
    @Column(name = "video_id")
    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "video_name")
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "video_context")
    public String getVideoContext() {
        return videoContext;
    }

    public void setVideoContext(String videoContext) {
        this.videoContext = videoContext;
    }

    @Basic
    @Column(name = "category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "audit_status")
    public Short getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Short auditStatus) {
        this.auditStatus = auditStatus;
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
    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoEntity that = (VideoEntity) o;
        return videoId == that.videoId &&
            categoryId == that.categoryId &&
            createId == that.createId &&
            updateId == that.updateId &&
            Objects.equals(videoName, that.videoName) &&
            Objects.equals(videoContext, that.videoContext) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(auditStatus, that.auditStatus) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId, videoName, videoContext, categoryId, userId, auditStatus, createId, createTime, updateId, updateTime);
    }
}
