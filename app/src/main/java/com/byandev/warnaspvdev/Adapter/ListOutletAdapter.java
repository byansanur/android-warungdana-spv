//package com.byandev.warnaspvdev.Adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.byandev.warnaspvdev.Api.ApiEndPoint;
//import com.byandev.warnaspvdev.Api.SharedPrefManager;
//import com.byandev.warnaspvdev.Api.UtilsApi;
//import com.byandev.warnaspvdev.R;
//import com.byandev.warnaspvdev.Response.RespOutletPerformance;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListOutletAdapter extends RecyclerView.Adapter<ListOutletAdapter.ListOutletHolder> {
//
//    private ArrayList<RespOutletPerformance.ListOutletPerformance> outletPerformances;
//    private Context context;
//    ApiEndPoint mApiService;
//    private SharedPrefManager sharedPrefManager;
//
//    public ListOutletAdapter(Context cont, List<RespOutletPerformance.ListOutletPerformance> outlet) {
//        this.context = cont;
//        this.outletPerformances = (ArrayList<RespOutletPerformance.ListOutletPerformance>) outlet;
//    }
//
//    @NonNull
//    @Override
//    public ListOutletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kpi_outlet, null);
//        mApiService = UtilsApi.getAPIService();
//        sharedPrefManager = new SharedPrefManager(context);
//        return new ListOutletHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ListOutletAdapter.ListOutletHolder holder, int position) {
//        final RespOutletPerformance.ListOutletPerformance oP = outletPerformances.get(position);
//
//        if (oP.getBooking().equals("")) {
//            holder.booking_count.setText(String.valueOf(oP.getBooking()));
//        } else {
//            holder.booking_count.setText(String.valueOf(oP.getBooking()));
//        }
//        if (oP.getLead().equals("")){
//            holder.leads_count.setText(String.valueOf(oP.getLead()));
//        } else {
//            holder.leads_count.setText(String.valueOf(oP.getLead()));
//        }
//        if (oP.getMonth().equals("")) {
//            holder.bulan.setText(oP.getMonth());
//        } else {
//            holder.bulan.setText(convertTime(oP.getMonth()));
//        }
//        holder.namaOutlet.setText(sharedPrefManager.getSpBranchName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return outletPerformances.size();
//    }
//
//    public class ListOutletHolder extends RecyclerView.ViewHolder {
//        TextView namaOutlet, bulan, leads_count, booking_count;
//        public ListOutletHolder(@NonNull View itemView) {
//            super(itemView);
//            namaOutlet = itemView.findViewById(R.id.namaOutlet);
//            bulan = itemView.findViewById(R.id.bulan);
//            leads_count = itemView.findViewById(R.id.leadsCount);
//            booking_count = itemView.findViewById(R.id.bookingCount);
//        }
//    }
//
//    private String convertTime(String time) {
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd MMM, yyyy");
//        java.util.Date date = null;
//        try {
//            date = format.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String convertedDate = time;
//        try {
//            convertedDate = format1.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return convertedDate;
//    }
//}
