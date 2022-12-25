package com.example.loginapp.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.tools.Helpers;


public class AddUserPersonalInfoFragment extends Fragment {
    private AddUserPersonalInfoFragmentListener mListener;

    public AddUserPersonalInfoFragment(AddUserPersonalInfoFragmentListener listener){
        this.mListener = listener;
    }


    private View mFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        this.mFragmentView = inflater.inflate(R.layout.fragment_add_user_personal_info, container, false);

        this.mFragmentView.findViewById(R.id.button_add_addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFormIsValidate();
            }
        });

        return this.mFragmentView;
    }



    public void checkFormIsValidate() {

        EditText editTextLastName = this.mFragmentView.findViewById(R.id.editText_add_lastName);
        EditText editTextFirstName = this.mFragmentView.findViewById(R.id.editText_add_firstName);
        EditText editTextDateOfBirth =this.mFragmentView.findViewById(R.id.editText_addUserPersonalInfo_DateOfBirth);
        EditText editTextAddress = this.mFragmentView.findViewById(R.id.editText_addUserPersonalInfo_address);
        EditText editTextPostalCode = this.mFragmentView.findViewById(R.id.editText_addUserPersonalInfo_postalCode);
        EditText editTextTown = this.mFragmentView.findViewById(R.id.editText_addUserPersonalInfo_town);

        EditText editTextCountry = this.mFragmentView.findViewById(R.id.editText_addUserPersonalInfo_country);

        String lastName = editTextLastName.getText().toString();
        String firstName = editTextFirstName.getText().toString();
        String dateOfBirth = editTextDateOfBirth.getText().toString();
        String address = editTextAddress.getText().toString();
        String postalCode = editTextPostalCode.getText().toString();

        String town = editTextTown.getText().toString();
        String country = editTextCountry.getText().toString();

        if (TextUtils.isEmpty(lastName) || TextUtils.isEmpty(firstName) )
        {
            Toast.makeText(this.getContext(),"All fields are required",Toast.LENGTH_SHORT).show();
        }
        else {
            if (this.mListener != null){
                this.mListener.onButtonNextIsClicked(lastName,firstName, dateOfBirth, address, postalCode,town, country);

            }
        }
    }

    public interface AddUserPersonalInfoFragmentListener{
        void onButtonNextIsClicked(String lastName,String firstName,String dateOfBirth,String address,
        String postalCode ,String town,String country
        );


}


}


