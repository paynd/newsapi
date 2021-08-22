package name.paynd.android.newsapi.articles

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
}

class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding by viewBinding(ArticleItemBinding::bind)

    fun bind(article: Article?) {
        with(viewBinding) {
            article?.let { item ->
                image.load(item.urlToImage) {
                    placeholder(ColorDrawable(Color.TRANSPARENT))
                }
                with(item) {
                    itemAuthor.setString(author)
                    itemDescription.setString(description)
                    itemTitle.setString(title)
                    itemDate.setString(publishedAt)
                }
            }
        }
    }
}

fun TextView.setString(text: String?) {
    text?.let {
        this.text = it
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title && oldItem.url == newItem.url
    }
}