package github.coderbuck.codehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import com.github.GetPinnedReposQuery
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

class GithubActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getPinnedReposQuery = GetPinnedReposQuery()

        apolloClient.query(getPinnedReposQuery)
            .enqueue(object : ApolloCall.Callback<GetPinnedReposQuery.Data?>() {
                override fun onFailure(e: ApolloException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(response: com.apollographql.apollo.api.Response<GetPinnedReposQuery.Data?>) {
                    response.data?.user?.pinnedItems?.edges
                }
            })
    }

    companion object {

        private val GITHUB_GRAPHQL_ENDPOINT = "https://api.github.com/graphql"

        private val httpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(NetworkInterceptor())
                .build()
        }


        private val apolloClient: ApolloClient by lazy {
            ApolloClient.builder()
                .serverUrl(GITHUB_GRAPHQL_ENDPOINT)
                .okHttpClient(httpClient)
                .build()
        }

        private class NetworkInterceptor : Interceptor {

            override fun intercept(chain: Interceptor.Chain?): Response {
                return chain!!.proceed(
                    chain.request().newBuilder().header("Authorization", "Bearer xxx").build()
                )
            }
        }

    }
}