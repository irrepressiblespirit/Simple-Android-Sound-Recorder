package com.skibnev.soundrecorder;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordingItem implements Parcelable {
    private String name; // file name
    private String filePath; //file path
    private int id; //id in database
    private int length; // length of recording in seconds
    private long time; // date/time of the recording

    public RecordingItem(Parcel in) {
        name = in.readString();
        filePath = in.readString();
        id = in.readInt();
        length = in.readInt();
        time = in.readLong();
    }

    public static final Parcelable.Creator<RecordingItem> CREATOR = new Parcelable.Creator<RecordingItem>() {
        public RecordingItem createFromParcel(Parcel in) {
            return new RecordingItem(in);
        }

        public RecordingItem[] newArray(int size) {
            return new RecordingItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(length);
        dest.writeLong(time);
        dest.writeString(filePath);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}