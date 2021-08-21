package name.paynd.android.newsapi.sources

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import name.paynd.android.newsapi.core.di.factories.VMFactory
import name.paynd.android.newsapi.sources.databinding.SourcesListBinding
import name.paynd.android.newsapi.sources.model.toSource
import name.paynd.android.newsapi.sources.vm.SourcesComponentViewModel
import name.paynd.android.newsapi.sources.vm.SourcesViewModel
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class SourcesFragment @Inject constructor(
    private val vmFactory: VMFactory
) : Fragment(R.layout.sources_list) {

    private val sourcesViewModel: SourcesViewModel by viewModels { vmFactory }
    private val componentViewModel: SourcesComponentViewModel by viewModels()

    private val viewBinding: SourcesListBinding by viewBinding(SourcesListBinding::bind)

    private var adapter: SourcesAdapter? = null

    // todo: how to navigate in multi-module projects with Nav component
    var handleSourceClick: HandleSourceClick? = null

    override fun onAttach(context: Context) {
        componentViewModel.sourcesComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sourcesAdapter = SourcesAdapter { source ->
            handleSourceClick?.invoke(source)
        }
        this.adapter = sourcesAdapter

        with(viewBinding.list) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = sourcesAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sourcesViewModel.sources.collect { list ->
                    adapter?.submitList(list.map { item -> item.toSource() })
                }
            }
        }
    }

    interface HandleSourceClick {
        operator fun invoke(source: String)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}