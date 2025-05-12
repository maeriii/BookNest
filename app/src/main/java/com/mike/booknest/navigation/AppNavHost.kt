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
import com.mike.booknest.ui.screens.contact.ContactScreen
import com.mike.booknest.ui.screens.dashboard.DashboardScreen
import com.mike.booknest.ui.screens.home.HomeScreen
import com.mike.booknest.ui.screens.order.OrderScreen
import com.mike.booknest.ui.screens.profile.ProfileScreen
import com.mike.booknest.ui.screens.review.ReviewScreen
import com.mike.booknest.ui.screens.splash.SplashScreen
import com.mike.booknest.ui.screens.wishlist.WishlistScreen
import com.mike.booknest.ui.screens.bookdetail.BookDetailScreen
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



        // Wishlist Screen
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

        // Order Screen
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

        // Book Detail Screen
        composable(
            route = "$ROUT_BOOK_DETAIL/{title}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Unknown Book"
            val description = backStackEntry.arguments?.getString("description") ?: "No description"
            BookDetailScreen(navController, title, description)
        }
    }
}