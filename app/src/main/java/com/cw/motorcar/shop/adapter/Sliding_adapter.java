package com.cw.motorcar.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.cw.motorcar.R;

public class Sliding_adapter extends PagerAdapter {

    private final LayoutInflater inflater;
//    private ArrayList<Images> images;
    Context context;
    public Sliding_adapter(Context context) {
        this.context = context;
//        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        View imageLayout = inflater.inflate(R.layout.custom_slide_image, container, false);

        if(imageLayout!=null) {

            final ImageView imageView = (ImageView) imageLayout
                    .findViewById(R.id.iv_slideimg);
            container.addView(imageLayout, 0);
        }

        return imageLayout;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
