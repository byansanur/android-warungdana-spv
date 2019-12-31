package com.byandev.warnaspvdev.MainActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TambahJadwalTemplateActivity extends AppCompatActivity {

  Context context;
  SharedPrefManager sharedPrefManager;
  ApiEndPoint mApiService;

  ImageView backButton;
  FloatingActionButton addActivity;
//  Toolbar addTemplate;
  RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle s) {
    super.onCreate(s);
    setContentView(R.layout.tambah_jadwal_template_activity);

    context = getApplicationContext();
    sharedPrefManager = new SharedPrefManager(context);
    mApiService = UtilsApi.getAPIService();

    backButton = findViewById(R.id.backButton);
//    addTemplate = findViewById(R.id.addTemplate);
    addActivity = findViewById(R.id.addActivity);
    recyclerView = findViewById(R.id.recyclerView);

    onClicks();
  }

  private void onClicks() {
    addActivity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, TambahJadwalActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
      }
    });
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, TambahJadwalActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
      }
    });
  }

  @Override
  public void onBackPressed(){
    super.onBackPressed();
    Intent i = new Intent(context, TambahJadwalActivity.class);
    startActivity(i);
    finish();
  }
}
