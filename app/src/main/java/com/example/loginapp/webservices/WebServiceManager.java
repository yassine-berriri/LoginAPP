package com.example.loginapp.webservices;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginapp.activities.MainActivity;
import com.example.loginapp.activities.UserActivity;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.datas.SaveDataManager;
import com.example.loginapp.webservices.entities.UserWeb;
import com.example.loginapp.webservices.requests.UserRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebServiceManager {

    public WebServiceManager (Context context) {
        this.mContext = context;
    }
    private ArrayList<UserWeb> arrayUserWeb=DataManager.getInstance().getListArrayUserWeb();
    private Context mContext;

    public void sendJsonPostRequest(UserRequest userRequest, WebServiceManagerListener listener) {

        try {

            // Make new json object and put params in it
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("username", userRequest.getUsername());
            jsonParams.put("password",userRequest.getPassword());
            jsonParams.put("organizationId",5);

            // Building a request
            String url = "https://portail.recette.cykleo.fr/pu/auth";
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonParams,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String access_token = null;
                            try {
                                access_token = response.getString("access_token");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            listener.onResponse(false, access_token);

                        }
                    },

                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                            String statusCode = String.valueOf(error.networkResponse.statusCode);
                            listener.onResponse(true, statusCode);
                            Log.d("Error.Response", statusCode);
                            // Handle the error
                        }
                    });

            /*

              For the sake of the example I've called newRequestQueue(getApplicationContext()) here
              but the recommended way is to create a singleton that will handle this.

              Read more at : https://developer.android.com/training/volley/requestqueue

              Category -> Use a singleton pattern

            */
            Volley.newRequestQueue(this.mContext).add(request);
        }
        catch(JSONException ex) {
            String exception = ex.toString();
            listener.onResponse(true, exception);

            // Catch if something went wrong with the params
        }

    }

    public void getJsonGetRequest(WebServiceManagerListener listener){

        String urlReadUser = "https://portail.recette.cykleo.fr/pu/auth";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlReadUser,null/*MainActivity.json*/, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (DataManager.getInstance().checkUserWebExistence(response.optString("firstname"))){

                }
                else{
                    UserWeb user = new UserWeb(
                            response.optString("sub"),
                            response.optString("costumer"),
                            response.optString("firstname"),
                            response.optString("lastname"),
                            response.optString("vlsService"),
                            response.optString("vldService"),
                            response.optString("exp")
                    ) ;


                    listener.onResponse(false, user);
                }

                // Log.d("Your Array Re++++++", response.toString());

                //JSONArray contentArray = response.getJSONArray("Response body");
                /*JSONObject user = response;
                UserWeb userWeb = new UserWeb();
                userWeb.setLastname(user.optString("lastname"));
                userWeb.setFirstname(user.optString("firstname"));


                addUserWeb(userWeb);*/
               // Log.e("TEST", "C");

                   /*for (int i = 0; i < response.length(); i++){
                        JSONObject user = response.getJSONObject(i);
                       UserModel userModel = new UserModel();
                        userModel.setLastName(user.optString("lastname"));
                        addUserModel(userModel);
                    }*/

            }
/*
            @Override
            public void onResponse(JSONObject response) {
                if (!response.equals(null)) {
                    Log.d("Your Array Response", response.toString());
                } else {
                    Log.d("Your Array Response", "Data Null");
                }
            }*/

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String statusCode = String.valueOf(volleyError.networkResponse.statusCode);
                listener.onResponse(true, statusCode);
                Log.d("Error.Response", statusCode);

                //loadingData.dismiss();
                   /* if (jsonObject.getInt(DataManager.json_Code) == 400) {
                        // onDialogErrorResponse();
                    }*/

            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer " +DataManager.getInstance().getToken());
                //params.put("access_token", "totot");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.mContext);
        requestQueue.add(request);

    }



}
