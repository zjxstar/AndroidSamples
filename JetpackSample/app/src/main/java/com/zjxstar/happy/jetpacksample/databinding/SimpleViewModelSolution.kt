package com.zjxstar.happy.jetpacksample.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 *
 * @author zjxstar
 * @date Created on 2019-07-04
 */
class SimpleViewModelSolution : ViewModel() {

    private val _name = MutableLiveData("Tom")
    private val _lastname = MutableLiveData("Jerry")
    private val _likes = MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastname: LiveData<String> = _lastname
    val likes: LiveData<Int> = _likes

    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > 9 -> Popularity.STAR
            it > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }

}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}