package com.kumar.ranjan.mobilephone.screen.dialog;

import com.kumar.ranjan.mobilephone.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SortOptionDialogFragment extends DialogFragment {

    private SortOptionSelectionListener sortOptionSelectionListener;

    @Override public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof SortOptionSelectionListener) {
            sortOptionSelectionListener = (SortOptionSelectionListener) getActivity();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setItems(R.array.sorting_options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        notifyActivityToPerformSorting(SortOptionType.PRICE_LOW_TO_HIGH);
                        break;
                    case 1:
                        notifyActivityToPerformSorting(SortOptionType.PRICE_HIGH_TO_LOW);
                        break;
                    case 2:
                        notifyActivityToPerformSorting(SortOptionType.RATING_5_To_1);
                        break;
                }
            }
        });

        return builder.create();
    }

    private void notifyActivityToPerformSorting(SortOptionType sortOptionType) {
        if (sortOptionSelectionListener != null) {
            sortOptionSelectionListener.onSortOptionSelected(sortOptionType);
        }
    }

    public interface SortOptionSelectionListener {
        void onSortOptionSelected(SortOptionType sortOptionType);
    }
}