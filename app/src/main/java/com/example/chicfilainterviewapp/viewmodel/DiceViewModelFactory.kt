import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DiceViewModelFactory(
    private val repository: DiceRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiceViewModel::class.java)) {
            return DiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
