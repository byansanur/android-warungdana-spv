package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.EditOrderActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderDetail;

import java.util.ArrayList;

public class ListOrderEditAdapter extends RecyclerView.Adapter<ListOrderEditAdapter.Holder> {
  ArrayList<RespOrderDetail.DataOrderDetail> list;
  Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  public ListOrderEditAdapter(){

  }

  @NonNull
  @Override
  public ListOrderEditAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_fragment, parent, false);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ListOrderEditAdapter.Holder holder, int position) {
    final RespOrderDetail.DataOrderDetail lists = list.get(position);
    holder.editIcon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        holder.editIcon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(context, EditOrderActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("id", lists.getId());
            i.putExtra("idOrderMstStatus", lists.getIdOrderMstStatus());
            context.startActivity(i);
          }
        });
//        BsEditOrder bsEditOrder = new BsEditOrder();
//        Bundle bundle = new Bundle();
//        bundle.putInt("id", lists.getId());
//        bundle.putString("status", lists.getStatus() + lists.getIdOrderMstStatus());
//        bundle.putInt("idOrderStatus", lists.getIdOrderMstStatus());
//        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//        bsEditOrder.setArguments(bundle);
//        bsEditOrder.show(fragmentManager,bsEditOrder.getTag());
      }
    });
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class Holder extends RecyclerView.ViewHolder {
    ImageView editIcon;
    public Holder(@NonNull View itemView) {
      super(itemView);
      editIcon = itemView.findViewById(R.id.editIcon);
    }
  }
}
