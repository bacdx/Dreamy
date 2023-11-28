package com.example.dreamy.UI.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialogVoucher extends BottomSheetDialogFragment {
    private Context context;
    public BottomSheetDialogVoucher(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view=LayoutInflater.from(getContext()).inflate().
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
