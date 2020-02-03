package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespUser;
import com.byandev.warnaspvdev.Response.RespUsers;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivityAdapter extends RecyclerView.Adapter<ListUserActivityAdapter.Holder> {

  private Context context;
  private ArrayList<RespUsers.RespListUsers> users, selected;
  private List<Integer> intArry;
  boolean [] itemCheck;

  public ListUserActivityAdapter(Context context, List users) {
    this.users = (ArrayList<RespUsers.RespListUsers>) users;
    this.context = context;
    itemCheck = new boolean[users.size()];
    this.selected = new ArrayList<>();
  }

  @NonNull
  @Override
  public ListUserActivityAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    @SuppressLint("InflateParams")
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_activity, null);
//    Holder holder = new Holder(view, activity);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ListUserActivityAdapter.Holder holder, final int position) {
    final RespUsers.RespListUsers listUsers = users.get(position);
//    holder.npm.setText(listUsers.getNpm());
    holder.nama.setText(listUsers.getName());
    holder.role.setText(listUsers.getPrivilegesName());
    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_person_white_24dp);
    holder.imageThumbnail.setBackground(drawable);
    if (itemCheck[position]) {
      highlightView(holder);
    }
    holder.ll.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!users.isEmpty()) {
          holder.ll.setBackgroundColor(0xFFFFFFFF);
          itemCheck[position]=false;
        } else {
          holder.ll.setBackgroundColor(Color.BLUE);
          itemCheck[position]=true;
        }
      }
    });
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
  private void highlightView(Holder holder) {
    holder.imageThumbnail.setImageResource(R.drawable.ic_check_blue_24dp);
    holder.ll.setBackgroundColor(ContextCompat.getColor(context, R.color.textSubColor));

  }

  private void unhighlightView(Holder holder) {
//    holder.imgList.setImageResource(R.drawable.oval);
    holder.ll.setBackgroundColor(ContextCompat.getColor(context, R.color.white_transparency));
  }

  public void addAll(List<RespUsers.RespListUsers> users) {
      clearAll(false);
      this.users = (ArrayList<RespUsers.RespListUsers>) users;
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

    public List<RespUsers.RespListUsers> getSelected() {
      return selected;
    }

  @Override
  public int getItemCount() {
    return users.size();
  }

  public class Holder extends RecyclerView.ViewHolder {
    TextView nama, npm, role;
    LinearLayout ll, llup;
    ImageView imageThumbnail;

    String name[];

    public Holder(@NonNull View itemView) {
      super(itemView);
      nama = itemView.findViewById(R.id.namaCfa);
//      npm = itemView.findViewById(R.id.idCfa);
      role = itemView.findViewById(R.id.privilegesN);
      ll = itemView.findViewById(R.id.llItem);
      llup = itemView.findViewById(R.id.llUp);

      imageThumbnail = itemView.findViewById(R.id.imageThumbnail);

    }
  }
}
