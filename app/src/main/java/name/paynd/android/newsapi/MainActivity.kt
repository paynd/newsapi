package name.paynd.android.newsapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import name.paynd.android.newsapi.articles.ArticlesFragment
import name.paynd.android.newsapi.databinding.ActivityMainBinding
import name.paynd.android.newsapi.sources.SourcesFragment

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("####", "onCreate")
        installSplashScreen()
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                (supportFragmentManager.findFragmentByTag(SOURCES) as SourcesFragment?)?.apply {
                    handleSourceClick = handleClick()
                }
            }
        }
    }

    private fun handleClick() = object : SourcesFragment.HandleSourceClick {
        override fun invoke(source: String) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ArticlesFragment>(
                    R.id.fragment_container,
                    args = Bundle().apply {
                        putString(ArticlesFragment.SOURCE, source)
                    }
                )
                addToBackStack(SOURCES)
            }
        }
    }

    companion object {
        const val SOURCES = "SOURCES"
    }
}