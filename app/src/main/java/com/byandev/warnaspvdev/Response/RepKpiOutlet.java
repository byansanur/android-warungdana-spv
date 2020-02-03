package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepKpiOutlet {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private List<OutletKpi> data = null;

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

  public List<OutletKpi> getData() {
    return data;
  }

  public void setData(List<OutletKpi> data) {
    this.data = data;
  }

  public class OutletKpi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_mst_branch")
    @Expose
    private Integer idMstBranch;
    @SerializedName("outlet_name")
    @Expose
    private String outletName;
    @SerializedName("total_order")
    @Expose
    private Integer totalOrder;
    @SerializedName("total_lead")
    @Expose
    private Integer totalLead;
    @SerializedName("outlet_status")
    @Expose
    private String outletStatus;

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getIdMstBranch() {
      return idMstBranch;
    }

    public void setIdMstBranch(Integer idMstBranch) {
      this.idMstBranch = idMstBranch;
    }

    public String getOutletName() {
      return outletName;
    }

    public void setOutletName(String outletName) {
      this.outletName = outletName;
    }

    public Integer getTotalOrder() {
      return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
      this.totalOrder = totalOrder;
    }

    public Integer getTotalLead() {
      return totalLead;
    }

    public void setTotalLead(Integer totalLead) {
      this.totalLead = totalLead;
    }

    public String getOutletStatus() {
      return outletStatus;
    }

    public void setOutletStatus(String outletStatus) {
      this.outletStatus = outletStatus;
    }

  }

}
