package github.coderbuck.codehub.api

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object Api {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://github.com")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val github = retrofit.create(GithubApi::class.java)
}