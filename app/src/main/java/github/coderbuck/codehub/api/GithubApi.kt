package github.coderbuck.codehub.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/{user}")
    fun getUserHomePage(@Path("user") user: String) : Call<String>
}