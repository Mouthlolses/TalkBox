package com.mytalkbox.talkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mytalkbox.talkbox.navigation.TalkBoxApp
import com.mytalkbox.talkbox.ui.theme.TalkBoxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { TalkBoxTheme(dynamicColor = false) { TalkBoxApp() } }
    }
}