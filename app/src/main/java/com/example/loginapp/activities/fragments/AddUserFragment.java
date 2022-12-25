package com.example.loginapp.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.models.UserModel;
import com.example.loginapp.tools.Helpers;

import java.util.ArrayList;


public class AddUserFragment extends Fragment {

   private AddUserFragmentListener mListener;

    public AddUserFragment(AddUserFragmentListener mListener ){
     this.mListener = mListener;
    }

    private View mFragmentView;
    private CheckBox checkboxAdmin;
     boolean passwordVisibility;

    //Boolean admin; //null
    //boolean isAdmin; //false


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.mFragmentView = inflater.inflate(R.layout.fragment_add_user, container, false);
        checkboxAdmin = this.mFragmentView.findViewById(R.id.checkbox_addUser_admin);
        EditText passwordAdmin = this.mFragmentView.findViewById(R.id.EditText_addUser_AdminPassword);



      /* passwordAdmin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=passwordAdmin.getRight()- passwordAdmin.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=passwordAdmin.getSelectionEnd();
                        if(passwordVisibility){
                            passwordAdmin.setCompoundDrawablesRelative(null,null,R.drawable.ic_baseline_visibility_off_24,null);
                            passwordAdmin.setTransformationMethod(new PasswordTransformationMethod());
                            passwordVisibility=false;
                        }else{
                            passwordAdmin.setCompoundDrawablesRelative(null,null,null,null);
                            passwordAdmin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility=true;
                        }
                        passwordAdmin.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });*/



        checkboxAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkboxAdmin.isChecked()) {
                    passwordAdmin.setVisibility(View.VISIBLE);
                }
                else {
                    passwordAdmin.setVisibility(View.GONE);
                }
                /*
                if(admin){
                    passwordAdmin.setVisibility(View.VISIBLE);
                    changeValue();
                }
                else{
                    passwordAdmin.setVisibility(View.GONE);
                    changeValue();
                }
                */
            }
        });

        this.mFragmentView.findViewById(R.id.button_addUser_validateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFormIsValidate();
            }
        });


        return this.mFragmentView;



    }
    public void checkFormIsValidate() {
        ArrayList<UserModel> arrayUserModel;
        arrayUserModel =  DataManager.getInstance().getListArrayUserModel();

        EditText editTextEmail = this.mFragmentView.findViewById(R.id.editText_addUser_email);
        EditText editTextPassword = this.mFragmentView.findViewById(R.id.EditText_addUser_AdminPassword);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean isAdmin = this.checkboxAdmin.isChecked();

        if (TextUtils.isEmpty(email)  || TextUtils.isEmpty(password) )
        {
            Toast.makeText(this.getContext(),"All fields are required",Toast.LENGTH_SHORT).show();
        }
        else if(!Helpers.isValidEmail(email)){
            Toast.makeText(this.getContext(),"Email in not valid ",Toast.LENGTH_SHORT).show();
        }
        else if (DataManager.getInstance().checkUserExistence(email)){
            Toast.makeText(this.getContext(),"Email exist ",Toast.LENGTH_SHORT).show();
        }
        else {
            if (this.mListener != null) {
                this.mListener.onButtonAddUserIsClicked(email,password,isAdmin);
            }
        }
    }

    /*
    public void changeValue(){
        admin=!admin;
    }
    */

    public interface AddUserFragmentListener{
        void onButtonAddUserIsClicked(String email,String password,boolean admin);

    }



}