package com.example.paulsample.ui.countries

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.paulsample.R
import com.example.paulsample.base.BaseActivity
import com.example.paulsample.databinding.ActivityCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : BaseActivity<ActivityCountriesBinding>() {

    /*
     * A getter for the [NavHostFragment] which is required due to the use of [FragmentContainerView]
     * Issue described at: https://issuetracker.google.com/issues/142847973
     */
    private val navHostFragment: NavHostFragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

    private val navController: NavController by lazy {
        checkNotNull(navHostFragment) {
            "navController cannot be accessed before the activity onCreate has been completed"
        }.navController
    }

    /**
     * Create view binding
     */
    override fun createViewBinding(): ActivityCountriesBinding =
        ActivityCountriesBinding.inflate(layoutInflater)

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
