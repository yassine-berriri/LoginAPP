package com.example.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.activities.fragments.AddUserFragment;
import com.example.loginapp.activities.fragments.AddUserGenderFragment;
import com.example.loginapp.activities.fragments.AddUserPersonalInfoFragment;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.datas.SaveDataManager;
import com.example.loginapp.models.UserModel;
import com.example.loginapp.tools.Helpers;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements AddUserGenderFragment.AddUserGenderFragmentListener
,AddUserPersonalInfoFragment.AddUserPersonalInfoFragmentListener, AddUserFragment.AddUserFragmentListener
{

    private  UserModel userModel;

    public final static int CODE_ADD_ACTIVITY = 123;


     //DBHelper DB;
    //Button addWomenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);




        this.userModel = new UserModel();

        AddUserGenderFragment AddUserGender = new AddUserGenderFragment(AddActivity.this);
        replaceFragment(AddUserGender);



      //  addWomenBtn=findViewById(R.id.button_add_women);
        /*this.findViewById(R.id.button_add_men).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              replaceFragment(new AddUserPersonalInfoFragment());
            }
        });*/
        //Button buttonAdd = this.findViewById(R.id.button_add_addButton);
        //DB = new DBHelper(this);
       /* this.findViewById(R.id.button_add_addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFormIsValidate();
            }
        });*/
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       // fragmentTransaction.add(R.id.frameLayout_add,fragment);
        fragmentTransaction.replace(R.id.frameLayout_add,fragment);
       // String backStateName = fragment.getClass().getName();
        /*boolean fragmentPopped = fragmentManager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout_add, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }*/
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

   /* public void checkFormIsValidate() {
        EditText editTextLastName = this.findViewById(R.id.editText_add_lastName) ;
        EditText editTextFirstName = this.findViewById(R.id.editText_add_firstName) ;
        EditText editTextEmail = this.findViewById(R.id.editText_add_email) ;

        String lastName = editTextLastName.getText().toString();
        String firstName = editTextFirstName.getText().toString();
        String email = editTextEmail.getText().toString();

        if (TextUtils.isEmpty(lastName) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(email) )
        {
            Toast.makeText(AddActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
        }
        else if (!Helpers.isValidEmail(email)) {
            Toast.makeText(AddActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
        }
        else {
            UserModel user = new UserModel(lastName, firstName, email) ;
            DataManager.getInstance().addUserModel(user);
            setResult(RESULT_OK);
            finish();
        }
    }*/

    @Override
    public void onBackPressed() {
        if (this.getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public void onButtonGenderIsClicked(boolean isMan) {
        //Bundle bundle =new Bundle();
        if (isMan) {
            this.userModel.setGender("HOMME");
            /*
            bundle.putString("Gender","homme");
            AddUserPersonalInfoFragment f2 = new AddUserPersonalInfoFragment(AddActivity.this);
            f2.setArguments(bundle);
            */
        }
        else {
            this.userModel.setGender("FEMME");

            /*
            bundle.putString("Gender","femme");
            AddUserPersonalInfoFragment f2 = new AddUserPersonalInfoFragment(AddActivity.this);
            f2.setArguments(bundle);
            */

        }
        this.replaceFragment(new AddUserPersonalInfoFragment(AddActivity.this));

    }

    @Override
    public void onButtonNextIsClicked(String lastName,String firstName,String dateOfBirth,String address
           ,String postalCode ,String town,String country) {
        /*Bundle bundle =new Bundle();
        bundle.putString("lastName",lastName);
        AddUserFragment f1 = new AddUserFragment();
        f1.setArguments(bundle);*/
        this.userModel.setLastName(lastName);
        this.userModel.setFirstName(firstName);
        this.userModel.setDateOfBirth(dateOfBirth);
        this.userModel.setAddress(address);
        this.userModel.setPostalCode(postalCode);
        this.userModel.setTown(town);
        this.userModel.setCountry(country);

        this.replaceFragment(new AddUserFragment(AddActivity.this));



    }

    public void onButtonAddUserIsClicked(String email,String password,boolean isAdmin){
        ArrayList<UserModel> arrayUserModel;
        arrayUserModel =  DataManager.getInstance().getListArrayUserModel();
        this.userModel.setEmail(email);
        this.userModel.setPassword(password);
        this.userModel.setAdmin(isAdmin);
        if(arrayUserModel != null)
        {
            this.userModel.setId(arrayUserModel.size());
        }else
        {
            this.userModel.setId(0);
        }



        UserModel user = new UserModel(

                   this.userModel.getId(),
                    this.userModel.getLastName(),
                    this.userModel.getFirstName(),
                    this.userModel.getEmail(),
                    this.userModel.getDateOfBirth(),
                    this.userModel.getAddress(),
                    this.userModel.getPassword(),
                    this.userModel.getGender(),
                    this.userModel.getPostalCode(),
                    this.userModel.getTown(),
                    this.userModel.getCountry(),
                    this.userModel.isAdmin()
            ) ;
            DataManager.getInstance().addUserModel(user);

            DataManager.getInstance().saveData(getApplicationContext());

            setResult(RESULT_OK);
            finish();





    }



                      /*

                      String nom = nom.getText().toString();
                      String prenom;
                      String email;

                      Boolean insert = DB.insertData(name,username,mail);
                      if(insert){
                          Toast.makeText(AddActivity.this,"Registred successfully",Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                          intent.putExtra("nom",nom.getText().toString());
                          intent.putExtra("prenom",prenom.getText().toString());
                          intent.putExtra("email",email.getText().toString());
                          startActivity(intent);
                      }
                    */




}