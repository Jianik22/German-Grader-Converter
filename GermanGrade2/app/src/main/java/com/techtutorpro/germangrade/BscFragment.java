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


public class BscFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public static BscFragment newInstance(String param1, String param2) {
        BscFragment fragment = new BscFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BscFragment() {
        // Required empty public constructor
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
        View view= inflater.inflate(R.layout.fragment_bsc, container, false);

        EditText eachieved, emax, emin, bachieved,bmax,bmin;
        Button result;
        LinearLayout result_layout;
        result_layout=view.findViewById(R.id.bsresultlayout);
        eachieved=view.findViewById(R.id.ecAchievedEt);
        emax=view.findViewById(R.id.ecmaxEt);
        emin=view.findViewById(R.id.ecminEt);
        bachieved=view.findViewById(R.id.bcAchievedEt);
        bmax=view.findViewById(R.id.bcmaxEt);
        bmin=view.findViewById(R.id.bcminEt);



        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog);

        TextView resulttv, performance;
        resulttv=dialog.findViewById(R.id.resultTextView);
        performance=dialog.findViewById(R.id.performanceTextView);

        ImageView cancel;
        cancel=dialog.findViewById(R.id.cancelbutton);

        result=view.findViewById(R.id.bscCalculate);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    new Admob(new OnDismiss() {
                        @Override
                        public void onDismiss() {

                        }
                    }).ShowInt(getActivity());

                    float eachievednumber=Float.parseFloat(eachieved.getText().toString());
                    float emaxnumber=Float.parseFloat(emax.getText().toString());
                    float eminnumber=Float.parseFloat(emin.getText().toString());
                    float etotoalresult=1+((emaxnumber-eachievednumber)/(emaxnumber-eminnumber))*3;

                    float bachievednumber=Float.parseFloat(bachieved.getText().toString());
                    float bmaxnumber=Float.parseFloat(bmax.getText().toString());
                    float bminnumber=Float.parseFloat(bmin.getText().toString());

                    float btotalresult=1+((bmaxnumber-bachievednumber)/(bmaxnumber-bminnumber))*3;

                    float result=(etotoalresult+btotalresult)/2;

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

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }catch (Exception exception){
                    Toast.makeText(getActivity(),"Please Fill Up all the Data", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}