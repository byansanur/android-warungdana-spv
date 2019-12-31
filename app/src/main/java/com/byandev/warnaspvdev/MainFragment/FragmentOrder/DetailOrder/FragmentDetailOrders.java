package com.byandev.warnaspvdev.MainFragment.FragmentOrder.DetailOrder;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.byandev.warnaspvdev.MainActivity.DetailOrdersActivity;
import com.byandev.warnaspvdev.MainActivity.HomeActivity;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderDetail;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailOrders extends Fragment {

    TextView createdAt, status,
            firstName, lastName, name, dataSource, penjamin, need,
            noPol, merek, type, model, year, tax, owner,
            plafond, cicilan, dp, tenor, otrCustom, otr;

    private ApiEndPoint mApiService;
    private Context context;
    private Button btBack;
    private NumberFormat formatter = new DecimalFormat("#,###");
    private boolean itShouldLoadMore = true;
    LinearLayout layoutDetail;

    // initialize key id in here
    private Integer id, idMstOrder;

    public FragmentDetailOrders() {
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle s) {
        View view = inflater.inflate(R.layout.framgment_detail_orders, container,false);
        context = getContext();
        mApiService = UtilsApi.getAPIService();
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        /**
         * Call an activity method from fragment
         * memanggil key yang sudah dideklarasi di activity detail orders
         */
            id = ((DetailOrdersActivity)getActivity()).getId();
        idMstOrder = ((DetailOrdersActivity)getActivity()).getIdMstorder();

        btBack = view.findViewById(R.id.btnBack);
        layoutDetail = view.findViewById(R.id.layoutDetail);

        // tv
        createdAt = view.findViewById(R.id.createdAt);
        status =  view.findViewById(R.id.statusOrder);
        // tv order profile
        firstName = view.findViewById(R.id.tvFirstName);
        lastName = view.findViewById(R.id.tvLastName);
        name = view.findViewById(R.id.tvName);
        dataSource = view.findViewById(R.id.tvDataSource);
        penjamin = view.findViewById(R.id.tvSuretyName);
        need = view.findViewById(R.id.tvNeed);
        // tv order unit
        noPol = view.findViewById(R.id.tvNoPol);
        merek =  view.findViewById(R.id.tvMerek);
        type = view.findViewById(R.id.tvType);
        model =  view.findViewById(R.id.tvModel);
        year =  view.findViewById(R.id.tvYear);
        tax =  view.findViewById(R.id.tvTaxStatus);
        owner = view.findViewById(R.id.tvOwner);
        otr = view.findViewById(R.id.tvOtr);
        // tv order fund
        plafond = view.findViewById(R.id.tvPlafond);
        cicilan = view.findViewById(R.id.tvInstallment);
        dp = view.findViewById(R.id.tvDp);
        tenor = view.findViewById(R.id.tvTenor);
        otrCustom = view.findViewById(R.id.tvOtrCustom);
        getDetailsMore();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        back();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDetails();
    }

    private void getDetails() {
        itShouldLoadMore = false;
        mApiService.orderDetail(id).enqueue(new Callback<RespOrderDetail>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<RespOrderDetail> call, Response<RespOrderDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getApiStatus() == 1) {
                        createdAt.setText(response.body().getData().getCreatedAt());
                        status.setText((CharSequence) response.body().getData().getStatus());
                        firstName.setText(response.body().getData().getFirstName());
                        lastName.setText(response.body().getData().getLastName());
                        name.setText(response.body().getData().getName());
                        dataSource.setText(response.body().getData().getDatasource());
                        penjamin.setText(response.body().getData().getSuretyName());
                        need.setText(response.body().getData().getNeed());
                        noPol.setText(response.body().getData().getNopol());
                        merek.setText(response.body().getData().getMerk());
                        type.setText(response.body().getData().getType());
                        model.setText(response.body().getData().getModel());
                        year.setText(String.valueOf(response.body().getData().getYear()));
                        tax.setText(response.body().getData().getTaxStatus());
                        owner.setText(response.body().getData().getOwner());
                        tenor.setText(String.valueOf(response.body().getData().getTenor()) + " Bulan");
                        if (response.body().getData().getOtr() != null) {
                            otr.setText("Rp " +formatter.format(response.body().getData().getOtr()));
                        } else otr.setText("null");
                        if (response.body().getData().getPlafond() != null) {
                            plafond.setText("Rp " + formatter.format(response.body().getData().getPlafond()));
                        } else plafond.setText("null");
                        if (response.body().getData().getInstallment() != null) {
                            cicilan.setText("Rp " + formatter.format(response.body().getData().getInstallment()));
                        } else cicilan.setText("null");
                        if (response.body().getData().getDownPayment() != null) {
                            dp.setText("Rp " + formatter.format(response.body().getData().getDownPayment()));
                        } else dp.setText("null");
                        if (response.body().getData().getOtrCustom() != null) {
                            otrCustom.setText("Rp "+formatter.format(response.body().getData().getOtrCustom()) );
                        } else otrCustom.setText("null");
                    }
                }
            }

            @Override
            public void onFailure(Call<RespOrderDetail> call, Throwable t) {
                itShouldLoadMore = true;
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Check your connection");
            }
        });
    }

    private void getDetailsMore() {
        itShouldLoadMore = false;
        mApiService.orderDetail(id).enqueue(new Callback<RespOrderDetail>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<RespOrderDetail> call, Response<RespOrderDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getApiStatus() == 1) {
                        createdAt.setText(response.body().getData().getCreatedAt());
                        status.setText((CharSequence) response.body().getData().getStatus());
                        firstName.setText(response.body().getData().getFirstName());
                        lastName.setText(response.body().getData().getLastName());
                        name.setText(response.body().getData().getName());
                        dataSource.setText(response.body().getData().getDatasource());
                        penjamin.setText(response.body().getData().getSuretyName());
                        need.setText(response.body().getData().getNeed());
                        noPol.setText(response.body().getData().getNopol());
                        merek.setText(response.body().getData().getMerk());
                        type.setText(response.body().getData().getType());
                        model.setText(response.body().getData().getModel());
                        year.setText(String.valueOf(response.body().getData().getYear()));
                        tax.setText(response.body().getData().getTaxStatus());
                        owner.setText(response.body().getData().getOwner());
                        tenor.setText(String.valueOf(response.body().getData().getTenor()));
                        if (response.body().getData().getOtr() != null) {
                            otr.setText("IDR " + formatter.format(response.body().getData().getOtr()));
                        } else otr.setText("null");
                        if (response.body().getData().getPlafond() != null) {
                            plafond.setText("IDR " + formatter.format(response.body().getData().getPlafond()));
                        } else plafond.setText("null");
                        if (response.body().getData().getInstallment() != null) {
                            cicilan.setText("IDR " + formatter.format(response.body().getData().getInstallment()));
                        } else cicilan.setText("null");
                        if (response.body().getData().getDownPayment() != null) {
                            dp.setText("IDR " + formatter.format(response.body().getData().getDownPayment()));
                        } else dp.setText("null");
                        if (response.body().getData().getOtrCustom() != null) {
                            otrCustom.setText("IDR "+formatter.format(response.body().getData().getOtrCustom()) );
                        } else otrCustom.setText("null");
                    }
                }
            }

            @Override
            public void onFailure(Call<RespOrderDetail> call, Throwable t) {
                itShouldLoadMore = true;
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Check your connection");
            }
        });
    }

    private void back() {
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomeActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }
}
