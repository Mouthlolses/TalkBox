package com.mytalkbox.talkbox.ui.screens.onboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytalkbox.talkbox.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun login(userEmail: String, userPassword: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            val response = repository.loginUser(userEmail, userPassword)

            _uiState.value = if (response.success) {
                LoginUiState.Success(response.message)
            } else {
                LoginUiState.Error(response.message)
            }
        }
    }
}


sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val message: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}