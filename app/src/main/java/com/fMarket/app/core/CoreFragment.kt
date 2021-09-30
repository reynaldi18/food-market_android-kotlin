package com.fMarket.app.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.fMarket.app.R
import com.fMarket.app.helper.Common
import org.koin.android.ext.android.inject


open class CoreFragment : Fragment() {
    private val analytics: FirebaseAnalytics by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        logScreenEvent()
    }

    private fun logScreenEvent() {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, javaClass.simpleName)
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        FirebaseCrashlytics.getInstance().log(javaClass.simpleName)
    }

    fun showProgress() = Common.showProgressDialog(context, null)
    fun hideProgress() = Common.dismissProgressDialog()

    open fun setupToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener { onBackPressed() }
        }
    }
}
