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

public class FermentingTutorialFragment extends Fragment {
    TextView fermenting_instructions;
    Button toBottlingButton;
    Button nextButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View w =inflater.inflate(R.layout.fermenting_tutorial, container, false);
        toBottlingButton = (Button)w.findViewById(R.id.toBottlingButton);
        toBottlingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w)
            {
                Fragment fragment = new BottlingTutorialFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.FermentingTutorial, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return w;
    }




}
