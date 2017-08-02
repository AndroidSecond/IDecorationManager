package com.haipeng.decoration.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.adapter.UserAdapter;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpDecorationGet;
import com.haipeng.decoration.manager.listener.OnHttpGetListener;
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
    OkHttpDecorationGet okHttpHomeworkGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_user);
        LinearLayoutManager VLM = new LinearLayoutManager(this);
        VLM.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) findViewById(R.id.rv_users);
        userAdapter = new UserAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(VLM);

        recyclerView.setAdapter(userAdapter);
        okHttpHomeworkGet = new OkHttpDecorationGet(this);
        okHttpHomeworkGet.requestUserModelsGet(0);
        okHttpHomeworkGet.setListener(this);
    }


    @Override
    public void getResponse(int var1, String jsonStr) {
        Gson gson = new Gson();
//        JSONObject jsonObject;
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(jsonStr);
//            jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                UserResponseModel userResponeseModel = gson.fromJson(jsonArray.getString(i), UserResponseModel.class);
                list.add(userResponeseModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userAdapter.setDatas(list);
            }
        });

    }

    @Override
    public void getResponeseFail(int val) {

    }
}
