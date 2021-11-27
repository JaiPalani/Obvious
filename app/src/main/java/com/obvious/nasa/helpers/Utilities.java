package com.obvious.nasa.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;

import com.obvious.nasa.others.ObviousLog;

/**
 * Company: EBM
 * Created by Jai on 13/05/19.
 */

public class Utilities {
    private static final String TAG = "Utilities";
    private static String logFileDir                                    = "/storage/emulated/0/";
    private static String logFileName                                   = "FanConfigurator_log.txt";

    /**
     * Method to create progress dialog without text
     */
    public static CustomProgressDialogue createProgressDialog(Context context) {
        CustomProgressDialogue dialog = new CustomProgressDialogue(context);

        try {
            //dialog.setTitle(context.getString(R.string.please_wait));
            //dialog.setMessage(context.getString(R.string.reading_data));
            //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        } catch (WindowManager.BadTokenException exception) {
            ObviousLog.d(TAG, "Caught Exception" + exception.getMessage());
        }
        return dialog;
    }

    public static Boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo wifiInfo, mobileInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiInfo.getState() == NetworkInfo.State.CONNECTED || mobileInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        } catch (Exception exception) {
            ObviousLog.d(TAG, "CheckConnectivity Exception: " + exception.getMessage());
        }
        return false;
    }
}
