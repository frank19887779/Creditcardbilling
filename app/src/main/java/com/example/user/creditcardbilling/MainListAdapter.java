package com.example.user.creditcardbilling;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Pet> list;

    private Context context;
    private Cursor cursor;

    private LayoutInflater inflater;

    private LinearLayout pageLayout;


    public MainListAdapter(Context context, List<Pet> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item_view, null);

        TextView tvdate = v.findViewById(R.id.日期);
        TextView tvamount = v.findViewById(R.id.金額);
        TextView tvrserved = v.findViewById(R.id.預留);
        TextView tvdescription = v.findViewById(R.id.備註);

        Pet pet = list.get(position);

        tvdate.setText(pet.getDate());
        tvamount.setText(pet.getAmount());
        tvrserved.setText(pet.getRserved());
        tvdescription.setText(pet.getDescription());


//        cursor.moveToPosition(position);
//        pageLayout = (LinearLayout) inflater.inflate(R.layout.item_view, null);
//
//        TextView tvdate = pageLayout.findViewById(R.id.日期);
//        TextView tvamount = pageLayout.findViewById(R.id.備註);
//        TextView tvrserved = pageLayout.findViewById(R.id.金額);
//        TextView tvdescription = pageLayout.findViewById(R.id.預留);
//
//        tvdate.setText(cursor.getString(0));
//        tvamount.setText(cursor.getString(1));
//        tvrserved.setText(cursor.getString(2));
//        tvdescription.setText(cursor.getString(3));

        return v;
    }
}
