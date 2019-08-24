package com.example.ace201m.teammayo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.ace201m.teammayo.R;
import com.example.ace201m.teammayo.dbhelper.DBHandler;
import com.example.ace201m.teammayo.dbhelper.User;

import org.json.JSONException;
import org.json.JSONObject;


public class Signup extends AppCompatActivity {

    private EditText phoneNo;
    private EditText pin;
    private EditText address;
    private EditText name;

    private String USER_URL;
    private Boolean LOGIN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        

        Button submit = (Button) findViewById(R.id.button);
        TextView redirect_signup = (TextView) findViewById(R.id.tv2);
        phoneNo = (EditText)findViewById(R.id.et1);
        pin = (EditText)findViewById(R.id.et2);
        address = (EditText)findViewById(R.id.et4);
        name = (EditText)findViewById(R.id.et3);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                awesomeValidation.addValidation(phoneNo,"^[0-9]{10}$","Enter Phone number correctly");
                awesomeValidation.addValidation(pin,"^[0-9]{4}$","Use a 4 digit number for PIN");
                awesomeValidation.addValidation(name,"^[a-z\\s]{1,}$","Name can't contain digits");

                if(awesomeValidation.validate()){
                    try {
                        final JSONObject user_data = new JSONObject("{\"name\":\"" + name.getText().toString() + "\"," +
                                "\"phoneNo\":\"" + phoneNo.getText().toString()
                                + "\",\"pin\":\"" + pin.getText().toString() + "\",\"address\":\"" + address.getText().toString() + "\"}");
                        RequestQueue req = Volley.newRequestQueue(getApplicationContext());

                        req.add(new StringRequest(Request.Method.POST, USER_URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                startActivity(new Intent(Signup.this, LoginActivity.class));
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "ERROR Occured : " + error.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


}
