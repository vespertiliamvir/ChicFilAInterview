import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiceViewModel(private val repository: DiceRepositoryImpl) : ViewModel() {

    // List of dice images for each dice result
    private val diceImages = listOf(
        "https://static-00.iconduck.com/assets.00/dice-1-icon-1024x1024-g6v19xk9.png",  // dice  1
        "https://static-00.iconduck.com/assets.00/die-face-2-emoji-510x512-76e9p01g.png",  // dice face 2
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGncwBpbCJ0tuKFymvAReKNgEdoqbF-BKZSA&s",  // dice face 3
        "https://static-00.iconduck.com/assets.00/dice-six-faces-four-icon-2048x2048-q612x0mr.png",  // dice face 4
        "https://game-icons.net/icons/ffffff/000000/1x1/delapouite/dice-six-faces-five.png",  // dice face 5
        "https://game-icons.net/icons/ffffff/000000/1x1/delapouite/dice-six-faces-six.png"   // dice face 6
    )

    // MutableStateFlow for dice result
    private val _diceResult = MutableStateFlow<Int?>(null)
    val diceResult: StateFlow<Int?> = _diceResult

    // MutableStateFlow for current dice image
    private val _currentDiceImage = MutableStateFlow<String>(diceImages[0])  // Default to the first image (dice 1)
    val currentDiceImage: StateFlow<String> = _currentDiceImage

    // Function to roll the dice
    fun rollDice() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.rollDice()
            result.onSuccess { randomInt ->
                Log.e("MONTAGNE", "Dice rolled: $randomInt")  // Log dice result
                _diceResult.value = randomInt
                updateDiceImage(randomInt)  // Update the dice image based on the roll result
            }.onFailure { error ->
                Log.e("MONTAGNE", "Error rolling dice: $error")  // Log any errors
                _diceResult.value = null
                _currentDiceImage.value = diceImages[0]  // Reset to the first image in case of failure
            }
        }
    }

    // Update the current dice image based on the dice roll result
    private fun updateDiceImage(diceValue: Int) {
        if (diceValue in 1..6) {
            _currentDiceImage.value = diceImages[diceValue - 1]  // Get the corresponding image
        } else {
            _currentDiceImage.value = diceImages[0]  // Default to dice 1 if something goes wrong
        }
    }
}
