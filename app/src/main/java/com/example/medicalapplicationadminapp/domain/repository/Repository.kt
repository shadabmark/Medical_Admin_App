package com.example.medicalapplicationadminapp.domain.repository

import com.example.medicalapplicationadminapp.domain.dataModel.AddProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.AddUserStockResponse
import com.example.medicalapplicationadminapp.domain.dataModel.DeleteSpecificUserIDResponse
import com.example.medicalapplicationadminapp.domain.dataModel.DeleteSpecificUserOrderResponse
import com.example.medicalapplicationadminapp.domain.dataModel.GetAllUserResponseItem
import com.example.medicalapplicationadminapp.domain.dataModel.GetSpecificProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.GetUserOrderProductResponseItem
import com.example.medicalapplicationadminapp.domain.dataModel.UpdateAdminProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.UpdateUserDetailsResponse
import com.example.medicalapplicationadminapp.domain.dataModel.UpdateUserOrderResponse
import com.example.medicalapplicationadminapp.utility.ResultState
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun createProduct(
        productName: String,
        productImage: String,
        productPrice: String,
        productCategory: String,
        productStock: String,
        expireDate: String
    ): Flow<ResultState<AddProductResponse>>

    suspend fun addUserStock(
        productId: String,
        productName: String,
        productCategory: String,
        expireDate: String,
        productPrice: String,
        productStock: String
    ): Flow<ResultState<AddUserStockResponse>>

    suspend fun getAllUsers(): Flow<ResultState<ArrayList<GetAllUserResponseItem>>>

    suspend fun getAllUserOrderProduct(): Flow<ResultState<ArrayList<GetUserOrderProductResponseItem>>>

    suspend fun getSpecificProduct(productID: String): Flow<ResultState<GetSpecificProductResponse>>

    suspend fun approveUsers(userID: String, isApproved: Int): Flow<ResultState<UpdateUserDetailsResponse>>

    suspend fun blockUsers(userID: String, block: Int): Flow<ResultState<UpdateUserDetailsResponse>>

    suspend fun approveUserOrder(orderID: String, orderStatus: Int): Flow<ResultState<UpdateUserOrderResponse>>

    suspend fun updateAdminProduct(productID: String, stock: Int): Flow<ResultState<UpdateAdminProductResponse>>

    suspend fun deleteUser(userID: String): Flow<ResultState<DeleteSpecificUserIDResponse>>

    suspend fun deleteUserOrder(orderID: String): Flow<ResultState<DeleteSpecificUserOrderResponse>>
}