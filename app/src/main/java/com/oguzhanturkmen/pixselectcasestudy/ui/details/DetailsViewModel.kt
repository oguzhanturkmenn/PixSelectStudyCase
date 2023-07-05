package com.oguzhanturkmen.pixselectcasestudy.ui.details

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.oguzhanturkmen.pixselectcasestudy.data.repo.PixRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val pixRepo: PixRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _list = MutableLiveData<ArrayList<String>>()
    val list: LiveData<ArrayList<String>> get() = _list

    // To retrieve data using savedStateHandle in the ViewModel.
    private val breedName: String? = savedStateHandle["breed"]
    private val subBreed: String? = savedStateHandle["subkind"]

    init {
        getAllBreedImage()
    }

    private fun getAllBreedImage() {
        viewModelScope.launch {
            try {
                if (subBreed != null) {
                    val result = breedName?.let { pixRepo.getSubBreedImages(it, subBreed) }
                    if (result != null) {
                        _list.postValue(result.message)
                    }
                } else {
                    val result = breedName?.let { pixRepo.getBreedImages(it) }
                    if (result != null) {
                        _list.postValue(result.message)
                    }
                }
            } catch (e: Exception) {
                Log.e("fail", e.message.toString())
            }
        }
    }

}