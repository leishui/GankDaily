package com.leishui.gankdaily.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leishui.gankdaily.R
import com.leishui.gankdaily.resultBean.AndroidResult
import kotlinx.android.synthetic.main.image_item.view.*
import kotlinx.android.synthetic.main.image_item.view.list_item
import kotlinx.android.synthetic.main.image_item.view.time
import kotlinx.android.synthetic.main.image_item.view.who
import kotlinx.android.synthetic.main.text_item.view.*

class BaseAdapter: RecyclerView.Adapter<BaseAdapter.RecyclerHolder>() {
    var list = ArrayList<AndroidResult.ResultsBean>()
    var data = AndroidResult.ResultsBean()

    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var onItemLongClickListener: OnItemLongClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.onItemLongClickListener = listener
    }


    interface OnItemClickListener{
        fun onItemClick(list:List<AndroidResult.ResultsBean>,position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(list:List<AndroidResult.ResultsBean>,position: Int)
    }


    //刷新 初始数据
    fun updateList(list: List<AndroidResult.ResultsBean>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    //加载更多数据
    fun loadMore(list: List<AndroidResult.ResultsBean>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    //数据加载完成后删去load_more
    fun noLoard(){

    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size)
            return 2
        else if(list[position].type == "福利")
            return 1
        else
            return 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        if (viewType == 0)
            return RecyclerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.text_item,
                    parent,
                    false
                )
            )
        else if (viewType == 1)
            return RecyclerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.image_item,
                    parent,
                    false
                )
            )
        else
            return RecyclerHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.load_more,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        if (position == list.size)
            return
        data = list[position]
        if (data.type == "福利"){
            Glide.with(holder.itemView).load(data.url).placeholder(R.drawable.ic_gank).error(R.drawable.ic_error).into(holder.img)
        }
        else{
            holder.des.text = data.desc
        }
        holder.time.text = data.publishedAt
        holder.who.text = data.who
        holder.item.setOnClickListener {
            onItemClickListener.onItemClick(list,position)
        }
        holder.item.setOnLongClickListener{
            onItemLongClickListener.onItemLongClick(list,position)
            true
        }
    }

    class RecyclerHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val img = itemView.img
        val des = itemView.des
        val time = itemView.time
        val who = itemView.who
        val item = itemView.list_item
    }

}