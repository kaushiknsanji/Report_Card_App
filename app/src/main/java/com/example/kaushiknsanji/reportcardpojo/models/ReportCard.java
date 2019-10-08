/*
 * Copyright 2017 Kaushik N. Sanji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kaushiknsanji.reportcardpojo.models;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Set;

/**
 * Report Card Model class that manages the Subject Marks and Grades of a Student
 * for the Year
 *
 * @author Kaushik N Sanji
 */
public class ReportCard {

    //Annotation constants that define the possible values of the subjects available
    public static final String PHYSICS_SUBJ_CODE = "PHY";
    public static final String CHEMISTRY_SUBJ_CODE = "CHEM";
    public static final String MATHEMATICS_SUBJ_CODE = "MATH";
    public static final String COMPUTER_SCIENCE_SUBJ_CODE = "CS";
    //Map to store the Subject code and the Marks of a Student
    private HashMap<String, Integer> mSubjectMarksMap;
    //Stores the Year information
    private int mYear;
    //Stores the name of the Student
    private String mName;

    /**
     * Constructor of {@link ReportCard}
     *
     * @param year The Integer Year of the Examination
     * @param name String containing the Name of the Student
     */
    public ReportCard(int year, String name) {
        mYear = year; //Saving the Year information
        mName = name; //Saving the Student's Name

        //Initializing the Map to store the Marks obtained by the Student
        mSubjectMarksMap = new HashMap<>();
    }

    /**
     * Setter Method for updating the Name of the Student
     *
     * @param name String containing the Name of the Student
     */
    public void setStudentName(String name) {
        mName = name;
    }

    /**
     * Getter Method that returns the Year of Examination
     *
     * @return The Integer Year of the Examination
     */
    public int getYear() {
        return mYear;
    }

    /**
     * Setter Method for updating the Year of Examination
     *
     * @param year The Integer Year of the Examination
     */
    public void setYear(int year) {
        mYear = year;
    }

    /**
     * Getter Method that returns the Name of the Student
     *
     * @return The String containing the Name of the Student
     */
    public String getName() {
        return mName;
    }

    /**
     * Method for entering the Marks of a Subject
     *
     * @param subjectCode is the Subject code as defined by the {@link Subject} annotation
     * @param marks       is the Integer value of the Marks obtained by the student for the <var>subjectCode</var>
     */
    public void enterSubjectMarks(@Subject String subjectCode, int marks) {
        if (marks > 100) {
            //If entered marks is greater than 100, it will be entered as 100
            marks = 100;
        }
        //Making an entry in the Map
        mSubjectMarksMap.put(subjectCode, marks);
    }

    /**
     * Method that allows for deleting the Subject Marks entry if present
     *
     * @param subjectCode is the Subject code as defined by the {@link Subject} annotation
     *                    whose entry needs to be deleted
     */
    public void deleteSubjectEntry(@Subject String subjectCode) {
        if (mSubjectMarksMap.containsKey(subjectCode)) {
            mSubjectMarksMap.remove(subjectCode);
        }
    }

    /**
     * Method that allows for editing/altering the Marks of a Subject
     *
     * @param subjectCode is the Subject code as defined by the {@link Subject} annotation
     * @param marks       is the updated Integer value of the Marks obtained by the student for the <var>subjectCode</var>
     */
    public void editSubjectMarks(@Subject String subjectCode, int marks) {
        //Propagating the call to #enterSubjectMarks as the Map overwrites the information
        enterSubjectMarks(subjectCode, marks);
    }

    /**
     * Method that returns the Marks entered for a Subject <var>subjectCode</var> if present
     *
     * @param subjectCode is the Subject code as defined by the {@link Subject} annotation
     *                    whose corresponding Marks entry value is required
     * @return Integer value of the Marks obtained by the student for the <var>subjectCode</var> passed.
     * Returns -1 if there is no entry for the given <var>subjectCode</var>.
     */
    public int getSubjectMarks(@Subject String subjectCode) {
        if (mSubjectMarksMap.containsKey(subjectCode)) {
            //Returning the entered value for the <var>subjectCode</var> when present
            return mSubjectMarksMap.get(subjectCode);
        }

        //Returning -1 when there is no entry for the given <var>subjectCode</var>
        return -1;
    }

    /**
     * Method that returns the Grade based on the marks obtained by the Student
     * for the <var>subjectCode</var> passed
     *
     * @param subjectCode is the Subject code as defined by the {@link Subject} annotation
     * @return String value representing the Grade obtained by the Student for the <var>subjectCode</var>.
     * If there is no entry for the given <var>subjectCode</var>, then "Not Applicable" will be returned.
     */
    public String getSubjectMarksGrade(@Subject String subjectCode) {
        if (mSubjectMarksMap.containsKey(subjectCode)) {
            //When Marks entry is present for the Subject

            //Retrieving the Marks
            int marks = mSubjectMarksMap.get(subjectCode);

            //Returning the Grade based on the Marks
            if (marks >= 90 && marks <= 100) {
                return "A+";
            } else if (marks >= 85 && marks <= 89) {
                return "A";
            } else if (marks >= 80 && marks <= 84) {
                return "A-";
            } else if (marks >= 75 && marks <= 79) {
                return "B+";
            } else if (marks >= 70 && marks <= 74) {
                return "B";
            } else if (marks >= 65 && marks <= 69) {
                return "B-";
            } else if (marks >= 60 && marks <= 64) {
                return "C+";
            } else if (marks >= 55 && marks <= 59) {
                return "C";
            } else if (marks >= 50 && marks <= 54) {
                return "C-";
            } else if (marks >= 45 && marks <= 49) {
                return "D+";
            } else if (marks >= 40 && marks <= 44) {
                return "D";
            } else if (marks >= 35 && marks <= 39) {
                return "D-";
            } else if (marks <= 34) {
                return "F(Fail)";
            }
        }

        //Returning "Not Applicable" when there is no entry for the given <var>subjectCode</var>
        return "Not Applicable";
    }

    /**
     * Returns a string representation of the Report Card.
     *
     * @return a string representation of the Report Card.
     */
    @Override
    public String toString() {
        //Initializing a StringBuilder to build the representation of the Report Card
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("Name: ").append(getName()).append("\n"); //Appending Name of the Student
        sBuilder.append("Year: ").append(getYear()).append("\n"); //Appending the Year of the Examination
        sBuilder.append("--------------------------------------").append("\n");
        //Appending the Subjects and the Grades: START
        Set<String> subjectSet = mSubjectMarksMap.keySet();
        for (String subjectCode : subjectSet) {
            sBuilder.append(subjectCode).append(": ");
            sBuilder.append(getSubjectMarksGrade(subjectCode)).append("\n");
        }
        //Appending the Subjects and the Grades: END
        sBuilder.append("--------------------------------------").append("\n");
        //Returning the representation built
        return sBuilder.toString();
    }

    //Defining the Subject StringDef annotation with Retention only at SOURCE
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({PHYSICS_SUBJ_CODE,
            CHEMISTRY_SUBJ_CODE,
            MATHEMATICS_SUBJ_CODE,
            COMPUTER_SCIENCE_SUBJ_CODE})
    public @interface Subject {
    }
}
