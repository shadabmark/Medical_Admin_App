package com.example.medicalapplicationadminapp.data.remote

import com.example.medicalapplicationadminapp.domain.dataModel.AddProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.AddUserStockResponse
import com.example.medicalapplicationadminapp.domain.dataModel.DeleteSpecificUserIDResponse
import com.example.medicalapplicationadminapp.domain.dataModel.DeleteSpecificUserOrderResponse
import com.example.medicalapplicationadminapp.domain.dataModel.GetAllUserResponse
import com.example.medicalapplicationadminapp.domain.dataModel.GetSpecificProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.GetUserOrderProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.UpdateAdminProductResponse
import com.example.medicalapplicationadminapp.domain.dataModel.UpdateUserDetailsResponse
import com.example.medicalapplicationadminapp.domain.dataModel.UpdateUserOrderResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface MedicalApi {

    @FormUrlEncoded
    @POST("addProduct")
    suspend fun createProduct(
        @Field("productName") productName: String,
        @Field("productImage") productImage: String,
        @Field("productPrice") productPrice: String,
        @Field("productCategory") productCategory: String,
        @Field("productStock") productStock: String,
        @Field("expireDate") expireDate: String

    ): Response<AddProductResponse>

    @FormUrlEncoded
    @POST("addUserStock")
    suspend fun addUserStock(
        @Field("productId") productId: String,
        @Field("productName") productName: String,
        @Field("productCategory") productCategory: String,
        @Field("expireDate") expireDate: String,
        @Field("productPrice") productPrice: String,
        @Field("productStock") productStock: String
    ): Response<AddUserStockResponse>

    @GET("getAllUsers")
    suspend fun getAllUsers(): Response<GetAllUserResponse>

    @GET("getUserOrderProduct")
    suspend fun getAllUserOrderProduct(): Response<GetUserOrderProductResponse>


    @FormUrlEncoded
    @PATCH("updateUserDetails")
    suspend fun updateUserDetails(
        @Field("userID") userID: String,
        @Field("isApproved") isApproved: Int
    ): Response<UpdateUserDetailsResponse>

    @FormUrlEncoded
    @PATCH("updateUserDetails")
    suspend fun updateUserDetails2(
        @Field("userID") userID: String,
        @Field("block") block: Int
    ): Response<UpdateUserDetailsResponse>

    @FormUrlEncoded
    @PATCH("updateUserOrder")
    suspend fun updateUserOrder(
        @Field("orderID") orderID: String,
        @Field("order_status") orderStatus: Int
    ): Response<UpdateUserOrderResponse>

    @FormUrlEncoded
    @PATCH("updateProduct")
    suspend fun updateAdminProduct(
        @Field("productID") productID: String,
        @Field("stock") stock: Int
    ): Response<UpdateAdminProductResponse>

    @FormUrlEncoded
    @POST("getSpecificProduct")
    suspend fun getSpecificProduct(
        @Field("productID") productID: String
    ): Response<GetSpecificProductResponse>

    @DELETE("deleteSpecificUsers")
    suspend fun deleteUser(
        @Query("userID") userID: String
    ): Response<DeleteSpecificUserIDResponse>

    @DELETE("deleteSpecificOrderProduct")
    suspend fun deleteSpecificUserOrder(
        @Query("orderID") orderID: String
    ): Response<DeleteSpecificUserOrderResponse>
}