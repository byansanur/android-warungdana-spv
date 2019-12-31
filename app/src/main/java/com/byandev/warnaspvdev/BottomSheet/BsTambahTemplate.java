package com.byandev.warnaspvdev.BottomSheet;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainFragment.FragmentAktivitas.TemplateFragment;
import com.byandev.warnaspvdev.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BsTambahTemplate extends BottomSheetDialogFragment {

    Spinner type,outlet;
    EditText lokasi;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;
    Integer idOutlet,idType;
    Button tambah;
    ProgressDialog loading;
    TemplateFragment templateFragment;


    //perbaikan tambah template

    public BsTambahTemplate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bs_add_template_jadwal,container,false);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getContext());
        tambah = view.findViewById(R.id.tambah);
        type = view.findViewById(R.id.tipe);
        outlet = view.findViewById(R.id.outlet);
        lokasi = view.findViewById(R.id.lokasi);
        initSpinnerType();
        initspinnerOutlet();

        return view;
    }

    private void initspinnerOutlet() {
//        mApiService.listOutlet(sharedPrefManager.getSpBranchId()).enqueue(new Callback<RespListOutlet>() {
//            @Override
//            public void onResponse(Call<RespListOutlet> call, Response<RespListOutlet> response) {
//                if (response.isSuccessful()){
//                    final List<ListOutlet> list = response.body().getData();
//                    final List<String> list1 = new ArrayList<>();
//                    for (int i=0;i<list.size();i++){
//                        list1.add(list.get(i).getOutletName());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.spinner_item,list1);
//                    outlet.setAdapter(adapter);
//                    outlet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            idOutlet = list.get(position).getId();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RespListOutlet> call, Throwable t) {
//
//            }
//        });
    }

    private void initSpinnerType() {
//        mApiService.activityType().enqueue(new Callback<RespListType>() {
//            @Override
//            public void onResponse(Call<RespListType> call, Response<RespListType> response) {
//                if (response.isSuccessful()){
//                    final List<ListType> list = response.body().getData();
//                    final List<String> list1 = new ArrayList<>();
//                    for (int i=0;i<list.size();i++){
//                        list1.add(list.get(i).getType());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.spinner_item,list1);
//                    type.setAdapter(adapter);
//                    type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            idType = list.get(position).getId();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RespListType> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();

        tambah.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(lokasi.getText())) {
                lokasi.setError("Wajib Diisi !!!");
            }else {
                loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
//                    mApiService.addActivityTemplate(idOutlet,idType,sharedPrefManager.getSpId()," "," ",lokasi.getText().toString())
//                            .enqueue(new Callback<RespPost>() {
//                                @Override
//                                public void onResponse(Call<RespPost> call, Response<RespPost> response) {
//                                    if (response.isSuccessful()){
//                                        loading.dismiss();
//                                        dismiss();
//                                        Toast.makeText(getContext(),"Berhasil Menambah Template Jadwal",Toast.LENGTH_LONG).show();
//
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<RespPost> call, Throwable t) {
//                                    loading.dismiss();
//                                    Toast.makeText(getContext(),"Gagal",Toast.LENGTH_LONG).show();
//                                }
//                            });
            }

            }
        });


    }
}
