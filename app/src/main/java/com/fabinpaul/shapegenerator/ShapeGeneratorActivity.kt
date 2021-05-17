package com.fabinpaul.shapegenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fabinpaul.shapegenerator.databinding.ActivityShapeGeneratorBinding
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
}