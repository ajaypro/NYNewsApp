package com.deepak.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("type")
    var type : String,
    @SerializedName("media-metadata")
    var mediaList : List<MediaMetaData>?
)