package github.coderbuck.codehub.api

import github.coderbuck.codehub.bean.Repo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("https://github.com/{user}")
    fun getUserHomePage(@Path("user") user: String): Call<String>

    @GET("/repos/{user}/{repo_name}")
    fun getRepo(@Path("user") user: String, @Path("repo_name") repoName: String): Call<Repo>

    @GET("https://raw.githubusercontent.com/CoderBuck/AutoReceiver/master/README.md")
    fun getReadme() : Call<ResponseBody>
}