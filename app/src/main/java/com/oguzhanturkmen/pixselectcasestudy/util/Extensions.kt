package com.oguzhanturkmen.pixselectcasestudy.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.changeDirection(view: View, id: Int) {
    findNavController(view).navigate(id)

}

fun Navigation.changeDirection(view: View, id: NavDirections) {
    findNavController(view).navigate(id)

}