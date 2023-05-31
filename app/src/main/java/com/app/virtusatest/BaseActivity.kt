package  com.app.virtusatest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class BaseActivity : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}