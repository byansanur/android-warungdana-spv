package com.byandev.warnaspvdev.MainActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.byandev.warnaspvdev.AlaramManager.BootCompletedIntentReceiver;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.Login;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText npm, password;
    TextView link;
    CheckBox eula;
    ProgressDialog loading;
    Context mContext;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;
    Integer  uID, roleId, outletId, branchId;
    String strNpm, strPass;
    String Npm, Nama, roleName, outletName, branchName;
    TextInputLayout inputPassword;
    LinearLayout lllogin;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_login);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        btnLogin = findViewById(R.id.btnLogin);
        npm = findViewById(R.id.loginNpm);
        password = findViewById(R.id.loginPassword);
        link = findViewById(R.id.llink);
        eula = findViewById(R.id.cbEula);

        lllogin = findViewById(R.id.lllogin);

        inputPassword = findViewById(R.id.inputPassword);

    }

    @Override
    protected void onStart() {
        super.onStart();
        link.setText(Html.fromHtml("<a href=https://lmu2018.github.io/WarungDana/> Syarat dan Ketentuan"));
        link.setMovementMethod(LinkMovementMethod.getInstance());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(npm.getText())) {
                    npm.setError("Required");
                } else if (TextUtils.isEmpty(password.getText())){
                    password.setError("Required");
                } else if (!eula.isChecked()) {
                    Toast toast = Toast.makeText(mContext, "Ceklist syarat & Ketentuan",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                } else {
                    loading = ProgressDialog.show(mContext, null, "Wait for the process", true,false);
                    strNpm = npm.getText().toString();
                    strPass = password.getText().toString();
                    loading.show();
                    requestLogin();
                }
            }
        });
        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    private void requestLogin() {
        mApiService.loginRequest(npm.getText().toString(),password.getText().toString())
                .enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    if (response.body().getApiStatus() == 1) {
                        loading.dismiss();
                        Integer userId = response.body().getData().getId();
                        uID = userId;
                        Nama = response.body().getData().getName();
                        roleName = response.body().getData().getPrivilegesName();
                        roleId = response.body().getData().getIdCmsPrivileges();
                        Npm = response.body().getData().getNpm();
                        outletId = response.body().getData().getIdMstOutlet();
                        outletName  = response.body().getData().getOutletName();
                        branchId = response.body().getData().getIdMstBranch();
                        branchName = response.body().getData().getBranchName();
                        Log.d("User Id:","" + userId);


                        sharedPrefManager.saveSPInt(SharedPrefManager.SP_ID, uID);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAME, Nama);
                        sharedPrefManager.saveSPInt(SharedPrefManager.SP_ROLES, roleId);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NPM, Npm);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLES_NAMES, roleName);
                        sharedPrefManager.saveSPInt(SharedPrefManager.SP_BRANCH_ID, branchId);
                        sharedPrefManager.saveSPInt(SharedPrefManager.SP_OUTLET_ID, outletId);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_BRANCH_NAME, branchName);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_OUTLET_NAME, outletName);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, response.body().getData().getToken());
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                        Log.d("Token login",sharedPrefManager.getSpToken());

//                        AutoLogout autoLogout = new AutoLogout();
//                        autoLogout.SaveAutoLogout();

                        startActivity(new Intent(mContext, HomeActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    } else {
                        loading.dismiss();
                        Toast.makeText(mContext, "Username paddword tidak valid", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Not Responding", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callAutoLogout() {
        Intent alarmIntent = new Intent(LoginActivity.this,
                BootCompletedIntentReceiver.class);
        alarmIntent.setAction("LogoutAction");
        Log.e("Method call", "AutoLogoutCall");
        alarmIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);

        Log.e("Logout", "Auto Logout set at..!" + calendar.getTime());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
