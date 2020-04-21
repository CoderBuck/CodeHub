package github.coderbuck.codehub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import github.coderbuck.codehub.api.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast("开始请求")
        request()
    }

    fun request() {
        val call = Api.github.getUserHomePage("coderbuck")
        call.enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                showToast("请求失败")
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                showToast("请求成功")
                val body = response.body()
                if (body == null) {
                    showToast("body == null")
                    return
                }

                println(body)
            }
        })
    }

    fun showToast(s: String) = Toast.makeText(this@MainActivity, s, Toast.LENGTH_LONG).show()
}
