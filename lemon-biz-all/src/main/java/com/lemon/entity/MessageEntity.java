package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author sjp
 * @date 2019/2/11
 **/
@Entity
@Table(name = "message", schema = "lemon", catalog = "")
public class MessageEntity {
    private long messageId;
    private String messageContext;
    private long videoId;
    private Long repeatId;
    private long loginId;
    private Long parentId;
    private String delFlag;
    private long createId;
    private Timestamp createTime;
    private long updateId;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "message_context")
    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
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
    @Column(name = "repeat_id")
    public Long getRepeatId() {
        return repeatId;
    }

    public void setRepeatId(Long repeatId) {
        this.repeatId = repeatId;
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
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "del_flag")
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
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
        MessageEntity that = (MessageEntity) o;
        return messageId == that.messageId &&
            videoId == that.videoId &&
            loginId == that.loginId &&
            createId == that.createId &&
            updateId == that.updateId &&
            Objects.equals(messageContext, that.messageContext) &&
            Objects.equals(repeatId, that.repeatId) &&
            Objects.equals(parentId, that.parentId) &&
            Objects.equals(delFlag, that.delFlag) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, messageContext, videoId, repeatId, loginId, parentId, delFlag, createId, createTime, updateId, updateTime);
    }
}
