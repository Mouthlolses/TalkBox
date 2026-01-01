package com.mytalkbox.talkbox.ui.screens.onboarding.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mytalkbox.talkbox.R
import com.mytalkbox.talkbox.data.local.model.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    var selectedIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Telefone", "E-mail")
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current

    when (state) {
        is RegisterUiState.Idle -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_action_arrow_back),
                                    "Voltar",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(start = 24.dp, end = 24.dp)
                        .navigationBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Insira seu telefone ou e-mail",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(24.dp))

                        TabRow(
                            selectedTabIndex = selectedIndex,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(30.dp)
                                )
                                .height(48.dp),
                            indicator = {},
                            divider = {}
                        ) {
                            tabs.forEachIndexed { index, title ->
                                val selected = selectedIndex == index

                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                        .padding(4.dp)
                                        .background(
                                            if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                            RoundedCornerShape(30.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Tab(
                                        selected = selected,
                                        onClick = { selectedIndex = index },
                                        modifier = Modifier.fillMaxSize(),
                                        text = {
                                            Text(
                                                title,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        if (selectedIndex == 0) {
                            OutlinedTextField(
                                value = phone,
                                onValueChange = { phone = it },
                                placeholder = { Text("BR +55  Número de telefone") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp)
                            )

                            Spacer(modifier = Modifier.height(26.dp))

                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                placeholder = { Text("Nome e sobrenome") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = userName,
                                onValueChange = { userName = it },
                                placeholder = { Text("Nome de usuário") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))


                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                placeholder = { Text("Senha") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp),
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
                                    PasswordVisualTransformation(),
                            )

                        } else {
                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                placeholder = { Text("Digite seu e-mail") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp)
                            )

                            Spacer(modifier = Modifier.height(26.dp))

                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                placeholder = { Text("Nome e sobrenome") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = userName,
                                onValueChange = { userName = it },
                                placeholder = { Text("Nome de usuário") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp)
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                placeholder = { Text("Senha") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(26.dp),
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
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            viewModel.register(
                                user = User(
                                    name = name,
                                    userName = userName,
                                    phoneNumber = phone,
                                    email = email,
                                    password = password
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            16.dp
                        )
                    ) {
                        Text(
                            "Registrar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }

        is RegisterUiState.Loading -> {
            CircularProgressIndicator()
        }

        is RegisterUiState.Success -> {
            Toast.makeText(context, (state as RegisterUiState.Success).message, Toast.LENGTH_SHORT)
                .show()
        }

        is RegisterUiState.Error -> {
            Toast.makeText(context, (state as RegisterUiState.Error).message, Toast.LENGTH_LONG)
                .show()
        }
    }
}
