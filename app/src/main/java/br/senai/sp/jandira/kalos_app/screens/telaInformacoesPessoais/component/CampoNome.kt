package br.senai.sp.jandira.kalos_app.screens.telaInformacoesPessoais.component



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.kalos_app.ui.theme.GreenKalos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoNome(value: String, aoMudar: (String) -> Unit, placeholder: String, isError: Boolean) {
    OutlinedTextField(
        value =  value,
        onValueChange = {
            aoMudar (it)
        },
        placeholder = {
            Text(text = placeholder, color = Color(0xFF606060))
        },
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()

        ,
        singleLine = true,
        isError = isError,
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = GreenKalos,
            containerColor = Color(0xFF393939),
            unfocusedBorderColor = Color(0xFF393939),
            focusedBorderColor = GreenKalos,
            cursorColor = GreenKalos
        )
    )

}
