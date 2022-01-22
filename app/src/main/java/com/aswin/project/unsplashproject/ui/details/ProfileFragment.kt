package com.aswin.project.unsplashproject.ui.details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aswin.project.unsplashproject.R
import com.aswin.project.unsplashproject.databinding.FragmentImageBinding
import com.aswin.project.unsplashproject.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val args by navArgs<ProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProfileBinding.bind(view)

        binding.apply {
            val photo = args.profile

            Glide.with(this@ProfileFragment)
                .load(photo.urls.full)
                .error(R.drawable.ic_user)
                .centerCrop()
                //.fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(profileImage)


            profileName.text = photo.user.username

            val uri = Uri.parse(photo.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)


        }
    }
}