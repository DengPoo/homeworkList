package com.example.tiktok;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class DataResponse {
    @SerializedName("_id")
    public String _id;
    @SerializedName("feedurl")
    public String feedurl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public long likecount;
    @SerializedName("avatar")
    public String avatar;

    @Override
    public String toString() {
        return "dataTable{" +
                "_id=" + _id +
                ", feedurl='" + feedurl + '\'' +
                ", nickname=" + nickname +
                '}';
    }
}