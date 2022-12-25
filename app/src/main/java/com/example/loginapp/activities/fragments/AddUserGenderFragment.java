package com.example.loginapp.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.loginapp.R;

public class AddUserGenderFragment extends Fragment {

    private AddUserGenderFragmentListener mListener;

    //public boolean Gender = false;

    public AddUserGenderFragment(AddUserGenderFragmentListener listener) {
        this.mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mFragmentView= inflater.inflate(R.layout.fragment_add_user_gender, container, false);

        mFragmentView.findViewById(R.id.button_add_men).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onButtonGenderIsClicked(true);
                }
            }
        });

        mFragmentView.findViewById(R.id.button_add_women).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onButtonGenderIsClicked(false);
                }
            }
        });

       return (mFragmentView);

    }

    public interface AddUserGenderFragmentListener {
        void onButtonGenderIsClicked(boolean isMan);
    }

}