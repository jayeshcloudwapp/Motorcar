package com.cw.motorcar.driver.ui.fragment.request;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cw.motorcar.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRequestFragment extends Fragment {

    @BindView(R.id.tv_toolbar_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_request, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        tvTitle.setText(getString(R.string.my_request));
        ivBack.setVisibility(View.GONE);
        tabLayout.setupWithViewPager(view_pager);
        createViewPager(view_pager);
        createTab();

    }

    private void createTab() {
        String[] tabname = {"Active", "In Progress", "Completed", "Cancelled"};

        for (int i = 0; i < tabname.length; i++) {
            TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tab, null);
            view.setText(tabname[i]);
            tabLayout.getTabAt(i).setCustomView(view);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = (TextView) tab.getCustomView();
                textView.setTextColor(getContext().getColor(R.color.black));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = (TextView) tab.getCustomView();
                textView.setTextColor(getContext().getColor(R.color.text2));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new RequestItemFragment(), "Active");
        adapter.addFrag(new InProgresFragment(), "In Progress");
        adapter.addFrag(new CompleteFragment(), "Completed");
        adapter.addFrag(new CanceledFragment(), "Cancelled");
        viewPager.setAdapter(adapter);
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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}