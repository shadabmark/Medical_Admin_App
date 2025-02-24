package com.example.medicalapplicationadminapp.data.repository

import com.example.medicalapplicationadminapp.data.remote.MedicalApi
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
import com.example.medicalapplicationadminapp.domain.repository.Repository
import com.example.medicalapplicationadminapp.utility.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val medicalApi: MedicalApi) : Repository {

    override suspend fun createProduct(
        productName: String,
        productImage: String,
        productPrice: String,
        productCategory: String,
        productStock: String,
        expireDate: String
    ): Flow<ResultState<AddProductResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = medicalApi.createProduct(
                productName, productImage, productPrice, productCategory, productStock, expireDate
            )
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultState.Success(body))
            } else {
                emit(ResultState.Error(response.errorBody()?.toString() ?: "Add Product Failed"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    override suspend fun addUserStock(
        productId: String,
        productName: String,
        productCategory: String,
        expireDate: String,
        productPrice: String,
        productStock: String
    ): Flow<ResultState<AddUserStockResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = medicalApi.addUserStock(
                productId, productName, productCategory, expireDate, productPrice, productStock
            )
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultState.Success(body))
            } else {
                emit(ResultState.Error(response.errorBody()?.toString() ?: "Add UserStock Failed"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    override suspend fun getAllUsers(): Flow<ResultState<ArrayList<GetAllUserResponseItem>>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = medicalApi.getAllUsers()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    emit(ResultState.Success(body))
                }
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getAllUserOrderProduct(): Flow<ResultState<ArrayList<GetUserOrderProductResponseItem>>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = medicalApi.getAllUserOrderProduct()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    emit(ResultState.Success(body))
                }
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getSpecificProduct(productID: String): Flow<ResultState<GetSpecificProductResponse>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = medicalApi.getSpecificProduct(productID)
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    emit(ResultState.Success(body))
                } else {
                    emit(
                        ResultState.Error(
                            response.errorBody()?.toString() ?: "Get Specific Product Failed"
                        )
                    )
                }
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun approveUsers(
        userID: String,
        isApproved: Int
    ): Flow<ResultState<UpdateUserDetailsResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = medicalApi.updateUserDetails(userID, isApproved)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultState.Success(body))
            } else {
                emit(ResultState.Error(response.errorBody()?.toString() ?: "Approval Failed"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.toString()))
        }
    }

    override suspend fun blockUsers(
        userID: String,
        block: Int
    ): Flow<ResultState<UpdateUserDetailsResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = medicalApi.updateUserDetails2(userID, block)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultState.Success(body))
            } else {
                emit(ResultState.Error(response.errorBody()?.toString() ?: "Block Failed"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.toString()))
        }
    }

    override suspend fun approveUserOrder(
        orderID: String,
        orderStatus: Int
    ): Flow<ResultState<UpdateUserOrderResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = medicalApi.updateUserOrder(orderID, orderStatus)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultState.Success(body))
            } else {
                emit(ResultState.Error(response.errorBody()?.toString() ?: "Update Order Failed"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    override suspend fun updateAdminProduct(
        productID: String,
        stock: Int
    ): Flow<ResultState<UpdateAdminProductResponse>> = flow {
        emit(ResultState.Loading)
        try {
            val response = medicalApi.updateAdminProduct(productID, stock)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultState.Success(body))
            } else {
                emit(ResultState.Error(response.errorBody()?.toString() ?: "Update Admin Failed"))
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    override suspend fun deleteUser(userID: String): Flow<ResultState<DeleteSpecificUserIDResponse>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = medicalApi.deleteUser(userID)
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    emit(ResultState.Success(body))
                } else {
                    emit(ResultState.Error(response.errorBody()?.string() ?: "Delete Failed"))                }
            } catch (e: Exception) {
                emit(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
            }
        }

    override suspend fun deleteUserOrder(orderID: String): Flow<ResultState<DeleteSpecificUserOrderResponse>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = medicalApi.deleteSpecificUserOrder(orderID)
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    emit(ResultState.Success(body))
                } else {
                    emit(ResultState.Error(response.errorBody()?.string() ?: "Delete Failed"))
                }
            } catch (e: Exception) {
                emit(ResultState.Error(e.localizedMessage ?: "unknown Error"))
            }
        }

}
