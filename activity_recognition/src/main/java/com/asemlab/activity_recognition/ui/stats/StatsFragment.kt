package com.asemlab.activity_recognition.ui.stats

// Developed by: Asem Abu alrub // AsemLab © 2025

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asemlab.activity_recognition.R
import com.asemlab.activity_recognition.databinding.FragmentStatsBinding
import com.asemlab.activity_recognition.ui.dialogs.OneButtonsDialog
import com.asemlab.activity_recognition.ui.dialogs.TwoButtonsDialog
import com.asemlab.activity_recognition.utilties.formatDate
import com.asemlab.activity_recognition.utilties.toDate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private lateinit var adapter: EntriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val statsViewModel =
            ViewModelProvider(this)[StatsViewModel::class.java]

        binding = FragmentStatsBinding.inflate(inflater, container, false)

        adapter = EntriesAdapter(mutableListOf()) { entry ->
            val date = entry.date.toDate().formatDate()

            OneButtonsDialog.Builder()
                .setTitle("Activity #${entry.id}")
                .setMessage(
                    resources.getString(
                        R.string.entry_message,
                        entry.points,
                        date,
                        entry.type.lowercase()
                    )
                )
                .setOkButton("Close") {}
                .build()
                .show(parentFragmentManager, "EntryDialog")
        }

        with(statsViewModel) {
            getAllActivities()

            entries.observe(viewLifecycleOwner) {
                it?.let {
                    adapter.setItems(it.toMutableList())
                }
            }
        }

        with(binding) {
            clearButton.setOnClickListener {
                TwoButtonsDialog.Builder()
                    .setTitle("Confirm deleting")
                    .setMessage("Are you sure to delete all data?")
                    .setOkButton("Yes") { _ ->
                        statsViewModel.clearData()
                    }.setCloseButton("No") { _ -> }
                    .build()
                    .show(parentFragmentManager, "DeleteDialog")
            }
            entriesRv.adapter = adapter

        }


        return binding.root
    }


}