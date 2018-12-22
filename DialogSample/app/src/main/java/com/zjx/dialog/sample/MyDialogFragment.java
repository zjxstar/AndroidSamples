package com.zjx.dialog.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogFragment extends DialogFragment {

    private static final String TAG = MyDialogFragment.class.getSimpleName();
    int mNum;

    static MyDialogFragment newInstance(int num) {
        MyDialogFragment f = new MyDialogFragment();
        Bundle arg = new Bundle();
        arg.putInt("num", num);
        f.setArguments(arg);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        TextView tv = v.findViewById(R.id.text);
        tv.setText("Dialog #" + mNum + ": using style "
                + getNameForNum(mNum));
        Button button = v.findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showDialog();
            }
        });

        return v;
    }

    private String getNameForNum(int num) {
        String result = "";
        switch ((mNum-1)%6) {
            case 1: result = "DialogFragment.STYLE_NO_TITLE"; break;
            case 2: result = "DialogFragment.STYLE_NO_FRAME"; break;
            case 3: result = "DialogFragment.STYLE_NO_INPUT"; break;
            case 4: result = "DialogFragment.STYLE_NORMAL"; break;
            case 5: result = "DialogFragment.STYLE_NORMAL"; break;
            case 6: result = "DialogFragment.STYLE_NO_TITLE"; break;
            case 7: result = "DialogFragment.STYLE_NO_FRAME"; break;
            case 8: result = "DialogFragment.STYLE_NORMAL"; break;
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d(TAG, "onDismiss: ");
        super.onDismiss(dialog);
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
