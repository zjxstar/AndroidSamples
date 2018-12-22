package com.zjx.dialog.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static com.zjx.dialog.sample.SecondActivity.TAG;

public class FragmentA extends Fragment {


    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "FragmentA onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "FragmentA onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "FragmentA onCreateView: ");
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "FragmentA onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "FragmentA onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "FragmentA onViewStateRestored: ");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "FragmentA onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "FragmentA onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "FragmentA onPause: ");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "FragmentA onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        Log.d(TAG, "FragmentA onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "FragmentA onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "FragmentA onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "FragmentA onDetach: ");
        super.onDetach();
    }
}
