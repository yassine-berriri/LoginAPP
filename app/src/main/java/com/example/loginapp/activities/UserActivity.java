package com.example.loginapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.adapters.UserModelAdapter;
import com.example.loginapp.adapters.UserRecycleModelAdapter;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.models.UserModel;
import com.example.loginapp.webservices.WebServiceManager;
import com.example.loginapp.webservices.WebServiceManagerListener;
import com.example.loginapp.webservices.entities.UserWeb;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserActivity extends AppCompatActivity implements UserModelAdapter.UserModelAdapterListener,UserRecycleModelAdapter.UserRecycleModelAdapterListener {

    private UserModelAdapter userModelAdapter;
    private UserRecycleModelAdapter userRecycleModelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_user);

        RecyclerView recyclerView = this.findViewById(R.id.recyclerView_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        this.userRecycleModelAdapter = new UserRecycleModelAdapter(UserActivity.this, UserActivity.this);
        recyclerView.setAdapter(userRecycleModelAdapter);

      /*  new WebServiceManager(this).getJsonGetRequest(new WebServiceManagerListener() {
            @Override
            public void onResponse(boolean hasError, Serializable serializable) {
                if(hasError){
                    String error = (String) serializable;
                    runOnUiThread( () -> {
                        Toast.makeText(UserActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    });
                }else{
                   // Log.d(, "onResponseArrayLit: ");

                    DataManager.getInstance().addUserWeb((UserWeb) serializable);


                        runOnUiThread( () -> {

                            userRecycleModelAdapter.forceNotifyDataSetChanged();
                        });

                }
            }
        });*/


        /*ListView recycleViewUser = this.findViewById(R.id.listView_user);
        this.userModelAdapter = new UserModelAdapter(UserActivity.this,UserActivity.this);
        recycleViewUser.setAdapter(userModelAdapter);*/

        this.findViewById(R.id.button_user_add).setOnClickListener( (view) -> {
            Intent intent = new Intent(UserActivity.this, AddActivity.class);
            startActivityForResult(intent, AddActivity.CODE_ADD_ACTIVITY);
           // startActivity(intent);

        });

        this.findViewById(R.id.button_user_disconnect).setOnClickListener((view ->{
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }));
    }



// add it to the RequestQueue



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddActivity.CODE_ADD_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                //this.userModelAdapter.forceNotifyDataSetChanged();
                this.userRecycleModelAdapter.forceNotifyDataSetChanged();
                Toast.makeText(UserActivity.this,"RESULT_OK",Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(UserActivity.this,"RESULT_CANCELED",Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == UserDetailsActivity.CODE_USER_ACTIVITY){
            if(resultCode == RESULT_OK){
               // this.userModelAdapter.forceNotifyDataSetChanged();
                this.userRecycleModelAdapter.forceNotifyDataSetChanged();
                Toast.makeText(UserActivity.this,"RESULT_OK",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(UserActivity.this,"RESULT_CANCELED",Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onCardClicked(UserModel user) {
        Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
        intent.putExtra("user",user);
        startActivityForResult(intent,UserDetailsActivity.CODE_USER_ACTIVITY);
    }


    public void onCardClicked(UserWeb user) {
        Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
        intent.putExtra("user",user);
        startActivityForResult(intent,UserDetailsActivity.CODE_USER_ACTIVITY);
    }





    /*
    private ListView ls;
    private String nom,prenom,email;
    private HashMap<String,String> map;
    private Params p = new Params();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        MaterialButton addButton = (MaterialButton) findViewById(R.id.AddButton);
        ls=findViewById(R.id.list);

        Bundle extras=getIntent().getExtras();
        if(extras!= null){
         nom=extras.getString("nom");
            prenom=extras.getString("prenom");
           // email=extras.getString("email");
          map = new HashMap<String,String>();
          map.put("nom",nom);
          map.put("prenom",prenom);
          p.values.add(map);
        }
        SimpleAdapter adapter= new SimpleAdapter(UserActivity.this,p.values,R.layout.item,
             new String[]{"nom","prenom"},new int[]{R.id.nom,R.id.prenom}
                );
          ls.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
    */

}