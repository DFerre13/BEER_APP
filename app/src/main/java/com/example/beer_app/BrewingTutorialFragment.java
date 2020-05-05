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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BrewingTutorialFragment extends Fragment {
    TextView brewing_instructions;
    Button End;
    DatabaseReference ref;
    int step_num = 0;
    int instruction = 0;
    String step;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.brewing_tutorial_fragment, container, false);

        return v;

    }
    /*written by Devin*/
    //
    @Override
    public void onStart() {
        super.onStart();

        brewing_instructions = (TextView) getView().findViewById(R.id.brew_tutorial);

        End =(Button) getView().findViewById(R.id.FinishButton);

        End.setOnClickListener(new View.OnClickListener() {
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
                                step = dataSnapshot.child("Step_4").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 5:
                                step = dataSnapshot.child("Step_5").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 6:
                                step = dataSnapshot.child("Step_6").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 7:
                                step = dataSnapshot.child("Step_7").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 8:
                                step = dataSnapshot.child("Step_8").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 9:
                                step = dataSnapshot.child("Step_9").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 10:
                                step = dataSnapshot.child("Step_10").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 11:
                                step = dataSnapshot.child("Step_11").getValue().toString();
                                brewing_instructions.setText(step);
                                break;
                            case 12:
                                brewing_instructions.setText("Brewing End!");
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
        //display brewing instructions

    }
}



