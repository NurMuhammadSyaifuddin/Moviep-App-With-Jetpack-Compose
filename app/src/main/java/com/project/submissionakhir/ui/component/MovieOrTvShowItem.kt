package com.project.submissionakhir.ui.component

import com.project.submissionakhir.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.submissionakhir.ui.theme.SubmissionAkhirTheme

@Composable
fun MovieOrTvShowItem(
    title: String,
    image: String?,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(120.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = if (image.isNullOrBlank()) Gray else image,
                contentDescription = title,
                modifier = modifier
                    .width(100.dp)
                    .background(
                        Gray
                    ).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier.width(16.dp))

            Text(text = title, fontFamily = FontFamily(Font(R.font.roboto_bold)), fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = modifier.padding(end = 16.dp))

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovieOrTvShowItemPreview() {
    SubmissionAkhirTheme {
        MovieOrTvShowItem(title = "Naruto Shipudden The Last Movie", image = "")
    }
}