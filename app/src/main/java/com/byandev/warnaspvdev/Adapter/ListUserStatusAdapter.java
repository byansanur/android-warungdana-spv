package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.MainActivity.DetailUserActivity;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUserStatus;

import java.util.ArrayList;
import java.util.List;

public class ListUserStatusAdapter extends
        RecyclerView.Adapter<ListUserStatusAdapter.ListUserStatusHolder> {

    ArrayList<RespUserStatus.ListUserStatus> userStatuses;
    Context context;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;

    public ListUserStatusAdapter(Context context, List userStatus) {
        this.context = context;
        this.userStatuses = (ArrayList<RespUserStatus.ListUserStatus>) userStatus;
    }

    @NonNull
    @Override
    public ListUserStatusAdapter.ListUserStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_status, null);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(context);
        return new ListUserStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserStatusAdapter.ListUserStatusHolder holder, int position) {
        final RespUserStatus.ListUserStatus listUserStatus = userStatuses.get(position);

        if (listUserStatus.getOutletName().equals("")) {
            holder.branchName.setText(listUserStatus.getBranchName());
        } else  {
            holder.branchName.setText(listUserStatus.getBranchName());
        }
        if (listUserStatus.getOutletName().equals("")) {
            holder.outletName.setText(listUserStatus.getOutletName());
        } else {
            holder.outletName.setText(listUserStatus.getOutletName());
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailUserActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userStatuses.size();
    }

    public class ListUserStatusHolder extends RecyclerView.ViewHolder {

        TextView branchName, outletName;
        RelativeLayout card;

        public ListUserStatusHolder(@NonNull View itemView) {
            super(itemView);
            branchName = itemView.findViewById(R.id.tvItemNamaBranch);
            outletName = itemView.findViewById(R.id.tvItemNamaOutlet);
            card = itemView.findViewById(R.id.layoutList);

        }
    }
}
