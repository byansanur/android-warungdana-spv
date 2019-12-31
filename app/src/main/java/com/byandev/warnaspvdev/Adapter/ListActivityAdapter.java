package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespListActivity;
import com.byandev.warnaspvdev.Response.RespOrderDetail;
import com.byandev.warnaspvdev.Response.RespOrderStatus;

import java.util.ArrayList;
import java.util.List;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ActivityHolder> {
  ArrayList<RespListActivity.ActivityList> lists;
  Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  public ListActivityAdapter(Context context, List activity) {
    this.context = context;
    this.lists = (ArrayList<RespListActivity.ActivityList>) activity;
  }

  @NonNull
  @Override
  public ListActivityAdapter.ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_list, parent, false);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    return new ListActivityAdapter.ActivityHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ListActivityAdapter.ActivityHolder holder, int position) {
    final RespListActivity.ActivityList list = lists.get(position);
    holder.tvTypeActivity.setText(list.getType());
    holder.tvLocationActivity.setText(list.getLocation());
    holder.tvUsersActivity.setText(list.getName());
    holder.tvItemStatus.setText(list.getStatus());
    holder.rlBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // ke detail activity
      }
    });
  }

  @Override
  public int getItemCount() {
    return lists.size();
  }

  public class ActivityHolder extends RecyclerView.ViewHolder {

    TextView tvTypeActivity, tvUsersActivity, tvLocationActivity, tvItemStatus;
    RelativeLayout rlBtn;

    public ActivityHolder(@NonNull View itemView) {
      super(itemView);
      tvItemStatus = itemView.findViewById(R.id.tvItemStatus);
      tvLocationActivity = itemView.findViewById(R.id.tvLocationActivity);
      tvUsersActivity = itemView.findViewById(R.id.tvUsersActivity);
      tvTypeActivity = itemView.findViewById(R.id.tvTypeActivity);
      rlBtn = itemView.findViewById(R.id.rlBtn);
    }
  }
}
