package com.example.projektzaliczeniowy;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Item implements Parcelable {
    String dateAdded;
    String dateEnd;
    String task;
    String priority;
    Boolean status;

    String path;

    public Item(String dateAdded, String dateEnd, String task, String priority, Boolean status,String path) {
        this.dateAdded = dateAdded;
        this.dateEnd = dateEnd;
        this.task = task;
        this.priority = priority;
        this.status = status;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    protected Item(Parcel in) {
        dateAdded = in.readString();
        dateEnd = in.readString();
        task = in.readString();
        priority = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(dateAdded);
        parcel.writeString(dateEnd);
        parcel.writeString(task);
        parcel.writeString(priority);
        parcel.writeBoolean(status);
    }

}
