package com.byandev.warnaspvdev.MainFragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.LoginActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUsers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements
    androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {

  Context context;
  Integer idUser;
  SharedPrefManager sharedPrefManager;
  ApiEndPoint mApiService;

  private androidx.appcompat.widget.Toolbar toolbar;
  TextView name, npm, branch, outlet, eula, theme, password;
  String namex, npmx, branchx, outletx;
  public ProfileFragment(){}

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
    View view = inflater.inflate(R.layout.profile_fragment, container, false);
    context = getContext();
    sharedPrefManager = new SharedPrefManager(getContext());
    mApiService = UtilsApi.getAPIService();
    toolbar = view.findViewById(R.id.toolbar);
    toolbar.setTitle("Profile");
    toolbar.setOnMenuItemClickListener(this);
    idUser = sharedPrefManager.getSpId();

    name = view.findViewById(R.id.spName);
    npm = view.findViewById(R.id.spNpm);
    branch = view.findViewById(R.id.spBranch);
    outlet = view.findViewById(R.id.spOutlet);
    eula = view.findViewById(R.id.tvAbout);
    theme = view.findViewById(R.id.tvTheme);
    password = view.findViewById(R.id.tvGantiPassword);

    initProfile();
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    dataUser();
  }

  private void dataUser() {
    name.setText(namex);
    npm.setText(npmx);
    branch.setText(branchx);
    outlet.setText(outletx);
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  private void initProfile() {
    final ProgressDialog progressDialog = new ProgressDialog(getContext());
    progressDialog.dismiss();
    mApiService.datauser(idUser).enqueue(new Callback<RespUser>() {
      @Override
      public void onResponse(Call<RespUser> call, Response<RespUser> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            try {
              namex = response.body().getData().getName();
              npmx = response.body().getData().getNpm();
              branchx = response.body().getData().getBranchName();
              outletx = response.body().getData().getOutletName();
            } catch (Exception e) {
              e.printStackTrace();
            }
            dataUser();
          }
        }
      }

      @Override
      public void onFailure(Call<RespUser> call, Throwable t) {
        progressDialog.show();
      }
    });
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {

    if (item.getItemId() == R.id.action_signOut) {
      AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
      ad.setMessage("Do you want to sign out ?").setCancelable(true)
          .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              sharedPrefManager.clearSharedPreferences();
              startActivity(new Intent(getContext(), LoginActivity.class)
                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
              getActivity().finish();
            }
          })
          .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
            }
          }).show();
      return true;
    }
    return false;
  }
}
