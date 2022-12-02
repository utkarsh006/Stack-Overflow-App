package com.tejas.stackoverflow.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejas.stackoverflow.api.ApiClient
import com.tejas.stackoverflow.api.ApiService
import com.tejas.stackoverflow.database.DatabaseHelper
import com.tejas.stackoverflow.model.Question
import com.tejas.stackoverflow.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel (
     context: Context
) : ViewModel() {
    private val mainRepository: MainRepository = MainRepository(ApiClient.getRetrofitInstance().create(ApiService::class.java),
        DatabaseHelper(context)
    )
    private var liveData = MutableLiveData<MutableList<Question>>()
    private var selectedFilter = MutableLiveData<String>()
    private val filterListMutLiveData = MutableLiveData<MutableList<String>>()
    val filterListLiveData get() = filterListMutLiveData

    fun setSelectedFilter(text: String) {
        selectedFilter.postValue(text)
    }

    fun getSelectedFilter() = selectedFilter

    private fun setFilterList(list: MutableList<Question>) {
        val tempFilterList = mutableListOf<String>()
        list.forEach {
            it.tags.forEach { tags ->
                if (!tempFilterList.contains(tags))
                    tags?.let { tag -> tempFilterList.add(tag) }
            }
        }

        filterListMutLiveData.postValue(tempFilterList)
    }

    fun getQuestionList(): LiveData<MutableList<Question>> {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getQuestionList().collect {
                setFilterList(it)
                liveData.postValue(it)
            }
        }
        return liveData
    }


}