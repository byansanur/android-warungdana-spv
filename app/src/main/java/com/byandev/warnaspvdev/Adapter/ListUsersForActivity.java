package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.MainFragment.FragmentTambahActivity.FragmentTambahActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespGetUserForActivity;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.byandev.warnaspvdev.Response.RespUsers2;

import java.util.ArrayList;
import java.util.List;

public class ListUsersForActivity extends RecyclerView.Adapter<ListUsersForActivity.ListHolder> {

  ArrayList<RespUsers2.Datus> users;
  Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  public ListUsersForActivity(Context context, List users) {
    this.context = context;
    this.users = (ArrayList<RespUsers2.Datus>) users;
  }

  @NonNull
  @Override
  public ListUsersForActivity.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    @SuppressLint("InflateParams")
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_activity, null);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    return new ListUsersForActivity.ListHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ListUsersForActivity.ListHolder holder, int position) {
    final RespUsers2.Datus list = users.get(position);
    holder.namaCfa.setText(list.getName());
    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_person_white_24dp);
    drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    holder.imgList.setBackground(drawable);

  }

  @Override
  public int getItemCount() {
    return users.size();
  }

  public class ListHolder extends RecyclerView.ViewHolder {

    TextView namaCfa;
    LinearLayout card;
    ImageView imgList;

    public ListHolder(@NonNull View itemView) {
      super(itemView);
      namaCfa = itemView.findViewById(R.id.namaCfa);
      imgList = itemView.findViewById(R.id.imageThumbnail);
      card = itemView.findViewById(R.id.llItem);
    }

  }
}
