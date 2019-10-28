package com.example.oasisNav.ui.add_person;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oasisNav.Navigation;
import com.example.oasisNav.R;
import com.example.oasisNav.Registration;
import com.example.oasisNav.com.example.oasisNav.mail.SendMail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Add_PersonFragment extends Fragment {
    Button regitser;
    EditText Fname,Lname,Email,Phone;
    String Id="",Password="";
    private static String REGISTER_URL="https://ingratiating-jacks.000webhostapp.com/Oasis/register_serviceman.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        Fname=view.findViewById(R.id.fName);
        Lname=view.findViewById(R.id.lName);
        Email=view.findViewById(R.id.Email);
        Phone=view.findViewById(R.id.phone);
        regitser=view.findViewById(R.id.register);
        regitser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     registerServieMan();
                Intent intent = new Intent(getContext(), Navigation.class);
                startActivity(intent);
            }
        });
        return view;
    }



    private void registerServieMan() {
        final String fname=Fname.getText().toString();
        final String lname=Lname.getText().toString();
        final String email=Email.getText().toString();
        final String phone=Lname.getText().toString();


        Id="DEL"+1234;
        Password="12345";

        if(TextUtils.isEmpty(fname)) {
            Toast.makeText(getContext(),"Name cannot be empty",Toast.LENGTH_SHORT).show();
            Fname.findFocus();
            return;
        }
        else if(TextUtils.isEmpty(lname)){
            Toast.makeText(getContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
            Lname.findFocus();
            return;
        }
        else {
            StringRequest request = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getContext(),""+response,Toast.LENGTH_LONG).show();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        SendMail sm = new SendMail(getActivity(), email, "Oasis Internationsal", "Registered Successfully" );
                        sm.execute();
                        Toast.makeText(getContext(),"Mail Sent  "+ jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    catch (JSONException e) {

                        e.printStackTrace();
                        Toast.makeText(getContext(),"Login Error"+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(),"Login Error"+error.toString(),Toast.LENGTH_SHORT).show();

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
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(request);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
