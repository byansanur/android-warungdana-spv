package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUsers;

import java.util.List;

public class AdapterSelectUser extends RecyclerView.Adapter<AdapterSelectUser.Holder> {

  private List<RespUsers.RespListUsers> users;

  private SparseBooleanArray selected;

  private Context context;

  AdapterSelectUser(List<RespUsers.RespListUsers> model) {
    if (model == null) {
      throw new IllegalArgumentException("modelData must not be null");
    }
    this.users = model;
  }

  public void addData(RespUsers.RespListUsers newModelData, int position) {
    users.add(position, newModelData);
    notifyItemInserted(position);
  }

  public void removeData(int position) {
    users.remove(position);
    notifyItemRemoved(position);
  }

  public RespUsers.RespListUsers getItem(int position) {
    return users.get(position);
  }

  public void toggleSelection(int pos) {
    if (selected.get(pos, false)) {
      selected.delete(pos);
    }
    else {
      selected.put(pos, true);
    }
    notifyItemChanged(pos);
  }

  public void clearSelections() {
    selected.clear();
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.
        from(parent.getContext()).
        inflate(R.layout.item_list_users, null);
    return new Holder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class Holder extends RecyclerView.ViewHolder {
    public Holder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
