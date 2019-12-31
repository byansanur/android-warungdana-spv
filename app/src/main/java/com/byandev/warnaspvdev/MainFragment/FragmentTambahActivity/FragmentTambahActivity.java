package com.byandev.warnaspvdev.MainFragment.FragmentTambahActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.ActivityListUserSelect;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespActivityType;
import com.byandev.warnaspvdev.Response.RespUsers2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTambahActivity extends Fragment implements View.OnClickListener {

  EditText etStartDate, etStartTime;
  EditText etEndDate, etEndTime;

  Context context;
  ApiEndPoint mApiService;
  SharedPrefManager sharedPrefManager;

  Spinner typeActivity;
  String typeee;
  private Integer idType;
  private Calendar calendar;

  TextView some_chips, clickDialog;


  private int mYear, mMonth, mDay, mHour, mMinute;


  public FragmentTambahActivity() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    View view = inflater.inflate(R.layout.fragment_tambah_activity, container, false);

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);
    calendar = Calendar.getInstance();

    clickDialog = view.findViewById(R.id.ClickDialog);
    clickDialog.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, ActivityListUserSelect.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
      }
    });
    some_chips = view.findViewById(R.id.tv_users);

    // start
    etStartDate = view.findViewById(R.id.jadwalActivityStart);
    etStartTime = view.findViewById(R.id.jadwalActivityStartTime);
    etStartTime.setOnClickListener(this);
    // end
    etEndDate = view.findViewById(R.id.jadwalActivityEnd);
    etEndTime = view.findViewById(R.id.jadwalActivityEndTime);
    etEndTime.setOnClickListener(this);
    selectDate();


    typeActivity = view.findViewById(R.id.typeActivity);
    initSp();

    return view;
  }


  @Override
  public void onStart() {
    super.onStart();
  }


  private void selectDate() {
    etStartDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,listener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1);
        datePickerDialog.show();
      }
      DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          calendar.set(Calendar.YEAR, year);
          calendar.set(Calendar.MONTH, month);
          calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

          SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
          etStartDate.setText(dateFormat.format(calendar.getTime()));
          etEndDate.setText(dateFormat.format(calendar.getTime()));
        }
      };
    });
    etEndDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,listener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1);
        datePickerDialog.show();
      }
      DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          calendar.set(Calendar.YEAR, year);
          calendar.set(Calendar.MONTH, month);
          calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

          SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
          etEndDate.setText(dateFormat.format(calendar.getTime()));
        }
      };
    });
  }

  private void initSp() {
    mApiService.activityType().enqueue(new Callback<RespActivityType>() {
      @Override
      public void onResponse(Call<RespActivityType> call, Response<RespActivityType> response) {
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
            if (response.body().getData() != null) {
              final List<RespActivityType.Typees> types = response.body().getData();
              final List<String> type = new ArrayList<>();
              for (int i = 0; i < types.size(); i++) {
                type.add(types.get(i).getType());
              }
              ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_2, type);
              typeActivity.setAdapter(adapter);
              int intSource = adapter.getPosition(typeee);
              typeActivity.setSelection(intSource);
              typeActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  idType = types.get(position).getId();
                  Log.d("type id",""+idType);
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
      public void onFailure(Call<RespActivityType> call, Throwable t) {

      }
    });
  }

  @Override
  public void onResume(){
    super.onResume();

  }

  @Override
  public void onClick(View v) {
    if (v == etStartTime) {
      final Calendar c = Calendar.getInstance();
      mHour = c.get(Calendar.HOUR_OF_DAY);
      mMinute = c.get(Calendar.MINUTE);
      TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
          etStartTime.setText(hourOfDay + ":" + minute);
          etEndTime.setText((hourOfDay + 1) + ":" + minute);
        }
      }, mHour, mMinute, true);
      tpd.show();
    }
    if (v == etEndTime) {
      final Calendar c = Calendar.getInstance();
      mHour = c.get(Calendar.HOUR_OF_DAY);
      mMinute = c.get(Calendar.MINUTE);
      TimePickerDialog tpd = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
          etEndTime.setText(hourOfDay + ":" + minute);
        }
      }, mHour, mMinute, true);
      tpd.show();
    }

  }

}
