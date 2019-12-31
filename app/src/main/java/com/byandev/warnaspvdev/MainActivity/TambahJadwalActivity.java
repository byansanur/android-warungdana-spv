package com.byandev.warnaspvdev.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.byandev.warnaspvdev.MainFragment.FragmentTambahActivity.FragmentTambahActivity;
import com.byandev.warnaspvdev.R;

public class TambahJadwalActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_tambah_jadwal);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        final Context context = getApplicationContext();

      TextView addTemplate = findViewById(R.id.addTemplate);
      addTemplate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          startActivity(new Intent(context, TambahJadwalTemplateActivity.class)
              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
          finish();
        }
      });

        ImageView back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
        loadFragment(new FragmentTambahActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        android.app.AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setMessage("Do you want to cancel adding activities?").setCancelable(true)
            .setPositiveButton("Yes i ido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent a = new Intent(TambahJadwalActivity.this, HomeActivity.class);
                    startActivity(a);
                    finish();
                }
            }).show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container,fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

}
