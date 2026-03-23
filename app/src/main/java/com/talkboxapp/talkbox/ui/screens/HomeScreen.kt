package com.talkboxapp.talkbox.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.talkboxapp.talkbox.R
import com.talkboxapp.talkbox.utils.submitCredentials

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (String, String) -> Unit
) {

    var userId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_outline_message),
                            contentDescription = "forMessage"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray.copy(alpha = 0.4f)
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TalkBox",
                    fontSize = 18.sp,

                    )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = userId,
                onValueChange = { userId = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text("UserId") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text("UserName") }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 56.dp, vertical = 26.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        submitCredentials(
                            context = context,
                            userId = userId,
                            userName = userName,
                            onSuccess = {
                                onNavigate(
                                    userId,
                                    userName
                                )
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Entrar")
                }
            }
        }
    }
}