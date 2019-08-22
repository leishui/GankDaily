package com.leishui.gankdaily.resultBean

class AndroidResult {
    //var isError: Boolean ?= null
    //var error: Boolean? = null
    var count: Int? = null
    var results: List<ResultsBean>? = null

    class ResultsBean {
        var desc: String? = null
        var publishedAt: String = ""
            get() {
                if (field.length > 10)
                    return field.substring(0, 10)
                else
                    return field
            }
        //var source: String? = null
        var type: String? = null
        var url: String? = null
        //var isUsed: Boolean = false
        var who: String? = null
        //var images: List<String>? = null
    }
}
