package com.example.priyamkumar.newcleanzo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Offers extends Fragment {


    public Offers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        final int[] i = {0};
        v=inflater.inflate(R.layout.fragment_offers2, container, false);
        final ImageView iv= (ImageView) v.findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.sofa);
       v.setOnTouchListener(new View.OnTouchListener(){public boolean onTouch(View v, MotionEvent event){

           if(event.getAction()==MotionEvent.ACTION_UP) {
              // Toast.makeText(getContext(), "done o", Toast.LENGTH_SHORT).show();

               Animation ani = AnimationUtils.loadAnimation(getContext(), R.anim.myanim);
               if (i[0] == 0) {iv.setAnimation(ani);
                   iv.setImageResource(R.drawable.coat);
                   i[0] = 1;
               } else if (i[0] == 1) {
                   iv.setAnimation(ani);
                   iv.setImageResource(R.drawable.sofa);
                   i[0] = 0;
               }

           }           return true;

       } });
        return v;
    }

}
