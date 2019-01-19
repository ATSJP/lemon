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
@Table(name = "user_info", schema = "lemon", catalog = "")
public class UserInfoEntity {
	private long		loginId;
	private String		userName;
	private String		engName;
	private boolean		gender;
	private Timestamp	birthday;
	private Integer		grade;
	private String		idCard;
	private String		userType;
	private String		qqAccount;
	private String		weChatAccount;
	private long		adderNo;
	private Timestamp	addTime;
	private long		updaterNo;
	private Timestamp	updateTime;
	private String		armCard;
	private String		drivingLicense;
	private String		passPort;
	private String		simplifiedChinese;

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
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@Basic
	@Column(name = "birthday")
	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
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
	@Column(name = "id_card")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Basic
	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	@Basic
	@Column(name = "arm_card")
	public String getArmCard() {
		return armCard;
	}

	public void setArmCard(String armCard) {
		this.armCard = armCard;
	}

	@Basic
	@Column(name = "driving_license")
	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	@Basic
	@Column(name = "pass_port")
	public String getPassPort() {
		return passPort;
	}

	public void setPassPort(String passPort) {
		this.passPort = passPort;
	}

	@Basic
	@Column(name = "simplified_chinese")
	public String getSimplifiedChinese() {
		return simplifiedChinese;
	}

	public void setSimplifiedChinese(String simplifiedChinese) {
		this.simplifiedChinese = simplifiedChinese;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserInfoEntity that = (UserInfoEntity) o;
		return loginId == that.loginId && gender == that.gender && adderNo == that.adderNo
				&& updaterNo == that.updaterNo && Objects.equals(userName, that.userName)
				&& Objects.equals(engName, that.engName) && Objects.equals(birthday, that.birthday)
				&& Objects.equals(grade, that.grade) && Objects.equals(idCard, that.idCard)
				&& Objects.equals(userType, that.userType) && Objects.equals(qqAccount, that.qqAccount)
				&& Objects.equals(weChatAccount, that.weChatAccount) && Objects.equals(addTime, that.addTime)
				&& Objects.equals(updateTime, that.updateTime) && Objects.equals(armCard, that.armCard)
				&& Objects.equals(drivingLicense, that.drivingLicense) && Objects.equals(passPort, that.passPort)
				&& Objects.equals(simplifiedChinese, that.simplifiedChinese);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginId, userName, engName, gender, birthday, grade, idCard, userType, qqAccount,
				weChatAccount, adderNo, addTime, updaterNo, updateTime, armCard, drivingLicense, passPort,
				simplifiedChinese);
	}
}
