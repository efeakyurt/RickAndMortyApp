package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RickAndMortyApp()
                }
            }
        }
    }
}

@Composable
fun RickAndMortyApp(viewModel: CharacterViewModel = viewModel()) {
    var selectedCharacter: Character? by remember { mutableStateOf(null) }

    if (selectedCharacter == null) {
        Column {
            Text(
                text = "Rick and Morty",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            LazyColumn {
                items(viewModel.characters.value) { character ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                selectedCharacter = character
                            }
                    ) {
                        AsyncImage(
                            model = character.image,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = character.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = selectedCharacter?.name ?: "Unknown",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = selectedCharacter?.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${selectedCharacter?.name ?: "Unknown"}", fontSize = 20.sp)
            Text(text = "Status: ${selectedCharacter?.status ?: "Unknown"}", fontSize = 20.sp)
            Text(text = "Species: ${selectedCharacter?.species ?: "Unknown"}", fontSize = 20.sp)
            Text(text = "Gender: ${selectedCharacter?.gender ?: "Unknown"}", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tap here to go back",
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    selectedCharacter = null
                }
            )
        }
    }
}
