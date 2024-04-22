package com.kabe.genshincharacters.features.home

import androidx.fragment.app.viewModels
import com.kabe.genshincharacters.databinding.FragmentHomeBinding
import com.kabe.genshincharacters.domain.Characters
import com.kabe.genshincharacters.features.base.BaseFragment
import com.kabe.genshincharacters.util.extensions.goTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val viewmodel: HomeFragmentViewModel by viewModels()

    override fun setUpView() {
        super.setUpView()

        viewBinding.button.setOnClickListener {
            goTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
        }

        viewmodel.getCharacters(true)


    }

    override fun collectViewModel() {
        super.collectViewModel()

        collect(viewmodel.characters) { characters ->
            updateTextView(characters)
        }
    }


    private fun updateTextView(characters: List<Characters>) {
        val text = characters.joinToString(separator = "\n") { it.name }
        viewBinding.textView.text = text
    }

    override fun loadContent() {
        super.loadContent()
    }

}

