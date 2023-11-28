package com.example.homework_13_jsonregister.registerFragment

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_13_jsonregister.databinding.FragmentMainBinding
import com.example.homework_13_jsonregister.extentions.BaseFragment
import com.example.homework_13_jsonregister.extentions.FieldViewModel
import com.example.homework_13_jsonregister.extentions.ParentData


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: FieldViewModel by viewModels()

    override fun setUp() {
        setAdapter()
    }

    fun setAdapter() {
        binding.recycler.layoutManager = LinearLayoutManager(context)
        val adapter = ParentRecyclerView()
        binding.recycler.adapter = adapter
        adapter.addData(setData())
    }

    fun setData(): MutableList<ParentData> {
        return viewModel.getJSONData(requireContext(), "sample_data.json")
    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {}
    }
}