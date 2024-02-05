package com.asemlab.samples.realm.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.realm.databinding.PersonItemBinding
import com.asemlab.samples.realm.model.Person

class PersonAdapter(private val items: MutableList<Person>, private val onClick: (Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    fun updateData(newList: List<Person>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    class PersonViewHolder private constructor(private val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person,  onClick: (Person) -> Unit) {
            binding.person = person
            binding.root.setOnClickListener {
                onClick(person)
            }
        }

        companion object {
            fun from(parent: ViewGroup): PersonViewHolder {
                val binding =
                    PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PersonViewHolder(binding)
            }
        }

    }
}