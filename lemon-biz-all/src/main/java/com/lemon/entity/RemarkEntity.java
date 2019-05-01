package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Entity
@Table(name = "remark", schema = "lemon", catalog = "")
public class RemarkEntity {
    private long remarkId;
    private String remarkContext;
    private Long repeatId;
    private String repeatName;
    private long loginId;
    private String loginName;
    private long videoId;
    private Long parentId;
    private Short delFlag;
    private long createId;
    private Timestamp createTime;
    private long updateId;
    private Timestamp updateTime;

    @Id
    @Column(name = "remark_id")
    public long getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(long remarkId) {
        this.remarkId = remarkId;
    }

    @Basic
    @Column(name = "remark_context")
    public String getRemarkContext() {
        return remarkContext;
    }

    public void setRemarkContext(String remarkContext) {
        this.remarkContext = remarkContext;
    }

    @Basic
    @Column(name = "repeat_id")
    public Long getRepeatId() {
        return repeatId;
    }

    public void setRepeatId(Long repeatId) {
        this.repeatId = repeatId;
    }

    @Basic
    @Column(name = "repeat_name")
    public String getRepeatName() {
        return repeatName;
    }

    public void setRepeatName(String repeatName) {
        this.repeatName = repeatName;
    }

    @Basic
    @Column(name = "login_id")
    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "del_flag")
    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
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
        RemarkEntity that = (RemarkEntity) o;
        return remarkId == that.remarkId &&
            loginId == that.loginId &&
            videoId == that.videoId &&
            createId == that.createId &&
            updateId == that.updateId &&
            Objects.equals(remarkContext, that.remarkContext) &&
            Objects.equals(repeatId, that.repeatId) &&
            Objects.equals(repeatName, that.repeatName) &&
            Objects.equals(loginName, that.loginName) &&
            Objects.equals(parentId, that.parentId) &&
            Objects.equals(delFlag, that.delFlag) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remarkId, remarkContext, repeatId, repeatName, loginId, loginName, videoId, parentId, delFlag, createId, createTime, updateId, updateTime);
    }
}
