package com.byandev.warnaspvdev.MainFragment.FragmentOrder.DetailOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.BottomSheet.BsEditOrder;
import com.byandev.warnaspvdev.MainActivity.DetailOrdersActivity;
import com.byandev.warnaspvdev.MainActivity.EditOrderActivity;
import com.byandev.warnaspvdev.MainActivity.HomeActivity;
import com.byandev.warnaspvdev.R;
import com.byandev.warnaspvdev.Response.RespOrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderFragmentDetail extends Fragment {

    private androidx.appcompat.widget.Toolbar toolbar;
    SharedPrefManager sharedPrefManager;
    ApiEndPoint mApiService;
    Context context;
    ImageView back;
    ImageView editIcon;
    private Integer id, idMstOrder;

    public OrderFragmentDetail() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        View view = inflater.inflate(R.layout.order_fragment, container, false);
        id = ((DetailOrdersActivity)getActivity()).getId();
        idMstOrder = ((DetailOrdersActivity)getActivity()).getIdMstorder();
//        Bundle bundle = getArguments();
//        id = bundle.getInt("id");
//        idOrderStatus = bundle.getInt("idOrderStatus");

        back = view.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomeActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });
        editIcon = view.findViewById(R.id.editIcon);
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent a = new Intent(context, EditOrderActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                a.putExtra("id", id);
//                a.putExtra("idOrderMstStatus", idMstOrder);
//                startActivity(a);
                BsEditOrder bsEditOrder = new BsEditOrder();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putInt("idMstOrder", idMstOrder);
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bsEditOrder.setArguments(bundle);
                bsEditOrder.show(fragmentManager, bsEditOrder.getTag() );
            }
        });

        if (idMstOrder != 5) {
            editIcon.setVisibility(View.VISIBLE);
        } else editIcon.setVisibility(View.GONE);

        context = getActivity();
        toolbar = view.findViewById(R.id.toolbar);
        ViewPager viewPager = view.findViewById(R.id.frame_container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentDetailOrders(), "Detail");
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
