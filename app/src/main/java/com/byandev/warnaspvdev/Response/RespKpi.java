package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespKpi {
    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private RespKpiData data;

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

    public RespKpiData getData() {
        return data;
    }

    public void setData() {
        this.data = data;
    }


    public class RespKpiData {
        @SerializedName("users")
        @Expose
        private Integer users;
        @SerializedName("brosur")
        @Expose
        private Integer brosur;
        @SerializedName("leads")
        @Expose
        private Integer leads;
        @SerializedName("target_call")
        @Expose
        private Integer targetCall;
        @SerializedName("deals")
        @Expose
        private Integer deals;
        @SerializedName("booking")
        @Expose
        private Integer booking;
        @SerializedName("target")
        @Expose
        private Integer target;

        public Integer getUsers() {
            return users;
        }

        public void setUsers(Integer users) {
            this.users = users;
        }

        public Integer getBrosur() {
            return brosur;
        }

        public void setBrosur(Integer brosur) {
            this.brosur = brosur;
        }

        public Integer getLeads() {
            return leads;
        }

        public void setLeads(Integer leads) {
            this.leads = leads;
        }

        public Integer getTargetCall() {
            return targetCall;
        }

        public void setTargetCall(Integer targetCall) {
            this.targetCall = targetCall;
        }

        public Integer getDeals() {
            return deals;
        }

        public void setDeals(Integer deals) {
            this.deals = deals;
        }

        public Integer getBooking() {
            return booking;
        }

        public void setBooking(Integer booking) {
            this.booking = booking;
        }

        public Integer getTarget() {
            return target;
        }

        public void setTarget(Integer target) {
            this.target = target;
        }
    }
}
