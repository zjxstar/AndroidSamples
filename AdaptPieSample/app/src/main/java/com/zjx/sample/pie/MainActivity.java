package com.zjx.sample.pie;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        showDisplayCutout();
    }

    // SDK 28以上才会有DisplayCutout
    @TargetApi(28)
    private void showDisplayCutout() {
        final View decorView = getWindow().getDecorView();
//        decorView.post(new Runnable() {
//            @Override
//            public void run() {
//            如果想在声明后期中获取刘海数据，必须要在Runnable中操作，否则会有空指针
//            }
//        });

    }

    @TargetApi(28)
    public void showDisplayCutout(View view) {
        // 注意：getRootWindowInsets()只有在视图加载完成后，才会返回，否则返回null
        DisplayCutout cutout = getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
        if (cutout == null) { // 没有刘海的时候拿到的DisplayCutout为null
            Log.d(TAG, "showDisplayCutout: 普通屏，没有刘海");
            return;
        }
        List<Rect> rects = cutout.getBoundingRects(); // 获取可能的凹凸块
        if (rects.size() == 1) {
            Log.d(TAG, "showDisplayCutout: 有刘海");
            Rect rect = rects.get(0);
            Log.d(TAG, "showDisplayCutout: 刘海区域：" + rect);
        } else {
            Log.d(TAG, "showDisplayCutout: 有刘海和下巴");
            for (Rect rect : rects) {
                Log.d(TAG, "showDisplayCutout: 区域：" + rect);
            }
        }

        // 获取安全区域的数据，单位px
        int safeInsetLeft = cutout.getSafeInsetLeft();
        int safeInsetTop = cutout.getSafeInsetTop();
        int safeInsetRight = cutout.getSafeInsetRight();
        int safeInsetBottom = cutout.getSafeInsetBottom();
        Log.d(TAG, "showDisplayCutout: 安全区域距离屏幕左边：" + safeInsetLeft);
        Log.d(TAG, "showDisplayCutout: 安全区域距离屏幕顶部：" + safeInsetTop);
        Log.d(TAG, "showDisplayCutout: 安全区域距离屏幕右边：" + safeInsetRight);
        Log.d(TAG, "showDisplayCutout: 安全区域距离屏幕底部：" + safeInsetBottom);

        int statusBarHeight = getStatusBarHeight();
        Log.d(TAG, "showDisplayCutout: 状态栏高度：" + statusBarHeight);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void toNextActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
