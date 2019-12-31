package com.byandev.warnaspvdev.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespMstLogDesc {

    @SerializedName("api_status")
    @Expose
    private Integer apiStatus;
    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("data")
    @Expose
    private List<ListMstLogDesc> data = null;

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

    public List<ListMstLogDesc> getData() {
        return data;
    }

    public void setData(List<ListMstLogDesc> data) {
        this.data = data;
    }

    public class ListMstLogDesc {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("id_mst_log_status")
        @Expose
        private Integer idMstLogStatus;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getIdMstLogStatus() {
            return idMstLogStatus;
        }

        public void setIdMstLogStatus(Integer idMstLogStatus) {
            this.idMstLogStatus = idMstLogStatus;
        }
    }

}
