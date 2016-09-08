package com.example.priyamkumar.newcleanzo;

/**
 * Created by Priyam Kumar on 7/30/2016.
 */
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    Context ctx;

    ArrayList<Grid> data;

    public GridAdapter(Context c, ArrayList<Grid> d) {
        ctx = c;
        data = d;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub

        if (arg1 == null) {
            LayoutInflater lf=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = lf.inflate(R.layout.cust, arg2, false);

        }

        ImageView iv = (ImageView) arg1.findViewById(R.id.imageView1);
        TextView tv = (TextView) arg1.findViewById(R.id.textView2);
        Grid g = data.get(arg0);
        tv.setText(g.title);
        tv.setTextColor(Color.parseColor(g.color));
        iv.setImageResource(g.image);

        return arg1;
    }
}