package com.obvious.nasa.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by Jai on 27/11/2021.
 * Location India
 */

public class AlertDialogFragment extends DialogFragment {

    private static final String TITLE_KEY = "title";
    private static final String MESSAGE_KEY = "message";
    private static final String CANCEL_KEY = "cancel";
    private static final String POSITIVE_KEY = "ok";
    private static final String NEGATIVE_KEY = "cancel";
    private static final String DEFAULT_TEXT_CHANGED = "defaultTextChanged";
    private static final String ONLY_POSITIVE_BUTTON = "onlyPositiveButton";
    private static DialogInterface.OnClickListener m_listener;

    /**
     * Summary
     *
     * @param title    The title of the alert box.
     * @param message  The to be displayed.
     * @param listener Handle the user events.
     * @return Fragment value will be returned.
     */
    public static AlertDialogFragment instance(String title, String message, boolean cancel, DialogInterface.OnClickListener listener) {
        AlertDialogFragment fragment = new AlertDialogFragment();

        // Set parameters
        Bundle params = new Bundle();
        params.putString(TITLE_KEY, title);
        params.putString(MESSAGE_KEY, message);
        params.putBoolean(CANCEL_KEY, cancel);
        params.putBoolean(ONLY_POSITIVE_BUTTON, true);
        params.putString(POSITIVE_KEY, "Ok");
        fragment.setArguments(params);
        m_listener = listener;
        return fragment;
    }

    public static AlertDialogFragment instance(String title, String message, boolean cancel, boolean onlyPositiveButton, String positiveButton, DialogInterface.OnClickListener listener) {
        AlertDialogFragment fragment = new AlertDialogFragment();

        // Set parameters
        Bundle params = new Bundle();
        params.putString(TITLE_KEY, title);
        params.putString(MESSAGE_KEY, message);
        params.putBoolean(CANCEL_KEY, cancel);
        params.putBoolean(ONLY_POSITIVE_BUTTON, onlyPositiveButton);
        params.putString(POSITIVE_KEY, positiveButton);
        fragment.setArguments(params);
        m_listener = listener;
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(TITLE_KEY);
        String message = getArguments().getString(MESSAGE_KEY);

        // Create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title);
        builder.setMessage(message);

        if (getArguments().getBoolean(ONLY_POSITIVE_BUTTON)) {
            builder.setPositiveButton(getArguments().getString(POSITIVE_KEY), m_listener);
        }

        if (getArguments().getBoolean(CANCEL_KEY)) {
            builder.setNegativeButton(android.R.string.cancel, m_listener);
        }
        if (getArguments().getBoolean(DEFAULT_TEXT_CHANGED)) {
            builder.setNegativeButton(getArguments().getString(NEGATIVE_KEY), m_listener);
            builder.setPositiveButton(getArguments().getString(POSITIVE_KEY), m_listener);
        }

        Dialog alert = builder.create();
        setCancelable(false);

        return alert;
    }
}
