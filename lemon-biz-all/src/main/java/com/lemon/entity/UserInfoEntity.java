package com.lemon.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author sjp
 * @date 2019/2/11
 **/
@Entity
@Table(name = "user_info", schema = "lemon", catalog = "")
public class UserInfoEntity {
    private long loginId;
    private String userName;
    private String engName;
    private short gender;
    private Timestamp birthday;
    private Integer grade;
    private String userType;
    private String idCard;
    private String qqAccount;
    private String weChatAccount;
    private long createId;
    private Timestamp createTime;
    private long updateId;
    private Timestamp updateTime;
    private Timestamp addTime;
    private Long adderNo;
    private String armCard;
    private String drivingLicense;
    private String passPort;
    private String simplifiedChinese;
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
    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
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
    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
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
        UserInfoEntity that = (UserInfoEntity) o;
        return loginId == that.loginId &&
            gender == that.gender &&
            createId == that.createId &&
            updateId == that.updateId &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(engName, that.engName) &&
            Objects.equals(birthday, that.birthday) &&
            Objects.equals(grade, that.grade) &&
            Objects.equals(userType, that.userType) &&
            Objects.equals(idCard, that.idCard) &&
            Objects.equals(qqAccount, that.qqAccount) &&
            Objects.equals(weChatAccount, that.weChatAccount) &&
            Objects.equals(createTime, that.createTime) &&
            Objects.equals(updateTime, that.updateTime) &&
            Objects.equals(addTime, that.addTime) &&
            Objects.equals(adderNo, that.adderNo) &&
            Objects.equals(armCard, that.armCard) &&
            Objects.equals(drivingLicense, that.drivingLicense) &&
            Objects.equals(passPort, that.passPort) &&
            Objects.equals(simplifiedChinese, that.simplifiedChinese) &&
            Objects.equals(updaterNo, that.updaterNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, userName, engName, gender, birthday, grade, userType, idCard, qqAccount, weChatAccount, createId, createTime, updateId, updateTime, addTime, adderNo, armCard, drivingLicense, passPort, simplifiedChinese, updaterNo);
    }
}
