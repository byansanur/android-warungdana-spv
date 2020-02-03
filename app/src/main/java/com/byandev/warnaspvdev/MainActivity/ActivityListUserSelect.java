package com.byandev.warnaspvdev.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.byandev.warnaspvdev.Adapter.AdapterUserSelect;
import com.byandev.warnaspvdev.Adapter.ListUserActivityAdapter;
import com.byandev.warnaspvdev.Adapter.ListUsersAdapter;
import com.byandev.warnaspvdev.Adapter.ListUsersForActivity;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.Helper.AlertDialogHelper;
import com.byandev.warnaspvdev.Listener.RecyclerClickListener;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUsers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListUserSelect extends AppCompatActivity implements ListUsersForActivity.OnItemCliked, Toolbar.OnMenuItemClickListener {

  private ApiEndPoint mApiService;
  Context context;
  SharedPrefManager sharedPrefManager;
  private Toolbar toolbar;
  private boolean itShouldLoadMore = true;

  private ArrayList<RespUsers.RespListUsers> users;
  private ListUsersForActivity adapter;
  private List<Integer> userId = new ArrayList<>();

   RecyclerView recyclerView;

//   private ActionMenuItemView menuNexts;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_user_select);

    final SwipeRefreshLayout refreshLayout = findViewById(R.id.pull);
    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        refreshLayout.setRefreshing(false);
      }
    });

    sharedPrefManager = new SharedPrefManager(this);
    mApiService = UtilsApi.getAPIService();

    toolbar = findViewById(R.id.toolbarSelectCount);
    toolbar.setTitle("Select users");
    toolbar.setOnMenuItemClickListener(this);
    toolbar.inflateMenu(R.menu.menu_next);
    toolbar.getMenu().findItem(R.id.nextMenu).setVisible(false);

//    menuNexts = toolbar.findViewById(R.id.nextMenu);


    context = this;

    users = new ArrayList<>();
    adapter = new ListUsersForActivity(context, users);
    adapter.setOnClick(this);
    LinearLayoutManager llm = new LinearLayoutManager(context);
    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(llm);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          if (!recyclerView.canScrollVertically(View.FOCUS_DOWN)) {
            if (itShouldLoadMore) {
//              loadMore();
            }
          }
        }
      }
    });
    firstLoad();
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onResume(){
    super.onResume();
  }

  private void firstLoad() {
    itShouldLoadMore = false;
    mApiService.searchUsers(sharedPrefManager.getSpBranchId()).enqueue(new Callback<RespUsers>() {
      @Override
      public void onResponse(Call<RespUsers> call, Response<RespUsers> response) {
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
            List<RespUsers.RespListUsers> usl = response.body().getData();
            users.addAll(usl);
            adapter.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onFailure(Call<RespUsers> call, Throwable t) {

      }
    });
  }

//  private void loadMore() {itShouldLoadMore = false;
//    mApiService.searchUsers(sharedPrefManager.getSpBranchId()).enqueue(new Callback<RespUsers>() {
//      @Override
//      public void onResponse(Call<RespUsers> call, Response<RespUsers> response) {
//        if (response.isSuccessful()) {
//          if (response.body() != null && response.body().getApiStatus() == 1) {
//            List<RespUsers.RespListUsers> list = response.body().getData();
//            users.addAll(list);
//            adapter.notifyDataSetChanged();
//          }
//        }
//      }
//
//      @Override
//      public void onFailure(Call<RespUsers> call, Throwable t) {
//
//      }
//    });
//
//  }


  @Override
  public void onBackPressed(){
    super.onBackPressed();
    Intent intent = new Intent(context, TambahJadwalActivity.class);
    startActivity(intent);
    finish();
  }


  @Override
  public void selectedUser(List<Integer> idU) {
    Toast.makeText(context, "tes "+idU.size(), Toast.LENGTH_SHORT).show();
//    userId.clear();
    userId = idU;
    if (userId.size() >= 1) {
     toolbar.getMenu().findItem(R.id.nextMenu).setVisible(true);
    } else  {
      toolbar.getMenu().findItem(R.id.nextMenu).setVisible(false);
    }
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nextMenu:
        return true;
        default:
    }
    return false;
  }
}
