package com.lemon.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author shijianpeng 2019/1/11
 */
@Entity
@Table(name = "login_info", schema = "lemon", catalog = "")
public class LoginInfoEntity {
	private long		loginId;
	private String		loginName;
	private String		loginPwd;
	private boolean		loginNameUpdateTimes;
	private String		email;
	private Boolean		emailStatus;
	private String		mobile;
	private Boolean		mobileStatus;
	private boolean		loginStatus;
	private Timestamp	registerTime;
	private String		inviteCode;
	private Boolean		auditStatus;
	private String		auditRemark;
	private long		adderNo;
	private Timestamp	addTime;
	private long		updaterNo;
	private Timestamp	updateTime;

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
	public boolean isLoginNameUpdateTimes() {
		return loginNameUpdateTimes;
	}

	public void setLoginNameUpdateTimes(boolean loginNameUpdateTimes) {
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
	public Boolean getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(Boolean emailStatus) {
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
	public Boolean getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(Boolean mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	@Basic
	@Column(name = "login_status")
	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
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
	public Boolean getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Boolean auditStatus) {
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
	@Column(name = "adder_no")
	public long getAdderNo() {
		return adderNo;
	}

	public void setAdderNo(long adderNo) {
		this.adderNo = adderNo;
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
	@Column(name = "updater_no")
	public long getUpdaterNo() {
		return updaterNo;
	}

	public void setUpdaterNo(long updaterNo) {
		this.updaterNo = updaterNo;
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
		LoginInfoEntity that = (LoginInfoEntity) o;
		return loginId == that.loginId && loginNameUpdateTimes == that.loginNameUpdateTimes
				&& loginStatus == that.loginStatus && adderNo == that.adderNo && updaterNo == that.updaterNo
				&& Objects.equals(loginName, that.loginName) && Objects.equals(loginPwd, that.loginPwd)
				&& Objects.equals(email, that.email) && Objects.equals(emailStatus, that.emailStatus)
				&& Objects.equals(mobile, that.mobile) && Objects.equals(mobileStatus, that.mobileStatus)
				&& Objects.equals(registerTime, that.registerTime) && Objects.equals(inviteCode, that.inviteCode)
				&& Objects.equals(auditStatus, that.auditStatus) && Objects.equals(auditRemark, that.auditRemark)
				&& Objects.equals(addTime, that.addTime) && Objects.equals(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginId, loginName, loginPwd, loginNameUpdateTimes, email, emailStatus, mobile,
				mobileStatus, loginStatus, registerTime, inviteCode, auditStatus, auditRemark, adderNo, addTime,
				updaterNo, updateTime);
	}
}
