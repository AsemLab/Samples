package com.asemlab.samples.appshortcuts

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.appshortcuts.databinding.ActivitySectionBinding

/**
 *  TODO Create and pin a shortcut
 */
class SectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_section)

        binding.pinButton.setOnClickListener {

            if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                val shortcut = ShortcutInfoCompat.Builder(this, "news_id")
                    .setShortLabel("News")
                    .setLongLabel("Open news section")
                    .setIcon(IconCompat.createWithResource(this, R.drawable.ic_news))
                    .setIntent(
                        Intent(this, MainActivity::class.java).setAction("news")
                    )
                    .build()
                val sender = ShortcutManagerCompat.createShortcutResultIntent(this, shortcut)
                val successCallback = PendingIntent.getBroadcast(
                    this, 0,
                    sender, PendingIntent.FLAG_IMMUTABLE
                )
                ShortcutManagerCompat.requestPinShortcut(
                    this,
                    shortcut,
                    successCallback.intentSender
                )
            }
        }

        val openDetails = {
            startActivity(Intent(this, NewsDetailsActivity::class.java))
        }

        binding.t1.setOnClickListener { openDetails() }
        binding.t2.setOnClickListener { openDetails() }
        binding.t3.setOnClickListener { openDetails() }


    }
}