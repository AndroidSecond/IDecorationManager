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
import com.haipeng.decoration.manager.listener.OnHttpPostListener;
import com.haipeng.decoration.manager.model.MasterModel;
import com.haipeng.decoration.manager.model.UserModel;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class AddMastersActivity extends Activity implements OnHttpPostListener,View.OnClickListener{

    Button back;
    Button commit;
    ImageView img;
    EditText etName, etPhone, etEmail, etPassword, etRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_add_master);
        back = (Button) findViewById(R.id.back);
        commit = (Button) findViewById(R.id.commit);
        img = (ImageView) findViewById(R.id.iv_add_master);

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

    public String getMasterModelJson(String imgPath){
        MasterModel masterModel = new MasterModel();
        masterModel.setName(filterStringExe(etName.getText()));
        masterModel.setPhone(filterStringExe(etPhone.getText()));
        masterModel.setEmail(filterStringExe(etEmail.getText()));
        masterModel.setImagePath(imgPath);
        Gson gson = new Gson();

        return gson.toJson(masterModel);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back:
                break;
            case R.id.commit:
                break;
            case R.id.iv_add_master:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
