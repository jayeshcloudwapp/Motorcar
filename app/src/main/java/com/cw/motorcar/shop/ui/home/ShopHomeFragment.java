package com.cw.motorcar.shop.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cw.motorcar.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopHomeFragment extends Fragment {

    private Context context;
    @BindView(R.id.tv_toolbar_title)
    TextView title;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tb_shop)
    TabLayout tbShop;
    @BindView(R.id.vp_shop)
    ViewPager vpShop;
    PagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        ButterKnife.bind(this, view);
        ivBack.setVisibility(View.GONE);
        title.setText("Shop");
        setupTabIcons();
    }

    private void setupTabIcons() {
        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        vpShop.setAdapter(pagerAdapter);
        tbShop.setupWithViewPager(vpShop);
        createTab();
    }

    private void createTab() {
        String[] tabname = {getString(R.string.categories), getString(R.string.brands)};

        for (int i = 0; i < tabname.length; i++) {
            TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_shop_tab, null);
            view.setText(tabname[i]);
            tbShop.getTabAt(i).setCustomView(view);
        }
        TextView textView = (TextView) tbShop.getTabAt(0).getCustomView();
        textView.setTextColor(getContext().getColor(R.color.black));

        tbShop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                TextView textView = (TextView) tab.getCustomView();
                textView.setTextColor(getContext().getColor(R.color.black));
            }
        });
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        private String tabTitles[] = new String[]{getString(R.string.categories), getString(R.string.brands)};

        public PagerAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new CategoryFragment();
                    break;
                case 1:
                    fragment = new BrandFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}