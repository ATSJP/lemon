package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Entity
@Table(name = "biz_file", schema = "lemon", catalog = "")
public class BizFileEntity {
    private long fileId;
    private long linkId;
    private short linkType;
    private String fileName;
    private String fileSuffix;
    private Short isDel;
    private Long createId;
    private Timestamp createTime;
    private Long updateId;
    private Timestamp updateTime;

    @Id
    @Column(name = "file_id")
    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "link_id")
    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    @Basic
    @Column(name = "link_type")
    public short getLinkType() {
        return linkType;
    }

    public void setLinkType(short linkType) {
        this.linkType = linkType;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_suffix")
    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizFileEntity that = (BizFileEntity) o;
        return fileId == that.fileId &&
            linkId == that.linkId &&
            linkType == that.linkType &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(fileSuffix, that.fileSuffix) &&
            Objects.equals(isDel, that.isDel) &&
            Objects.equals(createId, that.createId) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateId, that.updateId) &&
            Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, linkId, linkType, fileName, fileSuffix, isDel, createId, createTime, updateId, updateTime);
    }
}
