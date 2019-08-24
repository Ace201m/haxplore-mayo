package com.example.ace201m.teammayo.frags;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.ace201m.teammayo.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private String USER_URL = "";
    private Boolean loaded = false;
    private EditText phoneNo;
    private EditText state;
    private EditText city;
    private EditText name;
    private EditText address;
    private RadioGroup skill;
    private RadioButton skill_a, skill_b, skill_c;
    private EditText age;

    public AccFrag() {
        // Required empty public constructor
    }

    public static android.support.v4.app.Fragment newInstance() {
        AccFrag fragment = new AccFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_acc, container, false);

        Button b = v.findViewById(R.id.update_profile_b);
        phoneNo = (EditText)v.findViewById(R.id.phone_num);
        state = (EditText)v.findViewById(R.id.state);
        city = (EditText)v.findViewById(R.id.city);
        name = (EditText)v.findViewById(R.id.name);
        address = (EditText)v.findViewById(R.id.address);
        skill = (RadioGroup)v.findViewById(R.id.u_skill);
        skill_a = (RadioButton)v.findViewById(R.id.u_skill_a);
        skill_b = (RadioButton)v.findViewById(R.id.u_skill_b);
        skill_c = (RadioButton)v.findViewById(R.id.u_skill_c);
        age = (EditText)v.findViewById(R.id.age_u);


        if(loaded){
            Button logout = v.findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHandler db = new DBHandler(getContext(), null);
                    db.delete();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
            });


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                    awesomeValidation.addValidation(phoneNo,"^[0-9]{10}$","Enter Phone number correctly");
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
                                    "{\"action\":\"" + "2" + "\"," +
                                            "\"phoneNumber\":\"" + phoneNo.getText().toString() + "\"," +
                                            "\"name\":\"" + name.getText().toString() +
                                            "\",\"skill\":" + skills + "," +
                                            "\"age\":" + age.getText().toString() +
                                            ",\"city\":\"" + city.getText().toString() + "\"," +
                                            "\"state\":\"" + state.getText().toString() +
                                            "\"}");
                            RequestQueue req = Volley.newRequestQueue(getContext());

                            req.add(new JsonObjectRequest(Request.Method.POST, USER_URL, user_data,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast.makeText(getContext(), "User updated successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
                                }
                            }));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        else{
            populateData();
        }
        return v;
    }

    private void populateData() {
        DBHandler db = new DBHandler(getContext(), null);
        String user = db.select().get(0).getPhoneNo();
        USER_URL += "?phoneNumber=" + user;
        RequestQueue req = Volley.newRequestQueue(getContext());

        Log.i("DEBUG", USER_URL);
        req.add(new StringRequest(Request.Method.GET, USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject res = new JSONObject(response).getJSONObject("employee");
                    phoneNo.setText(res.getString("phoneNumber"));
                    state.setText(res.getString("state"));
                    city.setText(res.getString("city"));
                    name.setText(res.getString("name"));
                    address.setText(res.getString("address"));
                    String sk = res.getString("skill");

                    switch (sk){
                        case "0":
                            skill_a.toggle();
                            break;
                        case "1":
                            skill_b.toggle();
                            break;
                        case "2":
                            skill_c.toggle();
                            break;
                    }
                    loaded = true;
                    refresh();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("DEBUG", "dont know what is wrong");
            }
        }));
    }

    private void refresh() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
