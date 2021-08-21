package name.paynd.android.newsapi.sources.model

import name.paynd.android.newsapi.api.SourceDto

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
)

internal fun SourceDto.toSource(): Source {
    return Source(
        id = id,
        name = name,
        description = description,
        url = url,
    )
}