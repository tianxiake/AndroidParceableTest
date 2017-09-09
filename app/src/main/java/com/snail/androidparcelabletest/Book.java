package com.snail.androidparcelabletest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yongjie on 2017/9/9.
 */

public class Book implements Parcelable {
    public int price;
    public String bookName;

    public Book(int price, String bookName) {
        this.price = price;
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        this.price = in.readInt();
        this.bookName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(price);
        out.writeString(bookName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
