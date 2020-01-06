package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespActivityList;

import java.util.ArrayList;
import java.util.List;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ActivityHolder> {
  private ArrayList<RespActivityList.DataActivity> activityLists;
//  private ArrayList<RespActivityList.DataUser> usersList;
  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  public ListActivityAdapter(Context context, List<RespActivityList.DataActivity> activity) {
    this.context = context;
    this.activityLists = (ArrayList<RespActivityList.DataActivity>) activity;
//    this.usersList = (ArrayList<RespActivityList.DataUser>) users;
  }

  @NonNull
  @Override
  public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    @SuppressLint("InflateParams")
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activitys, null);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    return new ActivityHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ListActivityAdapter.ActivityHolder holder, int position) {
    final RespActivityList.DataActivity dataActivity = activityLists.get(position);
//    final  RespActivityList.DataUser dataUsers = usersList.get(position);

    holder.type.setText(dataActivity.getType());
    holder.location.setText(dataActivity.getLocation());
    holder.note.setText(dataActivity.getNote());

//    holder.date.setText(dataUsers.getCreatedAt());
//    holder.cfaa.setText(dataUsers.getNameUser());

    holder.btnDetail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // ke detail activity
      }
    });
  }

  @Override
  public int getItemCount() {
    return activityLists.size();
  }

  public class ActivityHolder extends RecyclerView.ViewHolder {
    TextView type, date, location, cfaa, note;
    ImageView btnDetail;
    public ActivityHolder(@NonNull View itemView) {
      super(itemView);
      type = itemView.findViewById(R.id.typeActivity);
      date = itemView.findViewById(R.id.createdAt);
      location = itemView.findViewById(R.id.tvLocationActivity);
      cfaa = itemView.findViewById(R.id.tvCfaActivity);
      note = itemView.findViewById(R.id.tvDescription);
      btnDetail = itemView.findViewById(R.id.detailBtn);
    }
  }
}
