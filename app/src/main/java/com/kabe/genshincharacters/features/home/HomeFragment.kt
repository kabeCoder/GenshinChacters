package com.kabe.genshincharacters.features.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kabe.genshincharacters.constant.PaginationLoadingIndicator
import com.kabe.genshincharacters.databinding.FragmentHomeBinding
import com.kabe.genshincharacters.domain.Characters
import com.kabe.genshincharacters.features.base.BaseFragment
import com.kabe.genshincharacters.util.ItemClickListener
import com.kabe.genshincharacters.util.extensions.goTo
import com.kabe.genshincharacters.util.extensions.gone
import com.kabe.genshincharacters.util.extensions.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), ItemClickListener<Characters> {
    private val viewmodel: HomeFragmentViewModel by viewModels()


    private val homeAdapter by lazy { HomeAdapter(this) }

    override fun setUpView() {
        super.setUpView()

        //viewmodel.getCharacters(true)

//        viewBinding.button.setOnClickListener {
//            goTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
//        }

        viewBinding.rvCharacters.apply {
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = gridLayoutManager
            adapter = homeAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        val totalItemCount = gridLayoutManager.itemCount
                        val visibleItemCount = childCount
                        val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()

                        // 10 items per page
                        if (
                            totalItemCount >= 10 &&
                            totalItemCount - visibleItemCount <= firstVisibleItem + 5
                        ) {
                            viewmodel.getCharacters()
                        }
                    }
                }
            })
        }

        viewBinding.srlCharacters.setOnRefreshListener {
            viewmodel.getCharacters(true)
        }

    }

    override fun collectViewModel() {
        super.collectViewModel()

        collect(viewmodel.loadingState) {
            when (it) {
                PaginationLoadingIndicator.NONE -> {
                    viewBinding.srlCharacters.isRefreshing = false
                    viewBinding.pbLoadMore.gone()
                }
                PaginationLoadingIndicator.SWIPE_REFRESH -> {
                    viewBinding.srlCharacters.isRefreshing = true
                }
                PaginationLoadingIndicator.MORE -> {
                    viewBinding.pbLoadMore.show()
                }
            }
        }

        collect(viewmodel.characters) { characters ->
            homeAdapter.submitList(characters)
        }

        collect(viewmodel.errorMessage) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    override fun loadContent() {
        super.loadContent()

        viewmodel.getCharacters(true)
    }

//    private fun updateTextView(characters: List<Characters>) {
//        val text = characters.joinToString(separator = "\n") { it.name }
//        viewBinding.textView.text = text
//    }

    override fun onItemClick(item: Characters) {
        goTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
    }

}


