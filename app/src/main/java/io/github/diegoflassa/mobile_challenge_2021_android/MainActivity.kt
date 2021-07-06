package io.github.diegoflassa.mobile_challenge_2021_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import dagger.hilt.android.AndroidEntryPoint
import io.github.diegoflassa.mobile_challenge_2021_android.databinding.ActivityMainBinding
import io.github.diegoflassa.mobile_challenge_2021_android.models.MyState
import io.github.diegoflassa.mobile_challenge_2021_android.models.NetworkStatusViewModel
import io.github.diegoflassa.mobile_challenge_2021_android.network.NetworkStatusTracker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import android.view.Menu
import android.view.MenuItem


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @ExperimentalCoroutinesApi
    private val networkStatusViewModel: NetworkStatusViewModel by lazy {
        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    val networkStatusTracker = NetworkStatusTracker(this@MainActivity)
                    val viewModel = NetworkStatusViewModel(networkStatusTracker)
                    @Suppress("UNCHECKED_CAST")
                    return viewModel as T
                }
            },
        ).get(NetworkStatusViewModel::class.java)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NormalTheme_NoActionBar)
        super.onCreate(savedInstanceState)

        networkStatusViewModel.state.observe(this) { state ->
            when (state) {
                MyState.Error -> {
                }
                MyState.Fetched -> {
                }
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.title = ""

        //val navHostFragment =
        //    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        //val navController = navHostFragment.navController
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_settings) {
            // do something
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
