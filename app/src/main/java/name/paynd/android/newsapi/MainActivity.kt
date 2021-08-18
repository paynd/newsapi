package name.paynd.android.newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import name.paynd.android.newsapi.databinding.ActivityMainBinding
import name.paynd.android.newsapi.sources_list.SourcesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            add(R.id.fragment_container, SourcesFragment())
            setReorderingAllowed(true)
        }
    }

    companion object {
        const val SOURCES = "sources"
    }
}