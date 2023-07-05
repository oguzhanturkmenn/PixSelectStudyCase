package com.oguzhanturkmen.pixselectcasestudy.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.oguzhanturkmen.pixselectcasestudy.R
import com.oguzhanturkmen.pixselectcasestudy.databinding.FragmentDetailsBinding
import com.oguzhanturkmen.pixselectcasestudy.util.changeDirection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(), DetailsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val detailsAdapter by lazy { DetailsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.detailsAdapter = detailsAdapter
        binding.rvDetails.adapter = detailsAdapter
    }

    private fun configureImages(images: ArrayList<String>?) {
        images.let {
            detailsAdapter.images = images
        }
    }

    private fun observeData() {
        viewModel.list.observe(viewLifecycleOwner) {
            it.let {
                configureImages(it)
            }
        }
    }

    override fun imgCircleClicked(img: String) {
        val action = DetailsFragmentDirections.actionDetailsFragmentToFullScreenImageFragment(img)
        Navigation.changeDirection(requireView(), action)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

