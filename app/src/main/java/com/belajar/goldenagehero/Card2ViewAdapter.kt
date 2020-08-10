package com.belajar.goldenagehero

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Card2ViewAdapter(private val listSahabat: ArrayList<Sahabat>) : RecyclerView.Adapter<Card2ViewAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.item_photo_preview1)
        var tvName: TextView = itemView.findViewById(R.id.nama_preview1)
        var tvDetail: TextView = itemView.findViewById(R.id.detail_preview1)
        //var btnFav: ImageView = itemView.findViewById(R.id.btnfav1)
        //var btnShare: ImageView = itemView.findViewById(R.id.btnshare1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_nextoptions, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSahabat.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val sahabat = listSahabat[position]

        Glide.with(holder.itemView.context)
            .load(sahabat.image)
            .apply(RequestOptions().override(400,550))
            .into(holder.imgPhoto)

        holder.tvName.text = sahabat.name
        holder.tvDetail.text = sahabat.detail

        holder.itemView.setOnClickListener {
            val showDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            showDetail.putExtra(DetailActivity.EXTRA_RV_IMAGES, sahabat.image)
            showDetail.putExtra(DetailActivity.EXTRA_RV_NAMES, sahabat.name)
            showDetail.putExtra(DetailActivity.EXTRA_RV_DETAILS, sahabat.kisah)
            showDetail.putExtra(DetailActivity.EXTRA_RV_SOURCES, sahabat.source)
            holder.itemView.context.startActivity(showDetail)

        }


    }
}