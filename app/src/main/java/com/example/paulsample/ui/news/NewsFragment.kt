package com.example.paulsample.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paulsample.base.BaseFragment
import com.example.paulsample.databinding.FragmentNewsBinding
import com.example.paulsample.utils.extensions.load
import com.example.paulsample.viewmodel.news.NewsAdapter
import com.example.paulsample.viewmodel.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    private lateinit var adapter: NewsAdapter

    private val args: NewsFragmentArgs by navArgs()

    private val newsArticleViewModel: NewsViewModel by activityViewModels()

    /**
     * Create Binding
     */
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentNewsBinding{
        return FragmentNewsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializing()
    }

    private fun initializing() {
        binding?.let {
            adapter = NewsAdapter()
            adapter.onNewsClicked = {
                // TODO: Your news item click invoked here
            }
            it.newsList.setEmptyView(it.emptyView.emptyView)
            it.newsList.setProgressView(it.progressView.progressView)
            it.newsList.layoutManager = LinearLayoutManager(context)
            it.newsList.adapter = adapter
        }

        observeNewsOfCountry(args.countryKey)
    }

    /**
     * Get country news using Network & DB Bound Resource
     * Observing for data change from DB and Network Both
     */
    private fun observeNewsOfCountry(countryKey: String) {
        binding?.let { binding ->
            newsArticleViewModel.getNewsArticles(countryKey).observe(viewLifecycleOwner) {
                when {
                    it.status.isLoading() -> {
                        binding.newsList.showProgressView()
                    }
                    it.status.isSuccessful() -> {
                        it?.load(binding.newsList) { news ->
                            adapter.replaceItems(news ?: emptyList())
                        }
                    }
                    it.status.isError() -> {
                        // TODO: Error Handling
                    }
                }
            }
        }
    }
}
