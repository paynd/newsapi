package name.paynd.android.newsapi.api

import android.annotation.SuppressLint
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*

@ExperimentalSerializationApi
@Serializer(forClass = Date::class)
internal object DateSerializer : KSerializer<Date> {
    @SuppressLint("SimpleDateFormat")
    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

    override fun deserialize(decoder: Decoder): Date {
        return dateFormat.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(dateFormat.format(value))
    }

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Date")
}