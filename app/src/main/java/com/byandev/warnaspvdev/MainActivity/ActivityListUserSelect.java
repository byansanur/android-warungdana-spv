package com.byandev.warnaspvdev.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainFragment.FragmentTambahActivity.FragmentListUsersAdd;
import com.byandev.warnaspvdev.R;

public class ActivityListUserSelect extends AppCompatActivity {

  private ApiEndPoint mApiService;
  Context context;
  SharedPrefManager sharedPrefManager;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_user_select);
    context = getApplicationContext();
    sharedPrefManager = new SharedPrefManager(context);
    mApiService = UtilsApi.getAPIService();

    loadFragment(new FragmentListUsersAdd());
  }

  private void loadFragment(Fragment fragment) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.frame_container,fragment);
    ft.addToBackStack(null);
    ft.commitAllowingStateLoss();
  }


  @Override
  public void onBackPressed(){
    Intent i = new Intent(context, TambahJadwalActivity.class);
    startActivity(i);
    finish();
  }

}
