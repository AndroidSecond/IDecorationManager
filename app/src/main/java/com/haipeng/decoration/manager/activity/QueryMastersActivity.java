package com.haipeng.decoration.manager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.haipeng.decoration.manager.R;
import com.haipeng.decoration.manager.adapter.MasterAdapter;
import com.haipeng.decoration.manager.http.okhttp3.OkHttpDecorationGet;
import com.haipeng.decoration.manager.listener.OnHttpGetListener;
import com.haipeng.decoration.manager.model.MasterResponeseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class QueryMastersActivity extends Activity implements OnHttpGetListener {

    List<MasterResponeseModel>  list= new ArrayList<MasterResponeseModel>();
    MasterAdapter masterAdapter;
    RecyclerView recyclerView;
    OkHttpDecorationGet okHttpHomeworkGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_master);

        recyclerView = (RecyclerView) findViewById(R.id.rv_master);
        masterAdapter = new MasterAdapter(this);
        recyclerView.setAdapter(masterAdapter);
        okHttpHomeworkGet = new OkHttpDecorationGet(this);
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
            for(int i =0;i<jsonArray.length();i++){
                MasterResponeseModel masterResponeseModel = gson.fromJson(jsonStr,MasterResponeseModel.class);
                list.add(masterResponeseModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        masterAdapter.setDatas(list);

    }

    @Override
    public void getResponeseFail(int val) {

    }
}
