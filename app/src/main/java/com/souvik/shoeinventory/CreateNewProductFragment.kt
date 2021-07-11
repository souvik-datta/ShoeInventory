package com.souvik.shoeinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.souvik.shoeinventory.databinding.CreateNewProductBinding
import com.souvik.shoeinventory.local.Entity

class CreateNewProductFragment : Fragment() {

    private lateinit var binding: CreateNewProductBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(CreateNewProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.create_new_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it) findNavController().popBackStack()
            else Toast.makeText(requireContext(), "Data cannot be stored in db", Toast.LENGTH_SHORT).show()
        })
    }


    private fun initView() {
        binding.ivSelectedImage.setOnClickListener {

        }
        binding.btnCreateProduct.setOnClickListener {
            viewModel.insertToDb(Entity().apply {
                name = binding.etName.text.toString().trim()
                size = binding.etSize.text.toString().toDouble()
                company = binding.etCompany.text.toString().trim()
                description = binding.etDesc.text.toString().trim()
                images = ""
            })

        }
    }
}