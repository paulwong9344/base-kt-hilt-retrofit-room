package com.example.paulsample.viewmodel.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paulsample.databinding.CellNewsArticleBinding
import com.example.paulsample.repository.model.news.News
import com.example.paulsample.utils.extensions.load

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    /**
     * List of news articles
     */
    private var newsArticles: List<News> = emptyList()

    var onNewsClicked: ((News) -> Unit)? = null

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemBinding =
            CellNewsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(itemBinding)
    }

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
        newsHolder.bind(newsArticles[position])

    /**
     * Number of items in the list to display
     */
    override fun getItemCount() = newsArticles.size

    /**
     * View Holder Pattern
     */
    inner class NewsHolder(private val itemBinding: CellNewsArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(newsArticle: News) = with(itemView) {
            itemBinding.tvNewsItemTitle.text = newsArticle.title
            itemBinding.ivNewsImage.load(itemBinding.root.context, newsArticle.urlToImage ?: "")
            itemBinding.root.setOnClickListener {
                onNewsClicked?.invoke(newsArticle)
            }
        }
    }

    /**
     * Swap function to set new data on updating
     */
    @SuppressLint("NotifyDataSetChanged")
    fun replaceItems(items: List<News>) {
        newsArticles = items
        notifyDataSetChanged()
    }
}
