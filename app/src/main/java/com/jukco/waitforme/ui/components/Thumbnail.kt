package com.jukco.waitforme.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jukco.waitforme.R

/* TODO : 대체 이미지와 오류 이미지 수정할 것 */

@Composable
fun RectThumbnail(
    imagePath: String,
    width: Dp = 154.dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imagePath)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.baseline_image_24),
        error = painterResource(R.drawable.img_store_example),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .defaultMinSize(minWidth = width)
            .aspectRatio(154f / 192f)
            .clip(
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 24.dp,
                    bottomStart = 4.dp,
                    bottomEnd = 4.dp,
                )
            )
    )
}

@Composable
fun SquareThumbnail(
    imagePath: String,
    size: Dp = 100.dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imagePath)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.baseline_image_24),
        error = painterResource(R.drawable.img_store_example),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .defaultMinSize(size)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(4.dp))
    )
}

@Preview(showBackground = true)
@Composable
private fun RectThumbnailPreview() {
    RectThumbnail(imagePath = "")
}

@Preview(showBackground = true)
@Composable
private fun SquareThumbnailPreview() {
    SquareThumbnail(imagePath = "")
}
