package com.careerguidance.adapter;

import android.content.Context;
import android.widget.TextView;

import com.careerguidance.DBLayout.CareerDataSource;
import com.careerguidance.DBLayout.GenderDataSource;
import com.careerguidance.DBLayout.InterestDataSource;
import com.careerguidance.DBLayout.LocationDataSource;
import com.careerguidance.DBLayout.SubjectDataSource;
import com.careerguidance.DBLayout.UniversityDataSource;
import com.careerguidance.DBLayout.UserDataSource;
import com.careerguidance.R;
import com.careerguidance.cgexception.CGException;
import com.careerguidance.model.Career;
import com.careerguidance.model.Gender;
import com.careerguidance.model.Interest;
import com.careerguidance.model.Location;
import com.careerguidance.model.Subject;
import com.careerguidance.model.University;
import com.careerguidance.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 12/4/14.
 */
public class CareerGuidance {
    private User user;

    private UserDataSource userDataSource;

    private LocationDataSource locationDataSource;

    private GenderDataSource genderDataSource;

    private SubjectDataSource subjectDataSource;

    private InterestDataSource interestDataSource;

    private CareerDataSource careerDataSource;

    private UniversityDataSource universityDataSource;

    private Context context;

    public CareerGuidance(Context contxt)
    {
        context = contxt;

        openDataSources();
    }

    public void openDataSources()
    {
        userDataSource = new UserDataSource(context);

        userDataSource.open();

        user = userDataSource.getUser(1);


        locationDataSource = new LocationDataSource(context);

        locationDataSource.open();


        genderDataSource = new GenderDataSource(context);

        genderDataSource.open();


        subjectDataSource = new SubjectDataSource(context);

        subjectDataSource.open();


        interestDataSource = new InterestDataSource(context);

        interestDataSource.open();


        careerDataSource = new CareerDataSource(context);

        careerDataSource.open();


        universityDataSource = new UniversityDataSource(context);

        universityDataSource.open();

    }
    public boolean userHasProfile() {
        return user.hasProfile();
    }

    //Getters

    //User Getters
    public void getUserFirstName()
    {
        user.getFirstName();
    }

    public void getUserLastName()
    {
        user.getLastName();
    }

    public Gender getUserGender()
    {
        return user.getGender();
    }

    public Location getUserLocation()
    {
        return user.getLocation();
    }

    public University getUserUniversity()
    {
        return user.getUniversityChoice();
    }

    public Career getUserCareer()
    {
        return user.getCareerChoice();
    }

    //Setters
    public void setUserFirstName(String strFirstname)
    {
        try
        {
           if (userDataSource.setFirstName(user.getId(), strFirstname))
           {
                user.setFirstName(strFirstname);
           }
           else
           {
                CGException cgException = new CGException("Unable to save name to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }

    public void setUserLastName(String strLastname)
    {
        try
        {
            if (userDataSource.setLastName(user.getId(), strLastname))
            {
                user.setGender(strLastname);
            }
            else
            {
                CGException cgException = new CGException("Unable to save name to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }

    public void setUserLocation(String strLocation)
    {
        try
        {
            if (userDataSource.setLocation(user.getId(), strLocation))
            {
                user.setGender(strLocation);
            }
            else
            {
                CGException cgException = new CGException("Unable to save name to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }

    public boolean isValidGender(String strGender)
    {
        List<Gender> genders = genderDataSource.getAllGenders();

        boolean validGender = false;

        for (Gender gender : genders)
        {
            if (strGender.equals(gender.getName()))
            {
                validGender = true;

                break;
            }
        }

        return validGender;
    }

    public void setUserGender(String strGender)
    {
        try {
            if (isValidGender(strGender))
            {
                userDataSource.setGender(user.getId(), strGender);

                user.setGender(strGender);
            }
            else
            {
                CGException cgException = new CGException("Invalid Gender");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }

    public void setUserUniversity(University university)
    {
        try
        {
            if (userDataSource.setUserUniversity(user.getId(), university))
            {
                user.setUniversityChoice(university);
            }
            else
            {
                CGException cgException = new CGException("Unable to save name to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }

    public void setUserCareer(Career career)
    {
        try
        {
            if (userDataSource.setUserCareer(user.getId(), career))
            {
                user.setCareerChoice(career);
            }
            else
            {
                CGException cgException = new CGException("Unable to save name to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }

    //University
    public List<University> getAllUniversity()
    {
        return universityDataSource.getAllUniversity();
    }

    public List<String> getAllUniversityNames()
    {
        return universityDataSource.getAllUniversityNames();
    }

    //Career
    public List<Career> getAllCareer()
    {
        return careerDataSource.getAllCareers();
    }

    public List<String> getAllCareerNames()
    {
        return careerDataSource.getAllCareerNames();
    }

    //Subject
    public List<Subject> getAllSubjects()
    {
        return subjectDataSource.getAllSubjects();
    }

    public List<String> getAllSubjectNames()
    {
        return subjectDataSource.getAllSubjectNames();
    }

    //Subject
    public List<Interest> getAllInterests()
    {
        return interestDataSource.getAllInterests();
    }

    public List<String> getAllInterestNames()
    {
        return interestDataSource.getAllInterestNames();
    }
}
