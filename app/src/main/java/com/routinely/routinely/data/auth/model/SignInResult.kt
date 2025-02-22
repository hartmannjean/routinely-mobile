package com.routinely.routinely.data.auth.model

sealed class SignInResult {
    data class Success(val token: String): SignInResult()
    data class Error(val message: List<String>): SignInResult() {
        companion object {
            fun stringMessage(message: String) = Error(listOf(message))
        }
    }
    data object Loading: SignInResult()
    data object Empty: SignInResult()
}