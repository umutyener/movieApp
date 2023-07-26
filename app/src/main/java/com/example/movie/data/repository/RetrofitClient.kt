import com.example.movie.Constants
import com.example.movie.data.network.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAuthApi(): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}
