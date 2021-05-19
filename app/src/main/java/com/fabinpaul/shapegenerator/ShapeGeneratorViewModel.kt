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

    val dimension1Value = MutableLiveData<String>()

    val dimension2Value = MutableLiveData<String>()

    private val _currentShape = MutableLiveData<String?>()
    val currentShape: LiveData<String?>
        get() = _currentShape

    private val _colorSelected = MutableLiveData<Int>(R.color.light_purple_aa)
    val colorSelected: LiveData<Int>
        get() = _colorSelected

    private val _strokeColor = MutableLiveData<Int>(R.color.light_purple_a6)
    val strokeColor: LiveData<Int>
        get() = _strokeColor

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

    fun getDimen1Value(): Int {
        val value = dimension1Value.value
        return value?.toInt() ?: 0
    }

    fun getDimen2Value(): Int {
        val value = dimension2Value.value
        return value?.toInt() ?: 0
    }

    fun colorSelected(colorString: String?) {
        when (colorString) {
            "Purple" -> {
                _colorSelected.value = R.color.light_purple_aa
                _strokeColor.value = R.color.light_purple_a6
            }
            "Red" -> {
                _colorSelected.value = R.color.light_red_aa
                _strokeColor.value = R.color.light_red_ff
            }
            "Green" -> {
                _colorSelected.value = R.color.light_green_aa
                _strokeColor.value = R.color.light_green_80
            }
            "Black" -> {
                _colorSelected.value = R.color.black_aa
                _strokeColor.value = R.color.black
            }
        }
    }
}