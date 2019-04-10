package com.simtop.please

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.simtop.please.data.db.PleaseDatabase
import com.simtop.please.data.network.*
import com.simtop.please.data.repository.PleaseRepository
import com.simtop.please.data.repository.PleaseRepositoryImpl
import com.simtop.please.ui.rates.RatesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class PleaseApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@PleaseApplication))

        bind() from singleton { PleaseDatabase(instance()) }
        bind() from singleton { instance<PleaseDatabase>().ratesDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { GNBService(instance()) }
        bind<PleaseNetworkDataSource>() with singleton { PleaseNetworkDataSourceImpl(instance()) }
        bind<PleaseRepository>() with singleton { PleaseRepositoryImpl(instance(), instance()) }
        bind() from provider { RatesViewModelFactory(instance()) }

    }
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
