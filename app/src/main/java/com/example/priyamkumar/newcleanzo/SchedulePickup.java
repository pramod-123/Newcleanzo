package com.example.priyamkumar.newcleanzo;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulePickup extends Fragment {
    ArrayList<Grid> gift=new ArrayList<Grid>();
static  int i=0,j=0,k=0;


    public SchedulePickup() {
        // Required empty public constructor
        gift.add(new Grid(R.drawable.wash1, "Wash & Fold", false,"#000000"));
        gift.add(new Grid(R.drawable.shirt, "Wash & Iron", false,"#4AA9ED"));
        gift.add(new Grid(R.drawable.iron, "Iron", false,"#FCEC06"));
        gift.add(new Grid(R.drawable.dryclean, "Dry Cleaning", false,"#C7D0D6"));
        gift.add(new Grid(R.drawable.shoe, "Shoe Wash", false,"#8F1FB5"));
        gift.add(new Grid(R.drawable.sofa, "Sofa Wash", false,"#FF0000"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v;
        v=inflater.inflate(R.layout.fragment_schedule_pickup, container, false);
        GridView gv= (GridView) v.findViewById(R.id.gridview);
        final TextView tv= (TextView) v.findViewById(R.id.textView);
        final TextView tv1= (TextView) v.findViewById(R.id.textView3);
         final Button b1= (Button) v.findViewById(R.id.button3);
        final Button b2= (Button) v.findViewById(R.id.button4);
        final Button b3= (Button) v.findViewById(R.id.button5);
        b1.setBackgroundColor(Color.parseColor("#99ff0000"));
        b1.setTextColor(Color.parseColor("#ffffff"));



        GridAdapter ia=new GridAdapter(getActivity(), gift);
        gv.setAdapter(ia);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if(gift.get(arg2).checked==false){
                    gift.get(arg2).checked=true;
                  LinearLayout all= (LinearLayout) arg1.findViewById(R.id.al);

                    all.setBackgroundColor(Color.parseColor("#550000FF"));
                }
                else if(gift.get(arg2).checked==true){
                    gift.get(arg2).checked=false;
                    LinearLayout all=  (LinearLayout) arg1.findViewById(R.id.al);

                    all.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                if(gift.get(5).checked==true)
                {
                    tv.setText("Minimum Order Price is 1000");
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    tv1.setText("");
                    b1.setText("REGULAR"+'\n'+"96 hr");
                    b1.setBackgroundColor(Color.parseColor("#99ff0000"));
                    b1.setTextColor(Color.parseColor("#ffffff"));
                    b2.setBackgroundColor(Color.parseColor("#ffffff"));
                    b3.setBackgroundColor(Color.parseColor("#ffffff"));
                    b2.setTextColor(Color.parseColor("#0000ff"));
                    b3.setTextColor(Color.parseColor("#0000ff"));
                    j=k=0;

                }
                else if(gift.get(4).checked==true)
                {
                    tv.setText("Minimum Order Price is 250");
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b1.setText("REGULAR"+'\n'+"96 hr");
                    b1.setBackgroundColor(Color.parseColor("#99ff0000"));
                    b1.setTextColor(Color.parseColor("#ffffff"));
                    b2.setBackgroundColor(Color.parseColor("#ffffff"));
                    b3.setBackgroundColor(Color.parseColor("#ffffff"));
                    b2.setTextColor(Color.parseColor("#0000ff"));
                    b3.setTextColor(Color.parseColor("#0000ff"));
                    tv1.setText("");
                    j=k=0;
                }
                else if(gift.get(0).checked==true||gift.get(1).checked==true||gift.get(2).checked==true||gift.get(3).checked==true)
                {
                    tv.setText("Minimum Order  Price is 200");
                    b1.setText("Regular"+'\n'+"48hrs");
                    b2.setVisibility(View.VISIBLE);
                    b3.setVisibility(View.VISIBLE);
                }
                else
                { tv.setText("Please Select a Service");
                    b1.setText("REGULAR"+'\n'+"48 hr");
                    b2.setVisibility(View.VISIBLE);
                    b3.setVisibility(View.VISIBLE);
                }

                }

        });
 b1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
          if(i==0){
             b1.setBackgroundColor(Color.parseColor("#99ff0000"));
              b1.setTextColor(Color.parseColor("#ffffff"));
              b2.setBackgroundColor(Color.parseColor("#ffffff"));
              b3.setBackgroundColor(Color.parseColor("#ffffff"));
              b2.setTextColor(Color.parseColor("#0000ff"));
              b3.setTextColor(Color.parseColor("#0000ff"));
              k=j=0;
              tv1.setText("");
              i=1;

          }



     }
 });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j==0){
                    b2.setBackgroundColor(Color.parseColor("#99ff0000"));
                    b1.setBackgroundColor(Color.parseColor("#ffffff"));
                    b3.setBackgroundColor(Color.parseColor("#ffffff"));
                    b2.setTextColor(Color.parseColor("#ffffff"));
                    b1.setTextColor(Color.parseColor("#0000ff"));
                    b3.setTextColor(Color.parseColor("#0000ff"));
                    tv1.setText("You agree to pay 1.5X of your bill");
                    k=0;
                    i=0;
                    j=1;
                }



            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k==0){
                    b3.setBackgroundColor(Color.parseColor("#99ff0000"));
                    b1.setBackgroundColor(Color.parseColor("#ffffff"));
                    b2.setBackgroundColor(Color.parseColor("#ffffff"));
                    b3.setTextColor(Color.parseColor("#ffffff"));
                    b2.setTextColor(Color.parseColor("#0000ff"));
                    b1.setTextColor(Color.parseColor("#0000ff"));
                    i=j=0;
                    tv1.setText("You agree to pay 2X of your bill");
                    k=1;
                }



            }
        });

        return v;
    }

}
