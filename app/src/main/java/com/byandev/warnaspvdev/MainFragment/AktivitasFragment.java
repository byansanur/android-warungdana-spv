package com.byandev.warnaspvdev.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.Helper.ViewPagerAdapter;
import com.byandev.warnaspvdev.MainActivity.TambahJadwalActivity;
import com.byandev.warnaspvdev.MainFragment.FragmentAktivitas.FragmentActivityTemplate;
import com.byandev.warnaspvdev.MainFragment.FragmentAktivitas.FragmentActivitys;
import com.byandev.warnaspvdev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AktivitasFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ApiEndPoint mApiService;
    SharedPrefManager sharedPrefManager;
    Context context;
    Integer idUser;
    private ImageView addJadwal;
//    FloatingActionButton addActivity;

    private boolean isConn = true;

    RecyclerView recyclerView;

    public AktivitasFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        View view =  inflater.inflate(R.layout.fragment_aktivitas, container, false);
         mApiService = UtilsApi.getAPIService();
         context = getContext();
         sharedPrefManager = new SharedPrefManager(context);
         idUser = sharedPrefManager.getSpId();

         addJadwal = view.findViewById(R.id.addJadwal);
         addJadwal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             startActivity(new Intent(context, TambahJadwalActivity.class)
                 .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
             getActivity().finish();
           }
         });

         toolbar = view.findViewById(R.id.toolbar);
//         toolbar.setTitle("Daily Activity");


         tabLayout = view.findViewById(R.id.tabsHome);
         viewPager = view.findViewById(R.id.frame_container);
         ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
         adapter.addFragment(new FragmentActivitys(), "Activity");
         adapter.addFragment(new FragmentActivityTemplate(), "Template");
         viewPager.setAdapter(adapter);
         tabLayout.setupWithViewPager(viewPager);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

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
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
