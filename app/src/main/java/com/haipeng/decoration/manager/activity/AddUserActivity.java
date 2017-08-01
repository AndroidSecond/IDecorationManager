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
import com.haipeng.decoration.manager.model.UserModel;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class AddUserActivity extends Activity implements OnHttpPostListener, View.OnClickListener {

    Button back;
    Button commit;
    ImageView img;
    EditText etName, etPhone, etEmail, etPassword, etRePassword;
    OkHttpDecorationGet okHttpGet;
    OkHttpDecorationPost okHttpPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
//        EventBus.getDefault().register(this);
        okHttpGet = new OkHttpDecorationGet(this);
        okHttpPost = new OkHttpDecorationPost(this);

        back = (Button) findViewById(R.id.back);
        commit = (Button) findViewById(R.id.commit);
        img = (ImageView) findViewById(R.id.iv_add_user);

        back.setOnClickListener(this);
        commit.setOnClickListener(this);
        img.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etRePassword = (EditText) findViewById(R.id.et_re_password);

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
                okHttpPost.requestSubmitUserPost(getUserModelJson(""));
                break;
            case R.id.iv_add_user:
                break;
        }
    }

    public String getUserModelJson(String imgPath){
        UserModel userModel = new UserModel();
        userModel.setName(filterStringExe(etName.getText()));
        userModel.setPhone(filterStringExe(etPhone.getText()));
        userModel.setEmail(filterStringExe(etEmail.getText()));
        userModel.setPassword(filterStringExe(etPassword.getText()));
        userModel.setRepeatPassword(filterStringExe(etRePassword.getText()));
        userModel.setImagePath(imgPath);
        Gson gson = new Gson();

        return gson.toJson(userModel);
    }

    public String filterStringExe(Editable eta){
        String temp = "";
        if(null == eta)
            return "";
        else
        {
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
