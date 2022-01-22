package com.aswin.project.unsplashproject.ui.post

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aswin.project.unsplashproject.R
import com.aswin.project.unsplashproject.data.UnsplashPhoto
import com.aswin.project.unsplashproject.databinding.FragmentPostBinding
import com.aswin.project.unsplashproject.ui.RecyclerViewItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment(R.layout.fragment_post),UnsplashPhotoAdapter.OnItemClickListener{

    private val viewModel by viewModels<PostViewModel>()

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPostBinding.bind(view)

        val adapter = UnsplashPhotoAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.addItemDecoration(RecyclerViewItemDecoration(requireActivity(), R.drawable.divider))
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter { adapter.retry() },
                footer = UnsplashPhotoLoadStateAdapter { adapter.retry() },
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }
    }

    override fun onItemClick(photo: UnsplashPhoto) {
        val action = PostFragmentDirections.actionPostFragmentToImageFragment(photo)
        findNavController().navigate(action)
    }

    override fun onProfileClick(userName: UnsplashPhoto?) {
        val action = userName?.let { PostFragmentDirections.actionPostFragmentToProfileFragment4(it) }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}