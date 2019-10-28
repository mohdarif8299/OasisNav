package com.example.oasisNav;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Manager_Login extends AppCompatActivity {
    CheckBox checkbox;
    EditText Id,Password;
    Button login;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__login);
        checkbox=findViewById(R.id.checkbox);
        Id=findViewById(R.id.Id);
        Password=findViewById(R.id.Password);
        login=findViewById(R.id.signin);
        progressBar=findViewById(R.id.progress);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();

            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    private void Login() {
        progressBar.setVisibility(View.VISIBLE);
        String myId=Id.getText().toString().trim();
        String myPassword=Password.getText().toString().trim();

        Intent intent=new Intent(Manager_Login.this,Navigation.class);
        startActivity(intent);
        finish();
    }
}
