package com.hj.interestingalbum.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoBean implements Parcelable {
    private int id;
    private int threedImg;
    private String title;

    public PhotoBean() {
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

    protected PhotoBean(Parcel in) {
        id = in.readInt();
        threedImg = in.readInt();
        title = in.readString();
    }

    public static final Creator<PhotoBean> CREATOR = new Creator<PhotoBean>() {
        @Override
        public PhotoBean createFromParcel(Parcel in) {
            return new PhotoBean(in);
        }

        @Override
        public PhotoBean[] newArray(int size) {
            return new PhotoBean[size];
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
