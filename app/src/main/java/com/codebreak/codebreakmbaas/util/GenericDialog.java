package com.codebreak.codebreakmbaas.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by PedroFelipe on 12/11/2015.
 */
public class GenericDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String EXTRA_ID = "id";
    private static final String EXTRA_MESSAGE = "message";
    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_BUTTONS = "buttons";
    private static final String DIALOG_TAG = "SimpleDialog";

    private int mDialogId;

    public static GenericDialog newDialog(int id, int title, int message, int[] buttonsTexts, boolean isCancelable) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_ID, id);
        bundle.putInt(EXTRA_TITLE, title);
        bundle.putInt(EXTRA_MESSAGE, message);
        bundle.putIntArray(EXTRA_BUTTONS, buttonsTexts);

        GenericDialog genericDialog = new GenericDialog();
        genericDialog.setArguments(bundle);
        genericDialog.setCancelable(isCancelable);
        return genericDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialogId = getArguments().getInt(EXTRA_ID);
        int title = getArguments().getInt(EXTRA_TITLE);
        int message = getArguments().getInt(EXTRA_MESSAGE);
        int[] buttons = getArguments().getIntArray(EXTRA_BUTTONS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);

        switch (buttons.length) {
            case 3:
                builder.setNeutralButton(buttons[2], this);

            case 2:
                builder.setNegativeButton(buttons[1], this);

            case 1:
                builder.setPositiveButton(buttons[0], this);
        }

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Activity activity = getActivity();
        if (activity instanceof OnClickDialog) {
            OnClickDialog listener = (OnClickDialog) activity;
            listener.onClick(mDialogId, which);
        }
    }

    public void open(FragmentManager fragmentManager) {
        Fragment dialogFragment = fragmentManager.findFragmentByTag(DIALOG_TAG);

        if (dialogFragment == null) {
            show(fragmentManager, DIALOG_TAG);
        }
    }

    public interface OnClickDialog {
        void onClick(int id, int button);
    }
}
