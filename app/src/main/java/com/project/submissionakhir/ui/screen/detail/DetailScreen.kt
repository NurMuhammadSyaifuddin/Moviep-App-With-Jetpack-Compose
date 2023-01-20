package com.project.submissionakhir.ui.screen.detail

import androidx.activity.compose.BackHandler
import com.project.submissionakhir.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.submissionakhir.utils.BASE_URL_API_IMAGE
import com.project.submissionakhir.utils.POSTER_SIZE_W185
import com.project.submissionakhir.utils.POSTER_SIZE_W780

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    name: String,
    releaseDate: String,
    overview: String?,
    backdrop: String?,
    poster: String?,
    onBackPressed: () -> Unit
) {

    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = if (backdrop.isNullOrBlank()) Gray else "$BASE_URL_API_IMAGE$POSTER_SIZE_W780$backdrop",
            contentDescription = name,
            modifier = modifier
                .fillMaxWidth()
                .height(320.dp)
                .padding(bottom = 24.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = if (poster.isNullOrBlank()) Gray else "$BASE_URL_API_IMAGE$POSTER_SIZE_W185$poster",
                contentDescription = name,
                modifier = modifier
                    .width(80.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.width(16.dp))

            Column(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = name,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontSize = 18.sp,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = releaseDate,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontSize = 14.sp,
                    modifier = modifier.padding(top = 8.dp)
                )

            }

        }

        Text(
            text = if (overview.isNullOrBlank()) "No Description" else overview,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 14.sp,
            modifier = modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify,
            lineHeight = 20.sp
        )

    }
}