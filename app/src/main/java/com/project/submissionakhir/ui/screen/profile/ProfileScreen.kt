package com.project.submissionakhir.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.submissionakhir.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = modifier) {
            Image(
                painter = painterResource(id = R.drawable.pas_foto_circle),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .width(160.dp)
                    .height(160.dp)
                    .padding(bottom = 24.dp)
            )

            Text(
                text = "Nur Muhammad Syaifuddin",
                modifier = modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.roboto_medium)
                ),
                fontSize = 20.sp
            )

            Text(
                text = "nurmuhammadsyaifuddin@std.unissula.ac.id",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.roboto_regular)
                ),
                fontSize = 16.sp
            )

        }
    }

}