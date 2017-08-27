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
import com.haipeng.decoration.manager.model.UserModel;
import com.haipeng.decoration.manager.utils.UniqueNumberUtils;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class AddMastersActivity extends Activity implements OnHttpPostListener, View.OnClickListener {

    Button back;
    Button commit;
    ImageView imageAavator, imageLicense;//,imgOther;
    EditText etName, etPhone, etEmail, etAddress, etLegalRep;
    OkHttpDecorationGet okHttpGet;
    OkHttpDecorationPost okHttpPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_master);
//        EventBus.getDefault().register(this);
        okHttpGet = new OkHttpDecorationGet(this);
        okHttpPost = new OkHttpDecorationPost(this);

        back = (Button) findViewById(R.id.back);
        commit = (Button) findViewById(R.id.commit);
        imageAavator = (ImageView) findViewById(R.id.iv_add_master_avator);
        imageLicense = (ImageView) findViewById(R.id.iv_add_license);

        back.setOnClickListener(this);
        commit.setOnClickListener(this);
        imageAavator.setOnClickListener(this);
        imageLicense.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etEmail = (EditText) findViewById(R.id.et_email);
        etAddress = (EditText) findViewById(R.id.et_address);
        etLegalRep = (EditText) findViewById(R.id.et_legal_representative);

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
                okHttpPost.requestSubmitMasterPost(getMasterModelJson("", ""));
                break;
            case R.id.iv_add_user:
                break;
        }
    }

    public String getMasterModelJson(String imgAvator, String imageLicense) {
        MasterModel model = new MasterModel();
        model.setUniqueNumber(UniqueNumberUtils.getUniqueNumber());
        model.setName(filterStringExe(etName.getText()));
        model.setPhone(filterStringExe(etPhone.getText()));
        model.setEmail(filterStringExe(etEmail.getText()));
        model.setAddress(filterStringExe(etAddress.getText()));
        model.setLegalRepresentative(filterStringExe(etLegalRep.getText()));
        model.setImageAvator(imgAvator);
        model.setImageLicense(imageLicense);
        model.setImageUrls("");
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
