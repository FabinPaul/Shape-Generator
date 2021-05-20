package com.fabinpaul.shapegenerator

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.fabinpaul.shapegenerator.databinding.ActivityShapeGeneratorBinding
import com.fabinpaul.shapegenerator.shapes.CircleShape
import com.fabinpaul.shapegenerator.shapes.Rectangle
import com.fabinpaul.shapegenerator.shapes.SquareShape
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShapeGeneratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShapeGeneratorBinding
    private val viewModel: ShapeGeneratorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shape_generator)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpShapeSpinner()
        setUpColorSpinner()
        binding.btnGenerate.setOnClickListener {
            updateShape(viewModel.currentShape.value)
        }
        setUpObservers()
        binding.ivShapeCanvas.post {
            viewModel.updateCanvas(binding.ivShapeCanvas.width, binding.ivShapeCanvas.height)
        }
    }

    private fun setUpObservers() {
        viewModel.colorSelected.observe(this, {
            updateShape(viewModel.currentShape.value)
        })
        viewModel.strokeColor.observe(this, {
            updateShape(viewModel.currentShape.value)
        })
        viewModel.snackBarMsgRes.observe(this, {
            it.getContentIfNotHandled()?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun setUpShapeSpinner() {
        val shapeArray = resources.getStringArray(R.array.shape_array)
        val adapter = ArrayAdapter<CharSequence>(
            this,
            R.layout.drop_down_item,
            shapeArray
        )
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.atvShapesDropDown.setAdapter(adapter)
        binding.atvShapesDropDown.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                viewModel.shapeSelected(shapeArray[position])
            }
    }

    private fun setUpColorSpinner() {
        val colorArray = resources.getStringArray(R.array.color_array)
        val adapter = ArrayAdapter<CharSequence>(
            this,
            R.layout.drop_down_item,
            colorArray
        )
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.atvArtBoardDropDown.setAdapter(adapter)
        binding.atvArtBoardDropDown.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                viewModel.colorSelected(colorArray[position])
            }
        binding.atvArtBoardDropDown.setText(colorArray[0], false)
    }

    private fun updateShape(shape: String?) {
        if (!viewModel.validateShapeDimens()) {
            return
        }
        val dimen1 = viewModel.getDimen1Value()
        val dimen2 = viewModel.getDimen2Value()
        val color = viewModel.colorSelected.value
        val strokeColor = viewModel.strokeColor.value
        when (shape) {
            "Circle" -> {
                if (color != null && strokeColor != null) {
                    val circleShape = CircleShape(
                        dimen1,
                        ContextCompat.getColor(this, color),
                        ContextCompat.getColor(this, strokeColor),
                        viewModel.canvasWidth,
                        viewModel.canvasHeight
                    )
                    binding.ivShapeCanvas.setImageDrawable(circleShape)
                }
            }
            "Square" -> {
                if (color != null && strokeColor != null) {
                    val circleShape = SquareShape(
                        dimen1,
                        ContextCompat.getColor(this, color),
                        ContextCompat.getColor(this, strokeColor),
                        viewModel.canvasWidth,
                        viewModel.canvasHeight
                    )
                    binding.ivShapeCanvas.setImageDrawable(circleShape)
                }
            }
            "Rectangle" -> {
                if (color != null && strokeColor != null) {
                    val circleShape = Rectangle(
                        dimen1,
                        dimen2,
                        ContextCompat.getColor(this, color),
                        ContextCompat.getColor(this, strokeColor),
                        viewModel.canvasWidth,
                        viewModel.canvasHeight
                    )
                    binding.ivShapeCanvas.setImageDrawable(circleShape)
                }
            }
        }

    }
}