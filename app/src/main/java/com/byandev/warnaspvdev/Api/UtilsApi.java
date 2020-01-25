package com.byandev.warnaspvdev.Api;

public class UtilsApi {
//    public static final String BASE_URL_API = "http://dna-developer.com:804/";
    public static final String BASE_URL_API = "http://192.168.77.55:3003/api_bian/v1/";
//    public static final String BASE_URL_API_ACTIVITY = "http://192.168.77.14:3000/service/bian/";
public static final String BASE_UPDATE_JSON = "http://dna-developer.com:804/updateapp/update.json";


    // Mendeklarasikan Interface BaseApiService
    public static ApiEndPoint getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiEndPoint.class);
    }

}
