package com.mati.tracker_data.remote.dto

import com.mati.tracker_data.remote.dto.Product

data class SearchDto(
    val products: List<Product>,
)