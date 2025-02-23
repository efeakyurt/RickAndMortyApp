package com.example.rickandmortyapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    var characters = mutableStateOf<List<Character>>(emptyList())

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCharacters()
                characters.value = response.results
            } catch (e: Exception) {
                characters.value = emptyList()
            }
        }
    }
}
