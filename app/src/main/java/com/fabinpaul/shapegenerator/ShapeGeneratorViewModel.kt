package com.fabinpaul.shapegenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabinpaul.shapegenerator.data.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShapeGeneratorViewModel @Inject constructor() : ViewModel() {

    var canvasHeight: Int = 0
        private set
    var canvasWidth: Int = 0
        private set

    private val _dimension1 = MutableLiveData(0)
    val dimension1: LiveData<Int>
        get() = _dimension1

    private val _dimension2 = MutableLiveData(0)
    val dimension2: LiveData<Int>
        get() = _dimension2

    val dimension1Value = MutableLiveData<String>()

    val dimension2Value = MutableLiveData<String>()

    private val _snackBarMsgRes = MutableLiveData<Event<Int?>>()
    val snackBarMsgRes: LiveData<Event<Int?>>
        get() = _snackBarMsgRes

    private val _colorSelected = MutableLiveData(R.color.light_purple_aa)
    val colorSelected: LiveData<Int>
        get() = _colorSelected

    private val _strokeColor = MutableLiveData(R.color.light_purple_a6)
    val strokeColor: LiveData<Int>
        get() = _strokeColor

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

    fun updateCanvas(width: Int, height: Int) {
        canvasWidth = width
        canvasHeight = height
    }

    fun validateShapeDimens(): Boolean {
        when (_currentShape.value) {
            "Circle" -> {
                return if (getDimen1Value() * 2 > canvasWidth || getDimen1Value() * 2 > canvasHeight) {
                    _snackBarMsgRes.value = Event(R.string.error_large_radius)
                    false
                } else {
                    true
                }
            }
            "Rectangle" -> {
                return when {
                    getDimen1Value() > canvasHeight -> {
                        _snackBarMsgRes.value = Event(R.string.error_large_length)
                        false
                    }
                    getDimen2Value() > canvasWidth -> {
                        _snackBarMsgRes.value = Event(R.string.error_large_width)
                        false
                    }
                    else -> {
                        true
                    }
                }
            }
            "Square" -> {
                return if (getDimen1Value() > canvasWidth || getDimen1Value() > canvasHeight) {
                    _snackBarMsgRes.value = Event(R.string.error_large_side)
                    false
                } else {
                    true
                }
            }
            else -> return false
        }
    }
}