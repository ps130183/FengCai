package com.wzdq.fengcai.retrofit.api;

//import rx.Observable;

import com.wzdq.fengcai.dto.LoginResultDto;
import com.wzdq.fengcai.retrofit.ApiConstant;
import com.wzdq.fengcai.retrofit.Response;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by kamangkeji on 17/2/10.
 */

public interface ApiService {


    /**
     * 登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstant.API_MODEL + "/Fcai/app/user/login")
    Observable<Response<LoginResultDto>> login(@Field("userName") String userName,
                                               @Field("userPwd") String userPwd);

    /**
     * 激活账号
     * @param userName
     * @param userPwd
     * @param payPwd
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstant.API_MODEL + "Fcai/app/user/activeuser")
    Observable<Response<String>> doActivateAccount(@Field("userName") String userName,
                                                   @Field("userPwd") String userPwd,
                                                   @Field("payPwd") String payPwd);

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @Multipart
    @POST(ApiConstant.API_MODEL + "/file/up")
    Observable<Response<String>> imageUpload(@Part("optionType") RequestBody optionType,
                                             @Part MultipartBody.Part file);
}
