package com.byandev.warnaspvdev.MainFragment.FragmentHome;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.byandev.warnaspvdev.Adapter.ListActivityAdapter;
import com.byandev.warnaspvdev.Adapter.ListLeadUsersAdapter;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.Adapter.ListOrderNew;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespActivityList;
import com.byandev.warnaspvdev.Response.RespLeadUsers;
import com.byandev.warnaspvdev.Response.RespOrderStatus;
import com.byandev.warnaspvdev.Response.RespOutletPerformance;
import com.byandev.warnaspvdev.Response.RespKpi;
import com.byandev.warnaspvdev.Utils.UtilsConnected;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.byandev.warnaspvdev.R.layout.*;
import static java.util.Calendar.YEAR;

/**
 * A simple {@link Fragment} subclass.
 * Create by Ratbyansa 2019-10
 */

public class FHome extends Fragment {

  private TextView tvPagi, tvName, tvDatelokasi, tvBulan,
      tvSKRG, tvKMRN, tvSEMUA,
      tvUsers, tvBrosur, tvLeads, tvTargetCall, tvDeals, tvBooking, tvTarget;
  private ImageView imgSkrg, imgKmrn, imgTotal;
  private Context context;
  private int users, brosur, leads, target_call, deals, booking, target;
  private ApiEndPoint mApiService;
  public SharedPrefManager sharedPrefManager;
  private String dateSekarang, dateKemarin;

  private ImageView imgDefault;
  FloatingActionButton avatar;

  // orderNew
  private ArrayList<RespOrderStatus.ListOrderStatus> orderList;
  private ListOrderNew listOrderNew;
  private TextView iconKosong;

  // activity
  private ArrayList<RespActivityList.DataActivity> activities;
  private ListActivityAdapter activityAdapter;
  private RecyclerView recylerViewAktivitas;

  // outletPerformance
//  private ArrayList<RespOutletPerformance.ListOutletPerformance> outletPerformance;
//  private ListOutletAdapter listOutletAdapter;

  // lead/users
  private ArrayList<RespLeadUsers.DataListLeadUsers> listLeadUsers;
  private ListLeadUsersAdapter listLeadUsersAdapter;

  private ProgressBar progresKpi;
  private Integer offset = 3, limit, limits;
  private boolean itShouldLoadMore = true;

  public FHome(){
    //required
  }

  @SuppressLint("Assert")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle save){
    View view = inflater.inflate(fragment_fhome, container, false);


    boolean isConn = UtilsConnected.isNetworkConnected(context);
    limit = Integer.parseInt(getResources().getString(R.string.limituserhome));
    limits = Integer.parseInt(getResources().getString(R.string.limitlead));

    iconKosong = view.findViewById(R.id.koneksi2);
    avatar = view.findViewById(R.id.fabAvatar);

//    try {
//      avatar.setImageBitmap(textAsBitmap(""+sharedPrefManager.getSPName().charAt(0), 40, Color.DKGRAY));
//    } catch (Exception e) {
//      avatar.setImageBitmap(textAsBitmap("null", 40, Color.DKGRAY));
//    }
//    ic_bs = view.findViewById(R.id.ic_bs);
//    ic_bs.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        BsHome bs = new BsHome();
//        FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
//        bs.show(fm,bs.getTag());
//      }
//    });

    context = getContext();
    mApiService = UtilsApi.getAPIService();
    sharedPrefManager = new SharedPrefManager(context);

    imgKmrn = view.findViewById(R.id.imgKMRN);
    imgSkrg = view.findViewById(R.id.imgSKRG);
    imgTotal = view.findViewById(R.id.imgTOTAL);

    // greeting
//    tvPagi = view.findViewById(R.id.semangatPagi);
    tvName = view.findViewById(R.id.namaspv);
    tvDatelokasi = view.findViewById(R.id.tanggalLokasi);
    tvBulan = view.findViewById(R.id.bulan_ini);

    // card view
    tvSKRG = view.findViewById(R.id.tvSKRG);
    tvKMRN = view.findViewById(R.id.tvKMRN);
    tvSEMUA = view.findViewById(R.id.tvSEMUA);
    final int accentColor = getResources().getColor(R.color.colorPrimary);
    tvSKRG.setTextColor(accentColor);
    tvSEMUA.setTextColor(Color.DKGRAY);
    tvKMRN.setTextColor(Color.DKGRAY);


    // tambah aktifitas
    Button tambahaktifitas = view.findViewById(R.id.btnAktifitas);
    tambahaktifitas.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, TambahJadwalActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
      }
    });

    // count
    tvUsers = view.findViewById(R.id.tv_users);
    tvBrosur = view.findViewById(R.id.tv_brosur);
    tvLeads = view.findViewById(R.id.tv_leads);
    tvTargetCall = view.findViewById(R.id.tv_target_call);
    tvDeals = view.findViewById(R.id.tv_deals);
    tvBooking = view.findViewById(R.id.tv_booking);
    tvTarget = view.findViewById(R.id.tv_target);

    // rv list order
    limit = Integer.parseInt(getResources().getString(R.string.limituserhome));
    orderList = new ArrayList<>();
    RecyclerView rvListUser = view.findViewById(R.id.recylerViewOrder);
    listOrderNew = new ListOrderNew(getContext(), orderList);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    rvListUser.setLayoutManager(layoutManager);
    rvListUser.setAdapter(listOrderNew);
    rvListUser.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {

          if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
            if (itShouldLoadMore) {
              iconKosong.setVisibility(View.VISIBLE);
              getOrdersLoadMore();

            }
          }
        }
      }
    });

    recylerViewAktivitas = view.findViewById(R.id.recylerViewAktivitas);
    recylerViewAktivitas.setHasFixedSize(true);
    activities = new ArrayList<>();
    activityAdapter = new ListActivityAdapter(context, activities);
    LinearLayoutManager llm = new LinearLayoutManager(context);
    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
    recylerViewAktivitas.setLayoutManager(llm);
    recylerViewAktivitas.setAdapter(activityAdapter);
    recylerViewAktivitas.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0){
          if (!recyclerView.canScrollVertically(RecyclerView.FOCUSABLE_AUTO)){
            if (itShouldLoadMore) {
              ProgressDialog pd = new ProgressDialog(context);
              pd.setMessage("Loading...");
              pd.show();
              getactivityLoadMore();
            }
          }
        }
      }
    });

    // rv list outlet performances
//    outletPerformance = new ArrayList<>();
//    RecyclerView rvKpiOutlet = view.findViewById(R.id.rvKpiOutlet);
////    progresKpi = view.findViewById(R.id.progressKPI);
//    listOutletAdapter = new ListOutletAdapter(getContext(), outletPerformance);
//    LinearLayoutManager lmperformance = new LinearLayoutManager(getContext());
//    lmperformance.setOrientation(LinearLayoutManager.HORIZONTAL);
//    rvKpiOutlet.setLayoutManager(lmperformance);
//    rvKpiOutlet.setAdapter(listOutletAdapter);
//    rvKpiOutlet.addOnScrollListener(new RecyclerView.OnScrollListener() {
//      @Override
//      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
//        if (dy > 0) {
//          if (!recyclerView.canScrollHorizontally(RecyclerView.FOCUSABLE_AUTO)) {
//            if (itShouldLoadMore) {
//              progresKpi.setVisibility(View.VISIBLE);
//            }
//          }
//        }
//      }
//    });

    // rv list lead users
    listLeadUsers = new ArrayList<>();
    RecyclerView rvLeadUsers = view.findViewById(R.id.rvKpiOutlet);
    listLeadUsersAdapter = new ListLeadUsersAdapter(getContext(), listLeadUsers);
    LinearLayoutManager ll = new LinearLayoutManager(getContext());
    ll.setOrientation(LinearLayoutManager.HORIZONTAL);
    rvLeadUsers.setLayoutManager(ll);
    rvLeadUsers.setAdapter(listLeadUsersAdapter);
    rvLeadUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
          if (!recyclerView.canScrollHorizontally(RecyclerView.FOCUSABLE_AUTO)) {
            if (itShouldLoadMore) {
              ProgressDialog pd = new ProgressDialog(context);
              pd.setMessage("Loading...");
              pd.show();
            }
          }
        }
      }
    });

    // calendar
    Calendar startDate = Calendar.getInstance();
    startDate.add(Calendar.MONTH, -1);
    Calendar endDate = Calendar.getInstance();
    endDate.add(Calendar.MONTH, 1);
    inisiasiSalam();

    return view;
  }



  @SuppressLint("SimpleDateFormat")
  private void getDateNow(){
    final Calendar calendar = Calendar.getInstance();

    String bulanDateNow = String.valueOf(calendar.get(Integer.parseInt("0"+ Calendar.MONTH)) + 1);
    String tahunDateNow = String.valueOf(calendar.get(YEAR));

    if (!(!bulanDateNow.equals("10")
        || !bulanDateNow.equals("11")
        || !bulanDateNow.equals("12")
        || !bulanDateNow.equals("0"))) {

    } else {
      bulanDateNow = "" + bulanDateNow;
    }
    dateSekarang = tahunDateNow+"-"+bulanDateNow;


    int blnKemarin = Integer.parseInt(bulanDateNow) - 1;

    bulanDateNow = String.valueOf(blnKemarin);

    if (!(!bulanDateNow.equals("10")
        || !bulanDateNow.equals("11")
        || !bulanDateNow.equals("12")
        || !bulanDateNow.equals("0"))) {

    } else {
      bulanDateNow = "" + bulanDateNow;
    }

    if (bulanDateNow.equals("0")){
      bulanDateNow = "12";
      tahunDateNow = String.valueOf(Integer.parseInt(bulanDateNow)-1);
    }

    dateKemarin = ""+tahunDateNow+"-"+bulanDateNow;


    tvBulan.setText(dateSekarang);

  }

  @Override
  public void onStart() {
    super.onStart();
    getDateNow();
    getKPI();
    getDPerformance();
    getAktivitas();
    getOrders();
  }


  private void getKPI() {
    tvBulan.setText(dateSekarang);
    mApiService.kpi(
        sharedPrefManager.getSpBranchId(),
        dateSekarang
    ).enqueue(new Callback<RespKpi>() {
      @Override
      public void onResponse(Call<RespKpi> call, Response<RespKpi> response) {
        if (response.isSuccessful()) {
          if (response.body().getApiStatus() == 1) {
            try {
              users = response.body().getData().getUsers();
              brosur = response.body().getData().getBrosur();
              leads = response.body().getData().getLeads();
              target_call = response.body().getData().getTargetCall();
              deals = response.body().getData().getDeals();
              booking = response.body().getData().getBooking();
              target = response.body().getData().getTarget();

              Log.e("KPI :", "" + response.body().getData());
            } catch (Exception e) {
              e.printStackTrace();
            }
            getKPISKRG();
          }
        }
      }

      @Override
      public void onFailure(Call<RespKpi> call, Throwable t) {
        Toast.makeText(context, "Bukan ip local", Toast.LENGTH_SHORT).show();
      }
    });


  }

  private void getKPISKRG() {
    tvUsers.setText(String.valueOf(users));
    tvBrosur.setText(String.valueOf(brosur));
    tvLeads.setText(String.valueOf(leads));
    tvTargetCall.setText(String.valueOf(target_call));
    tvDeals.setText(String.valueOf(deals));
    tvBooking.setText(String.valueOf(booking));
    tvTarget.setText(String.valueOf(target));
  }
  private void getKPIKMRIN() {
    tvUsers.setText(String.valueOf(users));
    tvBrosur.setText(String.valueOf(brosur));
    tvLeads.setText(String.valueOf(leads));
    tvTargetCall.setText(String.valueOf(target_call));
    tvDeals.setText(String.valueOf(deals));
    tvBooking.setText(String.valueOf(booking));
    tvTarget.setText(String.valueOf(target));
  }
  private void getKPITOTAL() {
    tvUsers.setText(String.valueOf(users));
    tvBrosur.setText(String.valueOf(brosur));
    tvLeads.setText(String.valueOf(leads));
    tvTargetCall.setText(String.valueOf(target_call));
    tvDeals.setText(String.valueOf(deals));
    tvBooking.setText(String.valueOf(booking));
    tvTarget.setText(String.valueOf(target));
  }

  private void reportKPI() {
    // membedakan warna sebuah text ketika active dan tidak active
    final int accentColor = getResources().getColor(R.color.pencarianText);
    final int textsubcolor = getResources().getColor(R.color.textSubColor);

    imgSkrg.setOnClickListener(new View.OnClickListener() {
      @SuppressLint("SimpleDateFormat")
      @Override
      public void onClick(View v) {
        tvSKRG.setTextColor(accentColor);
        tvKMRN.setTextColor(textsubcolor);
        tvSEMUA.setTextColor(textsubcolor);
        tvBulan.setText(dateSekarang);
        mApiService.kpi(
            sharedPrefManager.getSpBranchId(),
            dateSekarang
        ).enqueue(new Callback<RespKpi>() {
          @Override
          public void onResponse(Call<RespKpi> call, Response<RespKpi> response) {
            if (response.isSuccessful()) {
              if (response.body() != null && response.body().getApiStatus() == 1) {
                try {
                  users = response.body().getData().getUsers();
                  brosur = response.body().getData().getBrosur();
                  leads = response.body().getData().getLeads();
                  target_call = response.body().getData().getTargetCall();
                  deals = response.body().getData().getDeals();
                  booking = response.body().getData().getBooking();
                  target = response.body().getData().getTarget();
                } catch (Exception e) {
                  e.printStackTrace();
                  Log.e("Error apa", "kpiskrg cek :  " + e);
                }
                getKPISKRG();
              }
            }
          }

          @Override
          public void onFailure(Call<RespKpi> call, Throwable t) {

          }
        });

      }
    });


    imgKmrn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        tvKMRN.setTextColor(accentColor);
        tvSKRG.setTextColor(textsubcolor);
        tvSEMUA.setTextColor(textsubcolor);
        tvBulan.setText(dateKemarin);
        mApiService.kpi(
            sharedPrefManager.getSpBranchId(),
            dateKemarin
        ).enqueue(new Callback<RespKpi>() {
          @Override
          public void onResponse(Call<RespKpi> call, Response<RespKpi> response) {
            if (response.isSuccessful()) {
              if (response.body()!=null && response.body().getApiStatus() == 1) {
                try {
                  users = response.body().getData().getUsers();
                  brosur = response.body().getData().getBrosur();
                  leads = response.body().getData().getLeads();
                  target_call = response.body().getData().getTargetCall();
                  deals = response.body().getData().getDeals();
                  booking = response.body().getData().getBooking();
                  target = response.body().getData().getTarget();
                } catch (Exception e) {
                  e.printStackTrace();
                }
                getKPIKMRIN();
              }
            }
          }

          @Override
          public void onFailure(Call<RespKpi> call, Throwable t) {
          }
        });

      }
    });

    imgTotal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        tvSEMUA.setTextColor(accentColor);
        tvSKRG.setTextColor(textsubcolor);
        tvKMRN.setTextColor(textsubcolor);
        tvBulan.setText("Total");
        mApiService.kpi(
            sharedPrefManager.getSpBranchId(),
            null
        ).enqueue(new Callback<RespKpi>() {
          @Override
          public void onResponse(Call<RespKpi> call, Response<RespKpi> response) {
            if (response.isSuccessful()) {
              if (response.body() != null && response.body().getApiStatus() == 1) {
                try {
                  users = response.body().getData().getUsers();
                  brosur = response.body().getData().getBrosur();
                  leads = response.body().getData().getLeads();
                  target_call = response.body().getData().getTargetCall();
                  deals = response.body().getData().getDeals();
                  booking = response.body().getData().getBooking();
                  target = response.body().getData().getTarget();
                } catch (Exception e) {
                  e.printStackTrace();
                }
                getKPITOTAL();
              }
            }
          }
          @Override
          public void onFailure(Call<RespKpi> call, Throwable t) {

          }
        });
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    getKPISKRG();
    reportKPI();
    getKPIKMRIN();
    getKPITOTAL();


  }


  private void getAktivitas() {
    itShouldLoadMore = false;
    mApiService.getListActivityHome(
        sharedPrefManager.getSpBranchId(),
        limit,
        0,
        dateSekarang
    ).enqueue(new Callback<RespActivityList>() {
      @Override
      public void onResponse(Call<RespActivityList> call, Response<RespActivityList> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            List<RespActivityList.DataActivity> dataActivityList = response.body().getData();
            activities.addAll(dataActivityList);
            activityAdapter.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onFailure(Call<RespActivityList> call, Throwable t) {

      }
    });
  }

  private void getactivityLoadMore() {
    itShouldLoadMore = false;
    mApiService.getListActivityHome(
        sharedPrefManager.getSpBranchId(),
        limit,
        0,
        dateSekarang
    ).enqueue(new Callback<RespActivityList>() {
      @Override
      public void onResponse(Call<RespActivityList> call, Response<RespActivityList> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            List<RespActivityList.DataActivity> ad = response.body().getData();
            activities.addAll(ad);
            activityAdapter.notifyDataSetChanged();
            int index = activities.size();
            offset = index;
          }
        }
      }

      @Override
      public void onFailure(Call<RespActivityList> call, Throwable t) {

      }
    });
  }

  private void getDPerformance() {
    itShouldLoadMore = false;
    mApiService.listLeadUsers(
        sharedPrefManager.getSpBranchId(),
        dateSekarang,
        limits,
        0)
        .enqueue(new Callback<RespLeadUsers>() {
      @Override
      public void onResponse(Call<RespLeadUsers> call, Response<RespLeadUsers> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            if (response.body().getData() != null) {
              List<RespLeadUsers.DataListLeadUsers> list = response.body().getData();
              listLeadUsers.addAll(list);
              listLeadUsersAdapter.notifyDataSetChanged();
            }
          }
        }
      }

      @Override
      public void onFailure(Call<RespLeadUsers> call, Throwable t) {
        itShouldLoadMore = true;
      }
    });

//    mApiService.outletPerformance(
//        sharedPrefManager.getSpBranchId(),
//        dateSekarang
//    ).enqueue(new Callback<RespOutletPerformance>() {
//      @Override
//      public void onResponse(Call<RespOutletPerformance> call, Response<RespOutletPerformance> response) {
//        if (response.isSuccessful()) {
//          itShouldLoadMore = false;
//          if (response.body() != null && response.body().getApiStatus() == 1) {
//            if (response.body().getData() != null) {
//              List<RespOutletPerformance.ListOutletPerformance> list = response.body().getData();
//              outletPerformance.addAll(list);
//              listOutletAdapter.notifyDataSetChanged();
//
//            }
//          }
//        }
//      }
//
//      @Override
//      public void onFailure(Call<RespOutletPerformance> call, Throwable t) {
//        itShouldLoadMore = true;
//
//      }
//    });
  }

  private void getOrders() {
    itShouldLoadMore = false;
    mApiService.orderNewList(
        sharedPrefManager.getSpBranchId(),
        sharedPrefManager.getSpOutletId(),
        dateSekarang,
        limit,
        0
    ).enqueue(new Callback<RespOrderStatus>() {
      @Override
      public void onResponse(Call<RespOrderStatus> call, Response<RespOrderStatus> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            List<RespOrderStatus.ListOrderStatus> list = response.body().getData();
            orderList.addAll(list);
            listOrderNew.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onFailure(Call<RespOrderStatus> call, Throwable t) {
        itShouldLoadMore = true;
        iconKosong.setVisibility(View.VISIBLE);
      }
    });
  }

  private void getOrdersLoadMore() {
    itShouldLoadMore = false;
    mApiService.orderNewList(
        sharedPrefManager.getSpBranchId(),
        sharedPrefManager.getSpOutletId(),
        dateSekarang,
        limit,
        offset
    ).enqueue(new Callback<RespOrderStatus>() {
      @Override
      public void onResponse(Call<RespOrderStatus> call, Response<RespOrderStatus> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().getApiStatus() == 1) {
            List<RespOrderStatus.ListOrderStatus> list = response.body().getData();
            orderList.addAll(list);
            listOrderNew.notifyDataSetChanged();
            int source = orderList.size();
            offset = source;
          }
        }
      }

      @Override
      public void onFailure(Call<RespOrderStatus> call, Throwable t) {
        itShouldLoadMore = true;
        iconKosong.setVisibility(View.VISIBLE);
      }
    });
  }

  @SuppressLint("SetTextI18n")
  private void inisiasiSalam() {
    // overview date
    Date date = new Date();
    // menentukan tanggal hari ini
    @SuppressLint("SimpleDateFormat")
    DateFormat df = new SimpleDateFormat("dd MMM, yyyy");
    String hariini = df.format(Calendar.getInstance().getTime());
    Calendar cal = Calendar.getInstance();
    // set waktu untuk greeting
    cal.setTime(date);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      // floating action button set huruf pertama nama user
      avatar.setImageIcon(textAsBitmap(""+sharedPrefManager.getSPName().charAt(0), 40, Color.BLACK));
    }
//    int hour = cal.get(Calendar.HOUR_OF_DAY);
//    String greeting = null;
//    if (hour >= 12 && hour < 17) {
//      greeting = "Selamat Siang,";
//    } else if (hour >= 17 && hour < 21) {
//      greeting = "Selamat Sore,";
//    } else if (hour >= 21 && hour < 24) {
//      greeting = "Selamat Malam,";
//    } else {
//      greeting = "Selamat Pagi,";
//    }
//    tvPagi.setText(greeting);
    tvName.setText(sharedPrefManager.getSPName());
    // menampilkan tanggal berdasarkan hari ini
    tvDatelokasi.setText(hariini + " | " + sharedPrefManager.getSpBranchName());

  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  public static Icon textAsBitmap(String text, float textSize, int textColor) {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(textSize);
    paint.setColor(textColor);
    paint.setTextAlign(Paint.Align.LEFT);
    float baseline = -paint.ascent(); // ascent() is negative
    int width = (int) (paint.measureText(text) + 0.0f); // round
    int height = (int) (baseline + paint.descent() + 0.0f);
    Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(image);
    canvas.drawText(text, 0, baseline, paint);
    return Icon.createWithBitmap(image);
  }

}
