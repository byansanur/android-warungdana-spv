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

public class ListOrderNew extends RecyclerView.Adapter<ListOrderNew.ListOrderNewHolder> {

    /**
     * @initialize ArrayList for models/response class
     * @initialize Context for this class
     * @call ApiService in this, and SharedPrefManager for the save data shortly
     */

    ArrayList<RespOrderStatus.ListOrderStatus> listOrderStatuses;
    Context context;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;

    public ListOrderNew(Context context, List order) {
        this.context = context;
        this.listOrderStatuses = (ArrayList<RespOrderStatus.ListOrderStatus>) order;
    }


    /**
     * @see public ListOrderNew.ListOrderNewHolder onCreateViewHolder(ViewGroup, int)
     * @description Metode ini disebut benar saat adaptor dibuat dan digunakan untuk menginisialisasi ViewHolder Anda.
     */

    @NonNull
    @Override
    public ListOrderNew.ListOrderNewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // definisi view LayoutInflater mengarah ke layout item_list_orders
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_orders, parent, false);
        // definisi mApiService memanggil java class UtilsApi.java
        // dan memanggil class getApiService();
        mApiService = UtilsApi.getAPIService();
        // digunakan untuk menyimpan data ke sharedPrefManager khusus untuk class ini.
        sharedPrefManager = new SharedPrefManager(context);
        // mengembalikan view adapter
        return new ListOrderNew.ListOrderNewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ListOrderNew.ListOrderNewHolder holder, int position) {
        final RespOrderStatus.ListOrderStatus orderStatus = listOrderStatuses.get(position);

        // logic create
        // orderStatus didapat dari class model / package response dan disederhanakan menjadi orderStatus
        // jika orderStatus memanggil firstName itu kosong dan lastName kosong.
        // maka definisi textView untuk menSetText sesuai textView

        if (orderStatus.getFirstName().equals("") && orderStatus.getLastName().equals("")) {
            holder.namaDepan.setText(orderStatus.getFirstName());
        } else {
            // karena ada first name dan last name maka menggunakan annotation + untuk menggabung dalam 1 textView
            // jika benar data yang ada firstName & lastName maka akan menjalankan fungsi ini.
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
            // penggunaan converTime disini untuk mengkonvert time dari getCreatedAt
            holder.tanggal.setText(convertTime(orderStatus.getCreatedAt()));
        }
        holder.status.setText(orderStatus.getStatus());
        holder.card.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v for the onClick the card, dan memanggil activity lain dari class ini.
             */
            @Override
            public void onClick(View v) {
//                BsPreviewOrderFragment bs = new BsPreviewOrderFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", orderStatus.getId());
//                bundle.putString("nama", orderStatus.getFirstName() +" "+ orderStatus.getLastName());
//                bundle.putString("tanggal", orderStatus.getCreatedAt());
//                bundle.putString("status", orderStatus.getStatus() + orderStatus.getIdOrderMstStatus());
//                bundle.putString("reason", orderStatus.getIdOrderMstReason());
//                bundle.putInt("idOrder", orderStatus.getIdOrderMstStatus());
//                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
//                bs.setArguments(bundle);
//                bs.show(fm,bs.getTag());
                Intent a = new Intent(context, DetailOrdersActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                 intent dengan mengirim id order di teruskan kadalam activity detail orders
//                 idOrder didapat dari model "orderStatus"
                a.putExtra("id", orderStatus.getId());
                a.putExtra("idMstOrder", orderStatus.getIdOrderMstStatus());
                context.startActivity(a);
            }
        });

    }

    /**
     * @return ArrayList the size
     */
    @Override
    public int getItemCount() {
        return listOrderStatuses.size();
    }


    /**
     * @class list recyclerView
     */
    public class ListOrderNewHolder extends RecyclerView.ViewHolder {

        // class holder untuk definisi variable yang ada pada layout item_list_orders

        TextView namaDepan, merekType, status, tanggal;
        RelativeLayout card;
        ListOrderNewHolder(@NonNull View itemView) {
            super(itemView);
            namaDepan = itemView.findViewById(R.id.tvItemNamaDepan);
            merekType = itemView.findViewById(R.id.tvMerekType);
            status = itemView.findViewById(R.id.tvItemStatus);
            tanggal = itemView.findViewById(R.id.tvItemTanggal);
            card = itemView.findViewById(R.id.layoutItemCustomer);
        }
    }

    /**
     * @initialize simpleDateFormat
     * @param time for the convert time from java.util.date
     * @return convertDate
     */
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
