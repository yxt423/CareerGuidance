package com.careerguidance.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.careerguidance.R;
import com.careerguidance.adapter.CareerGuidance;

import java.io.File;

/**
 * Show the "match career tab (tab 2 in main page)"
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindCareerMatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindCareerMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FindCareerMatchFragment extends Fragment {
    private CareerGuidance careerGuidance;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageView careerPhoto = null;
    ImageView universityPhoto = null;

    // user photo related.
    ImageView userPhoto = null;
    File folder = null;
    File user_photo = null;
    TextView userName = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindCareerMatchFragment.
     */
    public static FindCareerMatchFragment newInstance(String param1, String param2) {
        FindCareerMatchFragment fragment = new FindCareerMatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public FindCareerMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        careerGuidance = new CareerGuidance(getActivity());

        // for profile photo
        folder = new File(Environment.getExternalStorageDirectory(), "pictures");
        if (!folder.exists()) {
            folder.mkdir();
        }
        user_photo = new File(folder, "user_photo.png");
    }

    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find_career_match, container, false);

        careerPhoto = (ImageView) v.findViewById(R.id.career_photo);
        universityPhoto = (ImageView) v.findViewById(R.id.university_photo);

        // set user photo
        userPhoto = (ImageView) v.findViewById(R.id.user_photo);
        if (user_photo.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(user_photo.getAbsolutePath());
            userPhoto.setImageBitmap(myBitmap);
        }
        // set user name
        userName = (TextView) v.findViewById(R.id.user_name);
        if (careerGuidance.userHasProfile()) {
            userName.setText(careerGuidance.getUserFirstName());
        }

        careerPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), CareerInfoActivity.class);
                intent.putExtra("career name", "Software Engineer");
                startActivity(intent);
            }
        });

        universityPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), UniversityInfoActivity.class);
                intent.putExtra("university name", "Carnegie Mellon University");
                startActivity(intent);
            }
        });

        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // switch tab to user profile edit page.
                MainTabHost activity = (MainTabHost) getActivity();
                FragmentTabHost mTabHost = activity.getFragmentTabHost();
                mTabHost.setCurrentTab(2);
            }
        });
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        public void onFragmentInteraction(Uri uri);
    }

}
