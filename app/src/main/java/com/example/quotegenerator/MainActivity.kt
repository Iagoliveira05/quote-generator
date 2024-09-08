package com.example.quotegenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quotegenerator.screen.QuoteScreen
import com.example.quotegenerator.ui.theme.QuoteGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteGeneratorTheme {
                QuoteScreen()
            }
        }
    }
}