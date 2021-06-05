package com.example.justdesserts.shared.logger

import android.util.Log

actual class MyLogger {
    actual fun logMessage(message: String) {
        Log.d("ApolloConnectionLogger",message)
    }
}