import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun LoadImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(imageUrl)

    // Display the image and show a loading indicator until it's loaded
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(painter = painter, contentDescription = null, modifier = Modifier.fillMaxSize())

        if (painter.state is coil.compose.AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}