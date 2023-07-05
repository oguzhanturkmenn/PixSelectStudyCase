package com.oguzhanturkmen.pixselectcasestudy.ui.breed

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.oguzhanturkmen.pixselectcasestudy.R
import com.oguzhanturkmen.pixselectcasestudy.data.entity.DogData
import com.oguzhanturkmen.pixselectcasestudy.databinding.FragmentBreedBinding
import com.oguzhanturkmen.pixselectcasestudy.util.changeDirection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedFragment : Fragment(), BreedAdapter.OnItemClickListener {
    private lateinit var binding: FragmentBreedBinding
    private val breedAdapter by lazy { BreedAdapter(this) }
    private val viewModel: BreedViewModel by viewModels()
    private val dogKind = ArrayList<DogData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_breed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    // A helper method that takes JSON data and returns a list of DogData.
    private fun getJsonObject(jsonObj: JsonObject): ArrayList<DogData> {
        jsonObj.get("message").asJsonObject.keySet().forEach {
            val keyvalue = jsonObj.get("message").asJsonObject.get(it)
            val listdata = ArrayList<String>()
            val jArray = keyvalue.asJsonArray as JsonArray?
            if (jArray != null) {
                for (i in 0 until jArray.size()) {
                    listdata.add(jArray[i].toString().split('"')[1])
                }
            }
            val dog = DogData(breed = it, subBreed = listdata)
            dogKind.add(dog)
        }
        return dogKind
    }

    // A method that displays subtypes in a dialog box.
    private fun showDialog(subKind: List<String>, kind: String) {
        val subKindArray = subKind.toTypedArray()
        var selectedItem = 0
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.sub_breeds))
            .setSingleChoiceItems(subKindArray, selectedItem) { dialog, which ->
                selectedItem = which
            }
            .setPositiveButton(getString(R.string.choose)) { dialog, _ ->
                val selectedSubKind = subKind[selectedItem]
                val action =
                    BreedFragmentDirections.actionBreedFragmentToDetailsFragment(
                        kind,
                        selectedSubKind
                    )
                Navigation.changeDirection(requireView(), action)
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }

    private fun observeData() {
        viewModel.list.observe(viewLifecycleOwner) {
            it?.let {
                binding.rvBreed.adapter = breedAdapter
                breedAdapter.updateItems(getJsonObject(it))
            }
        }
    }

    override fun onBreedWithSubKindClick(subBreed: List<String>, breed: String) {
        showDialog(subBreed, breed)
    }

    override fun onBreedClick(breed: String) {
        val action = BreedFragmentDirections.actionBreedFragmentToDetailsFragment(breed, null)
        Navigation.changeDirection(requireView(), action)
    }
}
