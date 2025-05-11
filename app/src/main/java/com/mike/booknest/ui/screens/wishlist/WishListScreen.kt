package com.mike.booknest.ui.screens.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

data class WishlistBook(val title: String, val author: String, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavController, title: String, description: String) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFF7A00), Color(0xFFFF4785)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    val wishlist = remember {
        mutableStateListOf(
            WishlistBook(title, description, R.drawable.book), // Add passed book
            WishlistBook("Atomic Habits", "James Clear", R.drawable.book),
            WishlistBook("The Alchemist", "Paulo Coelho", R.drawable.book),
            WishlistBook("Dune", "Frank Herbert", R.drawable.book),
            WishlistBook("Becoming", "Michelle Obama", R.drawable.book)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wishlist", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.background(gradient)
            )
        },
        containerColor = Color.Transparent,
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
                    .padding(padding)
                    .padding(16.dp)
            ) {
                if (wishlist.isEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "Your wishlist is empty.",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(wishlist) { book ->
                            WishlistBookItem(book) {
                                wishlist.remove(book)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WishlistBookItem(book: WishlistBook, onRemove: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = book.imageRes),
                contentDescription = "Book Image",
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, fontWeight = FontWeight.Bold)
                Text(book.author, fontSize = 13.sp, color = Color.Gray)
            }

            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Remove", tint = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WishListScreenPreview() {
    WishlistScreen(
        navController = rememberNavController(),
        title = "Sample Book",
        description = "Sample Author"
    )
}
