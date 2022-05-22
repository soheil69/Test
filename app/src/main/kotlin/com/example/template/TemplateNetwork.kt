package com.example.template

import com.example.template.di.CacheBase
import com.example.template.network.TemplateRestApi
import com.google.gson.Gson
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TemplateNetwork @Inject constructor(
    @CacheBase private val restApi: Lazy<TemplateRestApi>,
    private val gson: Gson
) {

    // override fun getPopularTvShows(
    //     page:Int
    // ): Single<DataResponse<TvShow>> =
    //     restApi.get()
    //         .getPopularTvShows(page)
    //         .flatMap { tvShowResponse ->
    //             TvShowResponseToTvShowDataResponseMapper().map(tvShowResponse)
    //                 .let { Single.just(it) }//?: Single.error(UnexpectedResponseException())
    //         }.onErrorResumeNext { t ->
    //             when (t) {
    //                 is HttpException -> {
    //                     Single.error(NetworkException(HttpExceptionToNetworkErrorMapper(gson).map(t)))
    //                 }
    //                 is JsonParseException -> Single.error(UnexpectedResponseException(t.message))
    //                 is SocketTimeoutException -> Single.error(TimeoutException(t))
    //                 is IOException -> Single.error(NoInternetException(t))
    //                 else -> Single.error(t)
    //             }
    //         }

}
