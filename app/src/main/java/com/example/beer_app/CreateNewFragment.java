package com.example.beer_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.beer_app.R;

public class CreateNewFragment extends Fragment {
    Button startPreTutorial;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_create_new, container, false);

        startPreTutorial =(Button)v.findViewById(R.id.Start_Tutorial_Button);

        startPreTutorial.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PreTutorialFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.CreateNew, fragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    /* Need something like this to replace the fragment

        getFragmentManager().beginTransaction();

        transaction.replace(R.id.CreateNew, PreTutorialFragment);
        transaction.commit();
        */}
}
