package com.byandev.warnaspvdev.MainFragment.FragmentAktivitas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.byandev.warnaspvdev.Adapter.ListActivityAdapter;
import com.byandev.warnaspvdev.Adapter.ListOrderApproveAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespListActivity;
import com.byandev.warnaspvdev.Response.RespOrderStatus;
import com.byandev.warnaspvdev.Response.RespUsers2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentActivitys extends Fragment {

    private RecyclerView recyclerView;
    private ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;
    Context context;
    private Integer offset = 15, limit;
    private boolean itShouldLoadMore = true;
    HorizontalCalendar horizontalCalendar;
    String selectedDate;
    ProgressBar progress;

  ArrayList<RespListActivity.ActivityList> listed;
  ListActivityAdapter listActivityAdapter;

    public FragmentActivitys() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
      final View view = inflater.inflate(R.layout.fragment_activitys, container, false);
      context = getContext();
      limit = Integer.parseInt(getResources().getString(R.string.limit));
      recyclerView = view.findViewById(R.id.rvActivitys);
      recyclerView.setHasFixedSize(true);
      mApiService = UtilsApi.getAPIService();
      sharedPrefManager = new SharedPrefManager(getContext());

      progress = view.findViewById(R.id.progress);

      listed = new ArrayList<>();
      listActivityAdapter = new ListActivityAdapter(getContext(), listed);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
      recyclerView.setLayoutManager(linearLayoutManager);
      recyclerView.setAdapter(listActivityAdapter);
      recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          if (dy > 0) {
            if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
              if (itShouldLoadMore) {
                progress.setVisibility(View.VISIBLE);
//                loadMore();

              }
            }
          }
        }
      });

//      Calendar startDate = Calendar.getInstance();
//      startDate.add(Calendar.YEAR, -1);
//      Calendar endDate = Calendar.getInstance();
//      endDate.add(Calendar.YEAR, 1);
//      final Calendar defaultSelectedDate = Calendar.getInstance();
//      horizontalCalendar = new HorizontalCalendar.Builder((Activity) context, R.id.calendarView)
//          .range(startDate, endDate)
//          .datesNumberOnScreen(5)
//          .mode(HorizontalCalendar.Mode.MONTHS)
//          .configure()
//          .formatMiddleText("MMM")
//          .sizeMiddleText(18)
//          .formatBottomText("yyyy")
//          .showTopText(false)
//          .showBottomText(true)
//          .end()
//          .defaultSelectedDate(defaultSelectedDate)
//          .build();
////        selectedDate = String.valueOf(defaultSelectedDate.get(Calendar.YEAR))+"-"+String.valueOf(defaultSelectedDate.get(Calendar.MONTH)+1);
//
//
//      String month = String.valueOf(defaultSelectedDate.get(Calendar.MONTH)+1);
//
//      if (month.equals("10") || month.equals("11") ||month.equals("12") || month.equals("0")){
//
//      }else{
//        month = "0"+month;
//
//      }
//
//      selectedDate = String.valueOf(defaultSelectedDate.get(Calendar.YEAR))+"-"+month;

        return view;
    }

  @Override
  public void onResume() {
    super.onResume();
//    firstLoad();
    listed.clear();
    listActivityAdapter.notifyDataSetChanged();
    progress.setVisibility(View.VISIBLE);
  }

  @Override
  public void onStart() {
    super.onStart();
  }
//
//  private void firstLoad() {
//      itShouldLoadMore = true;
//      mApiService.listActivity(sharedPrefManager.getSpOutletId(), limit, 0)
//          .enqueue(new Callback<RespListActivity>() {
//            @Override
//            public void onResponse(Call<RespListActivity> call, Response<RespListActivity> response) {
//              if (response.isSuccessful()) {
//                if (response.body() != null && response.body().getApiStatus() == 1) {
//                  List<RespListActivity.ActivityList> list = response.body().getData();
//                  listed.addAll(list);
//                  listActivityAdapter.notifyDataSetChanged();
//                  progress.setVisibility(View.GONE);
//                }
//                Log.d("data activity", ":" +listed);
//              }
//            }
//
//            @Override
//            public void onFailure(Call<RespListActivity> call, Throwable t) {
//              itShouldLoadMore = true;
//              progress.setVisibility(View.INVISIBLE);
//            }
//          });
//  }
//
//  private void loadMore() {
//      itShouldLoadMore = true;
//      mApiService.listActivity(sharedPrefManager.getSpOutletId(), limit, offset)
//          .enqueue(new Callback<RespListActivity>() {
//            @Override
//            public void onResponse(Call<RespListActivity> call, Response<RespListActivity> response) {
//              if (response.isSuccessful()) {
//                if (response.body() != null && response.body().getApiStatus() == 1) {
//                  List<RespListActivity.ActivityList> lists = response.body().getData();
//                  listed.addAll(lists);
//                  listActivityAdapter.notifyDataSetChanged();
//                  offset = lists.size();
//                  progress.setVisibility(View.GONE);
//                }
//              }
//            }
//
//            @Override
//            public void onFailure(Call<RespListActivity> call, Throwable t) {
//              itShouldLoadMore = true;
//              progress.setVisibility(View.INVISIBLE);
//            }
//          });
//  }

}
