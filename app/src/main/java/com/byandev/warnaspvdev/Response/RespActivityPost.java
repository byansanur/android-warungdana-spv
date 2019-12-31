package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespActivityPost {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private DataPost data;

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

  public DataPost getData() {
    return data;
  }

  public void setData(DataPost data) {
    this.data = data;
  }

  public class DataPost {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id_mst_outlet")
    @Expose
    private Integer idMstOutlet;
    @SerializedName("id_activity_mst_type")
    @Expose
    private Integer idActivityMstType;
    @SerializedName("id_cms_users")
    @Expose
    private Integer idCmsUsers;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;

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

    public Integer getIdCmsUsers() {
      return idCmsUsers;
    }

    public void setIdCmsUsers(Integer idCmsUsers) {
      this.idCmsUsers = idCmsUsers;
    }

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
    }

    public String getLat() {
      return lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

    public String getLng() {
      return lng;
    }

    public void setLng(String lng) {
      this.lng = lng;
    }

  }
}
