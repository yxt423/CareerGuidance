package com.careerguidance.cgexception;

/**
 * Created by chris on 12/4/14.
 */
public class CGException extends Exception
{
	 /*
	 * This class extends the Exception class and is used to catch errors
	 *
	 * It will display appropriate error messages related to the errors caught and allow the program to continue and
	 * terminate gracefully
	 */

    private String detail; //the details of the error that we are handling. Will be passed back for printing by the calling program.

    //default constructor

    public CGException ()
    {
        detail = "An unknown error has occurred";
    }

    //constructor taking in one String parameter which is the message that will be displayed
    public CGException (String msg)
    {
        detail = msg;
    }

    public String toString()
    {
        return "CGException encountered: " + detail;
    }
}
