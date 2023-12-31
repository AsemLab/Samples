package com.asemlab.samples.koin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.asemlab.samples.koin.database.UserDao
import com.asemlab.samples.koin.databinding.ActivityMainBinding
import com.asemlab.samples.koin.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    // TODO Inject from Koin
    private val userDao by inject<UserDao>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        lifecycleScope.launch {
            val users = getUsers()
            binding.users.text = users.joinToString("\n") { "${it.name} with id: ${it.id}" }
        }
    }

    private suspend fun getUsers(): List<User> {
        return lifecycleScope.async {
            withContext(Dispatchers.IO) {
                userDao.getAll()
            }
        }.await()
    }
}