package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ListActivityAdapterUser extends RecyclerView.Adapter<ListActivityAdapterUser.ActivityHolder> {

  private ArrayList<RespActivityList.DataUser> dataUsers;
  private Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  public ListActivityAdapterUser(Context context, List<RespActivityList.DataUser> userList) {
    this.context = context;
    this.dataUsers = (ArrayList<RespActivityList.DataUser>) userList;
  }

  @NonNull
  @Override
  public ListActivityAdapterUser.ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users_list_activity, null);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    return new ListActivityAdapterUser.ActivityHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ListActivityAdapterUser.ActivityHolder holder, int position) {
    final RespActivityList.DataUser data = dataUsers.get(position);
    holder.nama.setText(data.getNameUser().toCharArray().length);
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class ActivityHolder extends RecyclerView.ViewHolder {
    TextView nama;
    public ActivityHolder(@NonNull View itemView) {
      super(itemView);
      nama = itemView.findViewById(R.id.namaCfa);

    }
  }
}
