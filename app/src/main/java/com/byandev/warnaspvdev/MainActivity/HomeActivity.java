package com.byandev.warnaspvdev.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arthurivanets.bottomsheets.BottomSheet;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.BottomSheet.BsHome;
import com.byandev.warnaspvdev.MainFragment.AktivitasFragment;
import com.byandev.warnaspvdev.MainFragment.ProfileFragment;
import com.byandev.warnaspvdev.MainFragment.HomeFragment;
import com.byandev.warnaspvdev.MainFragment.OrderFragment;
import com.byandev.warnaspvdev.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Context context;
    private ApiEndPoint mApiService;
    int uID;
    private Boolean logoutcheck = false;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    SharedPrefManager sharedPrefManager;
    private Handler handler = new Handler();
    private Integer intVersion = 0, xmlVersion;
    private BottomNavigationView navigation;
    private Runnable r;
//    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme);
        } else setTheme(R.style.AppTheme);

        context = this;
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        mApiService = UtilsApi.getAPIService();
        uID = sharedPrefManager.getSpId();

        xmlVersion = Integer.parseInt(getResources().getString(R.string.version));

//        fab = findViewById(R.id.fabMenu);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());

        checkAndRequestPermissions();

    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!logoutcheck) {
            checkLogOutSystem();
        } else {
            handler.removeCallbacks(r);
        }
    }

    private void checkLogOutSystem(){
//        final AutoLogout autoLogout = new AutoLogout();
//
//        boolean checkLogout = autoLogout.CheckLogout();
//
//        if (checkLogout) {
//            autoLogout.logOutNow(getApplicationContext());
//        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        logoutcheck = false;
        handler.removeCallbacks(r);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (navigation.getSelectedItemId()!=R.id.navigation_home){
            navigation.setSelectedItemId(R.id.navigation_home);
        }else {
            logoutcheck = false;
            handler.removeCallbacks(r);
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        logoutcheck = false;
        handler.removeCallbacks(r);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint("RestrictedApi")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
//                    fab.setVisibility(View.GONE);
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_jadwal:
//                    fab.setVisibility(View.VISIBLE);
//                    fab.setImageDrawable(getDrawable(R.drawable.ic_add_black_24dp));
//                    fab.setBackgroundColor(getColor(R.color.fab));
                    fragment = new AktivitasFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_order:
//                    fab.setVisibility(View.GONE);
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    return true;
//                case R.id.navigation_profile:
//                    fragment = new ProfileFragment();
//                    loadFragment(fragment);
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container,fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

//    private void transparentStatusAndNavigation() {
//        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
//            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            );
//        }
//        if (Build.VERSION.SDK_INT >= 21 ) {
//            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//    }
//
//    private void setWindowFlag(final int bits, boolean on) {
//        Window win = getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }

    private boolean checkAndRequestPermissions() {
        int telpon = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int location = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int storageRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (telpon != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (location != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (storageRead != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
