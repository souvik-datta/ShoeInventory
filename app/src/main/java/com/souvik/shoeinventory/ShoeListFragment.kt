package com.souvik.shoeinventory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.souvik.shoeinventory.adapter.RvAdapter
import com.souvik.shoeinventory.databinding.FragmentShoeListBinding
import com.souvik.shoeinventory.local.Entity

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(ShoeListFragmentViewModel::class.java)
    }
    private lateinit var adapter: RvAdapter
    private var list = ArrayList<Entity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.list.observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            Log.d("TAG", "setUpObserver: ${list}")
            adapter.notifyDataSetChanged()
        })

    }

    private fun initView() {
        adapter = RvAdapter(list,object :RvAdapter.OnItemClick{
            override fun onClick(position: Int) {
                val bundle = Bundle()
                bundle.putSerializable("data",list[position])
                findNavController().navigate(R.id.action_shoeListFragment_to_createNewProductFragment,bundle)
            }
        })
        binding.rvListElement.adapter = adapter
        binding.fabAddNewProduct.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_createNewProductFragment)
        }
        viewModel.getAllData()
    }
}