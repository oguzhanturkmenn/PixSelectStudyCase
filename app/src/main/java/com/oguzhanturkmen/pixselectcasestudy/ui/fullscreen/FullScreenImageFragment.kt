package com.oguzhanturkmen.pixselectcasestudy.ui.fullscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.oguzhanturkmen.pixselectcasestudy.R
import com.oguzhanturkmen.pixselectcasestudy.databinding.FragmentFullScreenImageBinding
import com.oguzhanturkmen.pixselectcasestudy.util.downloadImage

class FullScreenImageFragment : Fragment() {
    private lateinit var binding: FragmentFullScreenImageBinding
    private val args: FullScreenImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_full_screen_image, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = args.fullimage
        Glide.with(requireContext()).load(image).into(requireView() as ImageView)
    }
}