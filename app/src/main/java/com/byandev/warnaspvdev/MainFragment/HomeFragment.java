package com.byandev.warnaspvdev.MainFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.arthurivanets.bottomsheets.BottomSheet;
import com.byandev.warnaspvdev.Adapter.ListUsersAdapter;
import com.byandev.warnaspvdev.BottomSheet.BsHome;
import com.byandev.warnaspvdev.MainActivity.HomeActivity;
import com.byandev.warnaspvdev.MainActivity.LoginActivity;
import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainFragment.FragmentHome.FHome;
import com.byandev.warnaspvdev.Menu.UbahPassword;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderStatus;
import com.byandev.warnaspvdev.Response.RespUsers;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment
    implements androidx.appcompat.widget.Toolbar.OnMenuItemClickListener{

    private androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewPager;
    SharedPrefManager sharedPrefManager;
    ApiEndPoint mApiService;
    String userAgent;
    Integer userId, lastLogin;
    Context context;
    TextView selamatPagi;
    CardView CardView;

    TextView tvSearch;
    RecyclerView recyclerView;

    ArrayList<RespUsers.RespListUsers> users;
    ListUsersAdapter listUser;

    Call<RespUsers> call = null;

//    private BottomSheetBehavior behavior;
//    private View bottomSheet;
//    private BottomSheetDialog mBottomSheetDialog;
//    ImageView more;

    public HomeFragment(){
        //required empty public constructor
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle Save){
        View view = inflater.inflate(R.layout.fragment_home, container,false);


        tvSearch = view.findViewById(R.id.tvPencarian);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvSearch.setVisibility(View.GONE);

        context = getActivity();
        toolbar = view.findViewById(R.id.Toolbars);
        toolbar.inflateMenu(R.menu.menu_home_2);
        toolbar.setOnMenuItemClickListener(this);
//        more = view.findViewById(R.id.ic_more);
//        more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showBottomSheetDialog();
//            }
//        });
//        bottomSheet = view.findViewById(R.id.bottom_sheet);
//        behavior = BottomSheetBehavior.from(bottomSheet);
        viewPager = view.findViewById(R.id.frame_container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FHome(), "Dashboard");
        viewPager.setAdapter(adapter);
        sharedPrefManager = new SharedPrefManager(getContext());
        selamatPagi = view.findViewById(R.id.selamatPagi);
        mApiService = UtilsApi.getAPIService();
        userAgent = System.getProperty("http.agent");
        userId = sharedPrefManager.getSpId();
        lastLogin = sharedPrefManager.getSpLastLogin();
        inisiasiSalam();
        return view;
    }

//    private void showBottomSheetDialog() {
//        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
//            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        }
//        final View view = getLayoutInflater().inflate(R.layout.bs_home, null);
//        mBottomSheetDialog = new BottomSheetDialog(context);
//        mBottomSheetDialog.setContentView(view);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        mBottomSheetDialog.show();
//        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                mBottomSheetDialog = null;
//            }
//        });
//
//    }

//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_order, menu);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//
//        final SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search cfa name");
//
//        try {
//            EditText etSearch = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
//            etSearch.setTextColor(getResources().getColor(R.color.pencarianText));
//            etSearch.setHintTextColor(getResources().getColor(R.color.pencarianText));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                searchView.setQuery("", false);
//                tvSearch.setVisibility(View.GONE);
//                recyclerView.setVisibility(RecyclerView.INVISIBLE);
//                users.clear();
//                listUser.notifyDataSetChanged();
//                return true;
//            }
//        });
//
//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvSearch.setVisibility(TextView.VISIBLE);
//            }
//        });
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (call != null) {
//                    call.cancel();
//                }
//                if (newText.length() >= 1) {
//                    tvSearch.setVisibility(View.GONE);
//                    callFilter(newText);
//                } else {
//                    users.clear();
//                    listUser.notifyDataSetChanged();
//                }
//                return false;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    private void callFilter(String newText) {
//        tvSearch.setVisibility(View.GONE);
//        tvSearch.setText("Finding...");
//        call = mApiService.searchUsers(
//            sharedPrefManager.getSpBranchId(),
//            newText
//        );
//        call.enqueue(new Callback<RespUsers>() {
//            @Override
//            public void onResponse(Call<RespUsers> call, Response<RespUsers> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().getData() != null) {
//                        tvSearch.setText("Search result");
//                        users.clear();
//                        listUser.notifyDataSetChanged();
//                        recyclerView.setVisibility(View.VISIBLE);
//
//                        List<RespUsers.RespListUsers> list = response.body().getData();
//
//                        if (list.size() <= 0) {
//                            tvSearch.setText("Search not founds");
//                        }
//
//                        users.addAll(list);
//                        listUser.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RespUsers> call, Throwable t) {
//                if (call.isCanceled()) {
//                }else {
//                    tvSearch.setText("Check your connenction");
//                }
//            }
//        });
//    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ganti:
                Intent intent = new Intent(getContext(), UbahPassword.class);
                startActivity(intent);
                return true;
            case R.id.switchTheme:
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setMessage("Changes theme ?").setCancelable(true)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes please!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                } else {
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                }
                                startActivity(new Intent(context, HomeActivity.class));
                                getActivity().finish();
                            }
                        })

                        .show();
                return true;
            case R.id.logout:
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getContext());
                alertdialog.setMessage("Do you want to sign out ?").setCancelable(true)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPrefManager.clearSharedPreferences();
//                                mApiService.logout(userAgent, "Logout", userId,""
//                                        + BuildConfig.VERSION_NAME).enqueue(new Callback<ResponseBody>() {
//                                    @Override
//                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                    }
//                                });
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                return true;
        }
        return false;
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
        int hour = cal.get(Calendar.HOUR_OF_DAY);
    String greeting = null;
    if (hour >= 12 && hour < 17) {
      greeting = "Selamat Siang";
    } else if (hour >= 17 && hour < 21) {
      greeting = "Selamat Sore";
    } else if (hour >= 21 && hour < 24) {
      greeting = "Selamat Malam";
    } else {
      greeting = "Selamat Pagi";
    }
    selamatPagi.setText(greeting);
//        tvName.setText(sharedPrefManager.getSPName());
//        // menampilkan tanggal berdasarkan hari ini
//        tvDatelokasi.setText(hariini + " | " + sharedPrefManager.getSpBranchName());

    }

    private void loginTimeOut() {
        Integer hasil = 0;
        Calendar calendar = Calendar.getInstance();
        Integer minute = calendar.get(Calendar.MINUTE);

        hasil = minute - lastLogin;

        if (hasil > 1){
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//            mApiService.userLogs(userAgent, "Logout", userId,""+BuildConfig.VERSION_NAME)
//                    .enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(context, "Not Responding", Toast.LENGTH_SHORT).show();
//                }
//            });
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) { super(fm); }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
    }
}

