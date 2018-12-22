package com.zjx.dialog.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, new FragmentA(), "a");
        ft.commit();
        Log.d(TAG, "onCreate: end");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.d(TAG, "onAttachFragment: start");
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment: end");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: start");
        super.onStart();
        Log.d(TAG, "onStart: end");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: start");
        super.onResume();
        Log.d(TAG, "onResume: end");
    }

    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: start");
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: end");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: start");
        super.onRestart();
        Log.d(TAG, "onRestart: end");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: start");
        super.onPause();
        Log.d(TAG, "onPause: end");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: start");
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: end");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: start");
        super.onStop();
        Log.d(TAG, "onStop: end");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: start");
        super.onDestroy();
        Log.d(TAG, "onDestroy: end");
    }

    @Override
    public void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow: start");
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: end");
    }
}
