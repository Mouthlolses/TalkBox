package com.mytalkbox.talkbox.ui.screens.onboarding.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytalkbox.talkbox.data.local.model.User
import com.mytalkbox.talkbox.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register(user: User) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading

            val response = repository.createUser(user)

            _uiState.value = if (response.success) {
                RegisterUiState.Success(response.message)
            } else {
                RegisterUiState.Error(response.message)
            }
        }
    }
}


sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val message: String) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}