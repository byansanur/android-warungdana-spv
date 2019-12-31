package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespUsers2 {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<Datus> data = null;

  public Integer getApiStatus() {
    return apiStatus;
  }

  public void setApiStatus(Integer apiStatus) {
    this.apiStatus = apiStatus;
  }

  public String getApiMessage() {
    return apiMessage;
  }

  public void setApiMessage(String apiMessage) {
    this.apiMessage = apiMessage;
  }

  public List<Datus> getData() {
    return data;
  }

  public void setData(List<Datus> data) {
    this.data = data;
  }

  public class Datus {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_mst_outlet")
    @Expose
    private Integer idMstOutlet;
    @SerializedName("outlet_name")
    @Expose
    private String outletName;
    @SerializedName("id_mst_branch")
    @Expose
    private Integer idMstBranch;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("id_cms_privileges")
    @Expose
    private Integer idCmsPrivileges;
    @SerializedName("privileges_name")
    @Expose
    private String privilegesName;
    @SerializedName("id_cms_users_oh")
    @Expose
    private Integer idCmsUsersOh;
    @SerializedName("id_cms_users_spv")
    @Expose
    private Integer idCmsUsersSpv;
    @SerializedName("id_cms_users_sub_dept")
    @Expose
    private Integer idCmsUsersSubDept;
    @SerializedName("spv")
    @Expose
    private String spv;
    @SerializedName("oh")
    @Expose
    private String oh;
    @SerializedName("sub_dept")
    @Expose
    private String subDept;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNpm() {
      return npm;
    }

    public void setNpm(String npm) {
      this.npm = npm;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public Integer getIdMstOutlet() {
      return idMstOutlet;
    }

    public void setIdMstOutlet(Integer idMstOutlet) {
      this.idMstOutlet = idMstOutlet;
    }

    public String getOutletName() {
      return outletName;
    }

    public void setOutletName(String outletName) {
      this.outletName = outletName;
    }

    public Integer getIdMstBranch() {
      return idMstBranch;
    }

    public void setIdMstBranch(Integer idMstBranch) {
      this.idMstBranch = idMstBranch;
    }

    public String getBranchName() {
      return branchName;
    }

    public void setBranchName(String branchName) {
      this.branchName = branchName;
    }

    public Integer getIdCmsPrivileges() {
      return idCmsPrivileges;
    }

    public void setIdCmsPrivileges(Integer idCmsPrivileges) {
      this.idCmsPrivileges = idCmsPrivileges;
    }

    public String getPrivilegesName() {
      return privilegesName;
    }

    public void setPrivilegesName(String privilegesName) {
      this.privilegesName = privilegesName;
    }

    public Integer getIdCmsUsersOh() {
      return idCmsUsersOh;
    }

    public void setIdCmsUsersOh(Integer idCmsUsersOh) {
      this.idCmsUsersOh = idCmsUsersOh;
    }

    public Integer getIdCmsUsersSpv() {
      return idCmsUsersSpv;
    }

    public void setIdCmsUsersSpv(Integer idCmsUsersSpv) {
      this.idCmsUsersSpv = idCmsUsersSpv;
    }

    public Integer getIdCmsUsersSubDept() {
      return idCmsUsersSubDept;
    }

    public void setIdCmsUsersSubDept(Integer idCmsUsersSubDept) {
      this.idCmsUsersSubDept = idCmsUsersSubDept;
    }

    public String getSpv() {
      return spv;
    }

    public void setSpv(String spv) {
      this.spv = spv;
    }

    public String getOh() {
      return oh;
    }

    public void setOh(String oh) {
      this.oh = oh;
    }

    public String getSubDept() {
      return subDept;
    }

    public void setSubDept(String subDept) {
      this.subDept = subDept;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

  }

}
