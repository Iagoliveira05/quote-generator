package com.example.quotegenerator.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotegenerator.api.quoteService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _quoteState = mutableStateOf(QuoteState())

    val quoteState: State<QuoteState> = _quoteState

    init {
        fetchQuote()
    }


     fun fetchQuote() {
         _quoteState.value = _quoteState.value.copy(
             loading = true
         )
        viewModelScope.launch {
            try {
                val response = quoteService.getQuote()
                _quoteState.value = _quoteState.value.copy(
                    loading = false,
                    error = null,
                    list = response
                )
            } catch (e: Exception) {
                _quoteState.value = _quoteState.value.copy(
                    loading = false,
                    error = "Error fetching ${e.message}",
                    list = emptyList()
                )
            } finally {
                println(_quoteState.value.error)
                println(_quoteState)
            }

        }
    }


    data class QuoteState(
        val loading: Boolean = true, // mudar para true
        val error: String? = null,
        val list: List<Quote> = emptyList()
    )
}

