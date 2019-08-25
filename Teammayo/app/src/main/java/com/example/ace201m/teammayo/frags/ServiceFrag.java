package com.example.ace201m.teammayo.frags;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ace201m.teammayo.R;
import com.example.ace201m.teammayo.adapter.LearnAdapter;
import com.example.ace201m.teammayo.dbhelper.DBHandler;
import com.example.ace201m.teammayo.dbhelper.LearnReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServiceFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServiceFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private String SERVICE_URL = "http://54.196.205.220/mayoapi/service.php";
    private String USER_URL = "http://54.196.205.220/mayoapi/employee.php";
    private String city = "";
    ArrayList<LearnReq> data=null;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ServiceFrag() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        ServiceFrag fragment = new ServiceFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_learn, container, false);
        ListView lv = (ListView)v.findViewById(R.id.learnlist);
        if(data==null)
            getCity();
        else{
            LearnAdapter ad = new LearnAdapter(getContext(), data);
            lv.setAdapter(ad);
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
                    JSONObject res = new JSONObject(response).getJSONObject("employee");
                    getData(res.getString("city"));
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

    private void getData(final String city) {
        RequestQueue conn = Volley.newRequestQueue(getContext(),null);

        SERVICE_URL += "?city="+ city;
        conn.add(new StringRequest(Request.Method.GET, SERVICE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    data = new ArrayList<>();
                    JSONObject res = new JSONObject(response);
                    JSONArray service = res.getJSONArray("service");
                    for(int i=0;i<service.length();i++){
                        JSONObject oneRes = service.getJSONObject(i);
                        String bodi = oneRes.getString("body");
                        LearnReq red = new LearnReq("1234567890",
                                oneRes.getString("serviceID"),
                                oneRes.getString("title"),
                                city,
                                bodi
                        );
                        red.setBody(bodi);
                        data.add(red);
                        Log.i("DEBUG",bodi +  red.getBody());
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
