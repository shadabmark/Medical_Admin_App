package com.example.medicalapplicationadminapp.domain.dataModel

data class GetSpecificProductResponse(
    val category: String,
    val expire_date: String,
    val id: Int,
    val price: Double,
    val product_id: String,
    val product_image: String,
    val product_name: String,
    val stock: Int
)