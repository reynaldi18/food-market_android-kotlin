package com.fMarket.app.helper

import com.google.gson.stream.MalformedJsonException
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.internal.EverythingIsNonNull
import java.lang.reflect.Type

internal class NullOnEmptyConverterFactory : Converter.Factory() {
    @EverythingIsNonNull
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter { body: ResponseBody ->
            if (body.contentLength() == 0L) return@Converter null
            try {
                delegate.convert(body)
            } catch (e: MalformedJsonException) {
                return@Converter null
            }
        }
    }
}
