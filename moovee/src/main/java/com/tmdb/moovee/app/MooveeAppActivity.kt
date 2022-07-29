package com.tmdb.moovee.app

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.moovee.engine.extension.buildDialog
import com.moovee.engine.extension.negativeAction
import com.moovee.engine.extension.positiveAction
import com.moovee.engine.extension.title
import com.moovee.engine.utils.dialog.AlertDialogView
import com.tmdb.moovee.R
import com.tmdb.moovee.app.screen.popular.ui.fragment.PopularMoviesFragment
import com.tmdb.moovee.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MooveeAppActivity : AppCompatActivity(), PopularMoviesFragment.HomeCallback {

    private lateinit var binding: ActivityMainBinding
    private var alertDialogView: AlertDialogView? = null

    companion object {
        const val FADING_ANIMATION_DURATION = 200L
        const val ALPHA_TRANSPARENT = 0.0f
    }

    private var isEditing: Boolean = false
//    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MooveeApp_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarContainer.toolbar)
        configureNavController()
    }

    private fun configureNavController() {
        val navController = findNavController()
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home,
//                R.id.nav_about
//            ),
//            binding.drawerLayout
//        )
        setupActionBarWithNavController(navController, null)
        binding.navView.setupWithNavController(navController)
    }

    private fun findNavController(): NavController {
        return (
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            ).navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp(null) || super.onSupportNavigateUp()
    }

    override fun onBackPressedFromHome() {
        configureDialog()
    }

    private fun configureDialog() {
        binding.drawerLayout.closeDrawer(Gravity.LEFT)
        createAlertDialog(
            getString(R.string.dialog_title),
            getString(R.string.dialog_negative_answer),
            getString(R.string.dialog_positive_answer),
            { finish() },
            { removeDialogPopup() }
        ).let {
            alertDialogView = it
            binding.appBarContainer.contentMainContainer.rootView.addView(
                alertDialogView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    private fun createAlertDialog(
        titleText: String,
        negativeText: String,
        positiveText: String,
        positiveClickAction: () -> Unit,
        negativeClickAction: () -> Unit
    ) =
        buildDialog(this) {
            title(titleText)
            positiveAction(positiveText) { positiveClickAction }
            negativeAction(negativeText) { negativeClickAction }
        }

    private fun removeDialogPopup() {
        alertDialogView?.let {
            it.animate()
                .alpha(ALPHA_TRANSPARENT)
                .setDuration(FADING_ANIMATION_DURATION)
                .withEndAction {
                    binding.appBarContainer.contentMainContainer.rootView.removeView(it)
                }
                .start()
        }
    }
}
