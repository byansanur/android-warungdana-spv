package com.byandev.warnaspvdev.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byandev.warnaspvdev.Adapter.ListUserActivityAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.Listener.RecyclerClickListener;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUsers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListUserSelect extends AppCompatActivity implements View.OnLongClickListener {

  private ApiEndPoint mApiService;
  Context context;
  SharedPrefManager sharedPrefManager;
  private Toolbar toolbar;

  public boolean in_action_mode = false;
  TextView countSelect;

  private ArrayList<RespUsers.RespListUsers> users;
  private ArrayList<RespUsers.RespListUsers> selectCounts = new ArrayList<>();

  private ActionMode actionMode;
  private boolean isMultiSelect = false;
  private List<Integer> selectedIds = new ArrayList<>();
  private ListUserActivityAdapter adapter;

  int counter = 0;

  Bundle bundle = new Bundle();

  String name[];
  Integer idUser[];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_user_select);
//    context = getApplicationContext();
    sharedPrefManager = new SharedPrefManager(this);
    mApiService = UtilsApi.getAPIService();

    toolbar = findViewById(R.id.toolbarSelectCount);
    toolbar.setTitle("Select users");
    setSupportActionBar(toolbar);

    countSelect = findViewById(R.id.countItemSelect);
    countSelect.setVisibility(View.GONE);

    users = new ArrayList<>();
    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    adapter = new ListUserActivityAdapter(users, ActivityListUserSelect.this);
    LinearLayoutManager ll = new LinearLayoutManager(ActivityListUserSelect.this);
    recyclerView.setLayoutManager(ll);
    recyclerView.setAdapter(adapter);
    recyclerView.setHasFixedSize(true);


//    loadFragment(new FragmentListUsersAdd());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_target, menu);
    return true;
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onResume(){
    super.onResume();
    firstLoad();
    users.clear();
    adapter.notifyDataSetChanged();
  }

  private void firstLoad() {
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

//
//  private void loadFragment(Fragment fragment) {
//    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//    ft.replace(R.id.frame_container,fragment);
//    ft.addToBackStack(null);
//    ft.commitAllowingStateLoss();
//  }


  @Override
  public void onBackPressed(){

    if (in_action_mode)
    {
      clearActionMode();
      adapter.notifyDataSetChanged();
    }
    else
    {
      super.onBackPressed();
    }
    Intent i = new Intent(this, TambahJadwalActivity.class);
    startActivity(i);
    finish();
  }


  @Override
  public boolean onLongClick(View v) {
    toolbar.getMenu().clear();
    toolbar.inflateMenu(R.menu.menu_ceklist);
    countSelect.setVisibility(View.VISIBLE);
    in_action_mode = true;
    adapter.notifyDataSetChanged();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    return true;
  }

  public void prepareSelection(View view, int position)
  {
    if (((CheckBox)view).isChecked())
    {
      selectCounts.add(users.get(position));
      counter = counter+1;
      updateCounter(counter);

    }
    else
    {
      selectCounts.remove(users.get(position));
      counter = counter-1;
      updateCounter(counter);
    }

  }

  public void updateCounter(int counter)
  {
    if (counter==0)
    {
      countSelect.setText("0 users selected");
    }
    else
    {
      countSelect.setText(counter + " users selected");
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId())
    {
      case R.id.menuCek:
//        idUser = bundle.getIntegerArrayList("idUser").toArray(new Integer[0]);
//        name = bundle.getStringArray("nama");
//        Intent a = new Intent(context, TambahJadwalActivity.class);
//        bundle = a.getBundleExtra(String.valueOf(name));
//        bundle = a.getIntegerArrayListExtra(users);
        return true;
      case R.id.clearSelect:
        if (in_action_mode)
        {
          clearActionMode();
          adapter.notifyDataSetChanged();
        }
        else if (item.getItemId()==android.R.id.home)
        {
          clearActionMode();
          adapter.notifyDataSetChanged();
        }
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void clearActionMode()
  {
    in_action_mode = false;
    toolbar.getMenu().clear();
    toolbar.inflateMenu(R.menu.menu_target);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    countSelect.setVisibility(View.GONE);
    countSelect.setText("0 users selected");
    counter = 0;
    selectCounts.clear();
  }
}
