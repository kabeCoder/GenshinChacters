package com.kabe.genshincharacters.features.home

import com.kabe.genshincharacters.databinding.FragmentHomeBinding
import com.kabe.genshincharacters.features.base.BaseFragment
import com.kabe.genshincharacters.util.extensions.goTo

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun setUpView() {
        super.setUpView()

        viewBinding.button.setOnClickListener {
            goTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
        }

    }

    override fun collectViewModel() {
        super.collectViewModel()
    }

    override fun loadContent() {
        super.loadContent()
    }

}

