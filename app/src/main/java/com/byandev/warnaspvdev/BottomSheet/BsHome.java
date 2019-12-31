package com.byandev.warnaspvdev.BottomSheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arthurivanets.bottomsheets.BaseBottomSheet;
import com.arthurivanets.bottomsheets.BottomSheet;
import com.arthurivanets.bottomsheets.config.BaseConfig;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.HomeActivity;
import com.byandev.warnaspvdev.MainActivity.LoginActivity;
import com.byandev.warnaspvdev.Menu.UbahPassword;
import com.byandev.warnaspvdev.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BsHome extends BottomSheetDialogFragment {

  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;
  LinearLayout ll1, ll2, ll3;

  public BsHome(){
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle s)
  {
    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
      getContext().setTheme(R.style.darkTheme);
    } else getContext().setTheme(R.style.AppTheme);
    View view = inflater.inflate(R.layout.bs_home, container, false);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(getContext());
    ll1 = view.findViewById(R.id.ll1);
    ll2 = view.findViewById(R.id.ll2);
    ll3 = view.findViewById(R.id.ll3);
    onClick();

    return view;
  }

  private void onClick() {
    // ll1 for the changes password
    ll1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getContext(), UbahPassword.class)
            .addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
    // ll2 for the change theme to dark mode
    ll2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
        ad.setMessage("Changes theme ?")
            .setCancelable(true)
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            })
            .setPositiveButton("Yes please!", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                startActivity(new Intent(getContext(), HomeActivity.class));
                getActivity().finish();
              }
            })
            .show();
      }
    });
    // ll3 for user logout
    ll3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());
        alertdialog.setMessage("Do you want to sign out ?").setCancelable(true)
            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                sharedPrefManager.clearSharedPreferences();
//                                mApiService.logout(userAgent, "Logout", userId,""
//                                        + BuildConfig.VERSION_NAME).enqueue(new Callback<ResponseBody>() {
//                                    @Override
//                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                    }
//                                });
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
              }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            })
            .show();
      }
    });
  }


//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.bs_home);
//  }
}
