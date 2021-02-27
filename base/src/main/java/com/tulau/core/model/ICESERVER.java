package com.tulau.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dragon on 1/31/18.
 */

public class ICESERVER implements Parcelable {
    private String url;
    private String password;
    private String username;

    public ICESERVER() {
    }

    public ICESERVER(Parcel in) {
        this.url = in.readString();
        this.password = in.readString();
        this.username = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(password);
        dest.writeString(username);
    }

    public static final Creator<ICESERVER> CREATOR
            = new Creator<ICESERVER>() {
        public ICESERVER createFromParcel(Parcel in) {
            return new ICESERVER(in);
        }

        public ICESERVER[] newArray(int size) {
            return new ICESERVER[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
