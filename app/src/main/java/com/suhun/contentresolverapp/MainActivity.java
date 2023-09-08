package com.suhun.contentresolverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView fieldNameResult;
    private ListView fieldContentList;
    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private String[] from = {"content"};
    private int[] to = {R.id.list_content};
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLstView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED /*grantResults[2] == PackageManager.PERMISSION_GRANTED*/){
                initContentResolver();
            }else{
                finish();
            }
        }
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
        if(!userAgreePermission()){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 123);
        }else{
            initContentResolver();
        }
    }

    public void getFieldContentFun(View view){

    }

    private boolean userAgreePermission(){
        boolean result = false;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==  PackageManager.PERMISSION_GRANTED||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            result = true;
        }

        return result;
    }

    private void initContentResolver(){
        contentResolver = getContentResolver();
    }
}