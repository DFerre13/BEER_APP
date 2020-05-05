package com.example.beer_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
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

public class FermentingTutorialFragment extends Fragment {

    TextView fermenting_instructions;
    Button toBottlingButton;
    Button nextButton;
    DatabaseReference ref;

    int step_num;
    String step;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v =inflater.inflate(R.layout.fermenting_tutorial, container, false);

        toBottlingButton = (Button)v.findViewById(R.id.toBottlingButton);
        toBottlingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = new BottlingTutorialFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.FermentingTutorial, fragment);
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

        fermenting_instructions = (TextView) getView().findViewById(R.id.fermentingText);
        nextButton =(Button) getView().findViewById(R.id.Next);

        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("instructions").child("fermentation");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        switch (step_num) {
                            case 0:
                                step = dataSnapshot.child("Step_1").getValue().toString();
                                fermenting_instructions.setText(step);
                                break;
                            case 1:
                                step = dataSnapshot.child("Step_2").getValue().toString();
                                fermenting_instructions.setText(step);
                                break;
                            case 2:
                                step = dataSnapshot.child("Step_3").getValue().toString();
                                fermenting_instructions.setText(step);
                                break;
                            case 3:
                                fermenting_instructions.setText("Fermenting End!");
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
