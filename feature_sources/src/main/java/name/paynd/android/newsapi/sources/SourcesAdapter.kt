package name.paynd.android.newsapi.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import name.paynd.android.newsapi.sources.databinding.SourcesItemBinding
import name.paynd.android.newsapi.sources.model.Source

class SourcesAdapter(private val clickListener: (String) -> Unit) :
    ListAdapter<Source, SourcesAdapter.ViewHolder>(SourceItemDiffCallback()) {
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
            binding.itemTitle.text = source.name
            binding.itemDescription.text = source.description
            binding.itemUrl.text = source.url
            binding.root.setOnClickListener {
                clickListener.invoke(source.id)
            }
        }
    }
}

private class SourceItemDiffCallback : DiffUtil.ItemCallback<Source>() {
    override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem.id == newItem.id
    }
}