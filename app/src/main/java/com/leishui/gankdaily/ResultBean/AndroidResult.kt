package com.leishui.gankdaily.ResultBean

class AndroidResult {
    /**
     * error : false
     * results : [{"_id":"5d37ff829d2122031b7980ea","createdAt":"2019-07-24T06:49:38.966Z","desc":"ViewPager懒加载极致优化","publishedAt":"2019-07-24T06:57:09.421Z","source":"web","type":"Android","url":"https://juejin.im/post/5d37bb8df265da1b8b2ba01a","used":true,"who":"潇湘剑雨"},{"_id":"5d37a6819d21220321462178","createdAt":"2019-07-24T00:29:53.742Z","desc":"用Kotlin实现抖音爆红的文字时钟，征服产品小姐姐","publishedAt":"2019-07-24T06:57:06.548Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/Z2baRq2LBjxsFiC3VKNfNw","used":true,"who":"潇湘剑雨"},{"_id":"5d2fbefe9d2122774f0cd8e6","createdAt":"2019-07-18T00:36:14.713Z","desc":"Android 应用架构\u2014\u2014 那些因为年轻犯的错。MVC，MVP，MVVM。Android开发者的艰辛历程","publishedAt":"2019-07-23T11:31:59.766Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/KZG5REiuig63337jDjv4_w","used":true,"who":"潇湘剑雨"},{"_id":"5d2f24d19d2122031ea5223f","createdAt":"2019-07-17T13:38:25.504Z","desc":"状态切换，让View状态的切换和Activity彻底分离开。用builder模式来自由的添加需要的状态View，可以设置有数据，数据为空，加载数据错误，网络错误，加载中等多种状态，并且支持自定义状态的布局。","images":["http://img.gank.io/4b63f35c-f631-417b-9d88-916e70901634","http://img.gank.io/b7de0a96-0023-4c4c-b120-9fcbf8c6046c","http://img.gank.io/45f8f9b5-0be1-410b-a50d-b8d72046abb2","http://img.gank.io/89a0e411-1049-4322-80a1-9bd8d6dfb35e","http://img.gank.io/f3e3b597-0317-45cd-8288-d866888ce297"],"publishedAt":"2019-07-17T13:40:33.502Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCStateLayout/blob/master/README_CH.md","used":true,"who":"潇湘剑雨"},{"_id":"5d2c2ed89d21220321462163","createdAt":"2019-07-15T07:44:24.953Z","desc":"一个强大的侧滑框架，内置十多种侧滑效果：你知道微信侧滑返回的联动效果，但你见过开门效果、百叶窗效果吗？","publishedAt":"2019-07-17T13:39:51.470Z","source":"web","type":"Android","url":"https://github.com/luckybilly/SmartSwipe","used":true,"who":"潇湘剑雨"},{"_id":"5d2593289d2122031ea5222e","createdAt":"2019-07-10T07:26:32.0Z","desc":"AndroidBLE蓝牙框架，包括扫描、连接、设置通知、发送数据、读取、接收数据和OTA升级以及各种直观的回调，近乎一行代码植入项目，可扩展配置蓝牙相关操作。","images":["http://img.gank.io/4a57235f-2138-4e77-b3b9-aef99f10ca13"],"publishedAt":"2019-07-13T08:40:56.375Z","source":"web","type":"Android","url":"https://github.com/Alex-Jerry/Android-BLE","used":true,"who":"潇湘剑雨"},{"_id":"5d2593559d2122031b7980cf","createdAt":"2019-07-10T07:27:17.723Z","desc":"AopArms编写了Android开发中常用的一套注解，如日志、异步处理、缓存、SP、延迟操作、定时任务、重试机制、try-catch安全机制、过滤频繁点击、拦截等，后续还会有更多更强大的注解功能加入","publishedAt":"2019-07-13T08:40:32.56Z","source":"web","type":"Android","url":"https://github.com/AICareless/AopArms","used":true,"who":"潇湘剑雨"},{"_id":"5d24011b9d2122774f0cd8dd","createdAt":"2019-07-09T02:51:07.520Z","desc":"SharedPreferences 不为人知的秘密（下）","publishedAt":"2019-07-10T12:04:35.126Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/U75XuBCM6B5di0tTaPyxUg","used":true,"who":"潇湘剑雨"},{"_id":"5d1ea3f89d2122774f0cd8da","createdAt":"2019-07-05T01:12:24.992Z","desc":"真当Flutter不能热更新？QQ团队开源动态化Flutter","publishedAt":"2019-07-05T10:48:37.144Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/bH_SC2YHtXwrGGvQXt5FMg","used":true,"who":"潇湘剑雨"},{"_id":"5d19f32b9d2122774f0cd8d2","createdAt":"2019-07-01T11:48:59.726Z","desc":"2019 最前沿的几个 Flutter 实践：微信、咸鱼、美团 ","publishedAt":"2019-07-01T11:49:08.311Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/TyjwBASNvxnQNXtC3zCG1w","used":true,"who":"潇湘剑雨"}]
     */

    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * _id : 5d37ff829d2122031b7980ea
         * createdAt : 2019-07-24T06:49:38.966Z
         * desc : ViewPager懒加载极致优化
         * publishedAt : 2019-07-24T06:57:09.421Z
         * source : web
         * type : Android
         * url : https://juejin.im/post/5d37bb8df265da1b8b2ba01a
         * used : true
         * who : 潇湘剑雨
         * images : ["http://img.gank.io/4b63f35c-f631-417b-9d88-916e70901634","http://img.gank.io/b7de0a96-0023-4c4c-b120-9fcbf8c6046c","http://img.gank.io/45f8f9b5-0be1-410b-a50d-b8d72046abb2","http://img.gank.io/89a0e411-1049-4322-80a1-9bd8d6dfb35e","http://img.gank.io/f3e3b597-0317-45cd-8288-d866888ce297"]
         */

        var _id: String? = null
        var createdAt: String? = null
            get() = field!!.substring(0, 10)
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var isUsed: Boolean = false
        var who: String? = null
        var images: List<String>? = null
    }
}
