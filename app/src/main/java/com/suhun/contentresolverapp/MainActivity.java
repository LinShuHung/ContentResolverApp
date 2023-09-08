package com.suhun.contentresolverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView fieldNameResult;
    private ListView fieldContentList;
    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private String[] from = {"content"};
    private int[] to = {R.id.list_content};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLstView();
    }

    private void initView(){
        fieldNameResult = findViewById(R.id.lid_fieldName);
        fieldContentList = findViewById(R.id.lid_fieldContent);
    }

    private void initLstView(){
        simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        fieldContentList.setAdapter(simpleAdapter);
    }

    public void getFieldNameFun(View view){

    }

    public void getFieldContentFun(View view){

    }
}