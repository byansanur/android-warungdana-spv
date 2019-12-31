package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespOrderUsers {
    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private List<ListOrderUsers> data = null;

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

    public List<ListOrderUsers> getData() {
        return data;
    }

    public void setData(List<ListOrderUsers> data) {
        this.data = data;
    }

    public class ListOrderUsers {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("npm")
        @Expose
        private String npm;
        @SerializedName("outlet_name")
        @Expose
        private String outletName;
        @SerializedName("branch_name")
        @Expose
        private String branchName;
        @SerializedName("privileges_name")
        @Expose
        private String privilegesName;
        @SerializedName("orders")
        @Expose
        private Integer orders;
        @SerializedName("approve")
        @Expose
        private Integer approve;
        @SerializedName("process")
        @Expose
        private Integer process;
        @SerializedName("cancel")
        @Expose
        private Integer cancel;
        @SerializedName("reject")
        @Expose
        private Integer reject;
        @SerializedName("paid")
        @Expose
        private Integer paid;

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

        public String getPrivilegesName() {
            return privilegesName;
        }

        public void setPrivilegesName(String privilegesName) {
            this.privilegesName = privilegesName;
        }

        public Integer getOrders() {
            return orders;
        }

        public void setOrders(Integer orders) {
            this.orders = orders;
        }

        public Integer getApprove() {
            return approve;
        }

        public void setApprove(Integer approve) {
            this.approve = approve;
        }

        public Integer getProcess() {
            return process;
        }

        public void setProcess(Integer process) {
            this.process = process;
        }

        public Integer getCancel() {
            return cancel;
        }

        public void setCancel(Integer cancel) {
            this.cancel = cancel;
        }

        public Integer getReject() {
            return reject;
        }

        public void setReject(Integer reject) {
            this.reject = reject;
        }

        public Integer getPaid() {
            return paid;
        }

        public void setPaid(Integer paid) {
            this.paid = paid;
        }
    }
}
