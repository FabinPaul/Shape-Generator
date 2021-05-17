package com.fabinpaul.shapegenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShapeGeneratorViewModel @Inject constructor() : ViewModel() {

    private val _dimension1 = MutableLiveData<Int>(0)
    val dimension1: LiveData<Int>
        get() = _dimension1

    private val _dimension2 = MutableLiveData<Int>(0)
    val dimension2: LiveData<Int>
        get() = _dimension2

    private val _dimension1Value = MutableLiveData<String>()
    val dimension1Value: LiveData<String>
        get() = _dimension1Value

    private val _dimension2Value = MutableLiveData<String>()
    val dimension2Value: LiveData<String>
        get() = _dimension2Value

    private val _currentShape = MutableLiveData<String?>()
    val currentShape: LiveData<String?>
        get() = _currentShape

    fun shapeSelected(shape: String?) {
        _currentShape.value = shape
        when (shape) {
            "Circle" -> {
                _dimension1.value = R.string.enter_radius
                _dimension2.value = 0
            }
            "Rectangle" -> {
                _dimension1.value = R.string.enter_length
                _dimension2.value = R.string.enter_width
            }
            "Square" -> {
                _dimension1.value = R.string.enter_side
                _dimension2.value = 0
            }
        }
    }

    fun generateShape() {
        if (_currentShape.value == null) {
            return
        }
    }
}