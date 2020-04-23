package github.coderbuck.codehub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import github.coderbuck.codehub.api.Api
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast("开始请求")
//        request()

        val html = assets.open("coderbuck.html").reader().buffered().use {
            it.readText()
        }
        val graph = HtmlParser.getGraph(html)
        graphView.commitGraphBean = graph

    }

    fun request() {
        val call = Api.github.getUserHomePage("coderbuck")
        call.enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                showToast("请求失败")
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                showToast("请求成功")
                val html = response.body()
                if (html == null) {
                    showToast("body == null")
                    return
                }
                val graphBean = HtmlParser.getGraph(html)
                val size = graphBean.columns[1].cells.size
                println("gwf: size = " + size)

                graphView.commitGraphBean = graphBean
            }
        })
    }

    fun showToast(s: String) = Toast.makeText(this@MainActivity, s, Toast.LENGTH_LONG).show()
}
