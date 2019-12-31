package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespLeadUsers;
import com.byandev.warnaspvdev.Response.RespOutletPerformance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListLeadUsersAdapter extends RecyclerView.Adapter<ListLeadUsersAdapter.leadUsersHolder> {

    private ArrayList<RespLeadUsers.DataListLeadUsers> leadUsers;
    private Context context;
    ApiEndPoint mApiService;
    private SharedPrefManager sharedPrefManager;

    public ListLeadUsersAdapter(Context cont, List<RespLeadUsers.DataListLeadUsers> outlet) {
        this.context = cont;
        this.leadUsers = (ArrayList<RespLeadUsers.DataListLeadUsers>) outlet;
    }

    @NonNull
    @Override
    public leadUsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kpi_outlet, null);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(context);
        return new leadUsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListLeadUsersAdapter.leadUsersHolder holder, int position) {
        final RespLeadUsers.DataListLeadUsers leadUser = leadUsers.get(position);

        if (leadUser.getLead().equals("")) {
            holder.leadsCount.setText(String.valueOf(leadUser.getLead()));
        } else holder.leadsCount.setText(String.valueOf(leadUser.getLead()));
        if (leadUser.getNew().equals("")) {
            holder.bookingCount.setText(String.valueOf(leadUser.getNew()));
        } else holder.bookingCount.setText(String.valueOf(leadUser.getNew()));
        holder.namaOutlet.setText(leadUser.getOutletName());
        holder.namaCfa.setText(leadUser.getName() + " | " + leadUser.getNpm());
        holder.privileges.setText(leadUser.getPrivilegesName());
        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // detail.
            }
        });
//        if (leadUser.getBooking().equals("")) {
//            holder.booking_count.setText(String.valueOf(leadUser.getBooking()));
//        } else {
//            holder.booking_count.setText(String.valueOf(leadUser.getBooking()));
//        }
//        if (leadUser.getLead().equals("")){
//            holder.leads_count.setText(String.valueOf(leadUser.getLead()));
//        } else {
//            holder.leads_count.setText(String.valueOf(leadUser.getLead()));
//        }
//        if (leadUser.getMonth().equals("")) {
//            holder.bulan.setText(leadUser.getMonth());
//        } else {
//            holder.bulan.setText(convertTime(leadUser.getMonth()));
//        }
//        holder.namaOutlet.setText(sharedPrefManager.getSpBranchName());
    }

    @Override
    public int getItemCount() {
        return leadUsers.size();
    }

    public class leadUsersHolder extends RecyclerView.ViewHolder {
        TextView namaOutlet, namaCfa, privileges, leadsCount, bookingCount;
        CardView cardViewItem;
        public leadUsersHolder(@NonNull View itemView) {
            super(itemView);
            namaOutlet = itemView.findViewById(R.id.namaOutlet);
            namaCfa = itemView.findViewById(R.id.namaCfa);
            privileges = itemView.findViewById(R.id.privileges);
            leadsCount = itemView.findViewById(R.id.leadsCount);
            bookingCount = itemView.findViewById(R.id.bookingCount);
            cardViewItem = itemView.findViewById(R.id.cardViewItem);

        }
    }

//    private String convertTime(String time) {
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd MMM, yyyy");
//        java.util.Date date = null;
//        try {
//            date = format.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String convertedDate = time;
//        try {
//            convertedDate = format1.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return convertedDate;
//    }

}
