package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespOrderStatus {
    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private List<ListOrderStatus> data = null;

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

    public List<ListOrderStatus> getData() {
        return data;
    }

    public void setData(List<ListOrderStatus> data) {
        this.data = data;
    }

    public static class ListOrderStatus {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_order_mst_status")
        @Expose
        private Integer idOrderMstStatus;
        @SerializedName("id_order_mst_reason")
        @Expose
        private String idOrderMstReason;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("merk")
        @Expose
        private String merk;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("plafond")
        @Expose
        private Integer plafond;
        @SerializedName("installment")
        @Expose
        private Integer installment;
        @SerializedName("tenor")
        @Expose
        private Integer tenor;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("reason")
        @Expose
        private String reason;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getIdOrderMstStatus() {
            return idOrderMstStatus;
        }

        public void setIdOrderMstStatus(Integer idOrderMstStatus) {
            this.idOrderMstStatus = idOrderMstStatus;
        }

        public String getIdOrderMstReason() {
            return idOrderMstReason;
        }

        public void setIdOrderMstReason(String idOrderMstReason) {
            this.idOrderMstReason = idOrderMstReason;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public String getMerk() {
            return merk;
        }

        public void setMerk(String merk) {
            this.merk = merk;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getPlafond() {
            return plafond;
        }

        public void setPlafond(Integer plafond) {
            this.plafond = plafond;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

    }

}
