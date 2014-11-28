package com.careerguidance.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.careerguidance.R;
import com.careerguidance.activity.helperActivity.SelectionActivity;
import com.careerguidance.adapter.StableArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Show user profile for editing. The "find me a career" button will
 * redirect user to tab 2.
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProfileFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ImageView profilePhoto = null;
    ImageView editName = null;
    TextView userName = null;

    private String birthday = null;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        // profile photo on click: show dialog for editing the profile photo:
        // take a new photo or choose an existing photo.
        profilePhoto = (ImageView) v.findViewById(R.id.profile_photo);
        profilePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_change_photo);
                dialog.setTitle("Change the photo");

                Button takePhotoButton = (Button) dialog.findViewById(R.id.take_photo_button);
                takePhotoButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button choosePhotoButton = (Button) dialog.findViewById(R.id.choose_photo_button);
                choosePhotoButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button cancelButton = (Button) dialog.findViewById(R.id.cancel_button);
                cancelButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        // the pencil picture on click: show the edit name dialog
        editName = (ImageView) v.findViewById(R.id.edit_name);
        userName = (TextView) v.findViewById(R.id.user_name);
        editName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showEditNameDialog(null);
            }
        });


        // the personal information list.
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        String[] values = new String[] {"Birthday", "Gender", "Location", "Grades", "Interests"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                switch (position) {
                    case 0:
                        showEditBirthdayDialog(list);
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        Intent intent = new Intent(getActivity(), SelectionActivity.class);
                        intent.putExtra("pageTitle", list.get(position));
                        startActivity(intent);
                        break;
                }
            }
        });

        // The "find me a career" button.
        Button findCareerMatchButton = (Button) v.findViewById(R.id.find_career_match_button);
        findCareerMatchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the matching function....

                // switch tab
                MainTabHost activity = (MainTabHost) getActivity();
                FragmentTabHost mTabHost = activity.getFragmentTabHost();
                mTabHost.setCurrentTab(1);
            }
        });

        return v;
    }

    // show the edit user name dialog once.
    public void showEditNameDialog(String message) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Name");
        if (message != null) {
            alert.setMessage(message);
        }

        final EditText input = new EditText(getActivity());
        input.setWidth(250);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                if (value == null || value.length() == 0) {
                    showEditNameDialog("Name can not be empty.");
                } else {
                    userName.setText(value);
                    // save user name in database.
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });

        alert.show();
    }

    // TODO: edit
    public void showEditBirthdayDialog(final ArrayList<String> list) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Birthday");

        final Calendar c = Calendar.getInstance();
        final DatePicker datePicker = new DatePicker(getActivity());
        datePicker.setCalendarViewShown(false);
        datePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
            }
        });
        alert.setView(datePicker);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                birthday = String.valueOf(c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR);
                list.set(0, birthday);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });
        alert.show();
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
