package com.haipeng.decoration.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.haipeng.decoration.manager.activity.AddMastersActivity;
import com.haipeng.decoration.manager.activity.AddOrdersActivity;
import com.haipeng.decoration.manager.activity.AddSuppliersActivity;
import com.haipeng.decoration.manager.activity.AddUserActivity;
import com.haipeng.decoration.manager.activity.QueryMastersActivity;
import com.haipeng.decoration.manager.activity.QueryOrdersActivity;
import com.haipeng.decoration.manager.activity.QuerySuppliersActivity;
import com.haipeng.decoration.manager.activity.QueryUserActivity;


public class MainActivity extends Activity implements View.OnClickListener {


    private Button btnAddUser, btnAddMaster, btnAddSupplier, btnAddOrder,
            btnQueryUser, btnQueryMaster, btnQuerySupplier, btnQueryOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddUser = (Button) findViewById(R.id.btn_add_user);
        btnAddMaster = (Button) findViewById(R.id.btn_add_master);
        btnAddSupplier = (Button) findViewById(R.id.btn_add_supplier);
        btnAddOrder = (Button) findViewById(R.id.btn_add_order);
        btnQueryUser = (Button) findViewById(R.id.btn_query_user);
        btnQueryMaster = (Button) findViewById(R.id.btn_query_master);
        btnQuerySupplier = (Button) findViewById(R.id.btn_query_supplier);
        btnQueryOrder = (Button) findViewById(R.id.btn_query_order);

        btnAddUser.setOnClickListener(this);
        btnAddMaster.setOnClickListener(this);
        btnAddSupplier.setOnClickListener(this);
        btnAddOrder.setOnClickListener(this);
        btnQueryUser.setOnClickListener(this);
        btnQueryMaster.setOnClickListener(this);
        btnQuerySupplier.setOnClickListener(this);
        btnQueryOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_add_user:
                startActivityM(AddUserActivity.class);
                break;
            case R.id.btn_add_master:
                startActivityM(AddMastersActivity.class);
                break;
            case R.id.btn_add_supplier:
                startActivityM(AddSuppliersActivity.class);
                break;
            case R.id.btn_add_order:
                startActivityM(AddOrdersActivity.class);
                break;

            case R.id.btn_query_user:
                startActivityM(QueryUserActivity.class);
                break;
            case R.id.btn_query_master:
                startActivityM(QueryMastersActivity.class);
                break;
            case R.id.btn_query_supplier:
                startActivityM(QuerySuppliersActivity.class);
                break;
            case R.id.btn_query_order:
                startActivityM(QueryOrdersActivity.class);
                break;


        }


    }

    public void startActivityM(Class<? extends Activity> tClass){
        Intent intent = null;
        intent = new Intent(this, tClass);
        startActivity(intent);
    }


}
