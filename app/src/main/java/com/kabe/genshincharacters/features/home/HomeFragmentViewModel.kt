package com.kabe.genshincharacters.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.genshincharacters.constant.PaginationLoadingIndicator
import com.kabe.genshincharacters.data.base.Status
import com.kabe.genshincharacters.domain.Characters
import com.kabe.genshincharacters.repository.characters.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
): ViewModel() {

    private val _loadingState = MutableStateFlow<PaginationLoadingIndicator>(PaginationLoadingIndicator.NONE)
    val loadingState: StateFlow<PaginationLoadingIndicator> = _loadingState.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String?>()
    val errorMessage: SharedFlow<String?> = _errorMessage.asSharedFlow()

    private val charactersList = mutableListOf<Characters>()
    private val _characters = MutableSharedFlow<MutableList<Characters>>()
    val characters: SharedFlow<List<Characters>> = _characters.asSharedFlow()

    private var currentPage = 0

    fun getCharacters(resetPage: Boolean = false) {

        if (_loadingState.value != PaginationLoadingIndicator.NONE) {
            return
        }

        _loadingState.value = if (resetPage) PaginationLoadingIndicator.SWIPE_REFRESH else PaginationLoadingIndicator.MORE


        viewModelScope.launch {

            if (resetPage) {
                currentPage = 0
            }

            val result = charactersRepository.getCharacters(currentPage + 1)

            when (result.status) {
                Status.SUCCESS -> result.data?.let {

                    if (resetPage) {
                        charactersList.clear()
                    }

                    charactersList.addAll(it)
                    currentPage += 1
                    _characters.emit(charactersList)

                    Log.d("HomeFragmentViewmodel", "Success")

                }
                Status.ERROR -> {
                    result.message?.let { _errorMessage.emit(it) }
                    Log.d("HomeFragmentViewmodel", "Failed")
                }
            }

            _loadingState.value = PaginationLoadingIndicator.NONE

        }

    }
}