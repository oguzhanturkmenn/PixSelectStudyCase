package com.oguzhanturkmen.pixselectcasestudy.ui.breed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.oguzhanturkmen.pixselectcasestudy.data.entity.DogData
//import com.oguzhanturkmen.pixselectcasestudy.data.entity.Response
import com.oguzhanturkmen.pixselectcasestudy.data.repo.PixRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(private val pixRepo: PixRepo) : ViewModel() {
    private val _list = MutableLiveData<JsonObject>()
    val list: LiveData<JsonObject> get() = _list

    init {
        getAllBreed()
    }

    private fun getAllBreed() {
        viewModelScope.launch {
            try {
                val result = pixRepo.getAllBreed()
                _list.postValue(result)
            } catch (e: Exception) {
                Log.e("fail", e.message.toString())
            }
        }
    }
}