package com.deepak.newsapp.data.local

import androidx.room.TypeConverter
import com.deepak.newsapp.data.model.Media
import com.deepak.newsapp.data.model.MediaMetaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverters {

    @TypeConverter
    fun fromMediaListToString(media : List<Media>) : String {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun fromStringToMediaList(media: String) : List<Media> {
        return Gson().fromJson(media, object : TypeToken<List<Media>>(){}.type)
    }

    @TypeConverter
    fun fromMetadataListToString(media : List<MediaMetaData>) : String {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun fromStringToMetadata(media: String) : List<MediaMetaData> {
        return Gson().fromJson(media, object : TypeToken<List<MediaMetaData>>(){}.type)
    }
}