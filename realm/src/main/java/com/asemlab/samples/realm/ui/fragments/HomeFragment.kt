package com.asemlab.samples.realm.ui.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.realm.databinding.FragmentHomeBinding
import com.asemlab.samples.realm.model.Person
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var personAdapter: PersonAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val c = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        personAdapter = PersonAdapter(mutableListOf()) {
            c.setPrimaryClip(ClipData.newPlainText("Person ID", it._id.toHexString()))
            Toast.makeText(
                requireContext(),
                "Person ID Copied to clipboard",
                Toast.LENGTH_SHORT
            ).show()
        }

        with(binding) {
            personsRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = personAdapter
            }

            lifecycleOwner = this@HomeFragment
            homeViewModel = this@HomeFragment.homeViewModel

            add.setOnClickListener {
                val person = Person().apply {
                    name = this@HomeFragment.homeViewModel.personName.value!!
                    phoneNumber = this@HomeFragment.homeViewModel.personMobile.value!!
                }
                this@HomeFragment.homeViewModel.addPerson(person)
            }

            delete.setOnClickListener {
                try {
                    val person = Person().apply {
                        _id = ObjectId(c.primaryClip?.getItemAt(0)?.text.toString())
                    }
                    this@HomeFragment.homeViewModel.deletePerson(person)
                } catch (e: Exception) {
                    Log.e("HomeFragment", "Error deleting person: ${e.message}")
                }
            }

            update.setOnClickListener {
                try {
                    val person = Person().apply {
                        _id = ObjectId(c.primaryClip?.getItemAt(0)?.text.toString())
                        name = this@HomeFragment.homeViewModel.personName.value!!
                        phoneNumber = this@HomeFragment.homeViewModel.personMobile.value!!
                    }
                    this@HomeFragment.homeViewModel.updatePerson(person)
                } catch (e: Exception) {
                    Log.e("HomeFragment", "Error deleting person: ${e.message}")
                }
            }

        }

        with(homeViewModel) {

            viewModelScope.launch {
                persons.collect {
                    personAdapter.updateData(it)
                    binding.nameET.text.clear()
                    binding.phoneNumberET.text.clear()
                    binding.idET.text.clear()
                }
            }

            personName.observe(viewLifecycleOwner) {
                binding.add.isEnabled = it.isNotEmpty() && personMobile.value?.isNotEmpty() ?: false
                binding.update.isEnabled =
                    it.isNotEmpty() && personMobile.value?.isNotEmpty() ?: false && personId.value?.isNotEmpty() ?: false
            }

            personMobile.observe(viewLifecycleOwner) {
                binding.add.isEnabled = it.isNotEmpty() && personName.value?.isNotEmpty() ?: false
                binding.update.isEnabled =
                    it.isNotEmpty() && personName.value?.isNotEmpty() ?: false && personId.value?.isNotEmpty() ?: false
            }

            personId.observe(viewLifecycleOwner) {
                binding.delete.isEnabled = it.isNotEmpty()
                binding.update.isEnabled =
                    it.isNotEmpty() && personName.value?.isNotEmpty() ?: false && personMobile.value?.isNotEmpty() ?: false
            }
        }

        return binding.root
    }

}