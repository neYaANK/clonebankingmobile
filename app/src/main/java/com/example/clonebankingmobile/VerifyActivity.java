package com.example.clonebankingmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clonebankingmobile.requests.CodeRequest;
import com.example.clonebankingmobile.responses.JwtResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyActivity extends Activity {
    private EditText code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        code = findViewById(R.id.codeView);
    }

    public void verifyHandler(View view) {
        CodeRequest codeRequest = new CodeRequest();
        codeRequest.setCode(code.getText().toString());
        NetworkService.getInstance().getAuthAPI().verifyUser(codeRequest,Credentials.JWT_TOKEN).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(response.isSuccessful()){
                    Credentials.JWT_TOKEN = response.body().getType()+" "+response.body().getToken();
                    Credentials.USER_ID = response.body().getId();
                    Intent secondActivityIntent = new Intent(view.getContext(), ProfileActivity.class);
                    startActivity(secondActivityIntent);
                }else{
                    Toast.makeText(view.getContext(),response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
