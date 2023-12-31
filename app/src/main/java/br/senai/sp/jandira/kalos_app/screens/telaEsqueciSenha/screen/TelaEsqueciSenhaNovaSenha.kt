package br.senai.sp.jandira.kalos_app.screens.telaEsqueciSenha.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.app_kalos.components.createButtonWithError2
import br.senai.sp.jandira.app_kalos.components.createButtonWithFunction
import br.senai.sp.jandira.app_kalos.components.createTextKalos
import br.senai.sp.jandira.app_kalos.components.getLogoKalos
import br.senai.sp.jandira.kalos_app.R
import br.senai.sp.jandira.kalos_app.Storage
import br.senai.sp.jandira.kalos_app.screens.telaCriarConta.components.CampoEmailCadastrar2
import br.senai.sp.jandira.kalos_app.screens.telaCriarConta.components.CampoSenhaCadastrar
import br.senai.sp.jandira.kalos_app.service.AlunoService
import br.senai.sp.jandira.kalos_app.service.RetrofitHelper
import br.senai.sp.jandira.kalos_app.ui.theme.GrayKalos
import br.senai.sp.jandira.kalos_app.ui.theme.GreenKalos
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

@Composable
fun TelaEsqueciSenhaNovaSenha(navController: NavController, lifecycleCoroutineScope: LifecycleCoroutineScope, localStorage: Storage) {
    val focusManger = LocalFocusManager.current
    val context = LocalContext.current
    val email = localStorage.lerValor(context, "emailEsqueciSenha")
    var senhaNovaState by remember {
        mutableStateOf("")
    }
    var senhaNovaStateError by remember {
        mutableStateOf("")
    }
    var confirmarState by remember {
        mutableStateOf("")
    }
    var confirmarStateError by remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            getLogoKalos(size = 150.dp)
            Text(
                text = stringResource(R.string.esqueci_a_senha),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.escolha_uma_nova_senha),
                color = Color.White,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(70.dp))


            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Digite a nova senha",
                    color = GrayKalos,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                CampoSenhaCadastrar(value = senhaNovaState.toString(),
                    keyboarActions = KeyboardActions(
                        onNext = { focusManger.moveFocus(focusDirection = FocusDirection.Down) }
                    ), keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    aoMudar = { novoValor ->
                        senhaNovaState = novoValor
                        senhaNovaStateError = ""
                    },
                    placeholder = "",
                    isError = senhaNovaStateError.isNotEmpty()
                )
                if (senhaNovaStateError.isNotEmpty()) {
                    createTextKalos(
                        content = senhaNovaStateError,
                        sizeText = 16,
                        colorText = Color.Red,
                        bold = 500,
                        alinhamento = TextAlign.Left,
                        modifier = Modifier.padding(start = 10.dp)

                    )
                }
                Spacer(modifier = Modifier.height(30.dp))



                Text(
                    text = "Confirme nova senha",
                    color = GrayKalos,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                CampoSenhaCadastrar(value = confirmarState.toString(),
                    keyboarActions = KeyboardActions(
                        onNext = { focusManger.moveFocus(focusDirection = FocusDirection.Down) }
                    ), keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    aoMudar = { novoValor ->
                        confirmarState = novoValor
                        confirmarStateError = ""
                    },
                    placeholder = "",
                    isError = confirmarStateError.isNotEmpty()
                )
                if (senhaNovaStateError.isNotEmpty()) {
                    createTextKalos(
                        content = senhaNovaStateError,
                        sizeText = 16,
                        colorText = Color.Red,
                        bold = 500,
                        alinhamento = TextAlign.Left,
                        modifier = Modifier.padding(start = 10.dp)

                    )
                }

            }
        }
        var statusCarregando by remember {
            mutableStateOf(false)
        }
        createButtonWithError2(textButton = stringResource(R.string.confirmar), corBotao = GreenKalos, statusCarregando ) {
            senhaNovaStateError = validarSenha(senhaNovaState, confirmarState)
            confirmarStateError = validarSenha(senhaNovaState, confirmarState)

            lateinit var alunoService: AlunoService
            alunoService = RetrofitHelper.getInstance().create(AlunoService::class.java)

            if(senhaNovaStateError == ""  && confirmarStateError == ""){
                statusCarregando = true
                lifecycleCoroutineScope.launch {
                    val body = JsonObject().apply {
                        addProperty("email", email)
                        addProperty("senha", senhaNovaState)
                    }

                    val result = alunoService.atualizarSenhaAluno(body)

                    if(result.isSuccessful){
                        Toast.makeText(context, "Senha atualizada", Toast.LENGTH_SHORT).show()
                        navController.navigate("senhaRedefinida")
                    }else{
                        Toast.makeText(context, "ERRO", Toast.LENGTH_SHORT).show()
                        
                    }
                }
            }
        }



    }
}

fun validarSenha( senhaNova: String, confirmarSenha: String): String {
    if ( senhaNova.isEmpty() || confirmarSenha.isEmpty()) {
        return "Os campos de senha não podem estar vazios"
    } else if (senhaNova.length < 8) {
        return "A senha deve conter pelo menos 8 caracteres"
    } else if (senhaNova.length > 12) {
        return "A senha excedeu o limite de 12 caracteres"
    } else if (senhaNova != confirmarSenha) {
        return "As senhas não coincidem"
    } else {
        return ""
    }
}