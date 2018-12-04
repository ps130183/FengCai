package com.wzdq.fengcai.retrofit.image;


import com.wzdq.fengcai.retrofit.Response;
import com.wzdq.fengcai.retrofit.RetrofitManager;
import com.wzdq.fengcai.retrofit.api.ApiService;
import com.wzdq.fengcai.utils.fileupload.FileUploadingListener;
import com.wzdq.fengcai.utils.fileupload.UploadFileRequestBody;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by PengSong on 18/1/25.
 */

public class ImageUpload {


    /**
     * 上传图片
     * @param optionType
     * @param imagePath
     * @return
     */
    public static Observable<Response<String>> imageUpload(ApiService service, String optionType, String imagePath){
        return imageUpload(service,optionType,imagePath,null);
    }

    /**
     * 上传图片
     * @param optionType
     * @param imagePath
     * @return
     */
    public static Observable<Response<String>> imageUpload(ApiService service, String optionType, String imagePath, FileUploadingListener fileUploadObserver){
        RetrofitManager util = new RetrofitManager();
        RequestBody requestOptionType = util.createRequestBody(optionType);
        File image = new File(imagePath);
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(image, fileUploadObserver);
        MultipartBody.Part part = MultipartBody.Part.createFormData("pictureFile", image.getName(), uploadFileRequestBody);

        return service.imageUpload(requestOptionType,part);
    }
}
