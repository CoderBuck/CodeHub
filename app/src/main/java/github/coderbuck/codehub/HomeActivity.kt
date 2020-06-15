package github.coderbuck.codehub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.coderbuck.codehub.databinding.ActivityHomeBinding
import github.coderbuck.codehub.util.contentView

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    private lateinit var bind: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityHomeBinding.bind(contentView)

        bind.go.setOnClickListener {
            val text = bind.repo.text.toString()
            startActivity(
                Intent(this, RepoActivity::class.java).apply {
                    putExtra("key", text)
                }
            )
        }


    }
}