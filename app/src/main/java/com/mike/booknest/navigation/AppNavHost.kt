package com.mike.booknest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mike.booknest.data.UserDatabase
import com.mike.booknest.repository.UserRepository
import com.mike.booknest.ui.screens.auth.LoginScreen
import com.mike.booknest.ui.screens.auth.RegisterScreen
import com.mike.booknest.ui.screens.contact.ContactScreen
import com.mike.booknest.ui.screens.dashboard.DashboardScreen
import com.mike.booknest.ui.screens.home.HomeScreen
import com.mike.booknest.ui.screens.order.OrderScreen
import com.mike.booknest.ui.screens.profile.ProfileScreen
import com.mike.booknest.ui.screens.review.ReviewScreen
import com.mike.booknest.ui.screens.splash.SplashScreen
import com.mike.booknest.ui.screens.wishlist.WishlistScreen
import com.mike.booknest.viewmodel.AuthViewModel
import com.mike.booknest.viewmodel.ProductViewModel


@Composable
fun AppNavHost(
    ProductViewModel: ProductViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }

        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }

        composable(ROUT_REVIEW) {
            ReviewScreen(navController)
        }

        // Wishlist Screen with title and description
        composable(
            route = "$ROUT_WISHLIST/{title}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Unknown Book"
            val description = backStackEntry.arguments?.getString("description") ?: "No description"
            WishlistScreen(navController, title, description)
        }

        // Order Screen with title and description
        composable(
            route = "$ROUT_ORDERS/{title}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Unknown Book"
            val description = backStackEntry.arguments?.getString("description") ?: "No description"
            OrderScreen(navController, title, description)
        }

        // Book Detail Screen (can be implemented similarly)
        composable(
            route = "$ROUT_ORDERS?title={title}&description={description}",
            arguments = listOf(
                navArgument("title") { defaultValue = "Untitled Book" },
                navArgument("description") { defaultValue = "No description" }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Untitled Book"
            val description = backStackEntry.arguments?.getString("description") ?: "No description"
            OrderScreen(navController, title, description)
        }

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_Register) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_Register) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
