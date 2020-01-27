package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespActivityList;

import java.util.ArrayList;

public class ListActivityUserAdapter extends RecyclerView.Adapter<ListActivityUserAdapter.Holder> {

  private ArrayList<RespActivityList.DataUser> dataUser = new ArrayList<>();
  private Context context;

  public ListActivityUserAdapter(ArrayList<RespActivityList.DataUser> dataUser, Context context) {
    this.dataUser = dataUser;
    this.context = context;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_cfa, null);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {

    holder.tvCfaActivity.setText(dataUser.get(position).getNameUser());
  }

  @Override
  public int getItemCount() {
    return dataUser.size();
  }

  public class Holder extends RecyclerView.ViewHolder {

    private TextView tvCfaActivity;

    public Holder(@NonNull View itemView) {
      super(itemView);

      tvCfaActivity = itemView.findViewById(R.id.tvCfaActivity);
    }
  }
}
