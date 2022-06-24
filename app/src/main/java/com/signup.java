package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bilkis.shaikhbilkis.MainActivity;
import com.bilkis.shaikhbilkis.R;
import com.bilkis.shaikhbilkis.THIRD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {
EditText n,ed,p,cp;
Button su;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        n=findViewById(R.id.names);
        ed=findViewById(R.id.emaild);
        p=findViewById(R.id.pass);
        cp=findViewById(R.id.passoc);
        su=findViewById(R.id.signup);
su.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        register();
    }
});
    }

    private void register() {
        String username=n.getText().toString();
        String email=ed.getText().toString();
        String password=p.getText().toString();
        String confirmpassword=cp.getText().toString();
        if (username.isEmpty()){
            n.requestFocus();
            n.setError("Plz enter your name");
            return;
        }
        if (email.isEmpty()){
            ed.requestFocus();
            ed.setError("Plz enter your email");
            return;
        }
        if (password.isEmpty()){
            p.requestFocus();
            p.setError("Plz enter your password");
            return;
        }
        if (confirmpassword.isEmpty()){
            cp.requestFocus();
            cp.setError("Plz enter your password");
            return;
        }

        Call<Resigetrrespones>call=APICLIENT
                .getInstance()
                .getApi()
                .register(username,email,password);
        call.enqueue(new Callback<Resigetrrespones>() {
            @Override
            public void onResponse(Call<Resigetrrespones> call, Response<Resigetrrespones> response) {
            Resigetrrespones resigetrrespones=    response.body();
                if (response.isSuccessful()){
                    Intent intent=new Intent(signup.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(signup.this, resigetrrespones.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(signup.this, resigetrrespones.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Resigetrrespones> call, Throwable t) {
                Toast.makeText(signup.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}