package com.example.user.creditcardbilling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mlist;
    private ArrayAdapter madapter;
    private String[] func= {"中國信託", "渣打銀行"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlist= (ListView) findViewById(R.id.list);

        madapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        mlist.setAdapter(madapter);
        mlist.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, China_Trust.class);
                startActivity(intent);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
