package com.byandev.warnaspvdev.MainFragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.byandev.warnaspvdev.Adapter.ListOrderApproveAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.Helper.ViewPagerAdapter;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.FragmentOrderApprove;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.FragmentOrderBatal;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.FragmentOrderCair;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.FragmentOrderProses;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.FragmentOrderReject;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderStatus;
import com.byandev.warnaspvdev.Response.RespSearchOrder;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.YEAR;

public class OrderFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Context context;
    Integer idUser;
    SharedPrefManager sharedPrefManager;

    ApiEndPoint mApiService;


    ArrayList<RespOrderStatus.ListOrderStatus> listed;
    ListOrderApproveAdapter listOrderApproveAdapter;

    Call<RespOrderStatus> call = null;

    TextView tvSearch;
    RecyclerView recyclerView;


    public OrderFragment(){
        // required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle save) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        mApiService = UtilsApi.getAPIService();
        context = getContext();
        sharedPrefManager = new SharedPrefManager(context);
        idUser = sharedPrefManager.getSpId();

        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Orders");

        tvSearch = view.findViewById(R.id.tvPencarian);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvSearch.setVisibility(View.GONE);


        tabLayout = view.findViewById(R.id.tabsHome);
        viewPager = view.findViewById(R.id.frame_container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentOrderApprove(), "Approve");
        adapter.addFragment(new FragmentOrderProses(), "Process");
        adapter.addFragment(new FragmentOrderBatal(), "Canceled");
        adapter.addFragment(new FragmentOrderCair(), "Paid");
        adapter.addFragment(new FragmentOrderReject(), "Reject");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        listed = new ArrayList<>();
        listOrderApproveAdapter = new ListOrderApproveAdapter(getContext(), listed);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listOrderApproveAdapter);

        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_order, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search orders name...");

        try {
            EditText etSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            etSearch.setTextColor(getResources().getColor(R.color.pencarianText));
            etSearch.setHintTextColor(getResources().getColor(R.color.pencarianText));
        } catch (Exception e) {
            e.printStackTrace();
        }


        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchView.setQuery("", false);
                tvSearch.setVisibility(View.GONE);
                recyclerView.setVisibility(RecyclerView.INVISIBLE);
                listed.clear();
                listOrderApproveAdapter.notifyDataSetChanged();
                return true;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSearch.setVisibility(TextView.VISIBLE);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (call != null) {
                    call.cancel();
                }
                if (newText.length() >= 1) {
                    tvSearch.setVisibility(View.GONE);
                    callFilter(newText);
                } else {
                    listed.clear();
                    listOrderApproveAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void callFilter(String newText) {
        tvSearch.setVisibility(View.GONE);
        tvSearch.setText("Finding...");

        call = mApiService.searchOrder(sharedPrefManager.getSpBranchId(), newText);

        call.enqueue(new Callback<RespOrderStatus>() {
            @Override
            public void onResponse(Call<RespOrderStatus> call, Response<RespOrderStatus> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        tvSearch.setText("Search result");
                        listed.clear();
                        listOrderApproveAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);

                        List<RespOrderStatus.ListOrderStatus> list = response.body().getData();

                        if (list.size() <= 0) {
                            tvSearch.setText("Search not founds");
                        }

                        listed.addAll(list);
                        listOrderApproveAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespOrderStatus> call, Throwable t) {
                if (call.isCanceled()) {

                }else {
                    tvSearch.setText("Check your connenction");
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}