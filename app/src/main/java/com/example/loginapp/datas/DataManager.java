package com.example.loginapp.datas;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginapp.activities.MainActivity;
import com.example.loginapp.models.UserModel;
import com.example.loginapp.webservices.entities.UserWeb;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private static DataManager singleInstance;
    private Context mContext;
    private String token;

    private DataManager(Context context) {
        this.mContext = context;
        this.arrayUserModel = SaveDataManager.getArrayUserModel(mContext);
        //this.arrayUserWeb = SaveDataManager.getArrayUserWeb(mContext);
    }

    public static DataManager getInstance() {
        return singleInstance;
    }

    public static void initInstance(Context context) {
        if (singleInstance == null) {
            singleInstance = new DataManager(context);
        }
    }

    //----------

    private ArrayList<UserModel> arrayUserModel;
    private ArrayList<UserWeb> arrayUserWeb;

    public  void saveData(Context c)
    {
        SaveDataManager.saveArrayUserModel(c,arrayUserModel);
    }

    public void setArrayUserModel(ArrayList<UserModel> arrayUserModel) {
        this.arrayUserModel = arrayUserModel;
        SaveDataManager.saveArrayUserModel(mContext, arrayUserModel);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<UserModel> getListArrayUserModel() {
        return this.arrayUserModel;
    }

    public ArrayList<UserWeb> getListArrayUserWeb() {
        //token();
        return this.arrayUserWeb;
    }

    public void addUserWeb(UserWeb newUserWeb){
        if(this.arrayUserWeb == null)
        {
            this.arrayUserWeb = new ArrayList<>();
        }

        this.arrayUserWeb.add(newUserWeb);
        SaveDataManager.saveArrayUserWeb(mContext, this.arrayUserWeb);
    }

    public void addUserModel(UserModel newUserModel) {

        if(this.arrayUserModel == null)
        {
            this.arrayUserModel = new ArrayList<>();
        }

        this.arrayUserModel.add(newUserModel);
        SaveDataManager.saveArrayUserWeb(mContext, this.arrayUserWeb);
    }

    public void deleteUserModel(UserModel deletedUserModel) {
        if (deletedUserModel != null) {
            int indexToDelete = -1;
            int currentIndex = 0;
            if (this.arrayUserModel != null && this.arrayUserModel.size() > 0) {
                for (UserModel userModel : this.arrayUserModel) {
                    if (userModel.getId() == deletedUserModel.getId()) {
                        indexToDelete = currentIndex;
                    }
                    currentIndex++;
                }
            }
            if (indexToDelete > -1) {
                this.arrayUserModel.remove(indexToDelete);
                SaveDataManager.saveArrayUserModel(mContext, this.arrayUserModel);
            }
        }

        //this.arrayUserModel.remove(deletedUserModel);
        //SaveDataManager.saveArrayUserModel(mContext, this.arrayUserModel);
    }

    public void updateUserModel(UserModel updatedUserModel) {

        if (updatedUserModel != null) {
            if (this.arrayUserModel != null &&this.arrayUserModel.size() > 0) {
                for (UserModel userModel : this.arrayUserModel) {
                    if (userModel.getId() == updatedUserModel.getId()) {
                        userModel.set(updatedUserModel);
                    }
                }
            }
        }
        /*
        if (updatedUserModel != null) {
            int indexToUpdate = -1;
            int currentIndex = 0;
            if (this.arrayUserModel != null && this.arrayUserModel.size() > 0) {
                for (UserModel userModel : this.arrayUserModel) {
                    if (userModel.getId() == updatedUserModel.getId()) {
                        indexToUpdate = currentIndex;
                    }
                    currentIndex++;
                }
            }
            if (indexToUpdate > -1) {
                this.arrayUserModel.set(indexToUpdate, updatedUserModel);
                SaveDataManager.saveArrayUserModel(mContext, this.arrayUserModel);
            }
        }
        */
    }

    public boolean checkUserExistence(String email) {
        if (this.arrayUserModel != null && this.arrayUserModel.size() > 0) {
            for (UserModel userModel : this.arrayUserModel) {
                if (userModel.getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
        /*
            int i = 0;
            while (i < this.arrayUserModel.size() && !arrayUserModel.get(i).getEmail().equals(email)) {
                i++;
            }
            if (i == this.arrayUserModel.size()) {
               return false;
            }
            else {
                return true;
            }
        }

         /*if (this.arrayUserModel.contains(email)) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    */
    }

    public boolean checkUserWebExistence(String firstname){
        if (this.arrayUserWeb != null && this.arrayUserWeb.size() > 0) {
            for (UserWeb userWeb : this.arrayUserWeb) {
                if (userWeb.getFirstname().equalsIgnoreCase(firstname)) {
                    return true;
                }
            }
        }
        return false;}
        /*
    }

    /*public void setData(JSONObject jo){
        if(arrayUserModel != null && arrayUserModel.size()>0 ){


        }
    }*/


   // String testUrl = "https://opentdb.com/api.php?amount=10";
  /*  String url = "https://portail.recette.cykleo.fr/pu/auth";
    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response) {
                    // display response

                    Log.d("response", response.toString());
                }
            },
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error.Response", error.toString());
                }
            }
    );*/








  // String urlReadUser = "https://portail.recette.cykleo.fr/pu/auth";
    public void token()  {
        Log.e("TEST", "B");
        //SharedPreferences usuario = getActivity().getSharedPreferences(DataManager.SharedPreferences, Context.MODE_PRIVATE);
        String urlReadUser = "https://portail.recette.cykleo.fr/pu/auth";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlReadUser,null/*MainActivity.json*/, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // Log.d("Your Array Re++++++", response.toString());

                    //JSONArray contentArray = response.getJSONArray("Response body");
                   JSONObject user = response;
                   UserWeb userWeb = new UserWeb();
                userWeb.setLastname(user.optString("lastname"));
                userWeb.setFirstname(user.optString("firstname"));


                addUserWeb(userWeb);
                Log.e("TEST", "C");

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
                try {
                   if(volleyError.networkResponse.data !=null){
                       String responseBody = new String(volleyError.networkResponse.data, "utf-8");
                       JSONObject jsonObject = new JSONObject(responseBody);
                       Log.d(jsonObject.toString(), "onErrorResponse9++++++++++++++: ");
                   }


                    //loadingData.dismiss();
                   /* if (jsonObject.getInt(DataManager.json_Code) == 400) {
                        // onDialogErrorResponse();
                    }*/

                } catch (JSONException e) {
                    //Handle a malformed json response
                    Log.d("Response", String.valueOf(e));
                } catch (UnsupportedEncodingException error) {

                    Log.d("Response", String.valueOf(error));
                }
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Authorization", "Bearer " +MainActivity.token);
                //params.put("access_token", "totot");
                return params;
            }
        };

        RequestQueue  requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(request);

    }




}
