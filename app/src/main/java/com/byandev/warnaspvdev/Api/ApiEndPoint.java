package com.byandev.warnaspvdev.Api;

import android.content.Intent;

import com.byandev.warnaspvdev.Response.Login;
import com.byandev.warnaspvdev.Response.RespActivityList;
import com.byandev.warnaspvdev.Response.RespActivityPost;
import com.byandev.warnaspvdev.Response.RespActivityStatus;
import com.byandev.warnaspvdev.Response.RespActivityType;
import com.byandev.warnaspvdev.Response.RespGetUserForActivity;
import com.byandev.warnaspvdev.Response.RespKpi;
import com.byandev.warnaspvdev.Response.RespLeadUsers;
import com.byandev.warnaspvdev.Response.RespListActivity;
import com.byandev.warnaspvdev.Response.RespMstOutlet;
import com.byandev.warnaspvdev.Response.RespMstOutletDetail;
import com.byandev.warnaspvdev.Response.RespMstStatus;
import com.byandev.warnaspvdev.Response.RespOrderDealsStatus;
import com.byandev.warnaspvdev.Response.RespOrderDetail;
import com.byandev.warnaspvdev.Response.RespOrderStatus;
import com.byandev.warnaspvdev.Response.RespOutletPerformance;
import com.byandev.warnaspvdev.Response.RespSearchOrder;
import com.byandev.warnaspvdev.Response.RespUpdateOrder;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUserStatus;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.byandev.warnaspvdev.Response.RespUsers2;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

import retrofit2.http.Field;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @FormUrlEncoded
    @POST("auth/login")
    Call<Login> loginRequest(@Field("npm") String npm,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("logout")
    Call<ResponseBody> logout(@Field("useragent") String userAgent,
                              @Field("description") String desc,
                              @Field("id_cms_users") Integer idUser,
                              @Field("version") String version);

    @FormUrlEncoded
    @POST("user_apps_logs_create")
    Call<ResponseBody> userLogs(@Field("useragent") String userAgent,
                                @Field("description") String desc,
                                @Field("id_cms_users") Integer idUser,
                                @Field("version") String version);

    @GET("dashboard")
    Call<RespKpi> kpi(@Query("id_mst_branch") Integer spBranchId,
                      @Query("created_at") String create_at);

    @GET("user")
    Call<RespUser> datauser(@Query("id") Integer id);

    @GET("dashboard/perfomance")
    Call<RespOutletPerformance> outletPerformance(@Query("id_mst_branch")Integer idMstBranch,
                                                  @Query("created_at") String createdAt);

    @GET("users/status")
    Call<RespUserStatus> userStatus(@Query("id_mst_branch")Integer idMstBranch);

    @GET("order/status")
    Call<RespOrderStatus> orderByStatus(@Query("id_mst_branch") Integer idMstBranch,
                                        @Query("id_mst_outlet") Integer idMstOutlet,
                                        @Query("id_order_mst_status") Integer idOrderMstStatus,
                                        @Query("limit") Integer limit,
                                        @Query("offset") Integer offset);

    @GET("order/status")
    Call<RespOrderStatus> orderStatus(@Query("id_mst_branch") Integer idMstBranch,
                                        @Query("id_mst_outlet") Integer idMstOutlet,
                                        @Query("id_order_mst_status") Integer idOrderMstStatus);

    @GET("order/status")
    Call<RespOrderStatus> orderNewList(@Query("id_mst_branch") Integer idMstBranch,
                                       @Query("id_mst_outlet") Integer idMstOutlet,
                                       @Query("created_at") String createdAt,
                                       @Query("limit") Integer limit,
                                       @Query("offset") Integer offset);

    @GET("order/detail")
    Call<RespOrderDetail>  orderDetail (@Query("id_order") Integer idOrder);

    // for kpi perorangan
    @GET("order/deals/status")
    Call<RespOrderDealsStatus> orderDealsStatus (@Query("id_mst_branch") Integer idMstBranch,
                                                 @Query("created_at") String createdAt);

    @GET("order/order_mst_status")
    Call<RespMstStatus> orderMstStatus();

    @FormUrlEncoded
    @PUT("order/update_status")
    Call<RespUpdateOrder> orderEdit(@Field("id") Integer id,
                                    @Field("updated_by") Integer updated_by,
                                    @Field("id_order_mst_status") Integer idOrderMstStatus);
    @FormUrlEncoded
    @PUT("order/update_status")
    Call<RespUpdateOrder> orderEditField(@Field("id") Integer id,
                                         @Field("updated_by") Integer updated_by,
                                         @Field("id_order_mst_status") Integer idOrderMstStatus,
                                         @Field("plafond") Integer plafond,
                                         @Field("down_payment") Integer down_payment,
                                         @Field("installment") Integer installment,
                                         @Field("tenor") Integer tenor);

    @GET("mst_outlet")
    Call<RespMstOutlet> outletList(@Query("id_mst_branch") Integer idMstBranch);

    @GET("mst_outlet_detail")
    Call<RespMstOutletDetail> outletDetail(@Query("id_mst_branch") Integer idMstBranch,
                                           @Query("id") Integer idOutlet);

    @GET("lead/users")
    Call<RespLeadUsers> listLeadUsers(@Query("id_mst_branch") Integer idMstBranch,
                                      @Query("created_at") String createdAt,
                                      @Query("limit") Integer limit,
                                      @Query("offset") Integer offset);

    @GET("order/status")
    Call<RespOrderStatus> searchOrder(@Query("id_mst_branch") Integer idMstBranch,
                                      @Query("first_name") String firstName);

    @GET("users")
    Call<RespUsers> searchUsers(@Query("id_mst_branch") Integer idMstBranch);

    @GET("users")
    Call<RespUsers> searchView(@Query("id_mst_branch") Integer idMstbranch,
                               @Query("name") String name);

    // service create by bian
    @GET("bian_activity_type")
    Call<RespActivityType> activityType();

//    @GET("bian_activity_list")
//    Call<RespListActivity> listActivity (@Query("id_mst_outlet") Integer idMstOutlet,
//                                         @Query("limit") Integer limit,
//                                         @Query("offset") Integer offset);

    @GET("users2")
    Call<RespUsers2> usersSearchCfaTelemarketing(@Query("id_mst_outlet") Integer idMstOutlet);

    @FormUrlEncoded
    @POST("bian_activity")
    Call<RespActivityPost> postActivity(@Field("id_mst_outlet") Integer idMstOutlet,
                                        @Field("id_activity_mst_type") Integer idActivityMstType,
                                        @Field("id_cms_users") Integer idCmsUsers,
                                        @Field("location") String location,
                                        @Field("lat") String lat,
                                        @Field("lng") String lng,
                                        @Field("start_date") String startDate,
                                        @Field("end_date") String endDate,
                                        @Field("started") String started,
                                        @Field("ended") String ended,
                                        @Field("note") String note,
                                        @Field("id_cms_users_activity") Integer idCmsUsersActivity);

    @GET("list_activity")
    Call<RespActivityList> getListActivity(@Query("id_mst_branch") Integer idMstBranch);

    @GET("list_activity")
    Call<RespActivityList> getListActivityHome(@Query("id_mst_branch") Integer idMstBranch,
                                               @Query("limit") Integer limit,
                                               @Query("offset") Integer offset);
}
