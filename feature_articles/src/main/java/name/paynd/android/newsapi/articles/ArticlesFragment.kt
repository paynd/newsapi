package name.paynd.android.newsapi.articles

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import name.paynd.android.newsapi.articles.databinding.ArticlesListBinding
import name.paynd.android.newsapi.articles.vm.ArticlesComponentViewModel
import name.paynd.android.newsapi.articles.vm.ArticlesViewModel
import name.paynd.android.newsapi.core.di.factories.VMFactory
import javax.inject.Inject

class ArticlesFragment : Fragment(R.layout.articles_list) {

    @Inject
    lateinit var vmFactory: VMFactory

    private val binding: ArticlesListBinding by viewBinding()
    private val componentViewModel: ArticlesComponentViewModel by viewModels()
    private val articlesViewModel: ArticlesViewModel by viewModels { vmFactory }

    private var source: String? = null

    override fun onAttach(context: Context) {
        componentViewModel.articlesComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            source = it.getString(SOURCE)
        }

        context?.let {
            val adapter = ArticlesAdapter(it)

            with(binding) {
                list.adapter = adapter
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    articlesViewModel.setCategory(source ?: "")
                    articlesViewModel.articles.collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
                }
            }

            adapter.addLoadStateListener { state ->
                with(binding) {
                    list.isVisible = state.refresh != LoadState.Loading
                    progress.isVisible = state.refresh == LoadState.Loading
                }
            }
        }
    }

    companion object {
        const val SOURCE = "source"
    }
}