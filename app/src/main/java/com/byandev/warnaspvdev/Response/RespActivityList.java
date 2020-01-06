package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespActivityList {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<DataActivity> data = null;

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

  public List<DataActivity> getData() {
    return data;
  }

  public void setData(List<DataActivity> data) {
    this.data = data;
  }


  public class DataActivity {

    @SerializedName("data_user")
    @Expose
    private List<DataUser> dataUser = null;
    @SerializedName("id_activity")
    @Expose
    private Integer idActivity;
    @SerializedName("id_activity_schedule")
    @Expose
    private Integer idActivitySchedule;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("started")
    @Expose
    private String started;
    @SerializedName("ended")
    @Expose
    private String ended;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("id_mst_outlet")
    @Expose
    private Integer idMstOutlet;
    @SerializedName("id_activity_mst_type")
    @Expose
    private Integer idActivityMstType;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("outlet_name")
    @Expose
    private String outletName;
    @SerializedName("id_mst_branch")
    @Expose
    private Integer idMstBranch;
    @SerializedName("branch_name")
    @Expose
    private String branchName;

    public List<DataUser> getDataUser() {
      return dataUser;
    }

    public void setDataUser(List<DataUser> dataUser) {
      this.dataUser = dataUser;
    }

    public Integer getIdActivity() {
      return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
      this.idActivity = idActivity;
    }

    public Integer getIdActivitySchedule() {
      return idActivitySchedule;
    }

    public void setIdActivitySchedule(Integer idActivitySchedule) {
      this.idActivitySchedule = idActivitySchedule;
    }

    public String getStartDate() {
      return startDate;
    }

    public void setStartDate(String startDate) {
      this.startDate = startDate;
    }

    public String getEndDate() {
      return endDate;
    }

    public void setEndDate(String endDate) {
      this.endDate = endDate;
    }

    public String getStarted() {
      return started;
    }

    public void setStarted(String started) {
      this.started = started;
    }

    public String getEnded() {
      return ended;
    }

    public void setEnded(String ended) {
      this.ended = ended;
    }

    public String getNote() {
      return note;
    }

    public void setNote(String note) {
      this.note = note;
    }

    public Integer getIdMstOutlet() {
      return idMstOutlet;
    }

    public void setIdMstOutlet(Integer idMstOutlet) {
      this.idMstOutlet = idMstOutlet;
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

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
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

  }

  public class DataUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id_activity_schedule")
    @Expose
    private Integer idActivitySchedule;
    @SerializedName("users_activity")
    @Expose
    private Integer usersActivity;
    @SerializedName("name_user")
    @Expose
    private String nameUser;

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

    public Integer getUsersActivity() {
      return usersActivity;
    }

    public void setUsersActivity(Integer usersActivity) {
      this.usersActivity = usersActivity;
    }

    public String getNameUser() {
      return nameUser;
    }

    public void setNameUser(String nameUser) {
      this.nameUser = nameUser;
    }

  }
}
