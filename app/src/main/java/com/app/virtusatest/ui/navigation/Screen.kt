package com.app.virtusatest.ui.navigation

import androidx.annotation.StringRes
import com.app.virtusatest.R
import com.app.virtusatest.utils.Constants.DETAILS
import com.app.virtusatest.utils.Constants.FAVOURITES
import com.app.virtusatest.utils.Constants.HOME


sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(HOME, R.string.text_home)
    object Details : Screen(DETAILS, R.string.text_details)
    object Favourites : Screen(FAVOURITES, R.string.text_favourites)
}
