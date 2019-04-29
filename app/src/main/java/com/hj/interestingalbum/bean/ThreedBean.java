package com.hj.interestingalbum.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ThreedBean implements Parcelable {
    private int id;
    private int threedImg;
    private String title;

    public ThreedBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThreedImg() {
        return threedImg;
    }

    public void setThreedImg(int threedImg) {
        this.threedImg = threedImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected ThreedBean(Parcel in) {
        id = in.readInt();
        threedImg = in.readInt();
        title = in.readString();
    }

    public static final Creator<ThreedBean> CREATOR = new Creator<ThreedBean>() {
        @Override
        public ThreedBean createFromParcel(Parcel in) {
            return new ThreedBean(in);
        }

        @Override
        public ThreedBean[] newArray(int size) {
            return new ThreedBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(threedImg);
        dest.writeString(title);
    }
}
