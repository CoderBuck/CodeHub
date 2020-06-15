package github.coderbuck.codehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import github.coderbuck.codehub.api.Api
import github.coderbuck.codehub.bean.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RepoActivity : AppCompatActivity(R.layout.activity_repo) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = intent.getStringExtra("key") ?: ""
        Timber.d("receive repo = $repo")
        val split = repo.split("/")
        request(split[0],split[1])
    }

    fun request(user: String, repoName: String) {
        val call = Api.github.getRepo(user, repoName)
        call.enqueue(object : Callback<Repo?> {
            override fun onFailure(call: Call<Repo?>, t: Throwable) {
                ToastUtils.showLong("repo onFailure")
            }

            override fun onResponse(call: Call<Repo?>, response: Response<Repo?>) {
                ToastUtils.showLong("repo onResponse")
                val body = response.body()
                if (body == null) {
                    return
                }

                Timber.d("body = %s", GsonUtils.toJson(body))
            }
        })
    }

}
