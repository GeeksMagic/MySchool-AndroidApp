package com.gmt.myschool.api;

import com.gmt.myschool.api.request.SignInRequest;
import com.gmt.myschool.api.response.MessageResponse;
import com.gmt.myschool.api.response.SignInResponse;
import com.gmt.myschool.api.response.SuperResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Barun on 12-02-2016.
 */
public interface RetrofitAPIService {

    @POST("users/login")
    public Call<SignInResponse> login(@Body SignInRequest request);
//
//    @GET("orders/distributors/{distributorId}")
//    public Call<OrderDataModel> ordersForDistributor(@Path("distributorId") String distributorId, @Query("startTime") long startTime, @Query("endTime") long endTime, @Query("isDigital") boolean isDigital);
//
//    @POST("orders/{orderid}/status/update/{status}")
//    public Call<OrderResponseModel> updateOrderStatus(@Path("orderid") String orderid, @Path("status") String status);
//
//    @POST("orders/status/update/{statusId}")
//    public Call<OrderDataModel> assignOrderDelivery(@Path("statusId") String statusId);
//
//    @POST("api/orders/delivery")
//    public Call<ApiResponseModel> updateStatusFromDeliveryList(@Body DeliveryOrderModel data);
//
//    @POST("api/orders/status")
//    public Call<ServerPullResponse> getResponseCodePullServer(@Body PullServerRequest data);
//
//    @POST("orders/shipstatus/bulkupdate/{statusId}")
//    public Call<BulkOrderUpdateResponse> bulkUpdateOrder(@Body BulkOrderUpdateRequest bulkRequest, @Path("statusId") long statusId);
//
//    @GET("distributors/{distributorId}")
//    public Call<DistributorModel> getLmdnDistributor(@Path("distributorId") String distributorId);
//
//    @POST("orders/shipment/booking")
//    public Call<BulkOrderUpdateResponse> bookingOrders(@Body BulkOrderUpdateRequest bulkRequest);
//
//    @POST("pin/{id}/resendpin")
//    public Call<SendPinResponse> sendPin(@Path("id") int id);
//
//    @POST("orders/serviceable/pincode")
//    public Call<CheckAreaPinCodeServiceabilityResponse> checkAreaPinCodeServiceability(@Body CheckAreaPinCodeServiceabilityRequest request);
//
//    @GET("config/{appName}")
//    public Call<CheckForUpdatesResponse> checkForUpdates(@Path("appName") String appName);
//
//    @GET("account/balance/DISTRIBUTOR/{distributorId}")
//    public Call<GetDistributorBalanceResponse> getDistributorBalance(@Path("distributorId") String distributorId);
//
//    @PUT("distributors/registerDevice")
//    public Call<GCMDeviceRegistrationRequest> gcmDeviceRegistrationRequest(@Body GCMDeviceRegistrationRequest registrationRequest);
//
//    @GET("retailers/{id}")
//    public Call<RetailerProfileModel> getRetailer(@Path("id") String id);
//
//    @PUT("returnorders")
//    public Call<EmptyResponse> addReturnBarcode(@Body AddReturnBarcodeRequest request);
//
//    @GET("returnorders/get/{returnBarcode}")
//    public Call<ReturnBarcodeResponse> getReturnBarcode(@Path("returnBarcode") String barcodeNumber);
//
//    @GET("returnorders/{distributorId}")
//    public Call<ReturnAllBarcodeResponse> getAllReturnBarcodes(@Path("distributorId") String distributorId);
//
//    @DELETE("returnorders/{returnBarcode}")
//    public Call<EmptyResponse> deleteReturnBarcode(@Path("returnBarcode") String barcodeNumber);
}
