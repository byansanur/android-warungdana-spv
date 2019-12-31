package com.byandev.warnaspvdev.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.byandev.warnaspvdev.MainFragment.FragmentOrder.DetailOrder.OrderFragmentDetail;
import com.byandev.warnaspvdev.R;

/**
 * Created by Ratbyansa 2019
 */

public class DetailOrdersActivity extends AppCompatActivity {

    /**
     * initialize key "name" putExtra
     * and getter this activity
     */
    private Integer id;
    public Integer getId() {
        return id;
    }

    private Integer idMstorder;
    public Integer getIdMstorder() {
        return idMstorder;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_orders);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        loadFragment(new OrderFragmentDetail());

        /**
         * deklarasi key dengan dengan method getIntent().getIntentExtra + key + defaultValue 0
         */
        id = getIntent().getIntExtra("id", 0);
        idMstorder = getIntent().getIntExtra("idMstOrder", 0);

    }



    public void afterUpdate(){

        Toast.makeText(getApplicationContext(),"Berhasil update",Toast.LENGTH_SHORT).show();
        loadFragment(new OrderFragmentDetail());


    }
    private void loadFragment(OrderFragmentDetail fragment) {
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
