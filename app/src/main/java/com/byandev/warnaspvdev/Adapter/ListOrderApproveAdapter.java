package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.BottomSheet.BsPreviewOrderFragment;
import com.byandev.warnaspvdev.MainActivity.DetailOrdersActivity;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListOrderApproveAdapter extends RecyclerView.Adapter<ListOrderApproveAdapter.ListUserHolder>{

    ArrayList<RespOrderStatus.ListOrderStatus> orderStatuses;
    Context context;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;

    public ListOrderApproveAdapter(Context context, List order) {
        this.context = context;
        this.orderStatuses = (ArrayList<RespOrderStatus.ListOrderStatus>) order;
    }

    @NonNull
    @Override
    public ListUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_orders, parent, false);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(context);
        return new ListUserHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListUserHolder holder, int position) {

        final RespOrderStatus.ListOrderStatus orderStatus = orderStatuses.get(position);

        if (orderStatus.getFirstName().equals("") && orderStatus.getLastName().equals("")) {
            holder.namaDepan.setText(orderStatus.getFirstName());
        } else {
            holder.namaDepan.setText(orderStatus.getFirstName() + " " + orderStatus.getLastName());
        }
        if (orderStatus.getMerk().equals("") && orderStatus.getType().equals("")) {
            holder.merekType.setText(orderStatus.getMerk());
        } else {
            holder.merekType.setText(orderStatus.getMerk() + " " + orderStatus.getType());
        }
        if (orderStatus.getCreatedAt().equals("")) {
            holder.tanggal.setText(orderStatus.getCreatedAt());
        } else {
            holder.tanggal.setText(convertTime(orderStatus.getCreatedAt()));
        }
        holder.status.setText(orderStatus.getStatus());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BsPreviewOrderFragment bs = new BsPreviewOrderFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", orderStatus.getId());
//                bundle.putString("nama", orderStatus.getFirstName() +" "+ orderStatus.getLastName());
//                bundle.putString("tanggal", orderStatus.getCreatedAt());
//                bundle.putString("status", orderStatus.getStatus() + orderStatus.getIdOrderMstStatus());
//                bundle.putString("reason", orderStatus.getIdOrderMstReason());
//                bundle.putInt("idOrderStatus", orderStatus.getIdOrderMstStatus());
//                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
//                bs.setArguments(bundle);
//                bs.show(fm,bs.getTag());
                Intent a = new Intent(context, DetailOrdersActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent dengan mengirim id order di teruskan kadalam activity detail orders
                a.putExtra("id", orderStatus.getId());
                a.putExtra("idMstOrder", orderStatus.getIdOrderMstStatus());
                context.startActivity(a);
            }
        });



    }

    @Override
    public int getItemCount() {
        return orderStatuses.size();
    }



    public class ListUserHolder extends RecyclerView.ViewHolder {

        TextView namaDepan, merekType, status, tanggal;
        RelativeLayout card;

        public ListUserHolder(@NonNull View itemView) {
            super(itemView);
            namaDepan = itemView.findViewById(R.id.tvItemNamaDepan);
            merekType = itemView.findViewById(R.id.tvMerekType);
            status = itemView.findViewById(R.id.tvItemStatus);
            tanggal = itemView.findViewById(R.id.tvItemTanggal);
            card = itemView.findViewById(R.id.layoutItemCustomer);
        }
    }

    private String convertTime(String time) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd MMM, yyyy");
        java.util.Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String convertedDate = time;
        try {
            convertedDate = format1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
}
