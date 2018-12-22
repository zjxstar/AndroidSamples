package com.zjx.dialog.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MySimpleDialogFragment extends DialogFragment {

    public static MySimpleDialogFragment newInstance() {
        return new MySimpleDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_dialog, container, false);
        TextView tv = view.findViewById(R.id.text);
        tv.setText("this is an instance of MySimpleDialogFragment");
        return view;
    }
}
