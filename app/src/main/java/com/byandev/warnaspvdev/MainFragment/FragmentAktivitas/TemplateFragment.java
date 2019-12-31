package com.byandev.warnaspvdev.MainFragment.FragmentAktivitas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.byandev.warnaspvdev.Adapter.ListActivityTemplateAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.ListActivityTemplate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemplateFragment extends Fragment {

    ProgressBar progress;
    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    ApiEndPoint mApiService;
    public ListActivityTemplateAdapter adapter;
    SwipeRefreshLayout pull;


    public TemplateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_template, container, false);
        sharedPrefManager = new SharedPrefManager(getContext());
        mApiService = UtilsApi.getAPIService();
        pull = view.findViewById(R.id.pull);
        progress = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recylerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
//        mApiService.activityTemplateList(sharedPrefManager.getSpId(),null).enqueue(new Callback<RespListActivityTemplate>() {
//            @Override
//            public void onResponse(Call<RespListActivityTemplate> call, Response<RespListActivityTemplate> response) {
//                if (response.isSuccessful()){
//                    if (!response.body().getData().isEmpty()){
//                        progress.setVisibility(View.GONE);
//                        List<ListActivityTemplate> list = response.body().getData();
//                        adapter = new ListActivityTemplateAdapter(getContext(),list);
//                        recyclerView.setAdapter(adapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RespListActivityTemplate> call, Throwable t) {
//
//            }
//        });

    }
    public void refresh(){
        pull.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onResume();
                pull.setRefreshing(false);
            }
        });
    }
}
