package com.leishui.gankdaily.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leishui.gankdaily.adapter.BaseAdapter
import com.leishui.gankdaily.activity.DisplayActivity
import com.leishui.gankdaily.R
import com.leishui.gankdaily.resultBean.AndroidResult
import com.leishui.gankdaily.util.ThreadUtil
import com.leishui.gankdaily.util.UrlUtil
import kotlinx.android.synthetic.main.fragment.*
import kotlinx.android.synthetic.main.load_more.*
import okhttp3.*
import java.io.IOException

open class BaseFragment(var type: String, var isNew: Boolean) : Fragment() {
    var page = 1
    val num = 10
    open var query = ""
    val adapter by lazy { BaseAdapter() }
    val swipeRefreshLayout: SwipeRefreshLayout by lazy { swiperefresh }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        initData(query)
        swipeRefreshLayout.setOnRefreshListener { initData(query) }
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    val manager = layoutManager
                    val lastPosition = manager.findLastVisibleItemPosition()
                    if (lastPosition == adapter.itemCount - 1)
                        loadMore(query)
                }
            }
        })
        //item点击事件
        adapter.setOnItemClickListener(object : BaseAdapter.OnItemClickListener {
            override fun onItemClick(list: List<AndroidResult.ResultsBean>, position: Int) {
                val intent = Intent(context, DisplayActivity::class.java)
                val listOfPosition = list[position]
                intent.putExtra("url", listOfPosition.url)
                intent.putExtra(
                    "title",
                    if (listOfPosition.desc?.length!! > 40) listOfPosition.desc?.substring(
                        0,
                        40
                    ) else listOfPosition.desc
                )
                startActivity(intent)
            }
        })
        //item长按事件
        adapter.setOnItemLongClickListener(object : BaseAdapter.OnItemLongClickListener {
            override fun onItemLongClick(list: List<AndroidResult.ResultsBean>, position: Int) {
                Toast.makeText(context, "复制链接成功", Toast.LENGTH_SHORT).show()
                val cm = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val mClipData = ClipData.newPlainText("Label", list[position].url)
                cm.setPrimaryClip(mClipData)
            }

        })
    }

    //初始数据
    fun initData(query: String) {
        val client = OkHttpClient()
        val url = UrlUtil.getPath(type, isNew, num, 1, query)
        val request = Request.Builder().url(url).get().build()
        page = 1
        more_progressbar?.isGone = false
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val gson = Gson()
                val searchResult = gson.fromJson<AndroidResult>(result, object : TypeToken<AndroidResult>() {}.type)
                ThreadUtil.runOnMainThread(Runnable {
                    //searchResult.results?.let { adapter.updateList(it) }
                    recyclerview?.scrollToPosition(0)
                    searchResult.results?.let { adapter.updateList(it) }
                    if (searchResult.count != num && searchResult.count != null) {
                        more_progressbar?.isGone = true
                        if (searchResult.count == 0)
                            context?.let { Toast.makeText(it, "无结果", Toast.LENGTH_SHORT).show() }
                        else
                            context?.let { Toast.makeText(it, "获取数据成功", Toast.LENGTH_SHORT).show() }
                        //Toast.makeText(context, "已加载全部结果", Toast.LENGTH_SHORT).show()
                    } else
                        context?.let { Toast.makeText(it, "获取数据成功", Toast.LENGTH_SHORT).show() }
                        //Toast.makeText(context, "获取数据成功", Toast.LENGTH_SHORT).show()
                    swipeRefreshLayout.isRefreshing = false
                })
            }

            override fun onFailure(call: Call, e: IOException) {
                swiperefresh?.isRefreshing = false
                ThreadUtil.runOnMainThread(Runnable {
                    Toast.makeText(context, "获取数据失败:网络连接失败\n$e", Toast.LENGTH_SHORT).show()
                })
            }
        })
    }

    //加载更多
    private fun loadMore(query: String) {
        val client = OkHttpClient()
        val url = UrlUtil.getPath(type, isNew, num, ++page, query)
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val gson = Gson()
                val androidResult = gson.fromJson<AndroidResult>(result, object : TypeToken<AndroidResult>() {}.type)
                ThreadUtil.runOnMainThread(Runnable {
                    if (androidResult.count != 0)
                        androidResult.results?.let { adapter.loadMore(it) }
                    else {
                        more_progressbar?.isGone = true
                        Toast.makeText(context, "已加载全部结果", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    Toast.makeText(context, "加载数据失败:网络连接失败$e", Toast.LENGTH_LONG).show()
                })
            }
        })
    }
}