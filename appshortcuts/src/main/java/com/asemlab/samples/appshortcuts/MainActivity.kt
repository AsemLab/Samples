package com.asemlab.samples.appshortcuts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.asemlab.samples.appshortcuts.databinding.ActivityMainBinding

/**
 *  TODO Create static and dynamic shortcuts
 *  Assign multiple intents
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val controller = findNavController(R.id.fragmentContainerView)
        binding.bottomNav.setupWithNavController(controller)


        when (intent.action) {
            "sports" -> controller.navigate(R.id.sports_nav)
            "business" -> controller.navigate(R.id.business_nav)
            "news" -> controller.navigate(R.id.news_nav)
        }


        val shortcuts = listOf(
            ShortcutInfoCompat.Builder(this, "id1")
                .setShortLabel("kooora")
                .setLongLabel("Open Kooora")
                .setRank(2)
                .setIcon(IconCompat.createWithResource(this, R.drawable.ic_globe))

                .setIntent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.kooora.com/")
                    )
                )
                .build(),
            // TODO Open multiple Activities
            ShortcutInfoCompat.Builder(this, "id2")
                .setShortLabel("News 1")
                .setLongLabel("Open News 1")
                .setRank(10)
                .setIcon(IconCompat.createWithResource(this, R.drawable.news))
                .setIntents(
                    arrayOf(
                        Intent(
                            this, SectionActivity::class.java
                        ).setAction(Intent.ACTION_VIEW),
                        Intent(
                            this, NewsDetailsActivity::class.java
                        ).setAction(Intent.ACTION_VIEW)
                    )
                )
                .build()

        )

        ShortcutManagerCompat.setDynamicShortcuts(this, shortcuts)

    }
}