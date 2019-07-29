package com.leishui.gankdaily.Util

import okhttp3.*

object UrlUtil {

    fun getPath(type: String, num: Int, page: Int): String {
        return "http://gank.io/api/data/$type/$num/$page"
    }
}