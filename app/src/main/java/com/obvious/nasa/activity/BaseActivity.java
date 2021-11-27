package com.obvious.nasa.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.obvious.nasa.R;
import com.obvious.nasa.fragment.AlertDialogFragment;
import com.obvious.nasa.helpers.CustomProgressDialogue;
import com.obvious.nasa.helpers.Utilities;
import com.obvious.nasa.others.ObviousLog;


/**
 * Created by Jai on 27/11/2021.
 * Location India
 */
public class BaseActivity extends AppCompatActivity {
    public static Context mContext;
    public final String TAG = getClass().getSimpleName();
    private static DialogInterface.OnClickListener m_listener_custome;
    private CustomProgressDialogue mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        ObviousLog.v(TAG, "onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ObviousLog.v(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ObviousLog.v(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ObviousLog.v(TAG, "onResume() in base activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ObviousLog.v(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ObviousLog.v(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObviousLog.v(TAG, "onDestroy()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ObviousLog.v(TAG, "onNewIntent()");
    }

    @Override
    public void onBackPressed() {
        ObviousLog.v(TAG, "onBackPressed()");
        showAlertDialog(getString(R.string.info_title), getString(R.string.app_close_msg), true, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (which == -1) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//***Change Here***
                    startActivity(intent);
                    finish();
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ObviousLog.v(TAG, "onConfigurationChanged()");
    }

    /**
     * Method to hide the window keyboard with out view
     */
    public void dismissKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Method to show the Progress dialog
     */
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = Utilities.createProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        } else {
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    /**
     * Method to hide Progress dialog.
     */
    public void hideProgressDialog() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to show alert dialog.
     *
     * @param message Message for the alert.
     */
    public void showAlertDialog(String title, String message) {
        try {
            AlertDialogFragment alertDialog = AlertDialogFragment.instance(title, message, false, null);
            alertDialog.show(getFragmentManager(), "alertDialog");
        } catch (WindowManager.BadTokenException e) {
            ObviousLog.e(TAG, "Exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to show alert dialog.
     *
     * @param title    The title for the alert.
     * @param message  Message for the alert.
     * @param cancel   The cancel.
     * @param listener Handle the user events.
     */
    public void showAlertDialog(String title, String message, boolean cancel, DialogInterface.OnClickListener listener) {
        try {
            AlertDialogFragment alertDialog = AlertDialogFragment.instance(title, message, cancel, listener);
            alertDialog.show(getFragmentManager(), "alertDialog");
        } catch (WindowManager.BadTokenException e) {
            ObviousLog.e(TAG, "Exception: " + e.getMessage());
        }
    }
}