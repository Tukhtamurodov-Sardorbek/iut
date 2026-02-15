package com.example.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layout.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                LayoutTeachingScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutTeachingScreen() {
    // 1. Scaffold: The structural layout with slots for standard components
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Layout Masterclass") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        // Column: Main vertical layout container
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // User Photo Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hjeong),
                        contentDescription = "User Photo",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentScale = ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.width(20.dp))
                
                Column {
                    Text(
                        text = "Prof. Hong Jeong",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Mobile Programming Course",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // 2. LazyRow: Efficient horizontal scrolling list
            Text(
                text = "Recent Contacts (LazyRow)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(10) { index ->
                    ContactChip(name = "User $index")
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // 3. Basic Layouts (Row, Box, Spacer)
            Text(
                text = "Basic Layouts (Row, Box, Spacer)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Column inside Row
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(40.dp).background(Color.Red))
                    Text("Stack")
                }
                
                // Spacer for gaps
                Spacer(modifier = Modifier.width(16.dp))

                // Box with overlay (Z-axis stacking)
                Box(contentAlignment = Alignment.Center) {
                    Box(modifier = Modifier.size(60.dp).background(Color.Blue))
                    Text("Top", color = Color.White)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // 4. LazyColumn: Efficient vertical scrolling list (Perfect for Chat)
            Text(
                text = "Messages (LazyColumn)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn(
                modifier = Modifier.weight(1f), // Takes up remaining space
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(20) { index ->
                    ChatBubble(
                        message = "This is message number $index. It shows how LazyColumn handles many items efficiently.",
                        isUser = index % 2 == 0
                    )
                }
            }
        }
    }
}

@Composable
fun ContactChip(name: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 2.dp,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                color = if (isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutTeachingPreview() {
    MyApplicationTheme {
        LayoutTeachingScreen()
    }
}
