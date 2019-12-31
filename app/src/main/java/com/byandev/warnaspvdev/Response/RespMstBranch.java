package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespMstBranch {

    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private List<ListMstBranch> data = null;

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

    public List<ListMstBranch> getData() {
        return data;
    }

    public void setData(List<ListMstBranch> data) {
        this.data = data;
    }

    public class ListMstBranch {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("branch_name")
        @Expose
        private String branchName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

    }

}
