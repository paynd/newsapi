package name.paynd.android.newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

//        val fragmentManager = supportFragmentManager
//        fragmentManager.commit {
//            add(R.id.fragment_container, SourcesFragment())
//        }
    }

    companion object {
        const val SOURCES = "sources"
    }
}