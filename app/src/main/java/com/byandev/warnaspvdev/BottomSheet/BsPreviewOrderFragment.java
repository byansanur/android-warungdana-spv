package com.byandev.warnaspvdev.BottomSheet;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.fragment.app.Fragment;

import com.byandev.warnaspvdev.Adapter.ListOrderAdapter2;
import com.byandev.warnaspvdev.Adapter.ListOrderEditAdapter;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.Helper.NumberTextWatcher;
import com.byandev.warnaspvdev.MainActivity.DetailOrdersActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespMstStatus;
import com.byandev.warnaspvdev.Response.RespOrderDealsStatus;
import com.byandev.warnaspvdev.Response.RespOrderDetail;
import com.byandev.warnaspvdev.Response.RespOrderStatus;
import com.byandev.warnaspvdev.Response.RespUpdateOrder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BsPreviewOrderFragment extends BottomSheetDialogFragment {

  private RelativeLayout btnEditRelative;
  private ImageView btnDetail, btnEdit;
  private TextView nama;
  private TextView unit;
  private TextView tanggal;
  private TextView editBtn, detailBtn;
  private Integer idUnit, id,idContact;
  private ApiEndPoint mApiService;
  private EditText otr,plafond,installment,tenor,down_payment;
  private String strtenor, status,reason=null, strMotor;
  private Integer idLoan;
  private Integer jumlah = 0;
  SharedPrefManager sharedPrefManager;
  private NumberFormat formatter = new DecimalFormat("#,###");
  private Spinner spinnerStatus;
  private Integer idOrderStatus;


  public BsPreviewOrderFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.bs_preview_order,container,false);
    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
      getContext().setTheme(R.style.darkTheme);
    } else getContext().setTheme(R.style.AppTheme);
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(getContext());
    btnDetail = view.findViewById(R.id.btnDetail);
    btnEdit = view.findViewById(R.id.btnEdit);
    editBtn = view.findViewById(R.id.editBtn);
    btnEditRelative = view.findViewById(R.id.btnEditRelative);
    detailBtn = view.findViewById(R.id.detailBtn);
    nama = view.findViewById(R.id.nama);
    tanggal = view.findViewById(R.id.tanggal);
    spinnerStatus = view.findViewById(R.id.spinnerStatus);

    unit = view.findViewById(R.id.motor);

//    spinnerReason = view.findViewById(R.id.spinnerReason);
//    spinnerReason.setVisibility(View.GONE);


    Bundle bundle = getArguments();
    nama.setText(bundle.getString("nama"));
    tanggal.setText(bundle.getString("tanggal"));
    status = bundle.getString("status");
    reason = bundle.getString("reason");
    idUnit = bundle.getInt("idUnit");
    id = bundle.getInt("id");
    idOrderStatus = bundle.getInt("idOrderStatus");
    idContact = bundle.getInt("idContact");
    otr = view.findViewById(R.id.otr);
    plafond = view.findViewById(R.id.plafond);
    installment = view.findViewById(R.id.angsuran);
    tenor = view.findViewById(R.id.tenor);
    down_payment = view.findViewById(R.id.dp);
    plafond.addTextChangedListener(autoTextWatcher);
    otr.addTextChangedListener(autoTextWatcher);
    otr.addTextChangedListener(new NumberTextWatcher(otr));
    plafond.addTextChangedListener(new NumberTextWatcher(plafond));
    installment.addTextChangedListener(new NumberTextWatcher(installment));
    down_payment.addTextChangedListener(new NumberTextWatcher(down_payment));

//        mApiService.detailUnit(idUnit).enqueue(new Callback<DetailUnit>() {
//            @Override
//            public void onResponse(Call<DetailUnit> call, Response<DetailUnit> response) {
//                if (response.isSuccessful()){
//                    unit.setText(response.body().getMerk()+" "+response.body().getModel());
//                    otr.setText(response.body().getOtr().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DetailUnit> call, Throwable t) {
//
//            }
//        });
    orderDetails();

    return view;
  }

  private void orderDetails() {
    mApiService.orderDetail(id).enqueue(new Callback<RespOrderDetail>() {
      @SuppressLint("SetTextI18n")
      @Override
      public void onResponse(Call<RespOrderDetail> call, final Response<RespOrderDetail> response) {
        if (response.isSuccessful()){
          if (response.body() != null && response.body().getApiStatus() ==1) {
            strMotor = response.body().getData().getMerk() +" "+ response.body().getData().getType();
            idLoan = response.body().getData().getId();
            plafond.setText(formatter.format(response.body().getData().getPlafond()));
            otr.setText(formatter.format(response.body().getData().getOtr()));
            installment.setText(formatter.format(response.body().getData().getInstallment()));
            down_payment.setText(formatter.format(response.body().getData().getDownPayment()));
            strtenor = String.valueOf(response.body().getData().getTenor());
            tenor.setText(strtenor);
            unit.setText(strMotor);
            idOrderStatus = response.body().getData().getIdOrderMstStatus();
            status = response.body().getData().getStatus();
            plafond.setEnabled(false);
            installment.setEnabled(false);
            down_payment.setEnabled(false);
            tenor.setEnabled(false);

            if (idOrderStatus != 5){
                initSpinnerStatus();
            } else {
              spinnerStatus.setVisibility(View.GONE);
            }
            spinnerStatus.setEnabled(false);

          }
        }
      }

      @Override
      public void onFailure(Call<RespOrderDetail> call, Throwable t) {
        Toast.makeText(getContext(),"Internet Bermasalah !",Toast.LENGTH_LONG).show();

      }
    });
  }

  private void initSpinnerStatus() {
//    mApiService.orderStatus(
//        sharedPrefManager.getSpId(),
//        sharedPrefManager.getSpOutletId(),
//        getIdOrderStatus()
//    ).enqueue(new Callback<RespOrderStatus>() {
//      @Override
//      public void onResponse(Call<RespOrderStatus> call, final Response<RespOrderStatus> response) {
//        if (response.isSuccessful()) {
//          if (response.body().getApiStatus()==1){
//            final List<RespOrderStatus.ListOrderStatus> list = response.body().getData();
//            final List<String> list1 = new ArrayList<>();
//            for (int i = 0; i < list.size(); ++i){ list1.add(list.get(i).getStatus()); }
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_2, list1);
//            spinnerStatus.setAdapter(adapter);
//            int Resource = adapter.getPosition(status);
//            spinnerStatus.setSelection(Resource);
//            spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//              @Override
//              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                idOrderStatus = list.get(position).getIdOrderMstStatus();
//                if (idOrderStatus == 5) {
//                  spinnerStatus.setVisibility(View.GONE);
//                } else spinnerStatus.setVisibility(View.VISIBLE);
//              }
//
//              @Override
//              public void onNothingSelected(AdapterView<?> parent) {
//
//              }
//            });
//          }
//        }
//      }
//
//      @Override
//      public void onFailure(Call<RespOrderStatus> call, Throwable t) {
//
//      }
//    });
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
              spinnerStatus.setAdapter(adapter);
              int intSource = adapter.getPosition(status);
              spinnerStatus.setSelection(intSource);
              spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//    mApiService.orderDealsStatus().enqueue(new Callback<RespOrderDealsStatus>() {
//      @Override
//      public void onResponse(Call<RespOrderDealsStatus> call, Response<RespOrderDealsStatus> response) {
//        if (response.isSuccessful()){
//          final List<RespOrderDealsStatus.ListOrderDealsStatus> list = response.body().getData();
//          final List<String> list1 = new ArrayList<>();
//          for (int i = 0; i < list.size(); i++) {
//            list1.add(list.get(i).getName());
//          }
//          ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_2, list1);
//          spinnerStatus.setAdapter(adapter);
//          int intSource = adapter.getPosition(status);
//          spinnerStatus.setSelection(intSource);
//          if (reason!=null){
//            spinnerStatus.setVisibility(View.VISIBLE);
//          }
//          spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//              idOrderStatus = list.get(position).getValue();
//              if (idOrderStatus==5){
//                spinnerStatus.setVisibility(View.GONE);
////                initSpinnerReason(idOrderStatus);
//              }else {
//                spinnerStatus.setVisibility(View.VISIBLE);
//              }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//          });
//        }
//      }
//
//      @Override
//      public void onFailure(Call<RespOrderDealsStatus> call, Throwable t) {
//
//      }
//    });
  }


//  private void initSpinnerReason(final int idReason){
//        mApiService.spv_order_reason_listing(idReason).enqueue(new Callback<RespListOrderStatus>() {
//            @Override
//            public void onResponse(Call<RespListOrderStatus> call, Response<RespListOrderStatus> response) {
//                if (response.isSuccessful()){
//                    final List<ListOrderStatus> list = response.body().getData();
//                    final List<String> list1 = new ArrayList<>();
//                    for (int i = 0; i < list.size(); i++) {
//                        list1.add(list.get(i).getReason());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_3, list1);
//                    spinnerReason.setAdapter(adapter);
//                    int intSource = adapter.getPosition(reason);
//                    spinnerReason.setSelection(intSource);
//                    spinnerReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            idReason1 = list.get(position).getId();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RespListOrderStatus> call, Throwable t) {
//
//            }
//        });
//
//  }

  @Override
  public void onResume() {
    super.onResume();

    btnDetail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent a = new Intent(getContext(), DetailOrdersActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        a.putExtra("id", id);
        a.putExtra("idContact", idContact);
        startActivity(a);
        dismiss();
      }
    });

    btnEditRelative.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
        ad.setMessage("Do you want to update the data?").setCancelable(true)
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
              }
            })
            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(final DialogInterface dialog, int which) {
                plafond.setEnabled(true);
                installment.setEnabled(true);
                down_payment.setEnabled(true);
                tenor.setEnabled(true);
                spinnerStatus.setEnabled(true);
                plafond.setInputType(InputType.TYPE_CLASS_NUMBER);
                down_payment.setInputType(InputType.TYPE_CLASS_NUMBER);
                installment.setInputType(InputType.TYPE_CLASS_NUMBER);
                tenor.setInputType(InputType.TYPE_CLASS_NUMBER);
                btnEdit.setBackgroundResource(R.drawable.bg_btn_solid);
                editBtn.setText("Save");
                editBtn.setTextColor(Color.WHITE);
                btnDetail.setVisibility(View.GONE);
                detailBtn.setVisibility(View.GONE);
                btnEditRelative.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                    mApiService.orderEdit(
                        id,
                        sharedPrefManager.getSpId(),
                        idOrderStatus
                    ).enqueue(new Callback<RespUpdateOrder>() {
                      @Override
                      public void onResponse(Call<RespUpdateOrder> call, Response<RespUpdateOrder> response) {
                        if (response.isSuccessful()){
                          mApiService.orderEditField(
                              id,
                              sharedPrefManager.getSpId(),
                              idOrderStatus,
                              Integer.parseInt(
                                  plafond.getText().toString()
                                      .replaceAll(",", "")
                                      .replaceAll("\\.", "")
                              ),
                              Integer.parseInt(
                                  down_payment.getText().toString()
                                      .replaceAll(",", "")
                                      .replaceAll("\\.", "")
                              ),
                              Integer.parseInt(
                                  installment.getText().toString()
                                      .replaceAll(",", "")
                                      .replaceAll("\\.", "")
                              ),
                              Integer.parseInt(
                                  tenor.getText().toString()
                              )
                          ).enqueue(new Callback<RespUpdateOrder>() {
                            @Override
                            public void onResponse(Call<RespUpdateOrder> call, Response<RespUpdateOrder> response) {
                              Toast.makeText(getContext(),"Berhasil mengubah data Order !",Toast.LENGTH_LONG).show();
                              dismiss();
                              ListOrderEditAdapter listOrderAdapter2 = new ListOrderEditAdapter();
                              listOrderAdapter2.notifyDataSetChanged();

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

  // TextWatcher

  private TextWatcher autoTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      sumDp();
      if (!plafond.getText().toString().isEmpty() && !otr.getText().toString().isEmpty() && jumlah >= 0) {
        down_payment.setText(formatter.format(jumlah));
      } else {
        down_payment.setText("");
      }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  };

  private void sumDp() {

    if (!otr.getText().toString().isEmpty() && !plafond.getText().toString().isEmpty()) {
      Integer MinOtr;
      Integer MinPlafond;
      MinOtr = Integer.parseInt(
          otr.getText().toString()
              .replaceAll(",", "")
              .replaceAll("\\.", ""));
      MinPlafond = Integer.parseInt(
          plafond.getText().toString()
              .replaceAll(",", "")
              .replaceAll("\\.", ""));
      jumlah = MinOtr - MinPlafond;
    }

  }

}
