package github.coderbuck.codehub

import android.app.Application
import com.blankj.utilcode.util.Utils
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Timber.plant(Timber.DebugTree())
    }
}
