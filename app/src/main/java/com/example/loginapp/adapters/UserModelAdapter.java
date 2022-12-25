package com.example.loginapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.loginapp.R;
import com.example.loginapp.activities.MainActivity;
import com.example.loginapp.activities.UserActivity;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.models.UserModel;

import java.util.ArrayList;

    public class UserModelAdapter extends BaseAdapter {
    private UserModelAdapterListener mListener;

    private Context mContext;
    private ArrayList<UserModel> arrayUserModel;


    public UserModelAdapter(Context context,UserModelAdapterListener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.arrayUserModel = DataManager.getInstance().getListArrayUserModel();
    }

    public void forceNotifyDataSetChanged() {
        this.arrayUserModel = DataManager.getInstance().getListArrayUserModel();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (this.arrayUserModel != null) {
            return this.arrayUserModel.size();
        }
        return 0;
    }

    @Override
    public UserModel getItem(int position) {
        if (this.arrayUserModel != null) {
            return this.arrayUserModel.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0; // not used
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell_user, viewGroup, false);
        }

        UserModel userModel = this.getItem(position);

        if (userModel != null) {
            TextView textViewLastName = view.findViewById(R.id.textView_cellUser_lastName);
            TextView textViewFirstName = view.findViewById(R.id.textView_cellUser_firstName);
            TextView textViewEmail = view.findViewById(R.id.textView_cellUser_email);
            TextView textViewDateOfBirth = view.findViewById(R.id.textView_cellUser_dateOfBirth);
            TextView textViewAddress = view.findViewById(R.id.textView_cellUser_address);
            TextView textViewPostalCode =  view.findViewById(R.id.textView_cellUser_PostalCode);
            TextView textViewTown = view.findViewById(R.id.textView_cellUser_town);
            TextView textViewCountry = view.findViewById(R.id.textView_cellUser_country);
            TextView textViewCivility = view.findViewById(R.id.textView_cellUser_civility);
            textViewLastName.setText(userModel.getLastName());
            textViewFirstName.setText((userModel.getFirstName()));
            textViewEmail.setText((userModel.getEmail()));
            textViewDateOfBirth.setText((userModel.getDateOfBirth()));
            textViewAddress.setText((userModel.getAddress()));
            textViewPostalCode.setText((userModel.getPostalCode()));
            textViewTown.setText((userModel.getTown()));
            textViewCountry.setText((userModel.getCountry()));
            textViewCivility.setText((userModel.getGender()));

            view.findViewById(R.id.CardView_cellUser_detail).setOnClickListener(view1 ->

                    mListener.onCardClicked(userModel));
        }

        /*view.findViewById(R.id.CardView_cellUser_detail).setOnClickListener((View) ->{

                onCardClicked();


        });*/


        return view;
    }
public interface UserModelAdapterListener {
        void onCardClicked(UserModel user);
}
}
