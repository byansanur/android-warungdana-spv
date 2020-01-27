package com.byandev.warnaspvdev.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.MainActivity.ActivityListUserSelect;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUsers;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivityAdapter extends RecyclerView.Adapter<ListUserActivityAdapter.Holder> {

  private Context context;
  private ArrayList<RespUsers.RespListUsers> users;
  private ActivityListUserSelect activity;

  public ListUserActivityAdapter(ArrayList<RespUsers.RespListUsers> list, Context context) {
    this.users = list;
    this.context = context;
    activity = (ActivityListUserSelect) context;
  }

  @NonNull
  @Override
  public ListUserActivityAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_activity, null);
    Holder holder = new Holder(view, activity);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ListUserActivityAdapter.Holder holder, int position) {
    holder.nama.setText(users.get(position).getName());
    holder.npm.setText(users.get(position).getNpm());
    holder.role.setText(users.get(position).getPrivilegesName());
    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_face_black_24dp);
    holder.imageThumbnail.setImageDrawable(drawable);
    if (!activity.in_action_mode) {
      holder.cb.setVisibility(View.GONE);
    } else {
      holder.cb.setVisibility(View.VISIBLE);
      holder.cb.setChecked(false);
//      Toast.makeText(context, "Name : "+users.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public int getItemCount() {
    return users.size();
  }

  public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nama, npm, role;
    LinearLayout ll;
    CheckBox cb;
    ActivityListUserSelect select;
    ImageView imageThumbnail;
    public Holder(@NonNull View itemView, ActivityListUserSelect select) {
      super(itemView);
      nama = itemView.findViewById(R.id.namaCfa);
      npm = itemView.findViewById(R.id.idCfa);
      role = itemView.findViewById(R.id.privilegesN);
      ll = itemView.findViewById(R.id.llItem);
      cb = itemView.findViewById(R.id.cek);
      cb.setOnClickListener(this);
      imageThumbnail = itemView.findViewById(R.id.imageThumbnail);
      this.select = select;
      ll.setOnLongClickListener(select);
    }

    @Override
    public void onClick(View v) {
      activity.prepareSelection(v, getAdapterPosition());
      Toast.makeText(context, "Name : "+users.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
    }
  }
}
