package com.example.loginapp;

import android.app.Application;


import com.example.loginapp.datas.DataManager;
import com.example.loginapp.datas.SaveDataManager;
import com.example.loginapp.models.UserModel;

import java.io.PrintWriter;
import java.util.ArrayList;

public class LoginAppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.initInstance(this.getApplicationContext());



       /* try ( PrintWriter writer = new PrintWriter( file ) ) {
            UserModel userModel = new UserModel();
            DataFileManager.writeObject(userModel, writer );
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
