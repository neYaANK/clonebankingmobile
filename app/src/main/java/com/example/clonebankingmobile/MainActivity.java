package com.example.clonebankingmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clonebankingmobile.requests.LoginRequest;
import com.example.clonebankingmobile.responses.JwtResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumber, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = findViewById(R.id.phoneNumberInput);
        password = findViewById(R.id.passwordInput);
    }

    public void loginHandler(View view) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber(phoneNumber.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        NetworkService.getInstance().getAuthAPI().loginUser(loginRequest).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(response.isSuccessful()){
                    Credentials.JWT_TOKEN = response.body().getType()+" "+response.body().getToken();
                    Intent secondActivityIntent = new Intent(view.getContext(), VerifyActivity.class);
                    startActivity(secondActivityIntent);
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}