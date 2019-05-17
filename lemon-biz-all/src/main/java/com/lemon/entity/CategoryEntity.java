package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * CategoryEntity
 *
 * @author sjp
 * @date 2019/2/11
 **/
@Entity
@Table(name = "category", schema = "lemon", catalog = "")
public class CategoryEntity {
    private long categoryId;
    private String categoryName;
    private long parentId;
    private short display;
    private long createId;
    private Timestamp createTime;
    private long updateId;
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "parent_id")
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "display")
    public short getDisplay() {
        return display;
    }

    public void setDisplay(short display) {
        this.display = display;
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
        CategoryEntity that = (CategoryEntity) o;
        return categoryId == that.categoryId &&
            parentId == that.parentId &&
            display == that.display &&
            createId == that.createId &&
            updateId == that.updateId &&
            Objects.equals(categoryName, that.categoryName) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, parentId, display, createId, createTime, updateId, updateTime);
    }
}
