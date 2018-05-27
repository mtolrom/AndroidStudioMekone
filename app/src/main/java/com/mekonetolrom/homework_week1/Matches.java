package com.mekonetolrom.homework_week1;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;

import org.hamcrest.Matcher;

public class Matches implements Parcelable  {
    public String imageUrl;
    public boolean liked;
    public String name;
    public String uid;
    public String lat;
    public String longitude;

    public Matches() {
        //Default constructor required
    }

    public Matches(Parcel in) {
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        name = in.readString();
        lat = in.readString();
        longitude = in.readString();
    }

    public static final Creator<Matches> CREATOR = new Creator<Matches>() {
        @Override
        public Matches createFromParcel(Parcel source) {
            return new Matches(source);
        }

        @Override
        public Matches[] newArray(int size) {
            return new Matches[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(name);
        dest.writeString(lat);
        dest.writeString(longitude);
    }
}