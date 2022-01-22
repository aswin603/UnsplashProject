package com.aswin.project.unsplashproject.api

import com.aswin.project.unsplashproject.data.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)