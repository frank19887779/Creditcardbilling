package com.example.user.creditcardbilling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private MyDBHelper helper;

    private Pet pet;
    public static final String KEY1 = "1";
    public static final String KEY2 = "2";
    public static final String KEY3 = "3";
    public static final String KEY4 = "4";

    private String mdate, mamount, mrserved, mdescription;

    private TextView etdate, etamount, etrserved, etdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        if(helper == null) {
            helper = new MyDBHelper(this);
        }
        initview();
    }

    public void initview(){
        etdate = findViewById(R.id.date);
        etamount = findViewById(R.id.amount);
        etrserved = findViewById(R.id.rserved);
        etdescription = findViewById(R.id.description);

        int id = getIntent().getExtras().getInt("_id");
        pet= helper.findById(id);

        etdate.setText(pet.getDate());
        etamount.setText(pet.getAmount());
        etrserved.setText(pet.getRserved());
        etdescription.setText(pet.getDescription());
    }

    public void click(View view)  {
        mdate = etdate.getText().toString().trim();
        mamount = etamount.getText().toString().trim();
        mrserved = etrserved.getText().toString().trim();
        mdescription = etdescription.getText().toString().trim();
//      Intent intent = getIntent();
//      intent.putExtra(KEY1, mdate);
//      intent.putExtra(KEY2, mamount);
//      intent.putExtra(KEY3,  mrserved);
//      intent.putExtra(KEY4, mdescription);
//      etResult(RESULT_OK, intent);

        pet.setDate(mdate);
        pet.setAmount(mamount);
        pet.setRserved(mrserved);
        pet.setDescription(mdescription);

        helper.update(pet);
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }

}
