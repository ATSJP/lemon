package com.lemon.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * UserInfoEntity
 *
 * @author sjp
 * @date 2019/5/25
 */
@Entity
@Table(name = "user_info", schema = "lemon", catalog = "")
public class UserInfoEntity {
	private long		loginId;
	private String		userName;
	private String		engName;
	private Short		gender;
	private Date		birthday;
	private Integer		grade;
	private Short		userType;
	private String		idCard;
	private String		qqAccount;
	private String		weChatAccount;
	private Long		createId;
	private Timestamp	createTime;
	private Long		updateId;
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
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Basic
	@Column(name = "eng_name")
	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	@Basic
	@Column(name = "gender")
	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	@Basic
	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Basic
	@Column(name = "grade")
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Basic
	@Column(name = "user_type")
	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	@Basic
	@Column(name = "id_card")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Basic
	@Column(name = "qq_account")
	public String getQqAccount() {
		return qqAccount;
	}

	public void setQqAccount(String qqAccount) {
		this.qqAccount = qqAccount;
	}

	@Basic
	@Column(name = "we_chat_account")
	public String getWeChatAccount() {
		return weChatAccount;
	}

	public void setWeChatAccount(String weChatAccount) {
		this.weChatAccount = weChatAccount;
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
		UserInfoEntity that = (UserInfoEntity) o;
		return loginId == that.loginId && Objects.equals(userName, that.userName)
				&& Objects.equals(engName, that.engName) && Objects.equals(gender, that.gender)
				&& Objects.equals(birthday, that.birthday) && Objects.equals(grade, that.grade)
				&& Objects.equals(userType, that.userType) && Objects.equals(idCard, that.idCard)
				&& Objects.equals(qqAccount, that.qqAccount) && Objects.equals(weChatAccount, that.weChatAccount)
				&& Objects.equals(createId, that.createId) && Objects.equals(createTime, that.createTime)
				&& Objects.equals(updateId, that.updateId) && Objects.equals(updateTime, that.updateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginId, userName, engName, gender, birthday, grade, userType, idCard, qqAccount,
				weChatAccount, createId, createTime, updateId, updateTime);
	}
}
