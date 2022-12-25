package com.example.loginapp.datas;

import android.content.Context;

import com.example.loginapp.models.UserModel;
import com.example.loginapp.webservices.entities.UserWeb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveDataManager {



    private static String fileUserName = "usersTest4.data";
    private static String fileUserWeb = "usersTest3.data";

    public static boolean saveArrayUserModel(Context context, ArrayList<UserModel> data) {
        try {
            FileOutputStream fos = context.openFileOutput(fileUserName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<UserModel> getArrayUserModel(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileUserName);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();
            if(readObject != null) {
                return (ArrayList<UserModel>) readObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static boolean saveArrayUserWeb(Context context, ArrayList<UserWeb> data) {
        try {
            FileOutputStream fos = context.openFileOutput(fileUserWeb, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<UserWeb> getArrayUserWeb(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileUserWeb);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();
            if(readObject != null) {
                return (ArrayList<UserWeb>) readObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
   /* public static void writeToFile(Context context , String fileName, String str){
        try{
            FileOutputStream fos =context.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write(str.getBytes(),0,str.length());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
