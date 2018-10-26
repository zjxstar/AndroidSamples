package com.zjx.sample.pie;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Field field = null;
        try {
            field = wifiManager.getClass().getDeclaredField("WIFI_SCAN_AVAILABLE");
            Log.d("ThirdActivity", (String) field.get(wifiManager));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void testLightGreyList(View view) {
        ReflectUtils.getMethod(TelephonyManager.class, "isMultiSimEnabled");
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void testDarkGreyList(View view) {
        ReflectUtils.getField(CarrierConfigManager.class, "KEY_ALWAYS_PLAY_REMOTE_HOLD_TONE_BOOL");
    }

    public void testBlackList(View view) {
        try {
            ReflectUtils.getMethod(ReflectUtils.getClass("android.net.util.IpUtils"), "ipChecksum", ByteBuffer.class, int.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
