package com.haipeng.decoration.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpDecorationGet;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpDecorationPost;
import com.haipeng.decoration.manager.listener.OnHttpPostListener;
import com.haipeng.decoration.manager.model.MasterModel;
import com.haipeng.decoration.manager.model.RecommendModel;
import com.haipeng.decoration.manager.utils.UniqueNumberUtils;

public class AddRecommendActivity extends Activity implements OnHttpPostListener, View.OnClickListener {

    Button back;
    Button commit;
    ImageView imageAavator;//,imgOther;
    EditText etTitle, etType, etMaster, etVendor, etOrder, etUrl;
    OkHttpDecorationGet okHttpGet;
    OkHttpDecorationPost okHttpPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recommend);
//        EventBus.getDefault().register(this);
        okHttpGet = new OkHttpDecorationGet(this);
        okHttpPost = new OkHttpDecorationPost(this);

        back = (Button) findViewById(R.id.back);
        commit = (Button) findViewById(R.id.commit);
        imageAavator = (ImageView) findViewById(R.id.iv_add_recommend_avator);

        back.setOnClickListener(this);
        commit.setOnClickListener(this);
        imageAavator.setOnClickListener(this);

        etTitle = (EditText) findViewById(R.id.et_title);
        etType = (EditText) findViewById(R.id.et_type);
        etMaster = (EditText) findViewById(R.id.et_master);
        etVendor = (EditText) findViewById(R.id.et_vendor);
        etOrder = (EditText) findViewById(R.id.et_order);
        etUrl = (EditText) findViewById(R.id.et_url);
    }


    @Override
    public void responsePostSuccess(int varl, String jsonStr) {

    }

    @Override
    public void responsePostFail(int varl) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                break;
            case R.id.commit:
                okHttpPost.requestRecommendPost(getRecomendModelJson(""));
                break;
            case R.id.iv_add_user:
                break;
        }
    }

    public String getRecomendModelJson(String imgAvator) {
        RecommendModel model = new RecommendModel();
        model.setUniqueNumber(UniqueNumberUtils.getUniqueNumber());
        model.setTitle(filterStringExe(etTitle.getText()));
        model.setType(filterStringExe(etType.getText()));
        model.setMasterUniqueNumber(filterStringExe(etMaster.getText()));
        model.setVendorUniqueNumber(filterStringExe(etVendor.getText()));
        model.setOrderUniqueNumber(filterStringExe(etOrder.getText()));
        model.setUrl(filterStringExe(etUrl.getText()));
        model.setImageAvator(imgAvator);
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    public String filterStringExe(Editable eta) {
        String temp = "";
        if (null == eta)
            return "";
        else {
            temp = eta.toString().trim();
            return temp;
        }
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
