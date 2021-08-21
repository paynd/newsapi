package name.paynd.android.newsapi.articles

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import name.paynd.android.newsapi.articles.databinding.ArticleItemBinding
import name.paynd.android.newsapi.articles.model.Article


class ArticlesAdapter(context: Context) :
    PagingDataAdapter<Article, ArticlesViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding by viewBinding(ArticleItemBinding::bind)

    fun bind(article: Article?) {
        with(viewBinding) {
            image.load(article?.urlToImage) {
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }
            itemAuthor.text = article?.author
            itemDescription.text = article?.description
            itemTitle.text = article?.title
        }
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
//        return false
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
//        return false
    }
}