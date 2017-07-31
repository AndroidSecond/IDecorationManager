package com.haipeng.decoration.manager.qiniu;

import android.content.Context;
import android.util.Log;

import com.haipeng.decoration.manager.constant.CommonConstant;
import com.haipeng.decoration.manager.constant.UrlConstant;
import com.haipeng.decoration.manager.model.ImageModel;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.storage.persistent.FileRecorder;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by wanin on 2017/4/24.
 */

public class UploadImageController {

    UploadManager uploadManager;

    Context mContext;

    public UploadImageController(Context context) {
        mContext = context;
    }

    public void initQINIU() {

        String dirPath = "/sdcard/break_file"; // 断点记录文件保存的文件夹位置
        Recorder recorder = null;
        try {
            recorder = new FileRecorder(dirPath);
        } catch (Exception e) {

        }

        //默认使用key的url_safe_base64编码字符串作为断点记录文件的文件名
        //避免记录文件冲突（特别是key指定为null时），也可自定义文件名(下方为默认实现)：
        KeyGenerator keyGenerator = new KeyGenerator() {
            @Override
            public String gen(String key, File file) {
                // 不必使用url_safe_base64转换，uploadManager内部会处理
                // 该返回值可替换为基于key、文件内容、上下文的其它信息生成的文件名
                return key + "_._" + new StringBuffer(file.getAbsolutePath()).reverse();
            }
        };


        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        //UploadManager uploadManager = new UploadManager(recorder);  // 1
        //UploadManager uploadManager = new UploadManager(recorder, keyGen); // 2
        // 或在初始化时指定：
        Configuration configuration = new Configuration.Builder()
                .recorder(recorder, keyGenerator)
                .build();

        uploadManager = new UploadManager(configuration);

    }


    UploadOptions uploadOptions;
    String mImageUrl = "";
    int total = 0;
    int mark = 0;
    List<String> rulePicsTemp = new ArrayList<String>();

    public void uploadFile(List<ImageModel> list, HashMap<String, String> hashMapHeads) {

        uploadOptions = new UploadOptions(hashMapHeads, "", false, upProgressHandler, upCancellationSignal);
        StringBuilder stringBuilder = new StringBuilder();
        mark = 0;
        mImageUrl = "";
        total = list.size();
        for (int i = 0; i < list.size(); i++) {
            try {
                File file = new File(list.get(i).getImagePath());
                if (file.exists())
                    uploadManager.put(file, file.getName(), CommonConstant.UploadQiniuToken, new MyUpCompletionHandler(), uploadOptions);

                rulePicsTemp.add(file.getName());
                Log.d("tag", "fileName" + file.getName());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }


    }

    UpProgressHandler upProgressHandler = new UpProgressHandler() {
        @Override
        public void progress(String key, double percent) {
            if (percent >= 99.00) {
//                ToastUtils.showLong("上传成功");
            }
        }
    };

    UpCancellationSignal upCancellationSignal = new UpCancellationSignal() {
        @Override
        public boolean isCancelled() {
            return false;
        }
    };


    class MyUpCompletionHandler implements UpCompletionHandler {



        @Override
        public void complete(String key, ResponseInfo info, JSONObject response) {

            String imageName = key;
            mImageUrl = mImageUrl + key + ",".trim();

            ++mark;

            Log.d("tag", "completion =" + key);
            Log.d("tag", "completion =" + mImageUrl);

//            EventBus.getDefault().post(new UploadImageComplementEvent(mImageUrl, imageName));

            if (mark == total) {
                if (mImageUrl.endsWith(","))
                    mImageUrl = mImageUrl.substring(0, mImageUrl.length() - 1);

                mImageUrl = sortPicsUrl(mImageUrl);

//                EventBus.getDefault().post(new UploadImageAllComplementEvent(mImageUrl, imageName));

            }
        }
    }

    public String sortPicsUrl(String mImageUrl) {

        String ruleStr = "";
        String domain = UrlConstant.isDebug ? "homework-test.shituxueba.com" : "homework.shituxueba.com";

        String str[] = mImageUrl.split(",");

        String key = "";

        for (int i = 0; i < rulePicsTemp.size(); i++) {

            for (int j = 0; j < str.length; j++) {

                if (rulePicsTemp.get(i).equals(str[j])) {
                    key = "http://" + domain + "/" + str[j]+",";
                    ruleStr = ruleStr + key;
                }

            }

        }

        Log.d("tag", "ruleStr = " + ruleStr);
        if (ruleStr.endsWith(","))
            ruleStr = ruleStr.substring(0, ruleStr.length() - 1);

        return ruleStr;

    }


}
