package com.snail.androidparcelabletest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yongjie on 2017/9/9.
 * 代码整体格式固定
 */

public class User implements Parcelable {
    private int userId;
    private String userName;
    private boolean isMale;
    //当前User引用Book类，Book也必须要序列化
    private Book book;


    public User(int userId, String userName, boolean isMale, Book book) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
        this.book = book;
    }

    //反序列化 固定的
    public User(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        //跟下面的序列化处理方式对应的
        this.isMale = in.readInt() == 1;
        //反序列化Book对象需要指定一个ClassLoader
        this.book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    //将我们对象序列化
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        //Parcel不能够直接写boolean可以写一个布尔数组
        //此处通过根据布尔值写数字给数字赋予含义可以巧妙的避开不能直接写boolean
        out.writeInt(isMale ? 1 : 0);
        //传递序列化的对象
        out.writeParcelable(book, 0);
    }

    @Override
    public int describeContents() {
        //对象含有文件描述符返回1,Parcelable提供了常量 CONTENTS_FILE_DESCRIPTOR
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
