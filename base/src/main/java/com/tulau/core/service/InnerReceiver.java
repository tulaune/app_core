package com.tulau.core.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tulau.core.helper.HardwareKeyWatcher;


public class InnerReceiver extends BroadcastReceiver {
    final String TAG = "InnerReceiver";
    final String SYSTEM_DIALOG_REASON_KEY = "reason";
    final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

    private HardwareKeyWatcher.OnHardwareKeysPressedListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null) {
                    Log.e(TAG,"reason "+reason);
                    if (mListener != null) {
                        if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                            mListener.onHomePressed();
                        } else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                            mListener.onRecentAppsPressed();
                        }
                    }
                }
                break;
            case Intent.ACTION_MEDIA_BUTTON:
                break;
            case Intent.ACTION_SCREEN_OFF:
                if (mListener != null) {
                    mListener.onScreenOff();
                }
                break;
            default:

                break;
        }
    }

    public HardwareKeyWatcher.OnHardwareKeysPressedListener getmListener() {
        return mListener;
    }

    public void setmListener(HardwareKeyWatcher.OnHardwareKeysPressedListener mListener) {
        this.mListener = mListener;
    }
}
