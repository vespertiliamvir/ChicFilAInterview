import retrofit2.Response
import retrofit2.http.GET

interface RandomApiService {
    // Define the endpoint and expected return type
    @GET("/integers/?num=1&min=1&max=6&col=1&base=10&format=plain&rnd=new")
    suspend fun getRandomInteger(): Response<String>  // Response in plain text
}
