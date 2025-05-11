package com.mike.booknest.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFF7A00), Color(0xFFFF4785)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.background(gradient)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("John Doe", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text("johndoe@example.com", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                Text("Nairobi, Kenya", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))

                Spacer(modifier = Modifier.height(24.dp))

                ProfileActionButton("Edit Profile") {
                    navController.navigate("editProfile")
                }

                Spacer(modifier = Modifier.height(12.dp))

                ProfileActionButton("Logout") {
                    // TODO: Implement logout logic like clearing auth tokens
                    navController.navigate("signIn") {
                        popUpTo("profile") { inclusive = true } // Clear backstack
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                ProfileActionButton("Sign In") {
                    navController.navigate("signIn")
                }
            }
        }
    )
}

@Composable
fun ProfileActionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(text, color = Color(0xFFFF4785), fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
