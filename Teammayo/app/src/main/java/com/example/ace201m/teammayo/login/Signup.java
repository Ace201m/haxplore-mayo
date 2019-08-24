package com.example.ace201m.teammayo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
    private RadioGroup skill;
    private EditText city;
    private EditText state;
    private EditText age;

    private String USER_URL = "http://54.196.205.220/mayoapi/employee.php";
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
        skill = (RadioGroup) findViewById(R.id.skill);
        city = (EditText) findViewById(R.id.city_sign);
        state = (EditText)findViewById(R.id.state_sign);
        age = (EditText)findViewById(R.id.age_a);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                awesomeValidation.addValidation(phoneNo,"^[0-9]{10}$","Enter Phone number correctly");
                awesomeValidation.addValidation(pin,"^[0-9]{4}$","Use a 4 digit number for PIN");
                awesomeValidation.addValidation(name,"^[a-z\\s]{1,}$","Name can't contain digits");
                awesomeValidation.addValidation(city,"^[a-z\\s]{1,}$","City Name can't contain digits");
                awesomeValidation.addValidation(state,"^[a-z\\s]{1,}$","State Name can't contain digits");
                awesomeValidation.addValidation(age,"^[0-9]{1,3}$","Invalid Age");


                int skills = -1;

                switch (skill.getCheckedRadioButtonId()){
                    case R.id.skill_a:
                        skills = 0;
                        break;
                    case R.id.skill_b:
                        skills = 1;
                        break;
                    case R.id.skill_c:
                        skills = 2;
                        break;
                }
                if(awesomeValidation.validate()){
                    try {
                        final JSONObject user_data = new JSONObject(
                                "{\"action\":\"" + "1" + "\"," +
                                "\"phoneNumber\":\"" + phoneNo.getText().toString() + "\"," +
                                        "\"name\":\"" + name.getText().toString() +
                                        "\",\"password\":\"" + pin.getText().toString() +
                                        "\",\"skill\":" + skills + "," +
                                        "\"age\":" + age.getText().toString() +
                                        ",\"city\":\"" + city.getText().toString() + "\"," +
                                        "\"state\":\"" + state.getText().toString() +
                                        "\"}");
                        RequestQueue req = Volley.newRequestQueue(getApplicationContext());

                        req.add(new JsonObjectRequest(Request.Method.POST, USER_URL, user_data,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
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
