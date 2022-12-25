package com.example.loginapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginapp.R;
import com.example.loginapp.adapters.UserModelAdapter;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.models.UserModel;
import com.example.loginapp.webservices.WebServiceManager;
import com.example.loginapp.webservices.WebServiceManagerListener;
import com.example.loginapp.webservices.entities.UserWeb;
import com.example.loginapp.webservices.requests.UserRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
   private UserModelAdapter userModelAdapter;
   public static String token;
   public static JSONObject json;
    ProgressBar loadingPB;
    TextView username;
    TextView password;
     TextView responseTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_main);
        ArrayList<UserModel> arrayUserModel;
        arrayUserModel =  DataManager.getInstance().getListArrayUserModel();

        username = (TextView) this.findViewById(R.id.username);
        password  = (TextView) this.findViewById(R.id.password);
        loadingPB = findViewById(R.id.idLoadingPB);
        Button login = (Button) this.findViewById(R.id.login);
        responseTV = findViewById(R.id.idTVResponse);
        //admin and admin
        login.setOnClickListener(view -> {

            //int i = 0;
          //  userModelAdapter = new UserModelAdapter(MainActivity.this);
            if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                Toast.makeText(MainActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
            }
            else {

               /* UserRequest userRequest = new UserRequest("giovanni@nfcom.com","Giovanni@123");
                loadingPB.setVisibility(View.VISIBLE);
                new WebServiceManager(this).sendJsonPostRequest(userRequest, new WebServiceManagerListener() {
                    @Override
                    public void onResponse(boolean hasError, Serializable serializable) {
                        runOnUiThread( () -> { loadingPB.setVisibility(View.GONE); });
                        if (hasError) {
                            String error = (String) serializable;
                            runOnUiThread( () -> {
                                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                            });
                        }
                        else {
                            String access_token = (String) serializable;
                            Log.d(access_token, "tokenNow: ");
                            DataManager.getInstance().setToken(access_token);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // on below line we are displaying a success toast message.
                                    Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    }
                });*/


                if(arrayUserModel !=null && arrayUserModel.size() !=0 ) {
                    boolean adminExist=false;
                    for (UserModel userModel : arrayUserModel) {
                        if(userModel != null){
                            if (userModel.getEmail().equals(username.getText().toString())
                                    && userModel.getPassword().equals(password.getText().toString())
                                    || (username.getText().toString().equals("admin")
                                    && password.getText().toString().equals("admin"))) {
                                adminExist = true;

                                Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                                //  UserWeb userWeb = new UserWeb("giovanni@nfcom.com","Giovanni@123",5);
                                // sendJsonPostRequest(userWeb);

                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            startActivity(intent);
                            finish();



                            }

                        }
                        if(adminExist == false){
                             Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                        }

                    }

                }

                else{
                    if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                        Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"LOGIN FAILED !",Toast.LENGTH_SHORT).show();
                    }
                }

            }



                 /*while (i < arrayUserModel.size() &&
                         !((userModelAdapter.getItem(i).getPassword().equals(password.getText().toString())
                                 && userModelAdapter.getItem(i).getFirstName().equals(username.getText().toString()))
                                 || (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")))
                 ) {
                     i++;
                 }
                 if (i == arrayUserModel.size()) {
                     Toast.makeText(MainActivity.this, "LOGIN FAILED !", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(MainActivity.this, UserActivity.class);
                     startActivity(intent);
                     finish();
                 }*/

                /* for( i=0;i<arrayUserModel.size();i++){



                     if((userModelAdapter.getItem(i).getPassword().equals(password.getText().toString())
                             && userModelAdapter.getItem(i).getFirstName().equals(username.getText().toString()))
                             || (username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
                     ){
                         Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(MainActivity.this, UserActivity.class);
                         startActivity(intent);
                         finish();
                     }
                     else{
                         Toast.makeText(MainActivity.this,"LOGIN FAILED !",Toast.LENGTH_SHORT).show();
                     }
                 }
             }*/




           /* else {
                Toast.makeText(MainActivity.this,"LOGIN FAILED !",Toast.LENGTH_SHORT).show();
            }*/
        });
        /*this.findViewById(R.id.button_main_chronos).setOnClickListener((view)->{
            Intent intent = new Intent(MainActivity.this, StopwatchActivity.class);
            startActivity(intent);

        });*/
    }


    /*private void postDataUsingVolley(String nom, String password2,Integer organizationId) throws JSONException {
        // url to post our data
        String url = "https://portail.recette.cykleo.fr/pu/auth";
        loadingPB.setVisibility(View.VISIBLE);

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty
                loadingPB.setVisibility(View.GONE);
                username.setText("");
                password.setText("");
                // on below line we are displaying a success toast message.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                try {
                    // on below line we are parsing the response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    String token = respObj.getString("access_token");


                    // on below line we are setting this string s to our text view.
                    responseTV.setText("Token : " + token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // method to handle errors.
                    Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    Log.d("Error.Response", error.toString());
                }
            }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                String organization = organizationId.toString();
                params.put("username", nom);
                params.put("password", password2);
                params.put("organizationId",organization);

                // at last we are
                // returning our params.
                return params;
            }
        };
        queue.add(request);

    }*/



    @Override
    public void onBackPressed() {
        return;
    }
}
