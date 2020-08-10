package com.belajar.goldenagehero

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class AboutActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val actionbar = supportActionBar
        actionbar!!.title = "About"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val ivContoh = findViewById<ImageView>(R.id.foto_thifal)
        val btnMailTo: Button = findViewById(R.id.btnmail)
        btnMailTo.setOnClickListener(this)

        val picSrc = R.drawable.foto_pas

        Glide.with(this)
            .load(picSrc)
            .apply(RequestOptions())
            .into(ivContoh)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnmail -> {
                val email = "thifal.nurrifqiak@gmail.com"
                val sendMail = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                startActivity(sendMail)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}