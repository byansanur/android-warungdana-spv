package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.BottomSheet.BsPreviewOrderFragment;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderDetail;
import com.byandev.warnaspvdev.Response.RespOrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListOrderAdapter2 extends RecyclerView.Adapter<ListOrderAdapter2.ListLeadHolder> {
    ArrayList<RespOrderDetail.DataOrderDetail> listOutlets;
    Context context;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;


    public ListOrderAdapter2(){
    }

    @Override
    public ListLeadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_order_home, parent, false);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(context);
        return new ListOrderAdapter2.ListLeadHolder(view);
    }

    private String convertTimeDay(String time) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
        java.util.Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String convertedDate = format1.format(date);
        return convertedDate;
    }



    @Override
    public void onBindViewHolder(final ListLeadHolder holder, int position) {
        final RespOrderDetail.DataOrderDetail list = listOutlets.get(position);

        if (list.getLastName()!=null){
            holder.konsumen.setText(list.getFirstName()+" "+list.getLastName());
        }else {
            holder.konsumen.setText(list.getFirstName());
        }

        holder.cfa.setText(list.getName());
        holder.outlet.setText(list.getDatasource());
        holder.tanggal.setText(convertTimeDay(list.getCreatedAt()));
        holder.status.setText(list.getStatus());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BsPreviewOrderFragment bsPreviewOrderFragment = new BsPreviewOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putString("konsumen", holder.konsumen.getText().toString());
                bundle.putInt("id", list.getId());
                bundle.putString("nama", list.getFirstName() +" "+ list.getLastName());
                bundle.putString("tanggal", list.getCreatedAt());
                bundle.putString("status", list.getStatus() + list.getIdOrderMstStatus());
                bundle.putInt("reason", list.getIdOrderMstReason());
                bundle.putInt("idOrderStatus", list.getIdOrderMstStatus());
                bundle.putString("tanggal", holder.tanggal.getText().toString());
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bsPreviewOrderFragment.setArguments(bundle);
                bsPreviewOrderFragment.show(fragmentManager,bsPreviewOrderFragment.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOutlets == null ? 0 :listOutlets.size();
    }


    public class ListLeadHolder extends RecyclerView.ViewHolder {
        TextView konsumen,cfa,outlet,tanggal,status;
        CardView cardView;

        public ListLeadHolder(View itemView) {
            super(itemView);
            konsumen = itemView.findViewById(R.id.konsumen);
            cfa = itemView.findViewById(R.id.cfa);
            outlet = itemView.findViewById(R.id.outlet);
            tanggal = itemView.findViewById(R.id.tanggal);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
