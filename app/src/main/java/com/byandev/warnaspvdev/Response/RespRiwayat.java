package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespRiwayat {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<Riwayat> data = null;

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

  public List<Riwayat> getData() {
    return data;
  }

  public void setData(List<Riwayat> data) {
    this.data = data;
  }

  // field
  public class Riwayat {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_data")
    @Expose
    private Integer idData;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("privileges_name")
    @Expose
    private String privilegesName;
    @SerializedName("outlet_name")
    @Expose
    private String outletName;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("id_cms_users")
    @Expose
    private Integer idCmsUsers;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("modul")
    @Expose
    private String modul;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id_cms_users_oh")
    @Expose
    private Integer idCmsUsersOh;
    @SerializedName("id_cms_users_spv")
    @Expose
    private Integer idCmsUsersSpv;
    @SerializedName("oh_name")
    @Expose
    private String ohName;
    @SerializedName("spv_name")
    @Expose
    private String spvName;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getIdData() {
      return idData;
    }

    public void setIdData(Integer idData) {
      this.idData = idData;
    }

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getNpm() {
      return npm;
    }

    public void setNpm(String npm) {
      this.npm = npm;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getPrivilegesName() {
      return privilegesName;
    }

    public void setPrivilegesName(String privilegesName) {
      this.privilegesName = privilegesName;
    }

    public String getOutletName() {
      return outletName;
    }

    public void setOutletName(String outletName) {
      this.outletName = outletName;
    }

    public String getBranchName() {
      return branchName;
    }

    public void setBranchName(String branchName) {
      this.branchName = branchName;
    }

    public Integer getIdCmsUsers() {
      return idCmsUsers;
    }

    public void setIdCmsUsers(Integer idCmsUsers) {
      this.idCmsUsers = idCmsUsers;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getModul() {
      return modul;
    }

    public void setModul(String modul) {
      this.modul = modul;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
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

    public String getOhName() {
      return ohName;
    }

    public void setOhName(String ohName) {
      this.ohName = ohName;
    }

    public String getSpvName() {
      return spvName;
    }

    public void setSpvName(String spvName) {
      this.spvName = spvName;
    }

  }

}
