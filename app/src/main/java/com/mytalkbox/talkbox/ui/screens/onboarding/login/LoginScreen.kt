package com.mytalkbox.talkbox.ui.screens.onboarding.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mytalkbox.talkbox.R
import com.mytalkbox.talkbox.ui.components.GoogleLoginButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current

    when (state) {

        is LoginUiState.Idle -> Scaffold(
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(start = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_action_arrow_back),
                                contentDescription = "Voltar",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->

            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Bem-vindo",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Faça login para continuar",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_stacked_email),
                            contentDescription = null
                        )
                    }
                )

                Spacer(Modifier.height(12.dp))


                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_lock),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                painter = if (showPassword) painterResource(R.drawable.ic_action_visibility)
                                else painterResource(R.drawable.ic_action_visibility_off),
                                contentDescription = "password"
                            )
                        }
                    },
                    visualTransformation = if (showPassword)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                )

                Spacer(Modifier.height(20.dp))


                Button(
                    onClick = {
                        viewModel.login(
                            userEmail = email,
                            userPassword = password
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(30.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        16.dp
                    )
                ) {
                    Text("Entrar", fontSize = 16.sp)
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(Modifier.weight(1f))
                    Text("  ou  ", color = Color.Gray, fontSize = 14.sp)
                    HorizontalDivider(Modifier.weight(1f))
                }

                Spacer(Modifier.height(16.dp))

                // Botão Login com Google (apenas UI)
                GoogleLoginButton(
                    onClick = {

                    }
                )
            }
        }

        is LoginUiState.Loading -> {
            CircularProgressIndicator()
        }

        is LoginUiState.Success -> {
            Toast.makeText(context, (state as LoginUiState.Success).message, Toast.LENGTH_SHORT)
                .show()
        }

        is LoginUiState.Error -> {
            Toast.makeText(context, (state as LoginUiState.Error).message, Toast.LENGTH_LONG)
                .show()
        }
    }
}