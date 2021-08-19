package name.paynd.android.newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val fragmentManager = supportFragmentManager
//        fragmentManager.commit {
//            add(R.id.fragment_container, SourcesFragment())
//        }
    }

    companion object {
        const val SOURCES = "sources"
    }
}