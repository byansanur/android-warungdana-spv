package com.byandev.warnaspvdev.MainFragment.FragmentOrder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.byandev.warnaspvdev.Adapter.ListOrderApproveAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOrderApprove extends Fragment {

    private RecyclerView recyclerView;
    private ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;
    private Context context;
    private Integer offset = 15, limit;
    LinearLayout iconKosong;
    ProgressBar progress;
    ImageView ic_kosong;

    private boolean itShouldLoadMore = true;

    ArrayList<RespOrderStatus.ListOrderStatus> listed;
    ListOrderApproveAdapter listOrderApproveAdapter;


    public FragmentOrderApprove(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle save) {
        final View view = inflater.inflate(R.layout.fragment_order_approve, container, false);
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.pull);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onAttach(getContext());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        limit = Integer.parseInt(getResources().getString(R.string.limit));
        iconKosong = view.findViewById(R.id.iconKosong);
        progress = view.findViewById(R.id.progress);
        ic_kosong = view.findViewById(R.id.ic_kosong);
        ic_kosong.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.recylerViewApprove);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getContext());

        listed = new ArrayList<>();
        listOrderApproveAdapter = new ListOrderApproveAdapter(getContext(), listed);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listOrderApproveAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        if (itShouldLoadMore) {
                            progress.setVisibility(View.VISIBLE);
                            ic_kosong.setVisibility(View.VISIBLE);
                            loadMore();

                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        firstLoad();
        listed.clear();
        listOrderApproveAdapter.notifyDataSetChanged();
        progress.setVisibility(View.VISIBLE);
        ic_kosong.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void firstLoad(){
        itShouldLoadMore = false;
        mApiService.orderByStatus(sharedPrefManager.getSpBranchId(), sharedPrefManager.getSpOutletId(),1, limit, 0).enqueue(new Callback<RespOrderStatus>() {
            @Override
            public void onResponse(Call<RespOrderStatus> call, Response<RespOrderStatus> response) {
                if (response.isSuccessful()) {
                  if (response.body() != null) {
                    if (response.body().getApiStatus() == 1) {
                      List<RespOrderStatus.ListOrderStatus> list = response.body().getData();
                      listed.addAll(list);
                      listOrderApproveAdapter.notifyDataSetChanged();
                      progress.setVisibility(View.GONE);
                      ic_kosong.setVisibility(View.GONE);
                    }
                  }
                }
            }

            @Override
            public void onFailure(Call<RespOrderStatus> call, Throwable t) {
                itShouldLoadMore = true;
                progress.setVisibility(View.GONE);
                ic_kosong.setVisibility(View.GONE);
            }
        });
    }

    private void loadMore() {
        itShouldLoadMore = false;
        mApiService.orderByStatus(sharedPrefManager.getSpBranchId(), sharedPrefManager.getSpOutletId(),1, limit, offset).enqueue(new Callback<RespOrderStatus>() {
            @Override
            public void onResponse(Call<RespOrderStatus> call, Response<RespOrderStatus> response) {
                if (response.isSuccessful()) {
                  if (response.body() != null) {
                    if (response.body().getApiStatus() == 1) {
                      List<RespOrderStatus.ListOrderStatus> list = response.body().getData();
                      listed.addAll(list);
                      listOrderApproveAdapter.notifyDataSetChanged();
                      limit = listed.size();
                      progress.setVisibility(View.GONE);
                      ic_kosong.setVisibility(View.GONE);
                    }
                  }
                }
            }

            @Override
            public void onFailure(Call<RespOrderStatus> call, Throwable t) {
                itShouldLoadMore = true;
                progress.setVisibility(View.VISIBLE);
                ic_kosong.setVisibility(View.VISIBLE);
            }
        });
    }
}
