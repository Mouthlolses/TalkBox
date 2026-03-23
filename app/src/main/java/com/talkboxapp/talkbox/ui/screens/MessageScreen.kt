package com.talkboxapp.talkbox.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.talkboxapp.talkbox.R
import com.talkboxapp.talkbox.utils.createGroupChatImpl
import com.talkboxapp.talkbox.utils.newChat

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MessageScreen(
    onNavigate: () -> Unit = {},
    userId: String = "",
    userName: String = ""
) {

    val tabs = listOf("Nova Conversa", "Novo Grupo de Conversa")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var showNewChatDialog by remember { mutableStateOf(false) }
    var inputUserId by remember { mutableStateOf("") }

    var showGroupDialog by remember { mutableStateOf(false) }
    var groupName by remember { mutableStateOf("") }
    var groupId by remember { mutableStateOf("") }
    var users by remember { mutableStateOf("") }

    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigate()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_outline_arrow_back),
                            contentDescription = "returnScreen"
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
                .padding(paddingValues)
        ) {
            SecondaryTabRow(
                selectedTabIndex = selectedTabIndex
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            when (selectedTabIndex) {

                0 -> {
                    showNewChatDialog = true
                }

                1 -> {
                    showGroupDialog = true
                }
            }
        }
    }

    if (showNewChatDialog) {
        AlertDialog(
            onDismissRequest = { showNewChatDialog = false },
            title = { Text("Insira o userID") },
            text = {
                OutlinedTextField(
                    value = inputUserId,
                    onValueChange = { inputUserId = it },
                    placeholder = { Text("User ID") }
                )
            },
            confirmButton = {
                Button(onClick = {
                    newChat(context, inputUserId)
                    showNewChatDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showNewChatDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }


    if (showGroupDialog) {
        AlertDialog(
            onDismissRequest = { showGroupDialog = false },
            title = { Text("Criar grupo") },
            text = {
                Column {
                    OutlinedTextField(
                        value = groupName,
                        onValueChange = { groupName = it },
                        placeholder = { Text("Nome do grupo") }
                    )
                    OutlinedTextField(
                        value = groupId,
                        onValueChange = { groupId = it },
                        placeholder = { Text("ID do grupo") }
                    )
                    OutlinedTextField(
                        value = users,
                        onValueChange = { users = it },
                        placeholder = { Text("Users (separados por vírgula)") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    createGroupChatImpl(
                        context = context,
                        groupName = groupName,
                        groupId = groupId,
                        usersIds = users.split(",")
                    )
                    showGroupDialog = false
                }) {
                    Text("Criar")
                }
            },
            dismissButton = {
                Button(onClick = { showGroupDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

}