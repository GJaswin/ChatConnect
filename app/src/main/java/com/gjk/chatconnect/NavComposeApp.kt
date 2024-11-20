package com.gjk.chatconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.gjk.chatconnect.nav.Action
import com.gjk.chatconnect.nav.Destination.AuthenticationOption
import com.gjk.chatconnect.nav.Destination.Home
import com.gjk.chatconnect.nav.Destination.Login
import com.gjk.chatconnect.nav.Destination.Register
import com.gjk.chatconnect.ui.theme.ChatConnectTheme
import com.gjk.chatconnect.view.AuthenticationView
import com.gjk.chatconnect.view.home.HomeView
import com.gjk.chatconnect.view.login.LoginView
import com.gjk.chatconnect.view.register.RegisterView

/**
 * The main Navigation composable which will handle all the navigation stack.
 */

@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    ChatConnectTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null)
                Home
            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                HomeView()
            }
        }
    }
}