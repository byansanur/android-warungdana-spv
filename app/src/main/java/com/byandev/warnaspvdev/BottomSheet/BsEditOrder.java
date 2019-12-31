package com.byandev.warnaspvdev.BottomSheet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.byandev.warnaspvdev.Adapter.ListOrderAdapter2;
import com.byandev.warnaspvdev.Adapter.ListOrderEditAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.Helper.NumberTextWatcher;
import com.byandev.warnaspvdev.MainActivity.DetailOrdersActivity;
import com.byandev.warnaspvdev.MainActivity.EditOrderActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespMstStatus;
import com.byandev.warnaspvdev.Response.RespOrderDetail;
import com.byandev.warnaspvdev.Response.RespUpdateOrder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BsEditOrder extends BottomSheetDialogFragment {

  private ApiEndPoint mApiService;
  private Context context;
  SharedPrefManager sharedPrefManager;
  private String strtenor, status,reason=null;
  private NumberFormat formatter = new DecimalFormat("#,###");
  private Integer id, idContact;
  private Spinner spStatus;
  private EditText etOtr, etDP, etInstallement, etPlafond, etTenor;
  Button save;
  private Integer idOrderStatus;
  private Integer jumlah = 0;
  private String namad, motord, tanggald;
  private TextView nama;
  private TextView motor;
  private TextView tanggal;
  public BsEditOrder() {
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    View view = inflater.inflate(R.layout.bs_edit_order, container, false);

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
//    id = ((EditOrderActivity)getActivity()).getId();

    nama = view.findViewById(R.id.nama);
    motor = view.findViewById(R.id.motor);
    tanggal = view.findViewById(R.id.tanggal);

    etOtr = view.findViewById(R.id.editOtr);
    etDP = view.findViewById(R.id.editDp);
    etInstallement = view.findViewById(R.id.editCicilan);
    etPlafond = view.findViewById(R.id.editPlafond);
    etTenor = view.findViewById(R.id.editTenor);

    etPlafond.addTextChangedListener(autoTextWatcher);
    etOtr.addTextChangedListener(autoTextWatcher);
    etOtr.addTextChangedListener(new NumberTextWatcher(etOtr));
    etPlafond.addTextChangedListener(new NumberTextWatcher(etPlafond));
    etInstallement.addTextChangedListener(new NumberTextWatcher(etInstallement));
    etDP.addTextChangedListener(new NumberTextWatcher(etDP));


    save = view.findViewById(R.id.btnUpdate);

    spStatus = view.findViewById(R.id.spinnerStatus);

    Bundle bundle = getArguments();
    status = bundle.getString("status");
    id = bundle.getInt("id");
    idOrderStatus = bundle.getInt("idOrderStatus");
    idContact = bundle.getInt("idContact");

    enableEditText();
    initSp();
    editTex();

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  private void editTex() {
    mApiService.orderDetail(id).enqueue(new Callback<RespOrderDetail>() {
      @SuppressLint("SetTextI18n")
      @Override
      public void onResponse(Call<RespOrderDetail> call, Response<RespOrderDetail> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            if (response.body().getData().getOtr() != null) {
              etOtr.setText(formatter.format(response.body().getData().getOtr()));
            }else etOtr.setText(null);
            if (response.body().getData().getPlafond() != null) {
              etPlafond.setText(formatter.format(response.body().getData().getPlafond()));
            } else etPlafond.setText(null);
            if (response.body().getData().getInstallment() != null) {
              etInstallement.setText(formatter.format(response.body().getData().getInstallment()));
            } else etInstallement.setText(null);
            if (response.body().getData().getDownPayment() != null) {
              etDP.setText(formatter.format(response.body().getData().getDownPayment()));
            } else etDP.setText(null);
            strtenor = String.valueOf(response.body().getData().getTenor());
            etTenor.setText(strtenor);
            idOrderStatus = response.body().getData().getIdOrderMstStatus();
            status = response.body().getData().getStatus();
            namad = response.body().getData().getFirstName() +" "+ response.body().getData().getLastName();
            nama.setText(namad);
            motord = response.body().getData().getMerk() +" "+ response.body().getData().getType();
            motor.setText(motord);
            tanggald = response.body().getData().getCreatedAt();
            tanggal.setText(tanggald);
            if (idOrderStatus != 5){
              initSp();
            } else {
              spStatus.setVisibility(View.GONE);
            }
            spStatus.setEnabled(false);
          }
        }
      }

      @Override
      public void onFailure(Call<RespOrderDetail> call, Throwable t) {

      }
    });
  }

  private void enableEditText() {
    etPlafond.setEnabled(false);
    etDP.setEnabled(false);
    etInstallement.setEnabled(false);
    etOtr.setEnabled(false);
    etTenor.setEnabled(false);
  }
  private void afterClickYes() {
    etPlafond.setEnabled(true);
    etInstallement.setEnabled(true);
    etDP.setEnabled(true);
    etTenor.setEnabled(true);
    spStatus.setEnabled(true);
  }
  private void afterClickYesButtonResponse() {
    save.setText("Save");
    save.setTextColor(Color.BLUE);
    save.setBackgroundResource(R.drawable.bg_btn_stroke);
  }

  private void afterClickYesInputType() {
    etPlafond.setInputType(InputType.TYPE_CLASS_NUMBER);
    etInstallement.setInputType(InputType.TYPE_CLASS_NUMBER);
    etDP.setInputType(InputType.TYPE_CLASS_NUMBER);
    etTenor.setInputType(InputType.TYPE_CLASS_NUMBER);

  }

  @Override
  public void onResume() {
    super.onResume();

    save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
        ad.setMessage("Are you sure you want to change this data?").setCancelable(true)
            .setNegativeButton("No i dont", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            })
            .setPositiveButton("Yes i do", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                afterClickYes();
                afterClickYesInputType();
                afterClickYesButtonResponse();
                save.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    mApiService.orderEdit(
                        id,
                        sharedPrefManager.getSpId(),
                        idOrderStatus
                    ).enqueue(new Callback<RespUpdateOrder>() {
                      @Override
                      public void onResponse(Call<RespUpdateOrder> call, Response<RespUpdateOrder> response) {
                        if (response.isSuccessful()) {
                          mApiService.orderEditField(
                              id,
                              sharedPrefManager.getSpId(),
                              idOrderStatus,
                              Integer.parseInt(
                                  etPlafond.getText().toString()
                                      .replaceAll(",", "")
                                      .replaceAll("\\.", "")
                              ),
                              Integer.parseInt(
                                  etDP.getText().toString()
                                      .replaceAll(",", "")
                                      .replaceAll("\\.", "")
                              ),
                              Integer.parseInt(
                                  etInstallement.getText().toString()
                                      .replaceAll(",", "")
                                      .replaceAll("\\.", "")
                              ),
                              Integer.parseInt(
                                  etTenor.getText().toString()
                              )
                          ).enqueue(new Callback<RespUpdateOrder>() {
                            @Override
                            public void onResponse(Call<RespUpdateOrder> call, Response<RespUpdateOrder> response) {
                              Toast.makeText(getContext(),"Berhasil mengubah data Order !",Toast.LENGTH_LONG).show();
                              dismiss();
                              ListOrderEditAdapter listOrderAdapter2 = new ListOrderEditAdapter();
                              listOrderAdapter2.notifyDataSetChanged();
                              ((DetailOrdersActivity)getActivity()).afterUpdate();

                            }

                            @Override
                            public void onFailure(Call<RespUpdateOrder> call, Throwable t) {
                              Toast.makeText(getContext(),"Internet Bermasalah !",Toast.LENGTH_LONG).show();
                            }
                          });
                        }
                      }

                      @Override
                      public void onFailure(Call<RespUpdateOrder> call, Throwable t) {
                        Toast.makeText(getContext(),"Internet Bermasalah !",Toast.LENGTH_LONG).show();
                      }
                    });
                  }
                });
              }
            })
            .show();

      }
    });


  }


  private void initSp() {
    mApiService.orderMstStatus().enqueue(new Callback<RespMstStatus>() {
      @Override
      public void onResponse(Call<RespMstStatus> call, Response<RespMstStatus> response) {
        if (response.isSuccessful()) {

          if (response.body().getApiStatus() == 1){

            if (response.body().getData() != null){

              final List<RespMstStatus.MstStatus> statuss = response.body().getData();
              final List<String> statused = new ArrayList<>();
              for (int i = 0; i < statuss.size(); i++){
                statused.add(statuss.get(i).getStatus());
              }
              ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_2, statused);
              spStatus.setAdapter(adapter);
              int intSource = adapter.getPosition(status);
              spStatus.setSelection(intSource);
              spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  idOrderStatus = statuss.get(position).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
              });

            }
          }
        }
      }

      @Override
      public void onFailure(Call<RespMstStatus> call, Throwable t) {

      }
    });

  }

  private TextWatcher autoTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      sumDp();
      if (!etPlafond.getText().toString().isEmpty() && !etOtr.getText().toString().isEmpty() && jumlah >= 0) {
        etDP.setText(formatter.format(jumlah));
      } else {
        etDP.setText("");
      }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  };

  private void sumDp() {

    if (!etOtr.getText().toString().isEmpty() && !etPlafond.getText().toString().isEmpty()) {
      Integer MinOtr;
      Integer MinPlafond;
      MinOtr = Integer.parseInt(
          etOtr.getText().toString()
              .replaceAll(",", "")
              .replaceAll("\\.", ""));
      MinPlafond = Integer.parseInt(
          etPlafond.getText().toString()
              .replaceAll(",", "")
              .replaceAll("\\.", ""));
      jumlah = MinOtr - MinPlafond;
    }

  }
}
