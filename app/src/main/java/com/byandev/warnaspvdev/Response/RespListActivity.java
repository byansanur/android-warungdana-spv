package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespListActivity {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<ActivityList> data = null;

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

  public List<ActivityList> getData() {
    return data;
  }

  public void setData(List<ActivityList> data) {
    this.data = data;
  }

  public class ActivityList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id_activity_schedule")
    @Expose
    private Integer idActivitySchedule;
    @SerializedName("id_cms_users")
    @Expose
    private Integer idCmsUsers;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id_cms_privileges")
    @Expose
    private Integer idCmsPrivileges;
    @SerializedName("privileges")
    @Expose
    private String privileges;
    @SerializedName("id_activity_mst_status")
    @Expose
    private Integer idActivityMstStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id_activity")
    @Expose
    private Integer idActivity;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("id_activity_mst_type")
    @Expose
    private Integer idActivityMstType;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id_mst_outlet")
    @Expose
    private Integer idMstOutlet;
    @SerializedName("outlet_name")
    @Expose
    private String outletName;

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

    public Integer getIdActivitySchedule() {
      return idActivitySchedule;
    }

    public void setIdActivitySchedule(Integer idActivitySchedule) {
      this.idActivitySchedule = idActivitySchedule;
    }

    public Integer getIdCmsUsers() {
      return idCmsUsers;
    }

    public void setIdCmsUsers(Integer idCmsUsers) {
      this.idCmsUsers = idCmsUsers;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getIdCmsPrivileges() {
      return idCmsPrivileges;
    }

    public void setIdCmsPrivileges(Integer idCmsPrivileges) {
      this.idCmsPrivileges = idCmsPrivileges;
    }

    public String getPrivileges() {
      return privileges;
    }

    public void setPrivileges(String privileges) {
      this.privileges = privileges;
    }

    public Integer getIdActivityMstStatus() {
      return idActivityMstStatus;
    }

    public void setIdActivityMstStatus(Integer idActivityMstStatus) {
      this.idActivityMstStatus = idActivityMstStatus;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public Integer getIdActivity() {
      return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
      this.idActivity = idActivity;
    }

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
    }

    public Integer getIdActivityMstType() {
      return idActivityMstType;
    }

    public void setIdActivityMstType(Integer idActivityMstType) {
      this.idActivityMstType = idActivityMstType;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
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

  }

}
