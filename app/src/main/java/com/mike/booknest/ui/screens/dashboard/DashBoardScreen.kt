package com.mike.booknest.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.booknest.R
import com.mike.booknest.navigation.ROUT_BOOK_DETAIL
import com.mike.booknest.navigation.ROUT_CONTACT
import com.mike.booknest.navigation.ROUT_HOME
import com.mike.booknest.navigation.ROUT_ORDERS
import com.mike.booknest.navigation.ROUT_PROFILE
import com.mike.booknest.navigation.ROUT_WISHLIST

@Composable
fun DashboardScreen(navController: NavController) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFFF5722), Color(0xFFE91E63)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(16.dp)
    ) {
        // Top Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.book),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "BookNest",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardCard("Home", R.drawable.home) {
                navController.navigate(ROUT_HOME)
            }
            DashboardCard("About", R.drawable.about) {
                navController.navigate(ROUT_BOOK_DETAIL)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardCard("Contact", R.drawable.phone) {
                navController.navigate(ROUT_CONTACT)
            }
            DashboardCard("Wishlist", R.drawable.heart) {
                navController.navigate(ROUT_WISHLIST)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Third Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DashboardCard("Profile", R.drawable.user) {
                navController.navigate(ROUT_PROFILE)
            }
            DashboardCard("Orders", R.drawable.orders) {
                navController.navigate(ROUT_ORDERS)
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(160.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}
