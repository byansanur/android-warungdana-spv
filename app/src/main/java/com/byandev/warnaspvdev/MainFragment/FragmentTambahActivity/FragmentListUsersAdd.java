package com.byandev.warnaspvdev.MainFragment.FragmentTambahActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Adapter.ListUsersAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.byandev.warnaspvdev.Utils.UtilsConnected;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentListUsersAdd extends Fragment {

  private ArrayList<RespUsers.RespListUsers> respListUsers;
  private ListUsersAdapter listUsersAdapter;
  private ApiEndPoint mApiService;
  Context context;
  SharedPrefManager sharedPrefManager;

  private Integer Offset = 3, limit, limits;
  private boolean itShouldLoadMore = true;

  private Toolbar toolbar;

  // search
  TextView tvSearch;
  RecyclerView recyclerView;
  Call<RespUsers> call = null;

  private Integer idCmsUserss;


  public FragmentListUsersAdd() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    View view = inflater.inflate(R.layout.fragment_list_user_add, container, false);

    context = getContext();
    sharedPrefManager = new SharedPrefManager(context);
    mApiService = UtilsApi.getAPIService();

//
    toolbar = view.findViewById(R.id.toolbar);
    toolbar.setTitle("Select users");
    setHasOptionsMenu(true);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


    tvSearch = view.findViewById(R.id.tvPencarian);
    recyclerView = view.findViewById(R.id.recyclerView);
    tvSearch.setVisibility(View.GONE);

    boolean isConn = UtilsConnected.isNetworkConnected(context);
    limit = Integer.parseInt(getResources().getString(R.string.limituserhome));
    limits = Integer.parseInt(getResources().getString(R.string.limitlead));


    RecyclerView recycler_view = view.findViewById(R.id.recycler_view);
    limit = Integer.parseInt(getResources().getString(R.string.limituserhome));
    respListUsers = new ArrayList<>();
    listUsersAdapter = new ListUsersAdapter(getContext(), respListUsers);
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler_view.getContext(),
//        layoutManager.getOrientation());
//
//    recycler_view.addItemDecoration(dividerItemDecoration);
    recycler_view.setLayoutManager(layoutManager);
    recycler_view.setAdapter(listUsersAdapter);
    recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
            if (itShouldLoadMore) {
              // icon kosong dan loadMore
              loadMore();
            }
          }
        }
      }
    });


    return view;
  }



  // search
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_order, menu);
    inflater.inflate(R.menu.menu_ceklist, menu);
    MenuItem menuItem = menu.findItem(R.id.action_search);

    MenuItem menuItem1 = menu.findItem(R.id.menuCek);
    menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        Intent a = new Intent(context, TambahJadwalActivity.class);
        a.putExtra("id", respListUsers.get(0).getId());
        startActivity(a);
        return false;
      }
    });

    final SearchView sv = (SearchView) menuItem.getActionView();
    sv.setQueryHint("Search name of users...");
    sv.setDrawingCacheBackgroundColor(getResources().getColor(R.color.white_transparency));

    try {
      EditText editText = sv.findViewById(androidx.appcompat.R.id.search_src_text);
      editText.setTextColor(getResources().getColor(R.color.pencarianText));
      editText.setHintTextColor(getResources().getColor(R.color.pencarianText2));
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
        sv.setQueryHint("");
        tvSearch.setVisibility(View.GONE);
        recyclerView.setVisibility(View.INVISIBLE);
        respListUsers.clear();
        listUsersAdapter.notifyDataSetChanged();
        return true;
      }
    });
    sv.setOnSearchClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        tvSearch.setVisibility(View.VISIBLE);
      }
    });
    sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
          listUsersAdapter.notifyDataSetChanged();
        }
        return false;
      }
    });
    super.onCreateOptionsMenu(menu, inflater);
  }



  private void callFilter(String newText) {
    tvSearch.setVisibility(View.GONE);
    tvSearch.setText("Searching...");

    call = mApiService.searchView(
        sharedPrefManager.getSpBranchId(), newText);
    call.enqueue(new Callback<RespUsers>() {
      @Override
      public void onResponse(Call<RespUsers> call, Response<RespUsers> response) {
        if (response.isSuccessful()) {
          if (response.body().getData() != null) {
            tvSearch.setText("Search result");
            respListUsers.clear();
            listUsersAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            List<RespUsers.RespListUsers> listUsers = response.body().getData();
            if (listUsers.size() <= 0) {
              tvSearch.setText("Search not founds");
            }
            respListUsers.addAll(listUsers);
            listUsersAdapter.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onFailure(Call<RespUsers> call, Throwable t) {
        if (call.isCanceled()) {

        } else {
          tvSearch.setText("Check your internet connections");
        }
      }
    });
  }


  // rvlistuser

  @Override
  public void onStart() {
    super.onStart();
    itemUsers();
  }

  @Override
  public void onResume() {
    super.onResume();
    loadMore();
  }

  private void itemUsers() {
    itShouldLoadMore = false;
    mApiService.searchUsers(
        sharedPrefManager.getSpBranchId()
    ).enqueue(new Callback<RespUsers>() {
      @Override
      public void onResponse(Call<RespUsers> call, Response<RespUsers> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            if (response.body().getData() != null) {
              List<RespUsers.RespListUsers> list = response.body().getData();
              respListUsers.addAll(list);
              listUsersAdapter.notifyDataSetChanged();
//              idCmsUserss = respListUsers.get(list).getId();
            }
          }
        }
      }

      @Override
      public void onFailure(Call<RespUsers> call, Throwable t) {

      }
    });
  }
  private void loadMore() {
    itShouldLoadMore = false;
    mApiService.searchUsers(sharedPrefManager.getSpBranchId()).enqueue(new Callback<RespUsers>() {
      @Override
      public void onResponse(Call<RespUsers> call, Response<RespUsers> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            List<RespUsers.RespListUsers> list = response.body().getData();
            respListUsers.addAll(list);
            listUsersAdapter.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onFailure(Call<RespUsers> call, Throwable t) {

      }
    });
  }


}
