package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * PlayDetailEntity
 *
 * @author sjp
 * @date 2019/5/3
 */
@Entity
@Table(name = "play_detail", schema = "lemon", catalog = "")
public class PlayDetailEntity {
    private long detailId;
    private long videoId;
    private String ip;
    private String sid;
    private Long createId;
    private Timestamp createTime;

    @Id
    @Column(name = "detail_id")
    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
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
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayDetailEntity that = (PlayDetailEntity) o;
        return detailId == that.detailId &&
            videoId == that.videoId &&
            Objects.equals(ip, that.ip) &&
            Objects.equals(sid, that.sid) &&
            Objects.equals(createId, that.createId) &&
            Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, videoId, ip, sid, createId, createTime);
    }
}
