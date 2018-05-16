package com.mekonetolrom.homework_week1;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class MatchesItem implements Parcelable {
    public String uid;
    public String name;
    public String imageUri;
    public boolean liked;

    public MatchesItem() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public MatchesItem(String name, String imageUri, boolean liked) {
        this.name = name;
        this.imageUri = imageUri;
        this.liked = liked;
    }

    public MatchesItem(Parcel in) {
        name = in.readString();
        imageUri = in.readString();
        liked = in.readByte() != 0;
    }

    public static final Creator<MatchesItem> CREATOR = new Creator<MatchesItem>() {
        @Override
        public MatchesItem createFromParcel(Parcel in) {
            return new MatchesItem(in);
        }

        @Override
        public MatchesItem[] newArray(int size) {
            return new MatchesItem[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("imageUri", imageUri);
        result.put("liked", liked);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUri);
        dest.writeByte((byte) (liked ? 1 : 0));
    }
}
