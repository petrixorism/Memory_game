package uz.ravshanbaxranov.memorygame.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.ravshanbaxranov.memorygame.R
import uz.ravshanbaxranov.memorygame.data.LeaderEntity
import uz.ravshanbaxranov.memorygame.databinding.LeaderboardItemBinding
import java.text.SimpleDateFormat

class LeaderboardAdapter : ListAdapter<LeaderEntity, LeaderboardAdapter.ViewHolder>(DiffItem) {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private val binding by viewBinding(LeaderboardItemBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.numberTv.text = (absoluteAdapterPosition + 1).toString()
                binding.nameTv.text = name
                binding.timeTv.text = duration
                binding.dateTv.text = SimpleDateFormat("dd-MMMM yyyy").format(time)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }


    object DiffItem : DiffUtil.ItemCallback<LeaderEntity>() {
        override fun areItemsTheSame(oldItem: LeaderEntity, newItem: LeaderEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LeaderEntity,
            newItem: LeaderEntity
        ): Boolean {
            return oldItem == newItem
        }

    }

}