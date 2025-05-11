package com.mike.booknest.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFF5722), Color(0xFFE91E63)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    val books = listOf(
        Book("Atomic Habits", R.drawable.book, "Atomic Habits is a guide on how small changes in habits can lead to remarkable results."),
        Book("1984", R.drawable.book, "1984 is a dystopian novel about totalitarian surveillance and mind control."),
        Book("The Alchemist", R.drawable.book, "A shepherd embarks on a journey to find a treasure and discover the meaning of his dreams."),
        Book("Sapiens", R.drawable.book, "Sapiens chronicles the history of humankind from ancient times to the present."),
        Book("The Great Gatsby", R.drawable.book, "The Great Gatsby explores themes of wealth, love, and the American Dream in the 1920s."),
        Book("The Hobbit", R.drawable.book, "A hobbit embarks on a quest to reclaim a stolen treasure from a dragon."),
        Book("Educated", R.drawable.book, "A memoir about growing up in a strict and abusive household and seeking education."),
        Book("Becoming", R.drawable.book, "Michelle Obama's memoir about her life and experiences as the First Lady of the United States."),
        Book("Harry Potter", R.drawable.book, "A young boy discovers he is a wizard and attends Hogwarts to defeat dark forces."),
        Book("The Book Thief", R.drawable.book, "The story of a young girl in Nazi Germany who finds solace in books during World War II."),
        Book("Dune", R.drawable.book, "A young nobleman navigates a universe filled with political intrigue and environmental challenges."),
        Book("It Ends With Us", R.drawable.book, "A love story that deals with difficult themes of abuse and strength.")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BookNest", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.background(gradient)
            )
        },
        containerColor = Color.Transparent,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    "Discover Your Next Read 📖",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(books) { book ->
                        BookItem(
                            title = book.title,
                            imageRes = book.imageRes,
                            onBookClick = {
                                navController.navigate("bookDetail/${Uri.encode(book.title)}/${Uri.encode(book.description)}")
                            },
                            onWishlistClick = {
                                navController.navigate("wishlist/${Uri.encode(book.title)}/${Uri.encode(book.description)}")
                            },
                            onOrderClick = {
                                navController.navigate("orders/${Uri.encode(book.title)}/${Uri.encode(book.description)}")
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun BookItem(
    title: String,
    imageRes: Int,
    onBookClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onOrderClick: () -> Unit
) {
    val buttonColor = Color(0xFFFF4081)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clickable(onClick = onBookClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$title Cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onWishlistClick,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                ) {
                    Text("Wishlist", fontSize = 12.sp, color = Color.White, maxLines = 1)
                }

                Button(
                    onClick = onOrderClick,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                ) {
                    Text("Order", fontSize = 7.sp, color = Color.White, maxLines = 1)
                }
            }
        }
    }
}

data class Book(val title: String, val imageRes: Int, val description: String)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
