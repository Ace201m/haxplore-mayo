package com.example.ace201m.teammayo.frags;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.ace201m.teammayo.R;
import com.example.ace201m.teammayo.dbhelper.DBHandler;
import com.example.ace201m.teammayo.login.LoginActivity;

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
//        EditText =
//
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//
//                awesomeValidation.addValidation(phoneNo,"^[0-9]{10}$","Enter Phone number correctly");
//                awesomeValidation.addValidation(pin,"^[0-9]{4}$","Use a 4 digit number for PIN");
//
//            }
//        });
        Button logout = v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(getContext(), null);
                db.delete();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        final EditText phoneNo = (EditText)v.findViewById(R.id.phone_num);
        final EditText state = (EditText)v.findViewById(R.id.state);
        final EditText city = (EditText)v.findViewById(R.id.city);
        final EditText name = (EditText)v.findViewById(R.id.name);
        final EditText address = (EditText)v.findViewById(R.id.address);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                awesomeValidation.addValidation(phoneNo,"^[0-9]{10}$","Enter Phone number correctly");
                awesomeValidation.addValidation(name,"^[a-z\\s]{1,}$","Name can't contain digits");
                awesomeValidation.addValidation(city,"^[a-z\\s]{1,}$","City Name can't contain digits");
                awesomeValidation.addValidation(state,"^[a-z\\s]{1,}$","State Name can't contain digits");

            }
        });
        return v;
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
