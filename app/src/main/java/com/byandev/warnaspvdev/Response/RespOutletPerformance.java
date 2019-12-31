package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespOutletPerformance {
    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private List<ListOutletPerformance> data = null;

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

    public List<ListOutletPerformance> getData() {
        return data;
    }

    public void setData(List<ListOutletPerformance> data) {
        this.data = data;
    }

    public class ListOutletPerformance {
        @SerializedName("month")
        @Expose
        private String month;
        @SerializedName("booking")
        @Expose
        private Integer booking;
        @SerializedName("lead")
        @Expose
        private Integer lead;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public Integer getBooking() {
            return booking;
        }

        public void setBooking(Integer booking) {
            this.booking = booking;
        }

        public Integer getLead() {
            return lead;
        }

        public void setLead(Integer lead) {
            this.lead = lead;
        }
    }
}
