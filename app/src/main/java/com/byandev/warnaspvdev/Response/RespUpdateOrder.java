package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespUpdateOrder {
  @SerializedName("api_status")
  @Expose
  private Integer apiStatus;
  @SerializedName("api_message")
  @Expose
  private String apiMessage;
  @SerializedName("data")
  @Expose
  private DataOrder data;

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

  public DataOrder getData() {
    return data;
  }

  public void setData(DataOrder data) {
    this.data = data;
  }

  public class DataOrder {

    @SerializedName("id_order")
    @Expose
    private Integer idOrder;
    @SerializedName("plafond")
    @Expose
    private Integer plafond;
    @SerializedName("down_payment")
    @Expose
    private Integer downPayment;
    @SerializedName("installment")
    @Expose
    private Integer installment;
    @SerializedName("tenor")
    @Expose
    private Integer tenor;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;

    public Integer getIdOrder() {
      return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
      this.idOrder = idOrder;
    }

    public Integer getPlafond() {
      return plafond;
    }

    public void setPlafond(Integer plafond) {
      this.plafond = plafond;
    }

    public Integer getDownPayment() {
      return downPayment;
    }

    public void setDownPayment(Integer downPayment) {
      this.downPayment = downPayment;
    }

    public Integer getInstallment() {
      return installment;
    }

    public void setInstallment(Integer installment) {
      this.installment = installment;
    }

    public Integer getTenor() {
      return tenor;
    }

    public void setTenor(Integer tenor) {
      this.tenor = tenor;
    }

    public Integer getUpdatedBy() {
      return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
      this.updatedBy = updatedBy;
    }

  }
}
