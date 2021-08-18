package name.paynd.android.newsapi.sources_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import name.paynd.android.newsapi.api.Source
import name.paynd.android.newsapi.sources_list.databinding.SourcesItemBinding

class SourcesAdapter : ListAdapter<Source, SourcesAdapter.ViewHolder>(SourceItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SourcesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: SourcesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(source: Source) {
            binding.content.text = source.name
            binding.itemNumber.text = source.id
        }
    }

}

private class SourceItemCallback : DiffUtil.ItemCallback<Source>() {
    override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem.id == newItem.id
    }
}