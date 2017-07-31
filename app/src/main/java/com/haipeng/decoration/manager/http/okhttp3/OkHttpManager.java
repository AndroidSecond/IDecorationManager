package com.haipeng.decoration.manager.http.okhttp3;

import okhttp3.OkHttpClient;

/**
 * Created by wanin on 2017/4/26.
 */

public class OkHttpManager {

    private OkHttpClient okHttpClient;
    private static OkHttpManager okHttpManager;

    public OkHttpManager() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpManager getInstance() {

        synchronized (OkHttpManager.class) {
            if (okHttpManager == null) {
                okHttpManager = new OkHttpManager();
                return okHttpManager;
            }
        }
        return okHttpManager;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}
