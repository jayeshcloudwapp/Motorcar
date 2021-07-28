package com.cw.motorcar.driver.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cw.motorcar.R;
import com.cw.motorcar.driver.ui.activity.auth.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    @BindView(R.id.layoutDots)
    LinearLayout dotsLayout;
    @BindView(R.id.tv_continue)
    TextView tvContinue;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    private TextView[] dots;
    private int[] layouts;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    private void initView() {
        context = WelcomeActivity.this;
        ButterKnife.bind(this);
            layouts = new int[]{
                    R.layout.welcome_slide1,
                    R.layout.welcome_slide2,
                    R.layout.welcome_slide3,
                    R.layout.welcome_slide4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            switch (position) {
                case 0:
                    tvContinue.setVisibility(View.GONE);
                    break;
                case 1:
                    tvContinue.setVisibility(View.GONE);
                    break;
                case 2:
                    tvContinue.setVisibility(View.GONE);
                    break;
                case 3:
                    tvContinue.setVisibility(View.VISIBLE);
                    break;

            }
            // changing the next button text 'NEXT' / 'GOT IT'
//            if (position == layouts.length - 1) {
//                // last page. make button text to GOT IT
//                //btnNext.setText(getString(R.string.start));
//                btnSkip.setVisibility(View.GONE);
//            } else {
//                // still pages are leftchangeStatusBarColor
//                //btnNext.setText(getString(R.string.next));
//                btnSkip.setVisibility(View.GONE);
//            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            // dots[i].setBackground(getResources().getDrawable(R.drawable.circle_background));
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {

        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @OnClick({R.id.tv_skip, R.id.tv_continue})
    void onForword() {
        startActivity(new Intent(context, LoginActivity.class));
    }

}