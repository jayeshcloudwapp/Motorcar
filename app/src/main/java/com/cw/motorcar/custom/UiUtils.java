package com.cw.motorcar.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cw.motorcar.R;
import com.google.android.material.snackbar.Snackbar;


/**
 * Created by Jay on 24/10/17.
 */

public class UiUtils {
    public static void animateFadeHide(Context context, View view) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            Animation animFadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
            view.startAnimation(animFadeOut);
            view.setVisibility(View.GONE);
        }
    }

    public static void animateFadeShow(Context context, View view) {
        if (view.getVisibility() == View.GONE) {
            Animation animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            view.startAnimation(animFadeIn);
            view.setVisibility(View.VISIBLE);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public static void showMessage(Context context, View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction("Action", null);
        snackbar.show();
    }



}
