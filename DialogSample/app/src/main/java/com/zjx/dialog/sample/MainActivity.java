package com.zjx.dialog.sample;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mStackLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        findViewById(R.id.show_alert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    public void showDialog() {
        mStackLevel++;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = MyDialogFragment.newInstance(mStackLevel);
        newFragment.show(ft, "dialog");
    }

    public void showAlertDialog() {
        DialogFragment alertDialog = MyAlertDialogFragment.newInstance(R.string.app_name);
        alertDialog.show(getSupportFragmentManager(), "alert");
    }

    public void doPositiveClick() {
        Toast.makeText(this, "Alert Positive Clicked", Toast.LENGTH_SHORT).show();
    }

    public void doNegativeClick() {
        Toast.makeText(this, "Alert Negative Clicked", Toast.LENGTH_SHORT).show();
    }

    public void showSimpleDialogAsDialog(View view) {
        DialogFragment simple = MySimpleDialogFragment.newInstance();
        simple.show(getSupportFragmentManager(), "dialog");
    }

    public void showSimpleDialogAsViewHierarchy(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        DialogFragment simple = MySimpleDialogFragment.newInstance();
        ft.add(R.id.embedded, simple);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void toSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
