import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://www.random.org"

    // Build a Moshi instance if you have other JSON APIs
    private val moshi = Moshi.Builder().build()

    val apiService: RandomApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RandomApiService::class.java)
    }
}
