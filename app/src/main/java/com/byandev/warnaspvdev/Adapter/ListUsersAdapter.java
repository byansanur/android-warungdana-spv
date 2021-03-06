package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beloo.widget.chipslayoutmanager.layouter.Item;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.byandev.warnaspvdev.Response.RespUsers.RespListUsers;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ListUsersAdapter extends
        RecyclerView.Adapter<ListUsersAdapter.ListUserHolder>{

    ArrayList<RespUsers.RespListUsers> users, selected;
    Context context;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;
    boolean [] itemCheck;

    public ListUsersAdapter(Context context, List users) {
        this.context = context;
        this.users = (ArrayList<RespListUsers>) users;
        itemCheck = new boolean[users.size()];
        this.selected = new ArrayList<>();
    }

//    public void setUsers(ArrayList<RespUsers.RespListUsers> userss){
//      this.users = new ArrayList<>();
//      this.users = userss;
//      notifyDataSetChanged();
//    }

    @Override
    public ListUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_activity, null);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(context);
        return new ListUserHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ListUserHolder holder, final int position) {

        final RespUsers.RespListUsers listUsers = users.get(position);
//        holder.npm.setText(listUsers.getNpm());
        holder.namaNpm.setText(listUsers.getName());
        holder.privilegesN.setText(listUsers.getPrivilegesName());
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_person_white_24dp);
        holder.imgList.setBackground(drawable);
        if (itemCheck[position]) {
          highlightView(holder);
        }
        holder.llItem.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (!users.isEmpty()) {
              holder.llItem.setBackgroundColor(0xFFFFFFFF);
              itemCheck[position]=false;
            } else {
              holder.llItem.setBackgroundColor(Color.BLUE);
              itemCheck[position]=true;
            }
          }
        });
//
        holder.llItem.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
//            Intent a = new Intent(context, TambahJadwalActivity.class);
//            a.putExtra("idUser", listUsers.getId());
//            a.putExtra("name", listUsers.getName());
//            context.startActivity(a);
          }
        });
//        holder.llItem.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            Intent a = new Intent(context, TambahJadwalActivity.class);
//            a.putExtra("id", listUsers.getId());
//            Toast.makeText(context,"id", Toast.LENGTH_SHORT).show();
//          }
//        });

      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (selected.contains(listUsers)) {
            selected.remove(listUsers);
            unhighlightView(holder);
          } else {
            selected.add(listUsers);
            highlightView(holder);
//            if (itemsArray.get(position) == users) {
//              itemsArray.put(position, users);
//            }
            Toast.makeText(context, "id users pilih : " + listUsers.getId() +" "+ listUsers.getName(), Toast.LENGTH_LONG).show();
          }
          holder.llItem.setBackgroundResource(R.color.white_transparency);

        }
      });
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (selected.contains(listUsers)) {
              selected.remove(listUsers);
              unhighlightView(holder);
            } else {
              selected.add(listUsers);
              highlightView(holder);
            }
          }
        });

        if (selected.contains(listUsers))
          highlightView(holder);
        else unhighlightView(holder);
    }
    private void highlightView(ListUserHolder holder) {
      holder.imgList.setImageResource(R.drawable.ic_check_blue_24dp);
      holder.llItem.setBackgroundColor(ContextCompat.getColor(context, R.color.textSubColor));

    }

    private void unhighlightView(ListUserHolder holder) {
      holder.imgList.setImageResource(R.drawable.oval);
      holder.llItem.setBackgroundColor(ContextCompat.getColor(context, R.color.white_transparency));
    }

    public void addAll(List<RespUsers.RespListUsers> users) {
      clearAll(false);
      this.users = (ArrayList<RespListUsers>) users;
      notifyDataSetChanged();
    }

    public void clearAll(boolean isNotify) {
      users.clear();
      selected.clear();
      if (isNotify) notifyDataSetChanged();
    }

    public void clearSelected() {
      selected.clear();
      notifyDataSetChanged();
    }

    public void selectAll() {
      selected.clear();
      selected.addAll(users);
      notifyDataSetChanged();
    }

    public List<RespListUsers> getSelected() {
      return selected;
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ListUserHolder extends RecyclerView.ViewHolder {

        TextView namaNpm, npm, privilegesN;
        ImageView imgList;
        LinearLayout llItem;

        public ListUserHolder(@NonNull View itemView) {
            super(itemView);
//            npm = itemView.findViewById(R.id.idCfa);
            namaNpm = itemView.findViewById(R.id.namaCfa);
            privilegesN = itemView.findViewById(R.id.privilegesN);
            imgList = itemView.findViewById(R.id.imageThumbnail);
            llItem = itemView.findViewById(R.id.llItem);
        }
    }
}
