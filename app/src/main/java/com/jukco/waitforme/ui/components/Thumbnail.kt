package com.jukco.waitforme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jukco.waitforme.R

/* TODO : 인터넷에서 이미지 로드로 기능 변경 시
    Image -> AsyncImage,
    imagePath: Int -> url: String
    로 변경.
    이미지 불러오기 실패 시 대체 이미지도 추가 */
private val exImagePath = R.drawable.img_store_example
val MIN_WIDTH = 154.dp

@Composable
fun LargeThumbnail(imagePath: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imagePath),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .defaultMinSize(minWidth = MIN_WIDTH, minHeight = 192.dp)
            .aspectRatio(154f / 192f)
            .clip(
                RoundedCornerShape(
                    topStart = 4.dp,
                    topEnd = 24.dp,
                    bottomStart = 4.dp,
                    bottomEnd = 4.dp,
                ),
            ),
    )
}

@Composable
fun MediumThumbnail(imagePath: Int, modifier: Modifier = Modifier) {
}

@Composable
fun SmallThumbnail(imagePath: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imagePath),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .defaultMinSize(100.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(4.dp)),
    )
}

@Preview(showBackground = true)
@Composable
private fun LargeThumbnailPreview() {
    LargeThumbnail(imagePath = exImagePath)
}

@Preview(showBackground = true)
@Composable
private fun MediumThumbnailPreview() {
    MediumThumbnail(imagePath = exImagePath)
}

@Preview(showBackground = true)
@Composable
private fun SmallThumbnailPreview() {
    SmallThumbnail(imagePath = exImagePath)
}
