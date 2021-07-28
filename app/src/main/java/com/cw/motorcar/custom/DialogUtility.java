package com.cw.motorcar.custom;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cw.motorcar.R;
import com.google.android.material.snackbar.Snackbar;


public class DialogUtility {
    private static ProgressDialog mProgressDialog;

  /*  public static Dialog showProgressDialog(Context context, boolean isCancelable, String message) {
        mProgressDialog = new ProgressDialog(context, R.style.MyAlertDialogStyle);
        mProgressDialog.setMessage(message);

        DoubleBounce doubleBounce = new DoubleBounce();
        mProgressDialog.setIndeterminateDrawable(doubleBounce);

        mProgressDialog.show();

        mProgressDialog.setCancelable(isCancelable);

        return mProgressDialog;
    }*/

    public static void pauseProgressDialog() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.cancel();
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean CheckNetwork(Context context, View view) {
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else {
            connected = false;
            //Toast.makeText(context,"Check Your Network Connection",Toast.LENGTH_LONG).show();

            Snackbar.make(view, R.string.no_internate_connection, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }

        return connected;
    }


    /* public static void showErrorDialog(final Context context, String msgResId) {
         final Dialog dialog = new Dialog(context);
         dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
             @Override
             public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                 // Prevent dialog close on back press button
                 return keyCode == KeyEvent.KEYCODE_BACK;
             }
         });
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         dialog.setCanceledOnTouchOutside(false);
         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
         dialog.setContentView(R.layout.dialog_error);

         TextView messageTv = (TextView) dialog.findViewById(R.id.tv_message);
         messageTv.setText(msgResId);

         TextView okBtn = (TextView) dialog.findViewById(R.id.btn_ok);
         okBtn.setTag(dialog);
         okBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.dismiss();
             }
         });
         dialog.show();
     }
 */
    public static void showToast(Context context, String msg) {
        try {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){

        }
    }

}
