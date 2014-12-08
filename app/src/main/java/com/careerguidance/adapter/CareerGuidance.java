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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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

        career_interestDataSource = new Career_InterestDataSource(context);

        genderDataSource = new GenderDataSource(context);

        interestDataSource = new InterestDataSource(context);

        locationDataSource = new LocationDataSource(context);

        subjectDataSource = new SubjectDataSource(context);

        universityDataSource = new UniversityDataSource(context);

        university_gradeDataSource = new University_GradeDataSource(context);

        university_interestDataSource = new University_InterestDataSource(context);


        userDataSource = new UserDataSource(context);

        userDataSource.open();

        user = userDataSource.getUser(1); //only 1 user so get the first and only user in the database

        userDataSource.close();


        user_gradeDataSource = new User_GradeDataSource(context);

        user_interestDataSource = new User_InterestDataSource(context);

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

    public ArrayList<Grade> getUserGrades()
    {
        return user.getGrades();
    }

    public ArrayList<Interest> getUserInterests()
    {
        return user.getInterests();
    }

    public ArrayList<String> getUserInterestNames()
    {
        ArrayList<String> interestNames = new ArrayList<String>();
        ArrayList<Interest> interests = getUserInterests();

        for (Interest interest: interests)
        {
            interestNames.add(interest.getName());
        }

        return interestNames;
    }

    public ArrayList<String> getUserGradeNames()
    {
        ArrayList<String> gradeNames = new ArrayList<String>();
        ArrayList<Grade> grades = getUserGrades();

        for (Grade grade: grades)
        {
            gradeNames.add(grade.getSubject().getName());
        }

        return gradeNames;
    }

    public String[] getUserGradeScores()
    {
        String [] gradeScores = new String [100];
        ArrayList<Grade> grades = getUserGrades();
        int count = 0;

        for (int i = 0; i < grades.size(); i++)
        {
            gradeScores[i] = (Double.toString(grades.get(i).getGPA()));
            count ++;
        }

        return gradeScores;
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

    public Date getUserBirthDate()
    {
        return user.getBirthDate();
    }

    //User Setters
    public void setUserFirstName(String strFirstName)
    {
        try
        {
            userDataSource.open();

           if (userDataSource.setFirstName(user.getId(), strFirstName))
           {
                user.setFirstName(strFirstName);
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
        finally
        {
            userDataSource.close();
        }
    }

    public void setUserLastName(String strLastname)
    {
        try
        {
            userDataSource.open();

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
        finally
        {
            userDataSource.close();
        }
    }

    public void setUserLocation(String strLocation)
    {
        try
        {
            userDataSource.open();

            locationDataSource.open();

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
        finally
        {
            userDataSource.close();

            locationDataSource.close();
        }
    }

    public void setUserBirthDate(Date birthDate)
    {
        try
        {
            userDataSource.open();

            if (userDataSource.setBirthDate(birthDate))
            {
                user.setBirthDate(birthDate);
            }
            else
            {
                CGException cgException = new CGException("Unable to save birthDate to database");

                throw cgException;
            }
        }
        catch (CGException e)
        {
            System.out.println(e);
        }
        finally
        {
            userDataSource.close();
        }
    }

    public void setUserGender(String strGender)
    {
        try
        {
            userDataSource.open();

            genderDataSource.open();

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
        finally
        {
            userDataSource.close();

            genderDataSource.close();
        }
    }

    public void setUserUniversity(University university)
    {
        try
        {
            userDataSource.open();

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
        finally {
            userDataSource.close();
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
        finally
        {
            userDataSource.close();
        }
    }

    public void addUserGrade(int subjectId, double gpa)
    {
        try
        {
            user_gradeDataSource.open();

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
        finally
        {
            user_gradeDataSource.close();
        }
    }

    public void delUserGrade(Grade grade)
    {
        try
        {
            user_gradeDataSource.open();

            user_gradeDataSource.deleteGrade(user.getId(), grade.getSubject().getId());

            user.delGrade(grade);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            user_gradeDataSource.close();
        }
    }

    public int subjectNameToId(String subjectName)
    {
        int subjectId = -1;
        try
        {
            subjectDataSource.open();

            subjectId = subjectDataSource.subjectNameToId(subjectName);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            subjectDataSource.close();
        };

        return subjectId;
    }

    public String subjectIdToName(int subjectId)
    {
        String subjectName = "";

        try
        {
            subjectDataSource.open();

            subjectName = subjectDataSource.subjectIdToName(subjectId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            subjectDataSource.close();
        };

        return subjectName;
    }

    public void addUserInterest(int interestId)
    {
        try
        {
            user_interestDataSource.open();

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
        finally
        {
            user_interestDataSource.close();
        }
    }

    //University
    public List<University> getAllUniversity()
    {
        List<University> uniList = new ArrayList<University>();

        try
        {
            universityDataSource.open();

            uniList = universityDataSource.getAllUniversity();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            universityDataSource.close();
        }

        return uniList;
    }

    public List<String> getAllUniversityNames()
    {
        List<String> nameList = new ArrayList<String>();
        try
        {
            universityDataSource.open();

            nameList = universityDataSource.getAllUniversityNames();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            universityDataSource.close();
        }

        return nameList;
    }

    public University getUniversityById(int universityId)
    {
        University university = null;
        try
        {
            universityDataSource.open();

            university =  universityDataSource.getUniversityById(universityId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            universityDataSource.close();
        }

        return university;
    }

    //Career
    public ArrayList<Career> getAllCareers()
    {
        ArrayList<Career> careerList = null;

        try
        {
            careerDataSource.open();

            careerList = careerDataSource.getAllCareers();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                careerDataSource.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        return careerList;
    }

    public ArrayList<String> getAllCareerNames()
    {
        ArrayList<String> careerNames = null;

        try
        {
            careerDataSource.open();

            careerNames = careerDataSource.getAllCareerNames();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                careerDataSource.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        return careerNames;
    }

    public Career getCareer(int careerId)
    {
        Career career = null;

        try
        {
            careerDataSource.open();

            career = careerDataSource.getCareerById(careerId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            careerDataSource.close();
        }

        return career;
    }

    //Subject
    public List<Subject> getAllSubjects()
    {
        ArrayList<Subject> subjects = null;

        try
        {
            subjectDataSource.open();

            subjects = subjectDataSource.getAllSubjects();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            subjectDataSource.close();
        }

        return subjects;
    }

    public List<String> getAllSubjectNames()
    {
        ArrayList<String> subjects = null;

        try
        {
            subjectDataSource.open();

            subjects = subjectDataSource.getAllSubjectNames();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            subjectDataSource.close();
        }

        return subjects;
    }

    //Interest
    public List<Interest> getAllInterests()
    {
        ArrayList<Interest> interests = null;

        try
        {
            interestDataSource.open();

            interests = interestDataSource.getAllInterests();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            interestDataSource.close();
        }

        return interests;
    }

    public List<String> getAllInterestNames()
    {
        ArrayList<String> interestNames = null;

        try
        {
            interestDataSource.open();

            interestNames = interestDataSource.getAllInterestNames();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            interestDataSource.close();
        }

        return interestNames;
    }

    public String getInterestName(int interestId)
    {
        String interestName = "";

        try
        {
            interestDataSource.open();

            interestName = interestDataSource.getNameFromId(interestId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            interestDataSource.close();
        }

        return interestName;
    }

    public int getInterestIdFromName(String interestName)
    {
        int interestId = -1;

        try
        {
            interestDataSource.open();

            interestId = interestDataSource.getIdFromName(interestName);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            interestDataSource.close();
        }

        return interestId;
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
