package com.scd.wechat.entity;

import java.io.Serializable;

/*员工信息实体类对应mysql数据库的employee表*/
public class Employee implements Serializable {
    private Integer employeeId;//主键自增

    private String employeeCode;//员工代号包括起头如S4M10880

    private String employeeName;//员工姓名

    private String employeeTitle;

    private Integer deptId;//所属部门ID

    private String deptLine;

    private Integer adminId;//主管ID

    private String employeeIndate;//到职时间

    private String employeeDeptDate;

    private String employeeOutdate;//离职时间

    private String employeeScode;//员工数字代号如s4m10880->10880

    private String employeeBirth;//出生日期

    private String employeeSex;//性别

    private String employeeEducation;//教育程度

    private String employeeStudy;//科系

    private String employeeProvince;//省份
    
    private String employeeWechatid;//员工微信号openID

    private String employeeVolk;

    private String employeeAddr;

    private String employeeBid;

    private String employeeJobDesc;

    private String employeeAcct;//登入账号

    private String employeePassword;

    private String empEduGp1;

    private String empEduGp2;

    private String empEduGp3;

    private String empEduGp4;

    private String empEduGp5;

    private String empEduGp6;

    private String empEduGp7;

    private String empEduGp8;

    private String empEduGp9;

    private String empEduGp10;

    private String levelGmo;

    private String levelPrd;

    private String levelEng;

    private String levelQc;

    private String levelPc;

    private String levelMc;

    private String levelHr;

    private String levelPur;

    private String levelSales;

    private String levelRd;

    private String levelRma;

    private String levelFna;

    private String levelCost;

    private String levelOther;

    private String levelMis;

    private String employeePhoto;

    private String creationDate;

    private String createdBy;

    private String lastUpdateDate;

    private String lastUpdatedBy;

    private String employeeIp;

    private String employeeOnjobDate;

    private String employeePhone;

    private String employeeExperience;

    private static final long serialVersionUID = 1L;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode == null ? null : employeeCode.trim();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle == null ? null : employeeTitle.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptLine() {
        return deptLine;
    }

    public void setDeptLine(String deptLine) {
        this.deptLine = deptLine == null ? null : deptLine.trim();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getEmployeeIndate() {
        return employeeIndate;
    }

    public void setEmployeeIndate(String employeeIndate) {
        this.employeeIndate = employeeIndate;
    }

    public String getEmployeeDeptDate() {
        return employeeDeptDate;
    }

    public void setEmployeeDeptDate(String employeeDeptDate) {
        this.employeeDeptDate = employeeDeptDate;
    }

    public String getEmployeeOutdate() {
        return employeeOutdate;
    }

    public void setEmployeeOutdate(String employeeOutdate) {
        this.employeeOutdate = employeeOutdate;
    }

    public String getEmployeeScode() {
        return employeeScode;
    }

    public void setEmployeeScode(String employeeScode) {
        this.employeeScode = employeeScode == null ? null : employeeScode.trim();
    }

    public String getEmployeeBirth() {
        return employeeBirth;
    }

    public void setEmployeeBirth(String employeeBirth) {
        this.employeeBirth = employeeBirth;
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex == null ? null : employeeSex.trim();
    }

    public String getEmployeeEducation() {
        return employeeEducation;
    }

    public void setEmployeeEducation(String employeeEducation) {
        this.employeeEducation = employeeEducation == null ? null : employeeEducation.trim();
    }

    public String getEmployeeStudy() {
        return employeeStudy;
    }

    public void setEmployeeStudy(String employeeStudy) {
        this.employeeStudy = employeeStudy == null ? null : employeeStudy.trim();
    }

    public String getEmployeeProvince() {
        return employeeProvince;
    }

    public void setEmployeeProvince(String employeeProvince) {
        this.employeeProvince = employeeProvince == null ? null : employeeProvince.trim();
    }

    public String getEmployeeVolk() {
        return employeeVolk;
    }

    public void setEmployeeVolk(String employeeVolk) {
        this.employeeVolk = employeeVolk == null ? null : employeeVolk.trim();
    }

    public String getEmployeeAddr() {
        return employeeAddr;
    }

    public void setEmployeeAddr(String employeeAddr) {
        this.employeeAddr = employeeAddr == null ? null : employeeAddr.trim();
    }

    public String getEmployeeBid() {
        return employeeBid;
    }

    public void setEmployeeBid(String employeeBid) {
        this.employeeBid = employeeBid == null ? null : employeeBid.trim();
    }

    public String getEmployeeJobDesc() {
        return employeeJobDesc;
    }

    public void setEmployeeJobDesc(String employeeJobDesc) {
        this.employeeJobDesc = employeeJobDesc == null ? null : employeeJobDesc.trim();
    }

    public String getEmployeeAcct() {
        return employeeAcct;
    }

    public void setEmployeeAcct(String employeeAcct) {
        this.employeeAcct = employeeAcct == null ? null : employeeAcct.trim();
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword == null ? null : employeePassword.trim();
    }

    public String getEmpEduGp1() {
        return empEduGp1;
    }

    public void setEmpEduGp1(String empEduGp1) {
        this.empEduGp1 = empEduGp1 == null ? null : empEduGp1.trim();
    }

    public String getEmpEduGp2() {
        return empEduGp2;
    }

    public void setEmpEduGp2(String empEduGp2) {
        this.empEduGp2 = empEduGp2 == null ? null : empEduGp2.trim();
    }

    public String getEmpEduGp3() {
        return empEduGp3;
    }

    public void setEmpEduGp3(String empEduGp3) {
        this.empEduGp3 = empEduGp3 == null ? null : empEduGp3.trim();
    }

    public String getEmpEduGp4() {
        return empEduGp4;
    }

    public void setEmpEduGp4(String empEduGp4) {
        this.empEduGp4 = empEduGp4 == null ? null : empEduGp4.trim();
    }

    public String getEmpEduGp5() {
        return empEduGp5;
    }

    public void setEmpEduGp5(String empEduGp5) {
        this.empEduGp5 = empEduGp5 == null ? null : empEduGp5.trim();
    }

    public String getEmpEduGp6() {
        return empEduGp6;
    }

    public void setEmpEduGp6(String empEduGp6) {
        this.empEduGp6 = empEduGp6 == null ? null : empEduGp6.trim();
    }

    public String getEmpEduGp7() {
        return empEduGp7;
    }

    public void setEmpEduGp7(String empEduGp7) {
        this.empEduGp7 = empEduGp7 == null ? null : empEduGp7.trim();
    }

    public String getEmpEduGp8() {
        return empEduGp8;
    }

    public void setEmpEduGp8(String empEduGp8) {
        this.empEduGp8 = empEduGp8 == null ? null : empEduGp8.trim();
    }

    public String getEmpEduGp9() {
        return empEduGp9;
    }

    public void setEmpEduGp9(String empEduGp9) {
        this.empEduGp9 = empEduGp9 == null ? null : empEduGp9.trim();
    }

    public String getEmpEduGp10() {
        return empEduGp10;
    }

    public void setEmpEduGp10(String empEduGp10) {
        this.empEduGp10 = empEduGp10 == null ? null : empEduGp10.trim();
    }

    public String getLevelGmo() {
        return levelGmo;
    }

    public void setLevelGmo(String levelGmo) {
        this.levelGmo = levelGmo == null ? null : levelGmo.trim();
    }

    public String getLevelPrd() {
        return levelPrd;
    }

    public void setLevelPrd(String levelPrd) {
        this.levelPrd = levelPrd == null ? null : levelPrd.trim();
    }

    public String getLevelEng() {
        return levelEng;
    }

    public void setLevelEng(String levelEng) {
        this.levelEng = levelEng == null ? null : levelEng.trim();
    }

    public String getLevelQc() {
        return levelQc;
    }

    public void setLevelQc(String levelQc) {
        this.levelQc = levelQc == null ? null : levelQc.trim();
    }

    public String getLevelPc() {
        return levelPc;
    }

    public void setLevelPc(String levelPc) {
        this.levelPc = levelPc == null ? null : levelPc.trim();
    }

    public String getLevelMc() {
        return levelMc;
    }

    public void setLevelMc(String levelMc) {
        this.levelMc = levelMc == null ? null : levelMc.trim();
    }

    public String getLevelHr() {
        return levelHr;
    }

    public void setLevelHr(String levelHr) {
        this.levelHr = levelHr == null ? null : levelHr.trim();
    }

    public String getLevelPur() {
        return levelPur;
    }

    public void setLevelPur(String levelPur) {
        this.levelPur = levelPur == null ? null : levelPur.trim();
    }

    public String getLevelSales() {
        return levelSales;
    }

    public void setLevelSales(String levelSales) {
        this.levelSales = levelSales == null ? null : levelSales.trim();
    }

    public String getLevelRd() {
        return levelRd;
    }

    public void setLevelRd(String levelRd) {
        this.levelRd = levelRd == null ? null : levelRd.trim();
    }

    public String getLevelRma() {
        return levelRma;
    }

    public void setLevelRma(String levelRma) {
        this.levelRma = levelRma == null ? null : levelRma.trim();
    }

    public String getLevelFna() {
        return levelFna;
    }

    public void setLevelFna(String levelFna) {
        this.levelFna = levelFna == null ? null : levelFna.trim();
    }

    public String getLevelCost() {
        return levelCost;
    }

    public void setLevelCost(String levelCost) {
        this.levelCost = levelCost == null ? null : levelCost.trim();
    }

    public String getLevelOther() {
        return levelOther;
    }

    public void setLevelOther(String levelOther) {
        this.levelOther = levelOther == null ? null : levelOther.trim();
    }

    public String getLevelMis() {
        return levelMis;
    }

    public void setLevelMis(String levelMis) {
        this.levelMis = levelMis == null ? null : levelMis.trim();
    }

    public String getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(String employeePhoto) {
        this.employeePhoto = employeePhoto == null ? null : employeePhoto.trim();
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }

    public String getEmployeeIp() {
        return employeeIp;
    }

    public void setEmployeeIp(String employeeIp) {
        this.employeeIp = employeeIp == null ? null : employeeIp.trim();
    }

    public String getEmployeeOnjobDate() {
        return employeeOnjobDate;
    }

    public void setEmployeeOnjobDate(String employeeOnjobDate) {
        this.employeeOnjobDate = employeeOnjobDate;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone == null ? null : employeePhone.trim();
    }

    public String getEmployeeExperience() {
        return employeeExperience;
    }

    public void setEmployeeExperience(String employeeExperience) {
        this.employeeExperience = employeeExperience == null ? null : employeeExperience.trim();
    }

	public String getEmployeeWechatid() {
		return employeeWechatid;
	}

	public void setEmployeeWechatid(String employeeWechatid) {
		this.employeeWechatid = employeeWechatid;
	}
}