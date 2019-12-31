package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespUserStatus {
    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private List<ListUserStatus> data = null;

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

    public List<ListUserStatus> getData() {
        return data;
    }

    public void setData(List<ListUserStatus> data) {
        this.data = data;
    }

    public class ListUserStatus {

        @SerializedName("id_branch")
        @Expose
        private Integer idBranch;
        @SerializedName("branch_name")
        @Expose
        private String branchName;
        @SerializedName("id_outlet")
        @Expose
        private Integer idOutlet;
        @SerializedName("outlet_name")
        @Expose
        private String outletName;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("active")
        @Expose
        private Integer active;
        @SerializedName("no_active")
        @Expose
        private Integer noActive;

        public Integer getIdBranch() {
            return idBranch;
        }

        public void setIdBranch(Integer idBranch) {
            this.idBranch = idBranch;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public Integer getIdOutlet() {
            return idOutlet;
        }

        public void setIdOutlet(Integer idOutlet) {
            this.idOutlet = idOutlet;
        }

        public String getOutletName() {
            return outletName;
        }

        public void setOutletName(String outletName) {
            this.outletName = outletName;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getActive() {
            return active;
        }

        public void setActive(Integer active) {
            this.active = active;
        }

        public Integer getNoActive() {
            return noActive;
        }

        public void setNoActive(Integer noActive) {
            this.noActive = noActive;
        }

    }
}
