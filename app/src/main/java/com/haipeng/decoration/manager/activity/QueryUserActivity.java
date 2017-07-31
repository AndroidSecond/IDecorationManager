package com.haipeng.decoration.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.adapter.UserAdapter;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpHomeworkBase;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpHomeworkGet;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpManager;
import com.haipeng.decoration.manager.listener.OnHttpGetListener;
import com.haipeng.decoration.manager.listener.OnHttpPostListener;
import com.haipeng.decoration.manager.model.MasterResponeseModel;
import com.haipeng.decoration.manager.model.UserResponseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class QueryUserActivity extends Activity implements OnHttpGetListener {

    List<UserResponseModel> list = new ArrayList<UserResponseModel>();
    UserAdapter userAdapter;
    RecyclerView recyclerView;
    OkHttpHomeworkGet okHttpHomeworkGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_user);
        recyclerView = (RecyclerView) findViewById(R.id.rv_users);
        userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        okHttpHomeworkGet = new OkHttpHomeworkGet(this,this);
        okHttpHomeworkGet.requestUserModelsGet(0);
    }


    @Override
    public void getResponse(int var1, String jsonStr) {
        Gson gson = new Gson();
        JSONObject jsonObject;
        JSONArray jsonArray;

        try {
            jsonObject = new JSONObject(jsonStr);
            jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                UserResponseModel userResponeseModel = gson.fromJson(jsonStr, UserResponseModel.class);
                list.add(userResponeseModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userAdapter.setDatas(list);

    }

    @Override
    public void getResponeseFail(int val) {

    }
}
