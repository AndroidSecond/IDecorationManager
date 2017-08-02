package com.haipeng.decoration.manager.http.okhttp3;

import android.content.Context;


import com.haipeng.decoration.manager.constant.UrlConstant;
import com.haipeng.decoration.manager.listener.OnHttpGetListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanin on 2017/4/26.
 */

public class OkHttpDecorationGet extends OkHttpDecorationBase {

    private String TAG = "tag";
    private OkHttpClient okHttpClient;
    private OkHttpManager okHttpManager;

    private OnHttpGetListener mListener;
    private int type = 0;
    private Context mContext;
    int tryCount = 1;
    String tryUrl;

    public OkHttpDecorationGet(Context context) {
        okHttpManager = OkHttpManager.getInstance();
        okHttpClient = okHttpManager.getOkHttpClient();
        this.mContext = context;
    }

    public void setListener(OnHttpGetListener listener) {
        this.mListener = listener;
    }

    public void requestTokenGet(int type) {
        this.type = type;
        String uri = UrlConstant.UPLOAD_IMAGE;
        Map<String, String> params = new HashMap<String, String>();
        params.put("backet", UrlConstant.qiniuImageAdress);
        initMap(params);
        uri = packageUri(params, uri);
        requestGetExecute(uri);
    }

    public void requestUserModelsGet(int type) {
        this.type = type;
        String uri = UrlConstant.URL_QUERY_USER;
//        Map<String, String> params = new HashMap<String, String>();
//        initMap(params);
//        uri = packageUri(params, uri);
        requestGetExecute(uri);
    }

    public void requestMasterModelsGet(int type) {
        this.type = type;
        String uri = UrlConstant.UPLOAD_IMAGE;
        Map<String, String> params = new HashMap<String, String>();
        initMap(params);
        uri = packageUri(params, uri);
        requestGetExecute(uri);
    }

    public void requestGetExecute(final String mUrl) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                tryUrl = mUrl;
                final Request request = new Request.Builder().get().url(mUrl).build();
                okHttpClient.newCall(request).enqueue(new CallBack());
            }
        }).start();

    }


    String content = "";

    public class CallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            tryRequest();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            if (null != response) {
                int responseCode = response.code();
                if (200 == responseCode) {

                    try {
                        content = response.body().string();

                        if (null != content) {
//                            JSONObject jsonObject = new JSONObject(content);
//                            if (null == jsonObject) {
//                            } else {
                            mListener.getResponse(type, content);
//                                if ("200".equals(jsonObject.getString("code"))) {
//                                    if (jsonObject.has("data")) {
//                                        mListener.getResponse(type, jsonObject.getJSONObject("data").toString());
//                                    } else if (jsonObject.has("msg")) {
//                                        mListener.getResponse(type, jsonObject.getJSONObject("msg").toString());
//                                    }
//                                } else {
////                                    EventBus.getDefault().post(new ToastEvent(jsonObject.getString("msg")));
//                                }

//                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
//                        mListener.getResponse(type, new JSONObject().toString());
                        mListener.getResponeseFail(type);
                    }
                } else {
                    tryRequest();
                }


            }
        }

    }

    public void tryRequest() {
        ++tryCount;
        if (tryCount <= 3) {
            requestGetExecute(tryUrl);
        } else {
//            EventBus.getDefault().post(new ToastEvent("网络连接失败"));
            tryCount = 0;
            return;
        }
    }

    public String packageUri(Map<String, String> params, String uri) {
        String pacUri = "";
        if (params != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : params.entrySet()) {
                sb.append(item.getKey() + "=" + item.getValue() + "&");
//                L.i(TAG, item.getKey() + " = " + item.getValue());
            }
            uri = (uri + "?" + sb.toString());
            if (uri.endsWith("&"))
                uri = uri.substring(0, uri.length() - 1);//去除最后的&
        }
        pacUri = uri;
        return pacUri;
    }


}
