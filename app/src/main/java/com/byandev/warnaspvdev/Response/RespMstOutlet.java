package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespMstOutlet {


        @SerializedName("api_status")
        @Expose
        private Integer apiStatus;
        @SerializedName("api_message")
        @Expose
        private String apiMessage;
        @SerializedName("data")
        @Expose
        private List<ListMstOutlet> data = null;

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

        public List<ListMstOutlet> getData() {
            return data;
        }

        public void setData(List<ListMstOutlet> data) {
            this.data = data;
        }

    public class ListMstOutlet {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("outlet_name")
        @Expose
        private String outletName;
        @SerializedName("id_mst_branch")
        @Expose
        private Integer idMstBranch;
        @SerializedName("branch_name")
        @Expose
        private String branchName;
        @SerializedName("outlet_id")
        @Expose
        private String outletId;
        @SerializedName("outlet_sys_id")
        @Expose
        private String outletSysId;
        @SerializedName("biz_type")
        @Expose
        private String bizType;
        @SerializedName("outlet_address")
        @Expose
        private String outletAddress;
        @SerializedName("outlet_fif_code")
        @Expose
        private String outletFifCode;
        @SerializedName("outlet_desc")
        @Expose
        private String outletDesc;
        @SerializedName("outlet_status")
        @Expose
        private String outletStatus;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getOutletId() {
            return outletId;
        }

        public void setOutletId(String outletId) {
            this.outletId = outletId;
        }

        public String getOutletSysId() {
            return outletSysId;
        }

        public void setOutletSysId(String outletSysId) {
            this.outletSysId = outletSysId;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }

        public String getOutletAddress() {
            return outletAddress;
        }

        public void setOutletAddress(String outletAddress) {
            this.outletAddress = outletAddress;
        }

        public String getOutletFifCode() {
            return outletFifCode;
        }

        public void setOutletFifCode(String outletFifCode) {
            this.outletFifCode = outletFifCode;
        }

        public String getOutletDesc() {
            return outletDesc;
        }

        public void setOutletDesc(String outletDesc) {
            this.outletDesc = outletDesc;
        }

        public String getOutletStatus() {
            return outletStatus;
        }

        public void setOutletStatus(String outletStatus) {
            this.outletStatus = outletStatus;
        }
    }
 }

