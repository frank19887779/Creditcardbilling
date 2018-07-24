package com.example.user.creditcardbilling;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.AbsSavedState;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class China_Trust extends AppCompatActivity implements AdapterView.OnItemClickListener{



    private static final int 請求碼 = 0;
    private static final String TAG = "123";

    private MyDBHelper helper;
    private ListView mlistview;
    private MainListAdapter mAdapter;

    private List<Pet> petList;

    private Parcelable mListState = null;
    private static final String LIST_STATE = "list_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china__trust);

        if(helper == null) {
            helper = new MyDBHelper(this);
        }

        init();

//        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
//        cursor = helper.getReadableDatabase().query("Pet ", null, null, null, null,
//                null, null);

    }

    public void init(){
        mlistview = (ListView) findViewById(R.id.list2);
        mlistview.setEmptyView(findViewById(R.id.empty));
        mlistview.setOnItemClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //helper.getAllPet();
        petList = getPetList();
        mAdapter = new MainListAdapter(this, petList);
        mlistview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    public List<Pet> getPetList(){
        return helper.getAllPet();
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case RESULT_OK:
                break;
        }

//        if(resultCode == RESULT_OK && requestCode == 請求碼){
//            Bundle build = data.getExtras();
//
//            String 日期 = build.getString(AddActivity.KEY1);
//            String 備註 = build.getString(AddActivity.KEY2);
//            String 金額 = build.getString(AddActivity.KEY3);
//            String 預留 = build.getString(AddActivity.KEY4);
//
//            Pet pet = new Pet(日期, 備註, 金額, 預留);
//            mList.add(pet);
//            mAdapter.notifyDataSetChanged();
//
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        mListState = mlistview.onSaveInstanceState();
        if(savedInstanceState != null){
            savedInstanceState.putParcelable(LIST_STATE, mListState);
        }

        Log.d(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            mListState = savedInstanceState.getParcelable(LIST_STATE);

            Log.d(TAG, "onRestoreInstanceState");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mListState != null){
            mlistview.onRestoreInstanceState(mListState);

            mListState = null;
        }
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

        final Pet pet = petList.get(position);
        String s = "第" + position + "項被點選";
        Log.d("China",s);

         new AlertDialog.Builder(this)
                .setMessage("請選擇動作")
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(China_Trust.this, UpdateActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("_id", pet.getId());
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    finish();
                    }
                })
                .setNeutralButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteById(pet.getId());
                        petList.remove(position);
                        Toast.makeText(China_Trust.this, "已刪除", Toast.LENGTH_SHORT).show();
                        mAdapter.notifyDataSetChanged();

                    }
                }).show();

    }

}
