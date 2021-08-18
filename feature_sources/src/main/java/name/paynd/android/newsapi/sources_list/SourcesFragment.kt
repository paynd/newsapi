package name.paynd.android.newsapi.sources_list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import name.paynd.android.newsapi.sources_list.databinding.SourcesListBinding

/**
 * A fragment representing a list of Items.
 */
class SourcesFragment : Fragment() {
    private val sourcesViewModel: SourcesViewModel by viewModels()
    private val componentViewModel: SourcesComponentViewModel by viewModels()
    private var adapter: SourcesAdapter? = null

    override fun onAttach(context: Context) {
        componentViewModel.sourcesComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sourcesAdapter = SourcesAdapter()
        this.adapter = sourcesAdapter

        val binding = SourcesListBinding.bind(view)

        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = sourcesAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sourcesViewModel.sources.collect {
                    adapter?.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}