package com.haipeng.decoration.manager.listener;

import org.json.JSONObject;

/**
 * Created by wanin on 2017/4/27.
 */

public interface OnHttpGetListener {
    void getResponse(int var1, String jsonStr);
    void getResponeseFail(int val);
}
