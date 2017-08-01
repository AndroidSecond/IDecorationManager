package com.haipeng.decoration.manager.listener;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/7/29.
 */

public interface OnHttpPostListener {
    void responsePostSuccess(int varl, String jsonStr);
    void responsePostFail(int varl);
}
