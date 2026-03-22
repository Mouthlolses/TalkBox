package com.talkboxapp.talkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.talkboxapp.talkbox.navigation.NavHost
import com.talkboxapp.talkbox.ui.theme.TalkBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { TalkBoxTheme { NavHost() } }
    }
}
