package com.example.ace201m.teammayo.frags;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ace201m.teammayo.R;
import com.example.ace201m.teammayo.adapter.JobAdapter;
import com.example.ace201m.teammayo.adapter.LearnAdapter;
import com.example.ace201m.teammayo.dbhelper.DBHandler;
import com.example.ace201m.teammayo.dbhelper.JobReq;
import com.example.ace201m.teammayo.dbhelper.LearnReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFrag extends Fragment {

    private String JOB_URL = "http://54.196.205.220/mayoapi/jobrequest.php";
    private String USER_URL = "http://54.196.205.220/mayoapi/employee.php";
    private String APP_URL = "http://54.196.205.220/mayoapi/jobapplication.php";
    private String city = "";
    ArrayList<JobReq> data=null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFrag() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        MainFrag fragment = new MainFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lv = (ListView)v.findViewById(R.id.joblist);
        if(data==null)
            getCity();
        else{
            for(int i=0;i<data.size();i++) {
                Log.i("DEBUG", "data fetched " + data.get(i).getStatus());
            }
            JobAdapter ad = new JobAdapter(getContext(), data);
            DBHandler db = new DBHandler(getContext(),null);
            final String user = db.select().get(0).getPhoneNo();
            lv.setAdapter(ad);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final JobReq oneJob = data.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    DBHandler db = new DBHandler(getContext(), null);
                    final String user = db.select().get(0).getPhoneNo();

                    builder.setMessage("Do Really want to apply for this Job")
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        final JSONObject app_data = new JSONObject(
                                                "{\"employeeID\":\"" + user + "\"," +
                                                        "\"jobID\":" + oneJob.getJobId() + "," +
                                                        "\"status\":0" +
                                                        "}");
                                        RequestQueue req = Volley.newRequestQueue(getContext());

                                        req.add(new JsonObjectRequest(Request.Method.POST, USER_URL, app_data,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        Toast.makeText(getContext(), "Application sent", Toast.LENGTH_LONG).show();
                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getContext(), "something is wrong", Toast.LENGTH_LONG).show();
                                            }
                                        }));
                                    }
                                    catch (JSONException e){
                                        Log.i("DEBUG", "jsonerror");
                                    }
                                }
                            })
                            .setNegativeButton("deny", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    builder.show();
                }
            });
        }
        return v;
    }

    private void getCity(){
        DBHandler db = new DBHandler(getContext(), null);
        String user = db.select().get(0).getPhoneNo();
        USER_URL += "?phoneNumber=" + user;
        RequestQueue req = Volley.newRequestQueue(getContext());

        Log.i("DEBUG", USER_URL);

        req.add(new StringRequest(Request.Method.GET, USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("DEBUG", "dfdd " + response);
                    JSONObject res = new JSONObject(response).getJSONObject("employee");
                    getData(res.getString("city"), res.getString("skill"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("DEBUG", "fiding " + error.getMessage());
            }
        }));
    }

    private void getData(final String city, final String skill) {
        RequestQueue conn = Volley.newRequestQueue(getContext(),null);

        JOB_URL += "?city="+ city;
        conn.add(new StringRequest(Request.Method.GET, JOB_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    data = new ArrayList<>();
                    JSONObject res = new JSONObject(response);
                    JSONArray course = res.getJSONArray("jobRequest");
                    for(int i=0;i<course.length();i++){
                        JSONObject oneRes = course.getJSONObject(i);
                        JobReq red = new JobReq(oneRes.getString("contractorID"),
                                oneRes.getInt("jobID"),
                                oneRes.getString("title"),
                                oneRes.getInt("numberOfPeople"),
                                oneRes.getString("city"),
                                oneRes.getString("address"),
                                oneRes.getInt("status"),
                                oneRes.getString("skill")
                        );

                        if(skill.equals(red.getSkill())){
                            data.add(red);
                        }
                    }
                    refresh();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
