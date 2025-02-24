package com.example.medicalapplicationadminapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _addProductState = MutableStateFlow(AddProductState())
    val addProductState = _addProductState.asStateFlow()

    private val _addUserStockState = MutableStateFlow(AddUserStockState())
    val addUserStockState = _addUserStockState.asStateFlow()

    private val _getAllUsersState = MutableStateFlow(GetAllUserState())
    val getAllUsersState = _getAllUsersState.asStateFlow()

    private val _getAllUserOrderProductState = MutableStateFlow(GetAllUserOrderProductState())
    val getAllUserOrderProductState = _getAllUserOrderProductState.asStateFlow()

    private val _deleteUserState = MutableStateFlow(DeleteSpecificUserIDState())
    val deleteUserState = _deleteUserState.asStateFlow()

    private val _deleteUserOrderState = MutableStateFlow(DeleteUserOrderState())
    val deleteUserOrderState = _deleteUserOrderState.asStateFlow()

    private val _updateUserDetailsState = MutableStateFlow(UpdateUserDetailsState())

    private val _userBlockState = MutableStateFlow(UserBlockState())

    private val _updateAdminProductState = MutableStateFlow(UpdateAdminProductState())

    private val _updateUserOrderState = MutableStateFlow(UpdateUserOrderState())


    fun createProduct(
        productName: String,
        productImage: String,
        productPrice: String,
        productCategory: String,
        productStock: String,
        expireDate: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createProduct(
                productName,
                productImage,
                productPrice,
                productCategory,
                productStock,
                expireDate
            ).collect { state ->
                when (state) {
                    is ResultState.Loading -> {
                        _addProductState.value = AddProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addProductState.value =
                            AddProductState(data = state.data, isLoading = false)
                    }

                    is ResultState.Error -> {
                        _addProductState.value =
                            AddProductState(error = state.message, isLoading = false)
                    }
                }
            }
        }
    }

    fun addUserStock(
        productId: String,
        productName: String,
        productCategory: String,
        expireDate: String,
        productPrice: String,
        productStock: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUserStock(
                productId,
                productName,
                productCategory,
                expireDate,
                productPrice,
                productStock
            ).collectLatest { state ->
                when (state) {
                    is ResultState.Loading -> {
                        _addUserStockState.value = AddUserStockState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _addUserStockState.value =
                            AddUserStockState(data = state.data, isLoading = false)
                        getAllUserOrderProduct()
                        Log.d("UserStock", "status: Success")
                    }

                    is ResultState.Error -> {
                        _addUserStockState.value =
                            AddUserStockState(error = state.message, isLoading = false)
                        Log.d("UserStock", "status: Something went wrong")
                    }
                }
            }
        }
    }

    init {
        getAllUsers()
        getAllUserOrderProduct()
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUsers().collectLatest { state ->
                when (state) {
                    is ResultState.Error -> {
                        _getAllUsersState.value =
                            GetAllUserState(error = state.message, isLoading = false)
                    }

                    ResultState.Loading -> {
                        _getAllUsersState.value = GetAllUserState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getAllUsersState.value =
                            GetAllUserState(data = state.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun getAllUserOrderProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUserOrderProduct().collectLatest { state ->
                when (state) {
                    is ResultState.Error -> {
                        _getAllUserOrderProductState.value =
                            GetAllUserOrderProductState(error = state.message, isLoading = false)
                    }

                    is ResultState.Loading -> {
                        _getAllUserOrderProductState.value =
                            GetAllUserOrderProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getAllUserOrderProductState.value =
                            GetAllUserOrderProductState(data = state.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun approveUserDetails(userID: String, isApproved: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.approveUsers(userID = userID, isApproved = isApproved)
                .collectLatest { state ->
                    when (state) {
                        is ResultState.Error -> {
                            _updateUserDetailsState.value =
                                UpdateUserDetailsState(error = state.message, isLoading = false)
                        }

                        ResultState.Loading -> {
                            _updateUserDetailsState.value = UpdateUserDetailsState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            _updateUserDetailsState.value =
                                UpdateUserDetailsState(data = state.data, isLoading = false)
                            getAllUsers()
                        }
                    }
                }
        }
    }

    fun blockUserDetails(userID: String, block: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.blockUsers(userID = userID, block = block)
                .collectLatest { state ->
                    when (state) {
                        is ResultState.Error -> {
                            _userBlockState.value =
                                UserBlockState(error = state.message, isLoading = false)
                        }

                        ResultState.Loading -> {
                            _userBlockState.value = UserBlockState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            _userBlockState.value =
                                UserBlockState(data = state.data, isLoading = false)
                            getAllUsers()
                        }
                    }
                }
        }
    }

    fun approveUserOrder(orderID: String, orderStatus: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.approveUserOrder(orderID, orderStatus).collectLatest { state ->
                when (state) {
                    is ResultState.Error -> {
                        _updateUserOrderState.value =
                            UpdateUserOrderState(error = state.message, isLoading = false)
                    }

                    is ResultState.Loading -> {
                        _updateUserOrderState.value =
                            UpdateUserOrderState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _updateUserOrderState.value =
                            UpdateUserOrderState(data = state.data, isLoading = false)
                        getAllUserOrderProduct()
                    }
                }
            }
        }
    }

    fun updateAdminStock(productID: String, orderQty: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSpecificProduct(productID).collectLatest { productState ->
                when (productState) {
                    is ResultState.Success -> {
                        val currentStock = productState.data.stock ?: 0
                        val newStock = currentStock - orderQty

                        if (newStock >= 0) {
                            _updateAdminProductState.value =
                                UpdateAdminProductState(isLoading = true)

                            repository.updateAdminProduct(productID, newStock)
                                .collectLatest { updateState ->
                                    when (updateState) {
                                        is ResultState.Success -> {
                                            _updateAdminProductState.value =
                                                UpdateAdminProductState(
                                                    data = updateState.data,
                                                    isLoading = false
                                                )
                                            getAllUserOrderProduct()
                                        }

                                        is ResultState.Error -> {
                                            _updateAdminProductState.value =
                                                UpdateAdminProductState(
                                                    error = updateState.message,
                                                    isLoading = false
                                                )
                                        }

                                        is ResultState.Loading -> {
                                            _updateAdminProductState.value =
                                                UpdateAdminProductState(isLoading = true)
                                        }
                                    }
                                }
                        } else {
                            _updateAdminProductState.value =
                                UpdateAdminProductState(error = "Stock is not sufficient!")
                        }
                    }

                    is ResultState.Error -> {
                        Log.e("Product Fetch", "Failed to fetch product: ${productState.message}")
                    }

                    is ResultState.Loading -> {}
                }
            }
        }
    }

    fun deleteUser(userID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(userID).collect { result ->
                when (result) {
                    is ResultState.Loading -> _deleteUserState.value =
                        DeleteSpecificUserIDState(isLoading = true)

                    is ResultState.Success -> {
                        _deleteUserState.value = DeleteSpecificUserIDState(data = result.data)
                        delay(500)
                        _deleteUserState.value = DeleteSpecificUserIDState()
                    }

                    is ResultState.Error -> _deleteUserState.value =
                        DeleteSpecificUserIDState(error = result.message)
                }
            }
        }
    }

    fun deleteUserOrder(orderID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserOrder(orderID).collectLatest { result ->
                when (result) {
                    is ResultState.Loading -> _deleteUserOrderState.value =
                        DeleteUserOrderState(isLoading = true)

                    is ResultState.Success -> {
                        _deleteUserOrderState.value = DeleteUserOrderState(data = result.data)
                        delay(500)
                        _deleteUserOrderState.value = DeleteUserOrderState()
                    }

                    is ResultState.Error -> _deleteUserOrderState.value =
                        DeleteUserOrderState(error = result.message)
                }
            }
        }
    }
}


data class GetAllUserState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<GetAllUserResponseItem> = emptyList()
)

data class GetAllUserOrderProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<GetUserOrderProductResponseItem> = emptyList()
)

data class UpdateUserDetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: UpdateUserDetailsResponse? = null
)

data class UserBlockState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: UpdateUserDetailsResponse? = null
)

data class UpdateUserOrderState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: UpdateUserOrderResponse? = null
)

data class UpdateAdminProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: UpdateAdminProductResponse? = null
)

data class AddProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: AddProductResponse? = null
)

data class AddUserStockState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: AddUserStockResponse? = null
)

data class DeleteSpecificUserIDState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: DeleteSpecificUserIDResponse? = null
)

data class DeleteUserOrderState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: DeleteSpecificUserOrderResponse? = null
)

data class GetSpecificProductState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: GetSpecificProductResponse? = null
)