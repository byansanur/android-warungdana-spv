package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUsers;

import java.util.ArrayList;

public class AdapterUserSelect extends RecyclerView.Adapter<AdapterUserSelect.Holder> {

  private ArrayList<RespUsers.RespListUsers> userList = new ArrayList<>();
  private ArrayList<RespUsers.RespListUsers> selectedUsers = new ArrayList<>();
  private Context context;
  private SparseBooleanArray select;

  public AdapterUserSelect(Context context, ArrayList<RespUsers.RespListUsers> userList, ArrayList<RespUsers.RespListUsers> selectedUsers) {
    this.userList = userList;
    this.selectedUsers = selectedUsers;
    this.context = context;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_activity, null);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    final RespUsers.RespListUsers listUsers = userList.get(position);
    holder.namaNpm.setText(listUsers.getName());
//    holder.npm.setText(listUsers.getNpm());
    holder.privilegesN.setText(listUsers.getPrivilegesName());
    if (selectedUsers.contains(userList.get(position)))
      holder.llItem.setBackgroundColor(ContextCompat.getColor(context, R.color.pencarianText2));
    else
      holder.llItem.setBackgroundColor(ContextCompat.getColor(context, R.color.pencarianText));

  }

  @Override
  public int getItemCount() {
    return userList.size();
  }

  public class Holder extends RecyclerView.ViewHolder {
    TextView namaNpm, npm, privilegesN;
    ImageView imgList;
    LinearLayout llItem;
    public Holder(@NonNull View itemView) {
      super(itemView);
//      npm = itemView.findViewById(R.id.idCfa);
      namaNpm = itemView.findViewById(R.id.namaCfa);
      privilegesN = itemView.findViewById(R.id.privilegesN);
      imgList = itemView.findViewById(R.id.imageThumbnail);
      llItem = itemView.findViewById(R.id.llItem);
    }
  }
}
