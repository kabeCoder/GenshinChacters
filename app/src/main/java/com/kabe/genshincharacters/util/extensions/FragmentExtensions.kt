package com.kabe.genshincharacters.util.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.goTo(navDirections: NavDirections) {
    findNavController().navigate(navDirections)
}
