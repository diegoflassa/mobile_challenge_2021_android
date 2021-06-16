@file:Suppress("unused")

package io.github.diegoflassa.mobile_challenge_2021_android

import android.app.Application
import android.content.Context
import io.github.diegoflassa.mobile_challenge_2021_android.models.AllPatientsFragmentViewModel
import io.github.diegoflassa.mobile_challenge_2021_android.models.PatientDetailsFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module
import java.lang.ref.WeakReference

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val myModules: Module = module {
            viewModel { AllPatientsFragmentViewModel(get()) }
            viewModel { PatientDetailsFragmentViewModel(get()) }
        }
        startKoin {
            // Fix bug of koin initialization
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(myModules)
        }
    }

    companion object {
        private val TAG = MyApplication::class.simpleName
        private lateinit var context: WeakReference<Context>
        fun getContext(): Context {
            return context.get()!!
        }
    }
}
