package com.astro.yourchannel.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.astro.yourchannel.PlaylistItemActivity
import com.astro.yourchannel.R
import com.astro.yourchannel.models.playlists.PlaylistsItem1
import kotlinx.android.synthetic.main.main_row.view.*

class YtAdapter(mContext : Context) : RecyclerView.Adapter<YtAdapter.YtViewHolder>() {

    private val TAG = "YtAdapter"
    private val mContext = mContext

    inner class YtViewHolder( itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<PlaylistsItem1>(){
        override fun areItemsTheSame(oldItem: PlaylistsItem1, newItem: PlaylistsItem1): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistsItem1, newItem: PlaylistsItem1): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YtViewHolder {
        return YtViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row,parent,false))
    }

    override fun onBindViewHolder(holder: YtViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        holder.itemView.apply {

            tvOverline.text = currentItem.snippet.publishedAt
            tvTitle.text = currentItem.snippet.title
            tvDescription.text = currentItem.snippet.description

            cardViewMain.setOnClickListener {
                Log.d(TAG, "onBindViewHolder: ${currentItem.snippet.title}")

              val gotoItem = Intent(mContext,PlaylistItemActivity::class.java)
                gotoItem.putExtra("playlistId",currentItem.id.toString())
                context.startActivity(gotoItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}