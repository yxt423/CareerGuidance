package com.careerguidance.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.careerguidance.R;
import com.careerguidance.activity.helperActivity.GradesActivity;
import com.careerguidance.activity.helperActivity.InterestsActivity;
import com.careerguidance.activity.helperActivity.LocationActivity;
import com.careerguidance.activity.helperActivity.SelectionActivity;
import com.careerguidance.adapter.CareerGuidance;
import com.careerguidance.model.Career;
import com.careerguidance.model.University;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

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
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int SELECT_PHOTO = 1;
    private static final int SELECT_GENDER_ACTIVITY = 2;
    private static final int SELECT_LOCATION_ACTIVITY = 3;
    private static final int SELECT_INTERESTS_ACTIVITY = 4;

    private CareerGuidance careerGuidance;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ListView listView = null;
    ImageView editName = null;
    TextView userName = null;

    String[] optionListStr = null;
    ArrayList<String> optionList = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    String birthday = null;
    Calendar calendar = Calendar.getInstance();

    // user photo related.
    ImageView profilePhoto = null;
    File folder = null;
    File user_photo = null;

    // for location setting.
    Locale myLocale;

    // result of matching
    Career user_career;
    University user_university;

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

    public ProfileFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        careerGuidance = new CareerGuidance(getActivity());

        optionListStr = new String[] {getString(R.string.profile_func0), getString(R.string.profile_func1),
                getString(R.string.profile_func2), getString(R.string.profile_func3), getString(R.string.profile_func4)};
        Collections.addAll(optionList, optionListStr);

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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        createProfilePhotoView(v);
        createNameView(v);
        createListView(v);
        createFindCareerButton(v);
        return v;
    }

    // profile photo on click: show dialog for editing the profile photo:
    // take a new photo or choose an existing photo.
    private void createProfilePhotoView(View v) {
        profilePhoto = (ImageView) v.findViewById(R.id.profile_photo);

        if (user_photo.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(user_photo.getAbsolutePath());
            profilePhoto.setImageBitmap(myBitmap);
        }

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
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                            // take a photo and save to local file.
                            Uri uriSavedImage = Uri.fromFile(user_photo);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                        dialog.dismiss();
                    }
                });

                Button choosePhotoButton = (Button) dialog.findViewById(R.id.choose_photo_button);
                choosePhotoButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
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
    }

    private void createNameView(View v) {
        // user name
        userName = (TextView) v.findViewById(R.id.user_name);
        if (careerGuidance.userHasProfile()) {
            userName.setText(careerGuidance.getUserFirstName());
        }

        // the pencil picture on click: show the edit name dialog
        editName = (ImageView) v.findViewById(R.id.edit_name);
        editName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showEditNameDialog(null);
            }
        });
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
                    careerGuidance.setUserFirstName(value);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });

        alert.show();
    }

    private void createListView(View v) {
        if (careerGuidance.getUserBirthDate() != null) {
            optionList.set(0, getString(R.string.profile_func0) + ":  " + careerGuidance.getUserBirthDate());
        }
        if (careerGuidance.getUserGender().getName() != null) {
            optionList.set(1, getString(R.string.profile_func1) + ":  " + careerGuidance.getUserGender().getName());
        }
        if (careerGuidance.getUserLocation().getName() != null) {
            optionList.set(2, getString(R.string.profile_func2) + ":  " + careerGuidance.getUserLocation().getName());
        }

        // the personal information list.
        listView = (ListView) v.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, optionList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        showEditBirthdayDialog();
                        break;
                    case 1:
                        intent = new Intent(getActivity(), SelectionActivity.class);
                        startActivityForResult(intent, SELECT_GENDER_ACTIVITY);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), LocationActivity.class);
                        startActivityForResult(intent, SELECT_LOCATION_ACTIVITY);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), GradesActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), InterestsActivity.class);
                        startActivityForResult(intent, SELECT_INTERESTS_ACTIVITY);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void showEditBirthdayDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(getString(R.string.profile_func0));

        final DatePicker datePicker = new DatePicker(getActivity());
        //datePicker.setCalendarViewShown(false);  uncomment this line for API level 10.
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
            }
        });
        alert.setView(datePicker);

        alert.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // save user's birthday.
                Date date = new Date();
                date.setTime(calendar.getTimeInMillis());
                careerGuidance.setUserBirthDate(date);

                birthday = String.valueOf(calendar.get(Calendar.MONTH) + 1) + "/" +
                        calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR);
                optionList.set(0, getString(R.string.profile_func0) + ":   " + birthday);
                adapter.notifyDataSetChanged();

            }
        });

        alert.setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });
        alert.show();
    }

    // The "find me a career" button.
    private void createFindCareerButton(View v) {
        Button findCareerMatchButton = (Button) v.findViewById(R.id.find_career_match_button);
        findCareerMatchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // call the matching function....

                user_career = careerGuidance.getCareer(3);
                user_university = careerGuidance.getUniversityById(1);
                careerGuidance.setUserCareer(user_career);
                careerGuidance.setUserUniversity(user_university);

                // switch tab
                MainTabHost activity = (MainTabHost) getActivity();
                FragmentTabHost mTabHost = activity.getFragmentTabHost();
                mTabHost.setCurrentTab(1);
            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // use the photo just taken as profile photo
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                if (user_photo.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(user_photo.getAbsolutePath());
                    profilePhoto.setImageBitmap(myBitmap);
                } else {
                    Toast.makeText(getActivity(), "Saving photo failed.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        // use the photo selected from local files as profile photo
        else if (requestCode == SELECT_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {

                Uri selectedImage = intent.getData();
                InputStream imageStream = null;
                FileOutputStream out = null;
                try {
                    imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                    Bitmap SelectedImage = BitmapFactory.decodeStream(imageStream);
                    profilePhoto.setImageBitmap(SelectedImage);

                    out = new FileOutputStream(user_photo);
                    SelectedImage.compress(Bitmap.CompressFormat.PNG, 100, out);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getActivity(), "Image not found", Toast.LENGTH_SHORT).show();
                }
            }
        }

        else if (requestCode == SELECT_GENDER_ACTIVITY && resultCode == Activity.RESULT_OK) {
            String value = intent.getStringExtra("returnValue");
            optionList.set(1, getString(R.string.profile_func1) + ":  " + value);
            adapter.notifyDataSetChanged();
            careerGuidance.setUserGender(value);
        }
        else if (requestCode == SELECT_LOCATION_ACTIVITY && resultCode == Activity.RESULT_OK) {
            String returnValue = intent.getStringExtra("returnValue");
            optionList.set(2, getString(R.string.profile_func2) + ":  " + returnValue);
            adapter.notifyDataSetChanged();
            careerGuidance.setUserLocation(returnValue);

            // change language setting.
            if (returnValue.contains("China")) {
                setLocale("zh");
            } else if (returnValue.contains("United States")) {
                setLocale("en");
            } else if (returnValue.contains("Kenya")) {
                setLocale("sw-KE");
            }
        }

        else if (requestCode == SELECT_INTERESTS_ACTIVITY && resultCode == Activity.RESULT_OK) {
//            ArrayList<String> selectedInterests  = (ArrayList<String>) intent.getSerializableExtra("returnValue");
//            if (selectedInterests.contains("Biology")) {
//
//            }
        }
    }

    // change language setting.
    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), MainTabHost.class);
        refresh.putExtra("currentTab", 2);
        getActivity().finish();
        startActivity(refresh);
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
