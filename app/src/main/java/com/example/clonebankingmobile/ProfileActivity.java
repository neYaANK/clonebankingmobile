package com.example.clonebankingmobile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clonebankingmobile.requests.ChangeEmailRequest;
import com.example.clonebankingmobile.responses.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends Activity {
    private ImageView profile;
    private TextView name,surname,phoneNumber;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        profile = findViewById(R.id.profilePic);
        name = findViewById(R.id.firstNameField);
        surname = findViewById(R.id.surnameField);
        phoneNumber = findViewById(R.id.phoneField);
        email = findViewById(R.id.editTextTextEmailAddress);
        refresh(this.getApplicationContext());
    }
    private void refresh(Context context){
        NetworkService.getInstance().getUserAPI().getUserInfo(Credentials.USER_ID,Credentials.JWT_TOKEN).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    UserResponse res = response.body();
                    name.setText(res.getName());
                    surname.setText(res.getSurname());
                    phoneNumber.setText(res.getPhoneNumber());
                    email.setText(res.getEmail());
                }else{
                    Toast.makeText(context,response.message(),Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT);
            }
        });
        NetworkService.getInstance().getUserAPI().getUserImage(Credentials.USER_ID,Credentials.JWT_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    profile.setImageBitmap(bmp);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT);
            }
        });
    }

    public void refreshHandler(View view) {
        refresh(view.getContext());
    }

    public void changeEmailHandler(View view) {
        NetworkService.getInstance().getUserAPI().updateUserEmail(new ChangeEmailRequest(email.getText().toString()),Credentials.USER_ID, Credentials.JWT_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) Toast.makeText(view.getContext(), "Email changed!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(view.getContext(),response.message(),Toast.LENGTH_SHORT);
                refresh(view.getContext());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(view.getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
