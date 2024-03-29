package com.example.ace201m.teammayo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.ace201m.teammayo.activity.MainActivity;
import com.example.ace201m.teammayo.dbhelper.DBHandler;
import com.example.ace201m.teammayo.dbhelper.User;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private EditText phoneNo;
    private EditText pin;

    private String USER_URL = "http://54.196.205.220/mayoapi/employee.php";
    private Boolean LOGIN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkLogin();
        changeView();

        Button submit = (Button) findViewById(R.id.button_login);
        TextView redirect_signup = (TextView) findViewById(R.id.tv2);
        phoneNo = (EditText)findViewById(R.id.et1);
        pin = (EditText)findViewById(R.id.et2);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DBHandler db = new DBHandler(getApplicationContext(), null);
                AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                awesomeValidation.addValidation(phoneNo,"^[0-9]{10}$","Enter Phone number correctly");
                awesomeValidation.addValidation(pin,"^[0-9]{4}$","Use a 4 digit number for PIN");

                if(awesomeValidation.validate()){
                    USER_URL += "?phoneNumber=" + phoneNo.getText().toString() +
                            "&password=" + pin.getText().toString();
                    RequestQueue req = Volley.newRequestQueue(getApplicationContext());

                    Log.i("DEBUG", USER_URL);
                    req.add(new StringRequest(Request.Method.GET, USER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            LOGIN = true;
                            db.insert(new User(phoneNo.getText().toString()));
                            changeView();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "ERROR Occured : " + error.getMessage(), Toast.LENGTH_LONG).show();
                            LOGIN = false;
                            changeView();
                        }
                    }));
                }
            }
        });

        redirect_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, Signup.class));
            }
        });

    }

    private void checkLogin() {
        DBHandler db = new DBHandler(this,null);
        LOGIN = (db.select().size()!=0);
    }

    private void changeView() {
        if(LOGIN){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else{
            setContentView(R.layout.activity_login);
        }
    }
}
