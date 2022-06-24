package com.bilkis.shaikhbilkis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.APICLIENT;
import com.LoginRespones;
import com.Resigetrrespones;
import com.signup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText emails, passwords;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emails = findViewById(R.id.email);
        passwords = findViewById(R.id.password);
        button = findViewById(R.id.signin);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        userlogin();
    }
});
    }

    private void userlogin()
    {
        String email=emails.getText().toString();
        String password=passwords.getText().toString();


        if (email.isEmpty()){
            emails.requestFocus();
            emails.setError("Plz enter your email");
            return;
        }
        if (password.isEmpty()){
            passwords.requestFocus();
            passwords.setError("Plz enter your password");
            return;
        }
        Call<LoginRespones>call=APICLIENT
                .getInstance()
                .getApi()
                .login(email,password);
        call.enqueue(new Callback<LoginRespones>() {
            @Override
            public void onResponse(Call<LoginRespones> call, Response<LoginRespones> response) {
                LoginRespones loginRespones=    response.body();
                if (response.isSuccessful()){
                    Intent intent=new Intent(MainActivity.this,THIRD.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, loginRespones.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, loginRespones.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginRespones> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}