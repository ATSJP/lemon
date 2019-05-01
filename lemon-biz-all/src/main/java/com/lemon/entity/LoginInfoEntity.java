package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author sjp
 * @date 2019/5/1
 **/
@Entity
@Table(name = "login_info", schema = "lemon", catalog = "")
public class LoginInfoEntity {
    private long loginId;
    private String loginName;
    private String loginPwd;
    private short loginNameUpdateTimes;
    private String email;
    private Short emailStatus;
    private String mobile;
    private Short mobileStatus;
    private short loginStatus;
    private Timestamp registerTime;
    private String inviteCode;
    private Short auditStatus;
    private String auditRemark;
    private long createId;
    private Timestamp createTime;
    private long updateId;
    private Timestamp updateTime;
    private Timestamp addTime;
    private Long adderNo;
    private Long updaterNo;

    @Id
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
    @Column(name = "login_pwd")
    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    @Basic
    @Column(name = "login_name_update_times")
    public short getLoginNameUpdateTimes() {
        return loginNameUpdateTimes;
    }

    public void setLoginNameUpdateTimes(short loginNameUpdateTimes) {
        this.loginNameUpdateTimes = loginNameUpdateTimes;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "email_status")
    public Short getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Short emailStatus) {
        this.emailStatus = emailStatus;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "mobile_status")
    public Short getMobileStatus() {
        return mobileStatus;
    }

    public void setMobileStatus(Short mobileStatus) {
        this.mobileStatus = mobileStatus;
    }

    @Basic
    @Column(name = "login_status")
    public short getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(short loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Basic
    @Column(name = "register_time")
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name = "invite_code")
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
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
    @Column(name = "audit_remark")
    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
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

    @Basic
    @Column(name = "add_time")
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Basic
    @Column(name = "adder_no")
    public Long getAdderNo() {
        return adderNo;
    }

    public void setAdderNo(Long adderNo) {
        this.adderNo = adderNo;
    }

    @Basic
    @Column(name = "updater_no")
    public Long getUpdaterNo() {
        return updaterNo;
    }

    public void setUpdaterNo(Long updaterNo) {
        this.updaterNo = updaterNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginInfoEntity that = (LoginInfoEntity) o;
        return loginId == that.loginId &&
            loginNameUpdateTimes == that.loginNameUpdateTimes &&
            loginStatus == that.loginStatus &&
            createId == that.createId &&
            updateId == that.updateId &&
            Objects.equals(loginName, that.loginName) &&
            Objects.equals(loginPwd, that.loginPwd) &&
            Objects.equals(email, that.email) &&
            Objects.equals(emailStatus, that.emailStatus) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(mobileStatus, that.mobileStatus) &&
            Objects.equals(registerTime, that.registerTime) &&
            Objects.equals(inviteCode, that.inviteCode) &&
            Objects.equals(auditStatus, that.auditStatus) &&
            Objects.equals(auditRemark, that.auditRemark) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateTime, that.updateTime) &&
            Objects.equals(addTime, that.addTime) &&
            Objects.equals(adderNo, that.adderNo) &&
            Objects.equals(updaterNo, that.updaterNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, loginName, loginPwd, loginNameUpdateTimes, email, emailStatus, mobile, mobileStatus, loginStatus, registerTime, inviteCode, auditStatus, auditRemark, createId, createTime, updateId, updateTime, addTime, adderNo, updaterNo);
    }
}
