package com.techtutorpro.germangrade;



import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techtutorpro.germangrade.adscontrol.Admob;
import com.techtutorpro.germangrade.adscontrol.OnDismiss;


public class MastersFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public MastersFragment() {
        // Required empty public constructor
    }


    public static MastersFragment newInstance(String param1, String param2) {
        MastersFragment fragment = new MastersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_masters, container, false);
        EditText ac;
        EditText min;
        EditText max;
        Button mcalculate;
        TextView resulttv, performance;
        ImageView cancelbutton;


        LinearLayout resultLayout;

        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);

        resulttv=dialog.findViewById(R.id.resultTextView);
        performance=dialog.findViewById(R.id.performanceTextView);
        cancelbutton=dialog.findViewById(R.id.cancelbutton);





        Float minnum;
        Float maxnum;
        ac =view.findViewById(R.id.AchievedEt);
        min=view.findViewById(R.id.minEt);
        max=view.findViewById(R.id.maxEt);
        mcalculate=view.findViewById(R.id.masters_button);
        mcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    new Admob(new OnDismiss() {
                        @Override
                        public void onDismiss() {

                        }
                    }).ShowInt(getActivity());
                   // resultLayout.setVisibility(View.VISIBLE);

                    float acnumber=Float.parseFloat(ac.getText().toString());
                    float maxnumber=Float.parseFloat(max.getText().toString());
                    float minnumber=Float.parseFloat(min.getText().toString());

                    float result=1+((maxnumber-acnumber)/(maxnumber-minnumber))*3;

                    resulttv.setText(String.valueOf(String.format(" %.5f", result)));



                    if (1<=result && result<=1.5){
                        performance.setText(getString( R.string.verygood));
                    }else if( 1.5<result && result<=2.5){
                        performance.setText(getString( R.string.good));
                    }else if (2.5<result && result<=3.5){
                        performance.setText(getString( R.string.satisfactory));
                    }else if (3.5<result && result<=4.0){
                        performance.setText(getString(R.string.sufficient));
                    }else {
                        performance.setText(getString(R.string.failed));
                    }

                    dialog.show();


                    cancelbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }

                    });



                }catch (Exception exception){
                    Toast.makeText(getActivity(),"Please fillup all data",Toast.LENGTH_SHORT).show();
                }



            }
        });





        return  view;
    }
}