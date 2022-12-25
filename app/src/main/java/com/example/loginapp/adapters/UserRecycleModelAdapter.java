package com.example.loginapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.datas.DataManager;
import com.example.loginapp.models.UserModel;
import com.example.loginapp.webservices.entities.UserWeb;

import java.util.ArrayList;

public class UserRecycleModelAdapter extends RecyclerView.Adapter<UserRecycleModelAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserModel> arrayUserModel;
    private UserRecycleModelAdapterListener mListener;

    public UserRecycleModelAdapter(Context context, UserRecycleModelAdapterListener listener){
        this.context = context;
        this.mListener = listener;
        this.arrayUserModel = DataManager.getInstance().getListArrayUserModel();
    }


    public UserModel getItem(int position) {
        if (this.arrayUserModel != null) {
            return this.arrayUserModel.get(position);
        }
        return null;
    }

    public void forceNotifyDataSetChanged() {
        this.arrayUserModel = DataManager.getInstance().getListArrayUserModel();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserRecycleModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.cell_user, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = this.getItem(position);
        if(userModel != null){
            TextView textViewLastName = holder.textViewLastName;
            TextView textViewFirstName = holder.textViewFirstName;
            TextView textViewEmail = holder.textViewEmail;
            TextView textViewDateOfBirth = holder.textViewDateOfBirth;
            TextView textViewAddress = holder.textViewAddress;
            TextView textViewPostalCode = holder.textViewPostalCode;
            TextView textViewTown = holder.textViewTown;
            TextView textViewCountry = holder.textViewCountry;
            TextView textViewCivility = holder.textViewCivility;

            /******************/
            // prepare the Request

            /*JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                textViewFirstName.setText(response.getString("firstname"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                textViewLastName.setText(response.getString("lastname"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                   /* response -> {
                        try{
                            //Create a JSON object containing information from the API.
                            JSONObject myJsonObject = new JSONObject(response);
                            textViewFirstName.setText(myJsonObject.getString("firstname"));
                            textViewLastName.setText(myJsonObject.getString("lastname"));
                            //totalDeathsWorld.setText(myJsonObject.getString("deaths"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    volleyError -> Toast.makeText(context.getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show()
            );/*
            RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //    requestQueue.add(myRequest);

            /******************/

           /* textViewLastName.setText(userWeb.getLastname());
            textViewFirstName.setText(userWeb.getFirstname());
            textViewEmail.setText(userWeb.getSub());
            textViewAddress.setText(userWeb.getCustomer());
            textViewDateOfBirth.setText(userWeb.getVldService());
            textViewPostalCode.setText(userWeb.getVlsService());
            textViewTown.setText(userWeb.getExp());*/
            textViewLastName.setText(userModel.getLastName());
            textViewFirstName.setText(userModel.getFirstName());
            textViewEmail.setText(userModel.getEmail());
            textViewAddress.setText(userModel.getAddress());
            textViewDateOfBirth.setText(userModel.getDateOfBirth());
            textViewPostalCode.setText(userModel.getPostalCode());
            textViewTown.setText(userModel.getTown());
            textViewCountry.setText(userModel.getCountry());
            textViewCivility.setText(userModel.getGender());
          holder.cardViewUser.setOnClickListener((view) ->{
             mListener.onCardClicked(userModel);
          });
        }


    }

    @Override
    public int getItemCount() {
        if(arrayUserModel != null && arrayUserModel.size()>0){
            return arrayUserModel.size();
        }
        return 0;

    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView textViewLastName,textViewFirstName,textViewEmail,textViewDateOfBirth,textViewAddress;
        TextView textViewPostalCode,textViewTown,textViewCountry,textViewCivility;

        CardView cardViewUser;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
             textViewLastName = (TextView) itemView.findViewById(R.id.textView_cellUser_lastName);
             textViewFirstName = (TextView) itemView.findViewById(R.id.textView_cellUser_firstName);
             textViewEmail = (TextView)itemView.findViewById(R.id.textView_cellUser_email);
             textViewDateOfBirth =(TextView) itemView.findViewById(R.id.textView_cellUser_dateOfBirth);
             textViewAddress = (TextView)itemView.findViewById(R.id.textView_cellUser_address);
             textViewPostalCode =(TextView)  itemView.findViewById(R.id.textView_cellUser_PostalCode);
             textViewTown = (TextView)itemView.findViewById(R.id.textView_cellUser_town);
             textViewCountry = (TextView)itemView.findViewById(R.id.textView_cellUser_country);
             textViewCivility =(TextView) itemView.findViewById(R.id.textView_cellUser_civility);
            cardViewUser = (CardView) itemView.findViewById(R.id.CardView_cellUser_detail);
            /*nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);*/
        }
    }

    public interface UserRecycleModelAdapterListener {
        void onCardClicked(UserModel user);
    }


}