package com.leishui.gankdaily.util

object UrlUtil {

    fun getPath(type: String,isNew:Boolean, num: Int, page: Int,query: String): String {
        if (type == "Search")
            return getSearchPath(query,num,page)
        else if (isNew)
            return "http://gank.io/api/data/$type/$num/$page"
        else
            return "http://gank.io/api/random/data/$type/$num"
    }
    fun getSearchPath(query:String,num: Int,page: Int):String{
        return "http://gank.io/api/search/query/$query/category/all/count/$num/page/$page "
    }
}