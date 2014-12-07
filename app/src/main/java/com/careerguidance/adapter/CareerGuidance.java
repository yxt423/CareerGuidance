package com.careerguidance.adapter;

import android.content.Context;

import com.careerguidance.cgexception.CGException;
import com.careerguidance.dblayout.CareerDataSource;
import com.careerguidance.dblayout.Career_InterestDataSource;
import com.careerguidance.dblayout.GenderDataSource;
import com.careerguidance.dblayout.InterestDataSource;
import com.careerguidance.dblayout.LocationDataSource;
import com.careerguidance.dblayout.SubjectDataSource;
import com.careerguidance.dblayout.UniversityDataSource;
import com.careerguidance.dblayout.University_GradeDataSource;
import com.careerguidance.dblayout.University_InterestDataSource;
import com.careerguidance.dblayout.UserDataSource;
import com.careerguidance.dblayout.User_GradeDataSource;
import com.careerguidance.dblayout.User_InterestDataSource;
import com.careerguidance.model.Career;
import com.careerguidance.model.Gender;
import com.careerguidance.model.Grade;
import com.careerguidance.model.Interest;
import com.careerguidance.model.Location;
import com.careerguidance.model.Subject;
import com.careerguidance.model.University;
import com.careerguidance.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chris on 12/4/14.
 */
public class CareerGuidance
{
    private static CareerDataSource careerDataSource;

    private static Career_InterestDataSource career_interestDataSource;

    private static GenderDataSource genderDataSource;

    private static InterestDataSource interestDataSource;

    private static LocationDataSource locationDataSource;

    private static SubjectDataSource subjectDataSource;

    private static UniversityDataSource universityDataSource;

    private static University_GradeDataSource university_gradeDataSource;

    private static User user;

    private static UserDataSource userDataSource;

    private static User_GradeDataSource user_gradeDataSource;

    private static User_InterestDataSource user_interestDataSource;

    private static University_InterestDataSource university_interestDataSource;

    private static Context context;

    public CareerGuidance(Context contxt)
    {
        context = contxt;

        openDataSources();
    }

    public void openDataSources()
    {
        careerDataSource = new CareerDataSource(context);

        careerDataSource.open();


        career_interestDataSource = new Career_InterestDataSource(context);

        career_interestDataSource.open();


        genderDataSource = new GenderDataSource(context);

        genderDataSource.open();


        interestDataSource = new InterestDataSource(context);

        interestDataSource.open();


        locationDataSource = new LocationDataSource(context);

        locationDataSource.open();


        subjectDataSource = new SubjectDataSource(context);

        subjectDataSource.open();


        universityDataSource = new UniversityDataSource(context);

        universityDataSource.open();


        university_gradeDataSource = new University_GradeDataSource(context);

        university_gradeDataSource.open();


        university_interestDataSource = new University_InterestDataSource(context);

        university_interestDataSource.open();


        userDataSource = new UserDataSource(context);

        userDataSource.open();

        user = userDataSource.getUser(1); //only 1 user so get the first and only user in the database


        user_gradeDataSource = new User_GradeDataSource(context);

        user_gradeDataSource.open();


        user_interestDataSource = new User_InterestDataSource(context);

        user_interestDataSource.open();
    }

    public void closeDataSources()
    {
        careerDataSource.close();

        genderDataSource.close();

        interestDataSource.close();

        locationDataSource.close();

        subjectDataSource.close();

        universityDataSource.close();

        university_gradeDataSource.close();

        university_interestDataSource.close();

        userDataSource.close();

        user_gradeDataSource.close();

        user_interestDataSource.close();
    }

    public boolean userHasProfile()
    {
        return user.hasProfile();
    }

    //Getters

    //User Getters
    public String getUserFirstName()
    {
        return user.getFirstName();
    }

    public String getUserLastName()
    {
        return user.getLastName();
    }

    public Gender getUserGender()
    {
        Gender gender = user.getGender();

        return gender;
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

    //User Setters
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
                user.setLastName(strLastname);
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
        try {
            if (locationDataSource.isValidLocation(strLocation))
            {
                int locationId = locationDataSource.getLocationId(strLocation);

                if (userDataSource.setLocation(user.getId(), locationId))
                    user.setLocation(strLocation);
            }
            else
            {
                CGException cgException = new CGException ("Invalid Location");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
    }


    public void setUserGender(String strGender)
    {
        try {
            if (genderDataSource.isValidGender(strGender))
            {
                int genderId = genderDataSource.getGenderId(strGender);

                if (userDataSource.setGender(user.getId(), genderId))
                    user.setGender(genderDataSource.getGenderObject(strGender));
            }
            else
            {
                CGException cgException = new CGException ("Invalid Gender");

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
            if (userDataSource.setUserUniversity(user.getId(), university.getId()))
            {
                user.setUniversityChoice(university);
            }
            else
            {
                CGException cgException = new CGException("Unable to save University to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
        catch (Exception e)
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
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addUserGrade(int subjectId, double gpa)
    {
        try
        {
            Grade userGrade = user_gradeDataSource.create_UserGrade(user.getId(), subjectId, gpa);

            if (userGrade != null)
            {
                user.addGrade(userGrade);
            }
            else
            {
                CGException exception = new CGException("Error adding grade");

                throw exception;
            }
        }
        catch(CGException e)
        {
            System.out.println(e);
        }
    }

    public void delUserGrade(Grade grade)
    {
        user_gradeDataSource.deleteGrade(user.getId(), grade.getSubject().getId());

        user.delGrade(grade);
    }

    public void addUserInterest(int interestId)
    {
        try
        {
            Interest newInterest = user_interestDataSource.addInterest(user.getId(), interestId);

            if (newInterest != null)
            {
                user.addInterest(newInterest);
            }
            else
            {
                CGException exception = new CGException("Error adding interest");

                throw exception;
            }
        }
        catch(CGException e)
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

    public University getUniversity(int universityId)
    {
        return universityDataSource.getUniversityById(universityId);
    }

    //Career
    public ArrayList<Career> getAllCareers()
    {
        return careerDataSource.getAllCareers();
    }

    public List<String> getAllCareerNames()
    {
        return careerDataSource.getAllCareerNames();
    }

    public Career getCareer(int careerId)
    {
        return careerDataSource.getCareerById(careerId);
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

    //Interest
    public List<Interest> getAllInterests()
    {
        return interestDataSource.getAllInterests();
    }

    public List<String> getAllInterestNames()
    {
        return interestDataSource.getAllInterestNames();
    }

    public String getInterestName(int interestId)
    {
        return interestDataSource.getNameFromId(interestId);
    }

    public int getInterestIdFromName(String interestName)
    {
        return interestDataSource.getIdFromName(interestName);
    }

    public HashMap<String, Double> match ()
    {
        List<Interest> userInterests = user.getInterests();

        ArrayList<Career> careers = getAllCareers();

        HashMap<String,Double> careerMatch = null;

        int careerMatchCounter;

        String careerName;

        ArrayList<String> careerInterests;

        for (int i = 0; i < careers.size(); i++)
        {
            careerMatchCounter = 0;

            careerName = careers.get(i).getName();

            careerInterests = career_interestDataSource.getCareer_InterestNames(careers.get(i).getId());

            for (int j = 0; j < careerInterests.size(); j++)
            {
                for (int k = 0; k < userInterests.size(); k++)
                {
                    if (userInterests.get(k).getName().equals(careerInterests.get(j)))
                    {
                        careerMatchCounter++;
                    }
                }
            }

            double matchPercent = careerMatchCounter / careerInterests.size();

            careerMatch.put(careerName, matchPercent);
        }

        return careerMatch;
    }
}
