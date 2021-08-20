package name.paynd.android.newsapi.articles

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import name.paynd.android.newsapi.articles.vm.ArticlesComponentViewModel
import name.paynd.android.newsapi.articles.vm.ArticlesViewModel
import javax.inject.Inject

private const val SOURCE = "source"

class ArticlesFragment : Fragment(R.layout.fragment_articles) {
    @Inject
    internal lateinit var articlesViewModelFactory: Lazy<ArticlesViewModel.Factory>

    private var source: String? = null

    private val componentViewModel: ArticlesComponentViewModel by viewModels()
    private val articlesViewModel: ArticlesViewModel by viewModels { articlesViewModelFactory.get() }

    override fun onAttach(context: Context) {
        componentViewModel.articlesComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                articlesViewModel.articles.collectLatest {
                    Log.d("####", "Collected paging data: $it")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            source = it.getString(SOURCE)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(source: String) =
            ArticlesFragment().apply {
                arguments = Bundle().apply {
                    putString(SOURCE, source)
                }
            }
    }
}