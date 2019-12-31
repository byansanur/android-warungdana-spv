package com.byandev.warnaspvdev.MainFragment.FragmentOrder.EditOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.MainActivity.DetailOrdersActivity;
import com.byandev.warnaspvdev.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentEdit extends Fragment {
  private androidx.appcompat.widget.Toolbar toolbar;
  SharedPrefManager sharedPrefManager;
  ApiEndPoint mApiService;
  Context context;
  ImageView back;
  ImageView editIcon;

  public FragmentEdit() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
    View view = inflater.inflate(R.layout.fragment_edit, container, false);

    back = view.findViewById(R.id.backButton);
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(context, DetailOrdersActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
      }
    });
    context = getActivity();
    toolbar = view.findViewById(R.id.toolbar);
    ViewPager viewPager = view.findViewById(R.id.frame_container);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
    adapter.addFragment(new FragmentEditOrder(), "Detail");
    viewPager.setAdapter(adapter);
    sharedPrefManager = new SharedPrefManager(getContext());
    mApiService = UtilsApi.getAPIService();
    return view;
  }

  @Override
  public void onResume(){
    super.onResume();
  }


  /**
   * Class viePageAdapter
   * Add other fragment
   */
  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
      super(fm);
    }
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
