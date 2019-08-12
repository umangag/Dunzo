package com.umang.dunzo;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mPrgDialog;

    public void showProgressDialog(String title, boolean cancelable) {
        if (mPrgDialog == null) {
            mPrgDialog = new ProgressDialog(this);
        }

        if (mPrgDialog.isShowing()) {
            return;
        }

        mPrgDialog.setTitle(title);
        mPrgDialog.setCanceledOnTouchOutside(cancelable);
        mPrgDialog.setCancelable(cancelable);
        mPrgDialog.show();
    }

    public synchronized void hideProgressDialog() {
        try {
            if (mPrgDialog != null && mPrgDialog.isShowing()) {
                mPrgDialog.dismiss();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Check internet connection
    public boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isAvailable = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if (!isAvailable)
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
