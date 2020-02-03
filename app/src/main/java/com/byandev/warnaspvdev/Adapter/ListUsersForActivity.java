package com.byandev.warnaspvdev.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.MainFragment.FragmentTambahActivity.FragmentTambahActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespGetUserForActivity;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.byandev.warnaspvdev.Response.RespUsers2;

import java.util.ArrayList;
import java.util.List;

public class ListUsersForActivity extends RecyclerView.Adapter<ListUsersForActivity.ListHolder> {

  Context context;
  private List<RespUsers.RespListUsers> daftarUsers;
  private List<Integer> idUser = new ArrayList<>();
//  private List<RespUsers.RespListUsers> select = new ArrayList<>();

  public void setOnClick(OnItemCliked onClick) {
    this.onClick = onClick;
  }

  private OnItemCliked onClick;

  public ListUsersForActivity(Context context, List<RespUsers.RespListUsers> daftarUsers) {
    this.context = context;
    this.daftarUsers = daftarUsers;
  }
  public interface OnItemCliked {
    void selectedUser(List<Integer> idU);
  }


  @NonNull
  @Override
  public ListUsersForActivity.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    @SuppressLint("InflateParams")
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_users_activity, null);
    return new ListUsersForActivity.ListHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ListUsersForActivity.ListHolder holder, final int position) {

    if (idUser.size() >= 1) {
      if (idUser.contains(daftarUsers.get(position).getId())) {
        holder.card.setBackgroundColor(Color.GRAY);

      } else {
        holder.card.setBackgroundColor(Color.WHITE);

      }
    }

    holder.namaCfa.setText(daftarUsers.get(position).getName());
    holder.privilegesName.setText(daftarUsers.get(position).getPrivilegesName());
    Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_person_white_24dp);
    drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    holder.imgList.setBackground(drawable);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (idUser.size() >= 1) {
          if (idUser.contains(daftarUsers.get(position).getId())) {
            holder.card.setBackgroundColor(Color.WHITE);
            idUser.remove(daftarUsers.get(position).getId());
          } else {
            idUser.add(daftarUsers.get(position).getId());
            holder.card.setBackgroundColor(Color.GRAY);
          }
        }else {
          idUser.add(daftarUsers.get(position).getId());
          holder.card.setBackgroundColor(Color.GRAY);
        }
        onClick.selectedUser(idUser);
      }
    });

  }

  @Override
  public int getItemCount() {
    return daftarUsers.size();
  }

  public class ListHolder extends RecyclerView.ViewHolder {

    TextView namaCfa, privilegesName;
    LinearLayout card;
    ImageView imgList;

    public ListHolder(@NonNull View itemView) {
      super(itemView);
      privilegesName = itemView.findViewById(R.id.privilegesN);
      namaCfa = itemView.findViewById(R.id.namaCfa);
      imgList = itemView.findViewById(R.id.imageThumbnail);
      card = itemView.findViewById(R.id.llItem);
    }

  }


}
