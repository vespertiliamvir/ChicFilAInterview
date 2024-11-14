import android.util.Log
import com.example.chicfilainterviewapp.model.DiceRepositoryI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DiceRepositoryImpl(
    private val apiService: RandomApiService
) : DiceRepositoryI {

    override suspend fun rollDice(): Result<Int> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRandomInteger()

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    // Log the raw response body to ensure it's what you expect
                    Log.e("MONTAGNE", "Response body: $responseBody")

                    // Check if the response body is not null and is a valid integer string
                    val parsedResult = responseBody?.trim()?.toIntOrNull()

                    if (parsedResult != null) {
                        // Successfully parsed the integer
                        return@withContext Result.success(parsedResult)
                    } else {
                        // Failed to parse, return failure with a clear message
                        Log.e("MONTAGNE", "Failed to parse integer from response")
                        return@withContext Result.failure(Exception("Failed to parse integer"))
                    }
                } else {
                    // API call failed
                    Log.e("MONTAGNE", "API call failed: ${response.message()}")
                    return@withContext Result.failure(HttpException(response))
                }
            } catch (e: Exception) {
                // Handle general errors
                Log.e("MONTAGNE", "Error occurred: ${e.message}", e)
                return@withContext Result.failure(e)
            }
        }
    }
}
