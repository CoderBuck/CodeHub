package github.coderbuck.codehub

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import github.coderbuck.codehub.api.Api
import github.coderbuck.codehub.bean.Repo
import github.coderbuck.codehub.databinding.ActivityRepoBinding
import github.coderbuck.codehub.util.contentView
import io.noties.markwon.Markwon
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RepoActivity : AppCompatActivity(R.layout.activity_repo) {

    private lateinit var bind: ActivityRepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRepoBinding.bind(contentView)
        val repo = intent.getStringExtra("key") ?: ""
        Timber.d("receive repo = $repo")
        val split = repo.split("/")
//        request(split[0], split[1])
//        testMd()
        testWebView()
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

    fun testMd() {
        val call = Api.github.getReadme()
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@RepoActivity, "onFailure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(this@RepoActivity, "onResponse", Toast.LENGTH_SHORT).show()

                val body = response.body()
                if (body == null) {
                    return
                }

                val md = body.string()
                val markwon = Markwon.create(this@RepoActivity)
                markwon.setMarkdown(bind.tv, md)
            }
        })

    }

    fun testWebView() {
        bind.webView.loadUrl("https://github.com/CoderBuck/AutoReceiver/blob/master/README.md")
    }

}
