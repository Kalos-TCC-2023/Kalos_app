package br.senai.sp.jandira.app_kalos.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import br.senai.sp.jandira.kalos_app.R
import br.senai.sp.jandira.kalos_app.ui.theme.fontFamily


@Composable
fun createTitleKalos(content: String, sizeText: Int, colorText: Color, bold: Int, alinhamento: TextAlign) {
    Text(
        text = content, fontSize = sizeText.sp, color = colorText, fontWeight = FontWeight(bold), textAlign = alinhamento, fontFamily = fontFamily
    )

}

@Composable
fun createTextKalos(content: String, sizeText: Int, colorText: Color, bold: Int, alinhamento:TextAlign , modifier: Modifier = Modifier) {
    Text(text = content, fontSize = sizeText.sp, color = colorText, fontWeight = FontWeight(bold), textAlign = alinhamento, modifier = modifier)

}
@Composable

fun createTextKalosRedirection(content: String, sizeText: Int, colorText: Color, bold: Int, alinhamento:TextAlign , navControlelr: NavController, navName: String) {
    Text(text = content, fontSize = sizeText.sp, color = colorText, fontWeight = FontWeight(bold), textAlign = alinhamento, modifier = Modifier .clickable {
        navControlelr.navigate(navName)
    })

}


@Preview
@Composable
fun teste() {
    createTextKalos(content = "sdfdsfdsf", sizeText = 10, colorText = Color.Red, bold = 20, alinhamento = TextAlign.Center, )
}