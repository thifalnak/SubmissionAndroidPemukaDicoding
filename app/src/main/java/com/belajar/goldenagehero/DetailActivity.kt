package com.belajar.goldenagehero

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.intellij.lang.annotations.JdkConstants

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvHeroesDetail: RecyclerView
    private var list: ArrayList<Sahabat> = arrayListOf()

    companion object {
        var EXTRA_RV_IMAGES = "extra_images"
        var EXTRA_RV_NAMES = "extra_names"
        var EXTRA_RV_DETAILS = "extra_details"
        var EXTRA_RV_SOURCES = "extra_sources"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        val imgDetail : ImageView = findViewById(R.id.imageondetail)
        val tvNameDetail : TextView = findViewById(R.id.nameondetail)
        val tvKisahDetail : TextView = findViewById(R.id.kisah)
        rvHeroesDetail = findViewById(R.id.rv_heroes1)
        val btnSource : Button = findViewById(R.id.btnsource)

        btnSource.setOnClickListener(this)

        rvHeroesDetail.setHasFixedSize(true)

        val getImg = intent.getIntExtra(EXTRA_RV_IMAGES, 0)
        val getName = intent.getStringExtra(EXTRA_RV_NAMES)
        val getDetail = intent.getStringExtra(EXTRA_RV_DETAILS)


        Glide.with(this)
            .load(getImg)
            .apply(RequestOptions())
            .into(imgDetail)
        tvNameDetail.text=getName
        tvKisahDetail.text=getDetail

        val actionbar = supportActionBar
        actionbar!!.title = "Kisah"
        actionbar.setDisplayHomeAsUpEnabled(true)


        rvHeroesDetail.setOnClickListener(this)

        list.addAll(SahabatData.listData)
        showRecyclerCard()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        go(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun go(selected: Int) {
        val getName = intent.getStringExtra(EXTRA_RV_NAMES)
        val getSource = intent.getStringExtra(EXTRA_RV_SOURCES)
        when(selected) {
            R.id.menu_share -> {
                val sendContent : Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Kisah $getName, Kunjungi di $getSource")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendContent, null)
                startActivity(shareIntent)
            }
        }
    }
    @SuppressLint("WrongConstant")
    private fun showRecyclerCard() {
        rvHeroesDetail.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        val card2ViewAdapter = Card2ViewAdapter(list)
        rvHeroesDetail.adapter = card2ViewAdapter
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnsource -> {
                val getSource = intent.getStringExtra(EXTRA_RV_SOURCES)
                val toSource = Intent(Intent.ACTION_VIEW, Uri.parse(getSource))
                startActivity(toSource)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}