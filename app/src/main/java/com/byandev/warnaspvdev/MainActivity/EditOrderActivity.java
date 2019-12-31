package com.byandev.warnaspvdev.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.DetailOrder.OrderFragmentDetail;
import com.byandev.warnaspvdev.MainFragment.FragmentOrder.EditOrder.FragmentEdit;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderDealsStatus;
import com.byandev.warnaspvdev.Response.RespOrderDetail;
import com.byandev.warnaspvdev.Response.RespOrderStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditOrderActivity extends AppCompatActivity {

    private Integer id;
    public Integer getId() {
        return id;
    }

    private Integer idMstOrder;
    public Integer getIdMstOrder() {
        return idMstOrder;
    }

    Context context;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;


//    public static void newInstance(Context context, RespOrderDetail data) {
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(TAG+".data", (Parcelable) data);
//        Intent intent = new Intent(context, EditOrderActivity.class);
//        intent.putExtra(bundle);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme);
        } else setTheme(R.style.AppTheme);
        context = this;
        sharedPrefManager = new SharedPrefManager(context);
        mApiService = UtilsApi.getAPIService();
        id = getIntent().getIntExtra("id", 0);
        idMstOrder = getIntent().getIntExtra("idMstorder", 0);
        loadFragment(new FragmentEdit());
    }

    private void loadFragment(FragmentEdit fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
