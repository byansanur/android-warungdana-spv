package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespOrderDetail {
    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private DataOrderDetail data;

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

    public DataOrderDetail getData() {
        return data;
    }

    public void setData(DataOrderDetail data) {
        this.data = data;
    }

    public static class DataOrderDetail extends RespOrderDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_order_mst_status")
        @Expose
        private Integer idOrderMstStatus;
        @SerializedName("id_order_mst_reason")
        @Expose
        private Integer idOrderMstReason;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("datasource")
        @Expose
        private String datasource;
        @SerializedName("nopol")
        @Expose
        private String nopol;
        @SerializedName("tax_status")
        @Expose
        private String taxStatus;
        @SerializedName("owner")
        @Expose
        private String owner;
        @SerializedName("merk")
        @Expose
        private String merk;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("otr")
        @Expose
        private Integer otr;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("plafond")
        @Expose
        private Integer plafond;
        @SerializedName("installment")
        @Expose
        private Integer installment;
        @SerializedName("down_payment")
        @Expose
        private Integer downPayment;
        @SerializedName("tenor")
        @Expose
        private Integer tenor;
        @SerializedName("need")
        @Expose
        private String need;
        @SerializedName("otr_custom")
        @Expose
        private Integer otrCustom;
        @SerializedName("surety_name")
        @Expose
        private String suretyName;
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

        public Integer getIdOrderMstReason() {
            return idOrderMstReason;
        }

        public void setIdOrderMstReason(Integer idOrderMstReason) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDatasource() {
            return datasource;
        }

        public void setDatasource(String datasource) {
            this.datasource = datasource;
        }

        public String getNopol() {
            return nopol;
        }

        public void setNopol(String nopol) {
            this.nopol = nopol;
        }

        public String getTaxStatus() {
            return taxStatus;
        }

        public void setTaxStatus(String taxStatus) {
            this.taxStatus = taxStatus;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
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

        public Integer getOtr() {
            return otr;
        }

        public void setOtr(Integer otr) {
            this.otr = otr;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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

        public Integer getDownPayment() {
            return downPayment;
        }

        public void setDownPayment(Integer downPayment) {
            this.downPayment = downPayment;
        }

        public Integer getTenor() {
            return tenor;
        }

        public void setTenor(Integer tenor) {
            this.tenor = tenor;
        }

        public String getNeed() {
            return need;
        }

        public void setNeed(String need) {
            this.need = need;
        }

        public Integer getOtrCustom() {
            return otrCustom;
        }

        public void setOtrCustom(Integer otrCustom) {
            this.otrCustom = otrCustom;
        }

        public String getSuretyName() {
            return suretyName;
        }

        public void setSuretyName(String suretyName) {
            this.suretyName = suretyName;
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
