package bobby.irawan.githubsearcher

import android.app.Application
import bobby.irawan.githubsearcher.di.repositoryModule
import bobby.irawan.githubsearcher.di.serviceModule
import bobby.irawan.githubsearcher.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppController)
            modules(
                listOf(
                    serviceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}