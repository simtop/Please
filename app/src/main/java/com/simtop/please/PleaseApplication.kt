package com.simtop.please

import android.app.Application
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.simtop.please.data.db.PleaseDatabase
import com.simtop.please.data.network.ConnectivityInterceptor
import com.simtop.please.data.network.ConnectivityInterceptorImpl
import com.simtop.please.data.network.GNBService
import com.simtop.please.data.network.datasource.PleaseNetworkDataSource
import com.simtop.please.data.network.datasource.PleaseNetworkDataSourceImpl
import com.simtop.please.data.provider.UnitProvider
import com.simtop.please.data.provider.UnitProviderImpl
import com.simtop.please.data.repository.PleaseRepository
import com.simtop.please.data.repository.PleaseRepositoryImpl
import com.simtop.please.ui.rates.RatesViewModelFactory
import com.simtop.please.ui.transactions.detail.TransactionsDetailViewModelFactory
import com.simtop.please.ui.transactions.list.TransactionsListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class PleaseApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@PleaseApplication))

        bind() from singleton { PleaseDatabase(instance()) }
        bind() from singleton { instance<PleaseDatabase>().ratesDao() }
        bind() from singleton { instance<PleaseDatabase>().transactionsDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { GNBService(instance()) }
        bind<PleaseNetworkDataSource>() with singleton {
            PleaseNetworkDataSourceImpl(instance()) }
        bind<PleaseRepository>() with singleton { PleaseRepositoryImpl(instance(), instance(),instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { RatesViewModelFactory(instance()) }
        bind() from provider {TransactionsListViewModelFactory(instance())}
        bind() from factory { detailSku : String -> TransactionsDetailViewModelFactory(detailSku,instance(),instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        //TODO:
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}
