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
import com.haipeng.decoration.manager.model.TemplateModel;
import com.haipeng.decoration.manager.model.UserModel;
import com.haipeng.decoration.manager.utils.UniqueNumberUtils;

import de.greenrobot.event.EventBus;

public class AddTemplatesActivity extends Activity implements OnHttpPostListener, View.OnClickListener {


    Button back;
    Button commit;
    ImageView imageAvator;
    EditText etTitle, etMasterId, etVendorId;
    OkHttpDecorationGet okHttpGet;
    OkHttpDecorationPost okHttpPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
//        EventBus.getDefault().register(this);
        okHttpGet = new OkHttpDecorationGet(this);
        okHttpPost = new OkHttpDecorationPost(this);

        back = (Button) findViewById(R.id.back);
        commit = (Button) findViewById(R.id.commit);
        imageAvator = (ImageView) findViewById(R.id.iv_avator);

        back.setOnClickListener(this);
        commit.setOnClickListener(this);
        imageAvator.setOnClickListener(this);

        etTitle = (EditText) findViewById(R.id.et_title);
        etMasterId = (EditText) findViewById(R.id.et_master);
        etVendorId = (EditText) findViewById(R.id.et_vendor);

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
                okHttpPost.requestSubmitTemplatePost(getTemplateModelJson(""));
                break;
            case R.id.iv_add_user:
                break;
        }
    }

    public String getTemplateModelJson(String imgAvator) {
        TemplateModel model = new TemplateModel();
        model.setUniqueNumber(UniqueNumberUtils.getUniqueNumber());
        model.setTitle(filterStringExe(etTitle.getText()));
        model.setMasterUniqueNumber(filterStringExe(etMasterId.getText()));
        model.setVendorUniqueNumber(filterStringExe(etVendorId.getText()));
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
