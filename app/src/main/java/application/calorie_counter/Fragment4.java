package application.calorie_counter;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment4 extends Fragment implements View.OnClickListener{

    Button b1, b2, b3;
    String age, weight, height;
    TextView tv1,tv2, tv3, tv4, tv5, tv6;
    FragmentsCommunicator fc;
    double bmi, water;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f4_layout,container,false);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Questv1-Bold.otf");

        MainActivity activity = (MainActivity) getActivity();
        age = activity.getMyData("age");
        weight = activity.getMyData("weight");
        height = activity.getMyData("height");
        b1 = (Button)view.findViewById(R.id.okBtn);
        b1.setOnClickListener(this);
        b2 = (Button)view.findViewById(R.id.waterBtn);
        b2.setOnClickListener(this);
        b3 = (Button)view.findViewById(R.id.caloriesBtn);
        b3.setOnClickListener(this);

        tv1 = (TextView)view.findViewById(R.id.bmiTV1);
        tv2 = (TextView)view.findViewById(R.id.bmiTV2);
        tv3 = (TextView)view.findViewById(R.id.genderTV);
        tv4 = (TextView)view.findViewById(R.id.ageTV);
        tv5 = (TextView)view.findViewById(R.id.weightTV);
        tv6 = (TextView)view.findViewById(R.id.waterTV);


        tv3.setText(activity.getMyData("gender"));
        tv3.setTypeface(tf);
        tv4.setText(age +" "+ getString(R.string.years));
        tv4.setTypeface(tf);
        tv5.setText(weight +" " + getString(R.string.kg));
        tv5.setTypeface(tf);


        //BMI Calculations
        bmi = (Integer.valueOf(weight) * 10000) / (Integer.valueOf(height) * Integer.valueOf(height));
        tv1.setText("Your BMI: " + Math.round(bmi * 10d) / 10d);
        tv1.setTypeface(tf);

        if (bmi >= 30){
            tv2.setText("Obesity");
        }else if ((bmi >= 25) && (bmi < 30)){
            tv2.setText("Overweight");
        }else if (bmi <= 18){
            tv2.setText("Under Weight");
        }else if((bmi > 18) && (bmi < 25) ){
            tv2.setText("Normal");
        }




        //Calculate Body water
        if (Integer.valueOf(age) <= 30){
            water = (Integer.valueOf(weight) * 42 * 2.95) / (28.3 * 100);

        }else if (Integer.valueOf(age) > 30 && Integer.valueOf(age) <= 35){
            water = (Integer.valueOf(weight) * 37 * 2.95) / (28.3 * 100);

        }else if (Integer.valueOf(age) > 35){
            water = (Integer.valueOf(weight) * 32 * 2.95) / (28.3 * 100);
        }

        tv6.setText("You need: " + (Math.round(water * 10d) / 10d) + " L/day");


        return view;
    }

    @Override
    public void onClick(View view) {
        fc = (FragmentsCommunicator) getActivity();
        if(view.getId() == R.id.okBtn){
            fc.respond("metabolic", 40);
            fc.respond("ok",0);
        } else if(view.getId() == R.id.waterBtn){
            Intent RemindersIntent = new Intent(getActivity(), RemindersActivity.class);
            startActivity(RemindersIntent);
        }else if(view.getId() == R.id.caloriesBtn) {
            Intent apiIntent = new Intent(getActivity(), ApiActivity.class);
            startActivity(apiIntent);
        }
    }
}
