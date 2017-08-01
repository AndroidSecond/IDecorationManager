package com.haipeng.decoration.manager.http.okhttp3;

import com.haipeng.decoration.manager.constant.UrlConstant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by wanin on 2017/4/28.
 */

public class OkHttpDecorationBase {
    /**
     * 根据时间戳和key获得md5
     *
     * @param timestamp
     * @param key
     * @return
     */
    private String getHash(String timestamp, String key) {
        String src = timestamp + key;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(src.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;

    }

    public void initMap(Map<String, String> params) {
        String timestamp = ((Long) System.currentTimeMillis()).toString();
        params.put("timestamp", timestamp);
//        params.put("hash", getHash(timestamp, UrlConstant.REQUEST_KEY));
    }

    public void initFormBody(FormBody.Builder body){
        String timestamp = ((Long) System.currentTimeMillis()).toString();
        body.add("timestamp", timestamp);
//        body.add("hash", getHash(timestamp, UrlConstant.REQUEST_KEY));
    }

}
