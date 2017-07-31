package com.haipeng.decoration.manager.listener;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/7/29.
 */

public interface OnHttpPostListener {
    void responsePostSuccess(int varl, JSONObject jsonObject);
    void responsePostFail(int varl);
}
