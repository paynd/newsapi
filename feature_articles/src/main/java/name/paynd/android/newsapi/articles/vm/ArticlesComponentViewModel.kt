package name.paynd.android.newsapi.articles.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import name.paynd.android.newsapi.articles.ArticlesComponent
import name.paynd.android.newsapi.articles.DaggerArticlesComponent
import name.paynd.android.newsapi.articles.articlesDepsProvider

// this vm is only for holding this component
class ArticlesComponentViewModel(application: Application): AndroidViewModel(application) {
    val articlesComponent: ArticlesComponent by lazy {
        DaggerArticlesComponent.builder()
            .deps(application.articlesDepsProvider.articlesDeps)
            .build()
    }
}