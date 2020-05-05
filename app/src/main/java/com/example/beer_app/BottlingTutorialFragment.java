package com.example.beer_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class BottlingTutorialFragment extends Fragment {

    TextView bottling_instructions;
    Button DoneButton;
    Button NextButton;
    DatabaseReference ref;
    int step_num = 0;
    String step;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v =inflater.inflate(R.layout.bottling_tutorial, container, false);
        DoneButton = (Button)v.findViewById(R.id.Done3);

        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.BottlingTutorial, fragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;

    }
    @Override
    public void onStart()
    {
        super.onStart();

        bottling_instructions = (TextView) getView().findViewById(R.id.BottlingText);

        NextButton =(Button) getView().findViewById(R.id.nextButton);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("instructions").child("bottling");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        switch (step_num) {
                            case 0:
                                step = dataSnapshot.child("Step_1").getValue().toString();
                                bottling_instructions.setText(step);
                                break;
                            case 1:
                                step = dataSnapshot.child("Step_2").getValue().toString();
                                bottling_instructions.setText(step);
                                break;
                            case 2:
                                step = dataSnapshot.child("Step_3").getValue().toString();
                                bottling_instructions.setText(step);
                                break;
                            case 3:
                                step = dataSnapshot.child("Step_4").getValue().toString();
                                bottling_instructions.setText(step);
                                break;
                            case 4:
                                step = dataSnapshot.child("Step_5").getValue().toString();
                                bottling_instructions.setText(step);
                                break;
                            case 5:
                                step = dataSnapshot.child("Step_6").getValue().toString();
                                bottling_instructions.setText(step);
                                break;
                            case 6:
                                bottling_instructions.setText("Bottling End!");
                                break;
                            default:
                        }
                        step_num = step_num + 1;
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }//end on start
    
    
    
    
}
