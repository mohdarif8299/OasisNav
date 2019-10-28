package com.example.oasisNav;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class Registration extends AppCompatActivity {
  Button regitser;
  EditText Fname,Lname,Email,Phone;
  String Id="",Password="";
    private static String REGISTER_URL="https://ingratiating-jacks.000webhostapp.com/Oasis/register_serviceman.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
          Fname=findViewById(R.id.fName);
          Lname=findViewById(R.id.lName);
          Email=findViewById(R.id.Email);
       Phone=findViewById(R.id.phone);
       regitser=findViewById(R.id.register);
       regitser.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
//                 registerServieMan();
               Intent intent = new Intent(Registration.this,Navigation.class);
               startActivity(intent);
            }
        });
    }
    private void registerServieMan() {
        final String fname=Fname.getText().toString();
        final String lname=Lname.getText().toString();
        final String email=Email.getText().toString();
        final String phone=Lname.getText().toString();
        Id="DEL"+1234;
        Password="12345";
        if(TextUtils.isEmpty(fname)) {
            Toast.makeText(getApplicationContext(),"Name cannot be empty",Toast.LENGTH_SHORT).show();
            Fname.findFocus();
            return;
        }
        else if(TextUtils.isEmpty(lname)){
            Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
            Lname.findFocus();
            return;
        }
        else {
            StringRequest request = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(),""+ jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                     }
                     catch (JSONException e) {

                         e.printStackTrace();
                         Toast.makeText(getApplicationContext(),"Login Error"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Login Error"+error.toString(),Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("fname", fname);
                    params.put("lname", lname);
                    params.put("phone", phone);
                    params.put("email", email);
                    params.put("password", Password);
                    params.put("id", Id);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void sendMail(){
    }
}
