package com.haipeng.decoration.manager.http.okhttp3;


import android.content.Context;

import com.haipeng.decoration.manager.constant.UrlConstant;
import com.haipeng.decoration.manager.listener.OnHttpPostListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanin on 2017/4/26.
 */

public class OkHttpDecorationPost extends OkHttpDecorationBase {

    OkHttpManager okHttpManager;
    OkHttpClient okHttpClient;
    public static int SUBMIT_HOMEWORK_TYPE = 0x001;
    private int type;
    String imageUrl = "";
    int tryCount = 1;
    FormBody tryRequestBody;
    String tryUrl = "";
    OnHttpPostListener httpPostListener;
    Context mContext;

    public OkHttpDecorationPost(Context context) {
        okHttpManager = OkHttpManager.getInstance();
        okHttpClient = okHttpManager.getOkHttpClient();
        this.mContext = context;
    }

    public void setOnPostListener(OnHttpPostListener httpPostListener) {
        this.httpPostListener = httpPostListener;
    }


    public void requestSubmitUserPost(String jsonStr) {
        FormBody.Builder requestBody = new FormBody.Builder();
//        initFormBody(requestBody);
        requestBody.add("UserModel", jsonStr);
        requestPostExecute(requestBody.build(), UrlConstant.URL_ADD_USER);
        type = SUBMIT_HOMEWORK_TYPE;

    }

    public void requestSubmitMasterPost(String jsonStr) {
        FormBody.Builder requestBody = new FormBody.Builder();
        initFormBody(requestBody);
        requestBody.add("MasterModel", jsonStr);
        requestPostExecute(requestBody.build(), UrlConstant.URL_ADD_MASTER);
        type = SUBMIT_HOMEWORK_TYPE;
    }

    public void requestSubmitVendorPost(String jsonStr) {
        FormBody.Builder requestBody = new FormBody.Builder();
        initFormBody(requestBody);
        requestBody.add("VendorModel", jsonStr);
        requestPostExecute(requestBody.build(), UrlConstant.URL_ADD_VENDOR);
        type = SUBMIT_HOMEWORK_TYPE;

    }
    public void requestSubmitTemplatePost(String jsonStr) {
        FormBody.Builder requestBody = new FormBody.Builder();
        initFormBody(requestBody);
        requestBody.add("TemplateModel", jsonStr);
        requestPostExecute(requestBody.build(), UrlConstant.URL_ADD_TEMPLATE);
        type = SUBMIT_HOMEWORK_TYPE;

    }

    public void requestRecommendPost(String jsonStr) {
        FormBody.Builder requestBody = new FormBody.Builder();
        initFormBody(requestBody);
        requestBody.add("RecommendModel", jsonStr);
        requestPostExecute(requestBody.build(), UrlConstant.URL_ADD_RECOMMEND);
        type = SUBMIT_HOMEWORK_TYPE;

    }


    public void requestPostExecute(final FormBody requestBody, final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                tryRequestBody = requestBody;
                tryUrl = url;
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                okHttpClient.newCall(request).enqueue(new MyCallback());
            }
        }).start();

    }

    String content = "";

    public class MyCallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            tryReqeust();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
//            Toast.makeText();
//            {"msg":"作业提交成功","code":200}
            if (null != response) {
                int responseCode = response.code();
                if (200 == responseCode) {

                    try {
                        content = response.body().string();

                        if (null != content) {
                            JSONObject jsonObject = new JSONObject(content);
                            if (null == jsonObject) {
                            } else {

                                if ("200".equals(jsonObject.getString("code"))) {
                                    if (jsonObject.has("data")) {
//                                        EventBus.getDefault().post(new ToastEvent(jsonObject.getString("data")));
                                        httpPostListener.responsePostSuccess(type, jsonObject.getString("data"));
                                    } else if (jsonObject.has("msg")) {
//                                        EventBus.getDefault().post(new ToastEvent(jsonObject.optString("msg")));
                                    }
                                } else {
//                                    EventBus.getDefault().post(new ToastEvent(jsonObject.getString("msg")));
                                }

                                if (type == SUBMIT_HOMEWORK_TYPE) {
//                                    EventBus.getDefault().post(new UploadServerEvent(imageUrl));
                                }

                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
//                        EventBus.getDefault().post(new ToastEvent("数据异常"));
                    }
                } else {
                    tryReqeust();
                }


            }

        }
    }

    public void tryReqeust() {
        ++tryCount;
        if (tryCount <= 3) {
            requestPostExecute(tryRequestBody, tryUrl);
        } else {
//            EventBus.getDefault().post(new ToastEvent("网络连接失败"));
            tryCount = 0;
            return;
        }
    }


}
