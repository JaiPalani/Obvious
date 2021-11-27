package com.obvious.nasa.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.obvious.nasa.R;
/**
 * Created by Jai on 17/07/19.
 * Company: ebmpapst
 * Location India
 */

public class CustomProgressDialogue extends Dialog {
    public CustomProgressDialogue(Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_progress_dialog, null);
        setContentView(view);
    }
}