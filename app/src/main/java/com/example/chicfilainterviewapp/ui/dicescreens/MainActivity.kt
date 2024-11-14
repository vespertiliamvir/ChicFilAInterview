package com.example.chicfilainterviewapp

import DiceRepositoryImpl
import DiceViewModel
import DiceViewModelFactory
import RetrofitInstance
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.chicfilainterviewapp.ui.theme.ChicFilAInterviewAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var diceViewModel: DiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create an instance of the repository
        val repository = DiceRepositoryImpl(RetrofitInstance.apiService)

        // Create the ViewModel using the factory
        diceViewModel = ViewModelProvider(this, DiceViewModelFactory(repository))
            .get(DiceViewModel::class.java)

        setContent {
            ChicFilAInterviewAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Pass the ViewModel to the Dice composable
                    Dice(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        diceViewModel = diceViewModel
                    )
                }
            }
        }
    }
}


@Composable
fun Dice(name: String, modifier: Modifier = Modifier, diceViewModel: DiceViewModel) {
    // Observe the dice result (StateFlow)
    val diceResult by diceViewModel.diceResult.collectAsState()

    // Get the image URL based on the current dice result
    val imageUrl by diceViewModel.currentDiceImage.collectAsState()

    // Log the current image URL to check if it's being updated correctly
    Log.e("MONTAGNE", "Current dice image: $imageUrl")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Display the dice image based on the current dice result
        AsyncImage(
            model = imageUrl,  // Use image URL from ViewModel
            contentDescription = "Dice Image",
            modifier = Modifier.size(200.dp), // Set the size of the image
            contentScale = ContentScale.Fit  // Adjust content scale as needed
        )

        // Button to roll the dice
        Button(
            onClick = { diceViewModel.rollDice() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            Text("Roll Dice")
        }
    }
}

//
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ChicFilAInterviewAppTheme {
//        Dice()
//    }
//}
