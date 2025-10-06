package com.asemlab.samples.rxjava.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Keep
// TODO Make the response classes Serializable if don't use Gson
//@Serializable
public class CountryResponseItem implements Serializable {

    @SerializedName("flags")
    public Flags flags;

    @SerializedName("name")
    public Name name;
    @SerializedName("region")
    public String region;

}