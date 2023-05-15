package com.example.mstarttask.presentation.home.second.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mstarttask.R
import com.example.mstarttask.databinding.RowDateBinding
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.presentation.home.second.AdapterClick


class DateAdapter(private val list: MutableList<DateModel>, private val listener: AdapterClick) :
    RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_date, parent, false)
        return DateViewHolder(RowDateBinding.bind(view))
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size


    inner class DateViewHolder(private val binding: RowDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DateModel, listener: AdapterClick) {
            val context = binding.root.context

            binding.tvId.text =
                context.getString(R.string.title_id_number_with_value, data.id.toString())

            binding.tvEventName.text =
                context.getString(R.string.event_name, data.eventName)

            binding.tvEventDescription.text =
                context.getString(R.string.event_description, data.eventDescription)

            binding.tvHijriDate.text =
                context.getString(R.string.hijri_Date, data.HijriDate)

            binding.tvGregorian.text =
                context.getString(R.string.gregorian_Date, data.GregorianDate)

            binding.tvServerTime.text =
                context.getString(R.string.server_Date, data.serverDatetime)


            binding.ivEdit.setOnClickListener {
                listener.onClickEdit(
                    data
                )
            }

            binding.ivDelete.setOnClickListener {
                listener.onDeleteClicked(data)
            }

            binding.root.setOnClickListener {
                data.isSelected = !data.isSelected;
                notifyItemChanged(adapterPosition)
                    listener.setIdEventSelected(data.id!!)
            }

            if (data.isSelected) {
                binding.cardView.setCardBackgroundColor(
                    context.resources.getColor(
                        R.color.purple_700
                    )
                )
            } else {
                binding.cardView.setCardBackgroundColor(
                    context.resources.getColor(
                        R.color.white
                    )
                )
            }
        }
    }
}


