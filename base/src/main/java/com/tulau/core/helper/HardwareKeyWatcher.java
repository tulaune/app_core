package com.tulau.core.helper;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tulau.core.service.InnerReceiver;

public class HardwareKeyWatcher {
    static final String TAG = "HardwareKeyWatcher";
    private Context mContext;
    private IntentFilter mFilter;
    private OnHardwareKeysPressedListener mListener;
    private InnerReceiver mReceiver;


    public interface OnHardwareKeysPressedListener {
        public void onHomePressed();
        public void onRecentAppsPressed();
        public void onScreenOff();
    }

    public HardwareKeyWatcher(Context context) {
        mContext = context;
        mFilter = new IntentFilter();
        mFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        mFilter.addAction(Intent.ACTION_SCREEN_OFF);
        mFilter.addAction(Intent.ACTION_SCREEN_ON);
        mFilter.setPriority(1000);
    }

    public void setOnHardwareKeysPressedListenerListener(OnHardwareKeysPressedListener listener) {
        mListener = listener;
        mReceiver = new InnerReceiver();
        mReceiver.setmListener(mListener);
    }

    public void startWatch() {
        if (mReceiver != null) {
            mContext.registerReceiver(mReceiver, mFilter);
        }
    }

    public void stopWatch() {
        if (mReceiver != null) {
            mContext.unregisterReceiver(mReceiver);
        }
    }

}