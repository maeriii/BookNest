package com.mike.booknest.ui.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

data class OrderedBook(
    val title: String,
    val quantity: Int,
    val priceKES: Double,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController, title: String, description: String) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFFF7A00), Color(0xFFFF4785)),
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    val orderedBooks = remember {
        mutableStateListOf(
            OrderedBook(title, 1, 1250.0, R.drawable.book), // Book passed from nav
            OrderedBook("The Great Gatsby", 1, 1350.0, R.drawable.book),
            OrderedBook("1984", 2, 980.0, R.drawable.book),
            OrderedBook("To Kill a Mockingbird", 1, 1120.0, R.drawable.book)
        )
    }

    val totalPriceKES = orderedBooks.sumOf { it.priceKES * it.quantity }
    val totalItems = orderedBooks.sumOf { it.quantity }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Order", color = Color.White) },
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
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { /* TODO: Handle order placement */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4785))
                ) {
                    Text("Place Order (Ksh ${"%,.2f".format(totalPriceKES)})")
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Items in your cart: $totalItems",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(orderedBooks) { book ->
                        OrderItemCard(book)
                    }
                }
                Spacer(modifier = Modifier.height(80.dp)) // Space for bottom bar
            }
        }
    )
}

@Composable
fun OrderItemCard(book: OrderedBook) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
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
                Text("Quantity: ${book.quantity}", fontSize = 12.sp, color = Color.Gray)
            }
            Text(
                text = "Ksh ${"%,.2f".format(book.priceKES * book.quantity)}",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderScreenPreview() {
    OrderScreen(
        navController = rememberNavController(),
        title = "Sample Book",
        description = "Sample Description"
    )
}
