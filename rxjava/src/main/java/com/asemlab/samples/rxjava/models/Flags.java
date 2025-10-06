package com.asemlab.samples.rxjava.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;

@Keep
public class Flags{

    @SerializedName("alt")
    public String alt;

    @SerializedName("png")
    public String png;

    @SerializedName("svg")
    public String svg;

}