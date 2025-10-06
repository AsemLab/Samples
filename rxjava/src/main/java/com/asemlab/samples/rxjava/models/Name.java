package com.asemlab.samples.rxjava.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;


@Keep
//@Serializable
public class Name {

        @SerializedName("common")
        public String common;

        @SerializedName("official")
        public String official;
}