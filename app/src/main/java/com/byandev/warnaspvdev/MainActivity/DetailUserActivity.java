package com.byandev.warnaspvdev.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.R;

public class DetailUserActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ApiEndPoint mApiService;
    Context context;
    SharedPrefManager sharedPrefManager;
    public ProgressDialog loading;
//    private DetailLead detailLead = new DetailLead();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
    }
}
