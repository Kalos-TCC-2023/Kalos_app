package br.senai.sp.jandira.kalos_app.screens.criarContaComponent.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.senai.sp.jandira.kalos_app.Storage
import br.senai.sp.jandira.kalos_app.R
import br.senai.sp.jandira.kalos_app.components.ContinueCom
import br.senai.sp.jandira.kalos_app.components.Espacamento
import br.senai.sp.jandira.kalos_app.screens.telaCriarConta.components.CamposCriarConta
import br.senai.sp.jandira.kalos_app.screens.telaCriarConta.components.HeaderCriarConta
import br.senai.sp.jandira.kalos_app.screens.telaFazerLogin.LoginScreeViewModel
import br.senai.sp.jandira.kalos_app.ui.theme.GreenKalos

@Composable
fun CriarContaScreen(navController: NavController, lifecycleScope: LifecycleCoroutineScope, classe: Storage, viewModel: LoginScreeViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        HeaderCriarConta(navController)

        Spacer(modifier = Modifier.height(30.dp))
        CamposCriarConta(navController, classe, lifecycleScope)


        Column (
            modifier = Modifier.padding(horizontal = 30.dp)
        ){
            ContinueCom(navController = navController, viewModel = viewModel, localstorage = classe, lifecycleScope)
        }

        Espacamento(tamanho = 30.dp)

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(R.string.ja_usuario),
                color = Color.White,
                fontSize = 12.sp
            )


            Text(
                text = stringResource(R.string.faca_login),
                fontSize = 12.sp,
                color = GreenKalos,
                modifier = Modifier.clickable { navController.navigate("fazerLogin") }
            )
        }
    }
    }

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun CriarContaScreenPreview() {
//    CriarContaScreen()
//}