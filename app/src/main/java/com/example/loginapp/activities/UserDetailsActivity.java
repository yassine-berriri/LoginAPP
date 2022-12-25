package com.example.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.loginapp.R;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.models.UserModel;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity {

    public final static int CODE_USER_ACTIVITY = 456;
    UserModel selectedUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ArrayList<UserModel> arrayUserModel;
        arrayUserModel =  DataManager.getInstance().getListArrayUserModel();
        //int id = getIntent().getIntExtra("id",0);
        UserModel user = (UserModel) getIntent().getSerializableExtra("user");
       /* for (UserModel userModel : arrayUserModel){
            if (userModel.getId()==id) {
                selectedUser = userModel.clone();
                //selectedUser.setGender(userModel.getGender());
            }
        }*/

         selectedUser=user.clone();

        if(selectedUser == null)
        {
            finish();

            return;
        }
        else {

            RadioButton menRadioBtn = findViewById(R.id.RadioButton_userDetails_man);
            RadioButton womenRadioBtn = findViewById(R.id.RadioButton_userDetails_women);
           // RadioGroup radioGroup = findViewById(R.id.RadioGroup_userDetails_gender);
            if(selectedUser.getGender().equals("HOMME")){
                menRadioBtn.setChecked(true);
            }
            else{
                womenRadioBtn.setChecked(true);
            }

            menRadioBtn.setOnClickListener((view)->{
                selectedUser.setGender("HOMME");
            });
            womenRadioBtn.setOnClickListener((view)->{
                selectedUser.setGender("FEMME");
            });

            /* radioGroup.setOnClickListener((view) ->{
                int idSelected = radioGroup.getCheckedRadioButtonId();
                RadioButton genderSelected =findViewById(idSelected);
                Log.i(genderSelected.getText().toString(), "genderSelected.getText().toString()++++++: ");
                selectedUser.setGender(genderSelected.getText().toString());
            });*/


            EditText editTextFirstName = findViewById(R.id.editText_userDetail_firstName);
            EditText editTextLastName = findViewById(R.id.editText_userDetail_lastName);
            EditText editTextDateOfBirth = findViewById(R.id.editText_userDetail_DateOfBirth);
            EditText editTextAddress = findViewById(R.id.editText_userDetail_address);
            EditText editTextPostalCode = findViewById(R.id.editText_userDetail_postalCode);
            EditText editTextTown = findViewById(R.id.editText_userDetail_town);
            EditText editTextCountry = findViewById(R.id.editText_userDetail_country);


            editTextFirstName.setText(selectedUser.getFirstName());
            editTextLastName.setText(selectedUser.getLastName());
            editTextDateOfBirth.setText(selectedUser.getDateOfBirth());
            editTextAddress.setText(selectedUser.getAddress());
            editTextPostalCode.setText(selectedUser.getPostalCode());
            editTextTown.setText(selectedUser.getTown());
            editTextCountry.setText(selectedUser.getCountry());

            this.findViewById(R.id.button_userDetails_deleteBtn).setOnClickListener( (view) ->{
                //DataManager.getInstance().saveData(getApplicationContext());
                DataManager.getInstance().deleteUserModel(selectedUser);
                setResult(RESULT_OK);
                finish();
            });


            this.findViewById(R.id.button_userDetail_edit).setOnClickListener((view) ->{

                DataManager.getInstance().updateUserModel(selectedUser);
                //DataManager.getInstance().saveData(getApplicationContext());
                setResult(RESULT_OK);
                finish();

               /* UserModel user = new UserModel(
                        selectedUser.getId(),
                        editTextLastName.getText().toString(),
                        editTextFirstName.getText().toString(),
                        selectedUser.getEmail(),
                        editTextDateOfBirth.getText().toString(),
                        editTextAddress.getText().toString(),

                        selectedUser.getPassword(),
                        selectedUser.getGender(),
                        editTextPostalCode.getText().toString(),
                        editTextTown.getText().toString(),
                        editTextCountry.getText().toString(),
                        selectedUser.isAdmin()


                );*/
            });

            editTextDateOfBirth.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setDateOfBirth(editable.toString());
                }
            });
            /***********************/
            editTextFirstName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setFirstName(editable.toString());
                }
            });
             /****************************/
            editTextLastName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setLastName(editable.toString());
                }
            });
            /*****************************/
            editTextAddress.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setAddress(editable.toString());
                }
            });
            /**************************/
            editTextPostalCode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setPostalCode(editable.toString());
                }
            });
            /*************************/
            editTextTown.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setTown(editable.toString());
                }
            });
            /*****************************/
            editTextCountry.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    selectedUser.setCountry(editable.toString());
                }
            });






        }









    }


}