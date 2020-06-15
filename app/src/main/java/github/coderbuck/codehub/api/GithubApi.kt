package github.coderbuck.codehub.api

import github.coderbuck.codehub.bean.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("https://github.com/{user}")
    fun getUserHomePage(@Path("user") user: String): Call<String>

    @GET("/repos/{user}/{repo_name}")
    fun getRepo(@Path("user") user: String, @Path("repo_name") repoName: String): Call<Repo>
}