package com.example.beer_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;

import static android.os.CountDownTimer.*;


public class BrewingTutorialFragment extends Fragment {
    TextView brewing_instructions;
    Button End;
    Button toFermentingButton;
    DatabaseReference ref;

    int step_num = 0;
    int instruction = 0;
    String step;
    int counter;

    long countDown1= 1800000; //30 min
    long countDown2 = 2400000;//40 min
    long countDown3 = 2700000;//45min
    long countDown4 = 3600000;//60 min



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.brewing_tutorial_fragment, container, false);

        toFermentingButton =(Button)v.findViewById(R.id.toFermentingTutorial);//removable

        toFermentingButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

                Fragment fragment = new FermentingTutorialFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.BrewTutorial, fragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;

    }
    /*written by Devin*/
    //
    @Override
    public void onStart()
    {
        super.onStart();

        brewing_instructions = (TextView) getView().findViewById(R.id.brew_tutorial);
        final TextView countTime = getView().findViewById(R.id.timerText);
        final TextView countTime2 = getView().findViewById(R.id.timerText2);
        final TextView countTime3 = getView().findViewById(R.id.timerText3);
        final TextView countTime4 = getView().findViewById(R.id.timerText4);
        End =(Button) getView().findViewById(R.id.FinishButton);
                //end of fermenting button
        End.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("instructions").child("brewing");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        switch (step_num) {
                            case 0:
                                step = dataSnapshot.child("Step_0").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 1:
                                step = dataSnapshot.child("Step_1").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 2:
                                step = dataSnapshot.child("Step_2").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 3:
                                step = dataSnapshot.child("Step_3").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 4:
                                //starts a timer for step 3 for steeping grains
                                //timer1   30 minutes

                                    new CountDownTimer (countDown1, 1000)
                                    {

                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            countTime.setText(String.valueOf(counter));
                                            countDown1 = millisUntilFinished;
                                            updateTime();
                                            counter--;
                                        }

                                        @Override
                                        public void onFinish() {
                                        countTime.setText(" ");
                                        }

                                        public void updateTime()
                                        {
                                            int minutes = (int) (countDown1 / 60000);
                                            int seconds = (int) (countDown1 % 60000 / 1000);
                                            String timeLeft;
                                            timeLeft= "Steep Grains:"+minutes;
                                            timeLeft += ":";
                                            if(seconds<10) timeLeft +="0";
                                            timeLeft+= ""+seconds;
                                            countTime.setText(timeLeft);
                                        }
                                    }.start();


                                break;
                            case 5:
                                step = dataSnapshot.child("Step_4").getValue().toString();
                                brewing_instructions.setText(step);
                                countTime.setText(" ");
                                break;
                            case 6:
                                step = dataSnapshot.child("Step_5").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 7:
                                step = dataSnapshot.child("Step_6").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 8:
                                //starts a timer for step 6 for the main boil
                                //timer2   60 minutes

                                new CountDownTimer (countDown2, 1000)
                                {

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        countTime2.setText(String.valueOf(counter));
                                        countDown2 = millisUntilFinished;
                                        updateTime();
                                        counter--;
                                    }

                                    @Override
                                    public void onFinish() {
                                        countTime2.setText("Finished!");
                                    }

                                    public void updateTime()
                                    {
                                        int minutes = (int) (countDown2 / 60000);
                                        int seconds = (int) (countDown2 % 60000 / 1000);
                                        String timeLeft;
                                        timeLeft= "Irish Moss: "+minutes;
                                        timeLeft += ":";
                                        if(seconds<10) timeLeft +="0";
                                        timeLeft+= ""+seconds;
                                        countTime2.setText(timeLeft);
                                    }
                                }.start();

                                //starts a timer for step 6
                                //timer2   40 minutes for Irish Moss

                                new CountDownTimer (countDown3, 1000)
                                {

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        countTime3.setText(String.valueOf(counter));
                                        countDown3 = millisUntilFinished;
                                        updateTime();
                                        counter--;
                                    }

                                    @Override
                                    public void onFinish() {
                                        countTime3.setText("Finished!");
                                    }

                                    public void updateTime()
                                    {
                                        int minutes = (int) (countDown3 / 60000);
                                        int seconds = (int) (countDown3 % 60000 / 1000);
                                        String timeLeft;
                                        timeLeft= "Secondary Hops: "+minutes;
                                        timeLeft += ":";
                                        if(seconds<10) timeLeft +="0";
                                        timeLeft+= ""+seconds;
                                        countTime3.setText(timeLeft);
                                    }
                                }.start();
                                //third timer fo second hop infusion

                                new CountDownTimer (countDown4, 1000)
                                {

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        countTime4.setText(String.valueOf(counter));
                                        countDown4 = millisUntilFinished;
                                        updateTime();
                                        counter--;
                                    }

                                    @Override
                                    public void onFinish() {
                                        countTime4.setText("Finished!");
                                    }

                                    public void updateTime()
                                    {
                                        int minutes = (int) (countDown4 / 60000);
                                        int seconds = (int) (countDown4 % 60000 / 1000);
                                        String timeLeft;
                                        timeLeft= "Main Boil "+minutes;
                                        timeLeft += ":";
                                        if(seconds<10) timeLeft +="0";
                                        timeLeft+= ""+seconds;
                                        countTime4.setText(timeLeft);
                                    }
                                }.start();

                                break;

                            case 9:
                                countTime2.setText(null);
                                countTime3.setText(" ");
                                countTime4.setText(" ");

                                step = dataSnapshot.child("Step_7").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 10:
                                step = dataSnapshot.child("Step_8").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 11:
                                step = dataSnapshot.child("Step_9").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 12:
                                step = dataSnapshot.child("Step_10").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 13:
                                step = dataSnapshot.child("Step_11").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 14:
                                brewing_instructions.setText("Brewing End!");
                                break;
                            default:
                        }
                        step_num = step_num + 1;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                    }
                });

            }
        });
        //display brewing instructions

    }
}



