package com.wc.orange.admin.model;

public class SysSmallapp extends BaseModel {
  private Long id;
  private String appId;
  private String projectName;
  private String appName;
  private String appDomain;
  private String appIp;
  private String appPort;
  private String deptId;
  // 非数据库字段
  private String deptName;

  public Long getId(){
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }
  public String getAppId(){
    return appId;
  }
  public void setAppId(String appId) {
    this.appId = appId;
  }
  public String getProjectName() {
    return projectName;
  }
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }
  public String getAppName() {
    return appName;
  }
  public void setAppName(String appName) {
    this.appName = appName;
  }
  public String getAppDomain() {
    return appDomain;
  }
  public void setAppDomain(String appDomain) {
    this.appDomain = appDomain;
  }
  public String getAppIp() {
    return appIp;
  }
  public void setAppIp(String appIp) {
    this.appIp = appIp;
  }
  public String getAppPort() {
    return appPort;
  }
  public void setAppPort(String appPort) {
    this.appPort = appPort;
  }
  public String getDeptId() {
    return deptId;
  }
  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }
  public String getDeptName() {
    return deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
}
