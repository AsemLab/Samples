package com.asemlab.samples.koin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.asemlab.samples.koin.database.UserDao
import com.asemlab.samples.koin.databinding.ActivityMainBinding
import com.asemlab.samples.koin.di.GOOGLE_NAME
import com.asemlab.samples.koin.model.HomePage
import com.asemlab.samples.koin.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    // TODO Inject from Koin
    private val userDao by inject<UserDao>()

    // TODO Inject from Koin using qualifiers
    private val homePage by inject<HomePage>(named(GOOGLE_NAME)) // OR YOUTUBE_NAME

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        lifecycleScope.launch {
            val users = getUsers()
            binding.users.text = users.joinToString("\n") { "${it.name} with id: ${it.id}" }
        }
        binding.url.text = homePage.url
    }

    private suspend fun getUsers(): List<User> {
        return lifecycleScope.async {
            withContext(Dispatchers.IO) {
                userDao.getAll()
            }
        }.await()
    }
}