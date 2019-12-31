package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespGetUserForActivity {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<GetUserForActivity> data = null;

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

  public List<GetUserForActivity> getData() {
    return data;
  }

  public void setData(List<GetUserForActivity> data) {
    this.data = data;
  }

  public class GetUserForActivity {

    public GetUserForActivity(
        Integer id,
        String name,
        String npm,
        Integer idCmsPrivileges,
        String namePrivileges,
        Integer idMstOutlet,
        String outletName,
        Integer idMstBranch,
        String branchName,
        String status
    ) {
      this.id = id;
      this.name = name;
      this.npm = npm;
      this.idCmsPrivileges = idCmsPrivileges;
      this.namePrivileges = namePrivileges;
      this.idMstOutlet = idMstOutlet;
      this.outletName = outletName;
      this.idMstBranch = idMstBranch;
      this.branchName = branchName;
      this.status = status;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("id_cms_privileges")
    @Expose
    private Integer idCmsPrivileges;
    @SerializedName("name_privileges")
    @Expose
    private String namePrivileges;
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
    @SerializedName("status")
    @Expose
    private String status;



    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
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

    public Integer getIdCmsPrivileges() {
      return idCmsPrivileges;
    }

    public void setIdCmsPrivileges(Integer idCmsPrivileges) {
      this.idCmsPrivileges = idCmsPrivileges;
    }

    public String getNamePrivileges() {
      return namePrivileges;
    }

    public void setNamePrivileges(String namePrivileges) {
      this.namePrivileges = namePrivileges;
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

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

  }
}
