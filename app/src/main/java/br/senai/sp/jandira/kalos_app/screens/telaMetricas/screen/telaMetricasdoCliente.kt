package br.senai.sp.jandira.kalos_app.screens.telaMetricas.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.app_kalos.components.createTitleKalos
import br.senai.sp.jandira.app_kalos.components.getLogoKalos
import br.senai.sp.jandira.kalos_app.Storage
import br.senai.sp.jandira.kalos_app.components.Espacamento
import br.senai.sp.jandira.kalos_app.components.SetaParaVoltar
import br.senai.sp.jandira.kalos_app.screens.telaMetricas.component.CampoTextoMetricas
import br.senai.sp.jandira.kalos_app.screens.telaMetricas.component.CampoTextoMetricas2
import br.senai.sp.jandira.kalos_app.screens.telaMetricas.component.TextoCampoAltura
import br.senai.sp.jandira.kalos_app.screens.telaMetricas.component.TextoCampoPeso

@Composable
fun TelaMetricas(navController: NavController, localStorage: Storage) {

    val context = LocalContext.current
    var estadoPeso = remember {
        mutableStateOf("")
    }

    var estadoPesoError = remember {
        mutableStateOf("")
    }

    val estadoAltura = remember {
        mutableStateOf("")
    }

    val estadoAlturaError = remember {
        mutableStateOf("")
    }


    Espacamento(tamanho = 40.dp)



    TextoCampoPeso(
        texto = "Digite seu peso atualmente (em kg):")


    CampoTextoMetricas(value = estadoPeso.value, aoMudar ={ novoValor ->
        estadoPeso.value = novoValor
        estadoPesoError.value = ""
        localStorage.salvarValor(context,estadoPeso.value, "peso")    },
        placeholder = "",
        isError = estadoPesoError.value.isNotEmpty() )

    Espacamento(tamanho = 20.dp)

    TextoCampoAltura(
        texto = "Digite sua altura em cm:"
    )

    CampoTextoMetricas2(value = estadoAltura.value, aoMudar ={ novoValor ->
        estadoAltura.value = novoValor
        estadoAlturaError.value = ""
        localStorage.salvarValor(context,estadoAltura.value, "altura")  },
        placeholder = "",
        isError = estadoAlturaError.value.isNotEmpty() )


}