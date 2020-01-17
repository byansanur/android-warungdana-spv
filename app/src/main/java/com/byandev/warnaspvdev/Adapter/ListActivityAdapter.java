package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespActivityList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ActivityHolder> {

  private ArrayList<RespActivityList.DataActivity> activityLists;
  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  public ListActivityAdapter(Context context, List<RespActivityList.DataActivity> activity ) {
    this.context = context;
    this.activityLists = (ArrayList<RespActivityList.DataActivity>) activity;
//    this.dataUsers = (ArrayList<RespActivityList.DataUser>) users;
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
    holder.type.setText(dataActivity.getType());
    holder.location.setText(dataActivity.getLocation());
    holder.note.setText(dataActivity.getNote());

//    holder.actyivitylistuser.addAll(dataActivity.getDataUser());
//    holder.adapter = new ListActivityUserAdapter(holder.actyivitylistuser,context);
//    holder.recyclerView.setAdapter(holder.adapter);

    for (RespActivityList.DataUser i : dataActivity.getDataUser()){
      if (holder.nameCFA != null){
        holder.nameCFA = holder.nameCFA + ", "+i.getNameUser();
      }else{
        holder.nameCFA = i.getNameUser();
      }
    }

    holder.namaCfaa.setText(holder.nameCFA);

    holder.date.setText(String.valueOf(dataActivity.getStartDate()));
//    holder.namaCfa.setText(dataActivity.getDataUser().get(position).getNameUser());
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
    TextView type, date, location, note, namaCfaa;
    Button btnDetail;
//    RecyclerView recyclerView;
//    ListActivityUserAdapter adapter;
//    ArrayList<RespActivityList.DataUser> actyivitylistuser = new ArrayList<>();
    String nameCFA;

//    LinearLayout llCfa;
    public ActivityHolder(@NonNull View itemView) {
      super(itemView);
      type = itemView.findViewById(R.id.typeActivity);
      date = itemView.findViewById(R.id.createdAt);
      location = itemView.findViewById(R.id.tvLocationActivity);
      namaCfaa = itemView.findViewById(R.id.tvCfaActivity);
      note = itemView.findViewById(R.id.tvDescription);
      btnDetail = itemView.findViewById(R.id.detailBtn);
//      recyclerView = itemView.findViewById(R.id.recyclerViewCFA);
//
//      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//      recyclerView.setLayoutManager(linearLayoutManager);
//      recyclerView.setHasFixedSize(true);


    }
  }
}
